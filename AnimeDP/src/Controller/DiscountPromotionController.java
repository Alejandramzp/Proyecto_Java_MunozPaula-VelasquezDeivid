
package Controller;

import Dao.DiscountPromotionDao;
import Model.DiscountPromotionModel;
import java.util.List;

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
    public double applyDiscount(double totalValue, List<DiscountPromotionModel> discounts) {
        return discounts.stream().mapToDouble(discount -> {
            switch (discount.getType().toLowerCase()) {
                case "2x1":
                    return totalValue * (1 - discount.getDiscountValue()); // Example: 50% off for 2x1
                case "mejor precio por cantidad":
                    return totalValue * (1 - discount.getDiscountValue()); // Discount based on quantity
                case "combo":
                    return totalValue * (1 - discount.getDiscountValue()); // Discount for combos
                default:
                    return 0;
            }
        }).sum();
    }
}

