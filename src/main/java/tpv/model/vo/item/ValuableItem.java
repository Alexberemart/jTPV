package tpv.model.vo.item;

import org.codehaus.jackson.annotate.JsonIgnore;
import tpv.model.enums.DiscountType;
import tpv.model.vo.DiscountManager.DiscountManager;
import tpv.model.vo.DiscountManager.impl.SecondUnit70Discount;
import tpv.model.vo.DiscountManager.impl.TwoPerThreeDiscount;
import tpv.model.vo.ValuableElement;
import tpv.model.vo.base.BaseEntity;
import tpv.util.DoubleUtils;

import javax.persistence.Transient;

public abstract class ValuableItem extends BaseEntity implements ValuableElement {

    protected Item item;
    protected Integer Quantity;

    protected DiscountManager discountManager;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    @Transient
    public DiscountManager getDiscountManager() {
        return discountManager;
    }

    @JsonIgnore
    public void setDiscountManager(DiscountManager discountManager) {
        this.discountManager = discountManager;
    }

    @Override
    @Transient
    public Double getPrice() {
        Double result = getQuantity() * item.getUnitPrice();
        result = DoubleUtils.round(result, 2);
        return result;
    }

    @Override
    @Transient
    public Double getDiscountAmount() {
        if (this.item.getDiscountType() == null){
            return 0.0;
        }
        if (DiscountType.parse(this.item.getDiscountType()) == DiscountType.SEVENTY_OFF) {
            SecondUnit70Discount secondUnit70Discount = new SecondUnit70Discount();
            this.setDiscountManager(secondUnit70Discount);
        }
        if (DiscountType.parse(this.item.getDiscountType()) == DiscountType.THREE_PER_TWO) {
            TwoPerThreeDiscount twoPerThreeDiscount = new TwoPerThreeDiscount();
            this.setDiscountManager(twoPerThreeDiscount);
        }
        Double result = this.discountManager.getDiscount(this);
        result = DoubleUtils.round(result, 2);
        return result;
    }

    @Override
    @Transient
    public Double getTaxesAmount() {
        Double result = getPrice();
        result -= getDiscountAmount();
        result *= this.item.getTaxRate();
        result /= 100;
        result = DoubleUtils.round(result, 2);
        return result;
    }

    @Override
    @Transient
    public Double getTotalAmount() {
        Double result = getPrice();
        result -= getDiscountAmount();
        result += getTaxesAmount();
        result = DoubleUtils.round(result, 2);
        return result;
    }
}
