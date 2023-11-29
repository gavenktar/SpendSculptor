package by.kirylarol.spendsculptor.service;

import by.kirylarol.spendsculptor.entities.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;


    @Test
    @Rollback
    public void createCategoryTest() {
        Category category = categoryService.createCategory("Алкоголь");
        assert (category != null);
        List<Category> categoryList = categoryService.getAll();
        Assert.assertNotEquals(0, categoryList.size());
        Assert.assertNotEquals(null, category.categoryId());
    }

    @Test
    @Rollback
    public void updateCategoryTest() {
        Category category = categoryService.createCategory("Алкоголь");
        Category category2 = categoryService.getByName("Алкоголь");
        Category category1 = categoryService.updateCategory("Выпивка", category);
        Assert.assertEquals("Выпивка", category1.categoryName());
        int id = category1.categoryId();
        Category categoryById = categoryService.getById(id);
        Assert.assertEquals("Выпивка", categoryById.categoryName());
        List<Category> categoryList = categoryService.getAll();
        Assert.assertEquals(1, categoryList.size());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteCategoryTest() {
        Category category = categoryService.createCategory("Абоба");
        Assert.assertNotNull(categoryService.getByName("Абоба"));
        categoryService.deleteCategory("Абоба");
        Assert.assertNull(categoryService.getById(category.categoryId()));
    }


}
