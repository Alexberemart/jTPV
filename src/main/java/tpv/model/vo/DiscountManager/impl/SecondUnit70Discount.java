package tpv.model.vo.DiscountManager.impl;

import tpv.model.vo.DiscountManager.DiscountManager;
import tpv.model.vo.item.ValuableItem;

public class SecondUnit70Discount implements DiscountManager{

    @Override
    public Double getDiscount(ValuableItem valuableItem) {
        Double discount = 70.0;
        discount /= 100;
        discount *= valuableItem.getQuantity() / 2;
        discount *= valuableItem.getItem().getUnitPrice();
        return discount;
    }
}
