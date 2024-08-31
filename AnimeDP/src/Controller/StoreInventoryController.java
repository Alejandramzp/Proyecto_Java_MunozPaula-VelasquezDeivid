
package Controller;

import Dao.StoreInventoryDao;
import Model.StoreInventory;
import java.util.List;

public class StoreInventoryController {
    private StoreInventoryDao inventoryDao;

    public StoreInventoryController() {
        this.inventoryDao = new StoreInventoryDao();
    }

    public boolean addProductToInventory(StoreInventory product) {
        return inventoryDao.addProductToInventory(product);
    }

    public boolean updateInventoryAfterSale(int inventoryID, int quantitySold) {
        return inventoryDao.updateInventoryAfterSale(inventoryID, quantitySold);
    }

    public List<StoreInventory> getProductsByBusinessID(int businessID) {
        return inventoryDao.getProductsByBusinessID(businessID);
    }
}
