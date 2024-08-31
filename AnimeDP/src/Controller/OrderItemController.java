
package Controller;

import Dao.OrderItemDao;
import Model.OrderItemModel;

import java.util.List;

public class OrderItemController {
    private OrderItemDao orderItemDao;

    public OrderItemController() {
        this.orderItemDao = new OrderItemDao();
    }

    public boolean addOrderItem(OrderItemModel orderItem) {
        return orderItemDao.addOrderItem(orderItem);
    }
    
    public boolean orderExists(int orderID) {
        return orderItemDao.orderExists(orderID);
    }

    public List<OrderItemModel> getOrderItemsByOrderId(int orderID) {
        return orderItemDao.getOrderItemsByOrderId(orderID);
    }
}
