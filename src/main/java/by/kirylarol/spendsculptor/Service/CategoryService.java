package by.kirylarol.spendsculptor.Service;


import by.kirylarol.spendsculptor.entities.Category;
import by.kirylarol.spendsculptor.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category getById(int id){
        return categoryRepository.findById(id).orElse(null);
    }

    public Category getByName(String name){
        return categoryRepository.findDistinctFirstByCategoryName(name);
    }

    @Transactional
    public Category createCategory (Category category){
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(int id, Category category){
        category.setCategoryId(id);
        return categoryRepository.save(category);
    }

    public Category createCategory (String name){
        Category category = new Category();
        category.setCategoryName(name);
        return createCategory(category);
    }
}
