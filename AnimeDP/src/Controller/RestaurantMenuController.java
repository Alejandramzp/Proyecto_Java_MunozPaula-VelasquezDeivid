
package Controller;

import Dao.BusinessDao;
import Dao.DishDao;
import Dao.RestaurantMenuDao;
import Model.DishModel;
import Model.RestaurantMenuModel;
import java.util.List;

public class RestaurantMenuController {
    private DishDao dishDao = new DishDao();
    private RestaurantMenuDao menuDao = new RestaurantMenuDao();

    public RestaurantMenuController() {
        this.dishDao = new DishDao();
        this.menuDao = new RestaurantMenuDao();
    }
    
    public boolean addRestaurantMenu(RestaurantMenuModel menu) {
        return menuDao.addRestaurantMenu(menu);
    }

    public boolean updateRestaurantMenu(RestaurantMenuModel menu) {
        return menuDao.updateRestaurantMenu(menu);
    }
    
    public boolean isStore(int businessID) {
        return menuDao.isStore(businessID);
    }
    
    public void displayMenuWithDishes(int businessId) {
        List<DishModel> dishes = dishDao.getDishesByBusinessId(businessId);

        if (dishes.isEmpty()) {
            System.out.println("No se encontraron platos para el restaurante con ID: " + businessId);
            return;
        }
        
        System.out.println("\n---------------------------------------------------------");
        System.out.println("Platos disponibles para el restaurante con ID: " + businessId);
        for (DishModel dish : dishes) {
            System.out.println("-> " + dish.getDescription() + " (" + dish.getType() + ") - Tiempo de preparación: " + dish.getPreparationTimeMinutes() + " minutos");
        }
        System.out.println("---------------------------------------------------------");
    }
}

