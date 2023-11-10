package by.kirylarol.spendsculptor.controllers;


import by.kirylarol.spendsculptor.entities.Category;
import by.kirylarol.spendsculptor.entities.User;
import by.kirylarol.spendsculptor.repos.CategoryRepository;
import by.kirylarol.spendsculptor.security.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    Util util;

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(Util util, CategoryRepository categoryRepository) {
        this.util = util;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("categories/all")
    List<Category> getAll() throws Exception {
        User user = util.getUser();
        return categoryRepository.findAllByUser(user.getId());
    }

}
