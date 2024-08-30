
package Controller;

import Dao.CategoryDao;
import Model.CategoryModel;

import java.util.List;

public class CategoryController {

    private CategoryDao categoryDao;

    public CategoryController() {
        this.categoryDao = new CategoryDao();
    }

    public boolean addCategory(String name, int age, String gender) {
        CategoryModel category = new CategoryModel(0, name, age, gender);
        return categoryDao.addCategory(category);
    }

    public boolean deleteCategory(int categoryID) {
        return categoryDao.deleteCategory(categoryID);
    }

    public CategoryModel getCategoryById(int categoryID) {
        return categoryDao.getCategoryById(categoryID);
    }

    public List<CategoryModel> listCategories() {
        return categoryDao.listCategories();
    }
}

