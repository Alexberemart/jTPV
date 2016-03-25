package tpv.model.vo.item;

import tpv.model.enums.DiscountType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import tpv.model.vo.base.BaseEntity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "items")
@Entity
public class Item extends BaseEntity {

    protected String code;
    protected String description;
    protected Double unitPrice;
    protected Double taxRate;
    protected Integer discountType;

    @Column(nullable = false)
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    @Column(nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    //USAR CUANDO ENUMS
    @JsonProperty
    public void setDiscountType(DiscountType discountType) {
        if(discountType == null) {
            this.discountType = null;
        } else {
            this.discountType = discountType.getValue();
        }
    }

    //USAR CUANDO ENUMS
    @JsonIgnore
    public void setDiscountType(Integer discountType) {
        DiscountType parse = DiscountType.parse(discountType);
        if (parse != null) {
            this.discountType = discountType;
        }
    }
}
