
package Controller;

import Dao.OrderDao;
import Model.OrderModel;

public class OrderController {
    private OrderDao orderDao;

    public OrderController() {
        this.orderDao = new OrderDao();
    }

    public boolean createOrder(OrderModel order) {
        return orderDao.addOrder(order);
    }
    
    public OrderModel getOrderById(int orderID) {
        return orderDao.getOrderById(orderID);
    }

    public boolean advanceOrderStatus(int orderID) {
        OrderModel order = orderDao.getOrderById(orderID);
        if (order == null) {
            System.out.println("Pedido no encontrado.");
            return false;
        }

        String currentStatus = order.getStatus();
        String newStatus;

        switch (currentStatus) {
            case "Registrado" -> newStatus = "Pagado";
            case "Pagado" -> newStatus = "Entregado";
            default -> {
                System.out.println("El pedido ya ha sido entregado.");
                return false;
            }
        }

        return orderDao.updateOrderStatus(orderID, newStatus);
    }
}

