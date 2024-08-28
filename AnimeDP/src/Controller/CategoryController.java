/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.CategoryDao;
import Model.CategoryModel;

import java.util.List;

public class CategoryController {
    private CategoryDao categoryDao;

    public CategoryController() {
        categoryDao = new CategoryDao();
    }

    public boolean addCategory(int categoryID, String categoryName, int age, String gender) {
        CategoryModel category = new CategoryModel(categoryID, categoryName, age, gender);
        return categoryDao.addCategory(category);
    }

    public List<CategoryModel> getAllCategories() {
        return categoryDao.getAllCategory();
    }
}

