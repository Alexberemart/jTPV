package tpv.model.vo.DiscountManager.impl;

import tpv.model.vo.DiscountManager.DiscountManager;
import tpv.model.vo.item.ValuableItem;

public class TwoPerThreeDiscount implements DiscountManager{

    @Override
    public Double getDiscount(ValuableItem valuableItem) {
        Integer itemCount = valuableItem.getQuantity() / 3;
        return valuableItem.getItem().getUnitPrice() * itemCount;
    }
}
