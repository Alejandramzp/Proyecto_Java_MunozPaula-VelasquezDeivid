
package Controller;

import Dao.DishDao;
import Model.DishModel;

public class DishController {
    private DishDao dishDao;

    public DishController() {
        this.dishDao = new DishDao();
    }

    public boolean addDish(DishModel dish) {
        return dishDao.addDish(dish);
    }

    public boolean updateDish(DishModel dish) {
        return dishDao.updateDish(dish);
    }
}

