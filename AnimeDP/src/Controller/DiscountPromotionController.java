
package Controller;

import Dao.DiscountPromotionDao;
import Dao.OrderItemDao;
import Model.DiscountPromotionModel;
import Model.OrderItemModel;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountPromotionController {
    private DiscountPromotionDao discountPromotionDao;

    public DiscountPromotionController() {
        this.discountPromotionDao = new DiscountPromotionDao();
    }

    public boolean addDiscountPromotion(DiscountPromotionModel discountPromotion) {
        return discountPromotionDao.addDiscountPromotion(discountPromotion);
    }

    public List<DiscountPromotionModel> getAllDiscountPromotions() {
        return discountPromotionDao.getAllDiscountPromotions();
    }

    public DiscountPromotionModel getDiscountPromotionById(int discountID) {
        return discountPromotionDao.getDiscountPromotionById(discountID);
    }

    public boolean updateDiscountPromotion(DiscountPromotionModel discountPromotion) {
        return discountPromotionDao.updateDiscountPromotion(discountPromotion);
    }

    public boolean deleteDiscountPromotion(int discountID) {
        return discountPromotionDao.deleteDiscountPromotion(discountID);
    }

    // Apply discount to an order
    public double applyDiscountToOrderItems(int orderId, List<Integer> itemIds, List<DiscountPromotionModel> discounts) {
        OrderItemDao orderItemDao = new OrderItemDao();
        List<OrderItemModel> items = orderItemDao.getItemsByOrderId(orderId);

        // Filtrar los ítems seleccionados para aplicar el descuento
        List<OrderItemModel> itemsToDiscount = items.stream()
            .filter(item -> itemIds.contains(item.getOrderItemID()))
            .collect(Collectors.toList());

        // Aplicar los descuentos
        double totalDiscountedValue = itemsToDiscount.stream().mapToDouble(item -> {
            double itemTotal = item.getQuantity() * item.getIndividualValue();
            for (DiscountPromotionModel discount : discounts) {
                if (discount.getType().equalsIgnoreCase("2x1")) {
                    // Lógica de descuento 2x1
                    itemTotal *= (1 - discount.getDiscountValue());
                } else if (discount.getType().equalsIgnoreCase("mejor precio por cantidad")) {
                    // Lógica de descuento por cantidad
                    itemTotal *= (1 - discount.getDiscountValue());
                } else if (discount.getType().equalsIgnoreCase("combo")) {
                    // Lógica de descuento por combo
                    itemTotal *= (1 - discount.getDiscountValue());
                }
            }
            return itemTotal;
        }).sum();

        return totalDiscountedValue;
    }

}

