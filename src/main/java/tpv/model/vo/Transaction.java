package tpv.model.vo;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import tpv.model.vo.base.BaseEntity;
import tpv.model.vo.item.PayableElement;
import tpv.model.vo.item.impl.TransactionItem;
import tpv.util.DoubleUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "transactions")
@Entity
public class Transaction extends BaseEntity implements ValuableElement, PayableElement {

    List<TransactionItem> transactionItemList = new ArrayList<>();
    List<TransactionPayment> transactionPaymentList = new ArrayList<>();

    @OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JsonManagedReference("transaction")
    public List<TransactionItem> getTransactionItemList() {
        return transactionItemList;
    }

    public void setTransactionItemList(List<TransactionItem> transactionItemList) {
        this.transactionItemList = transactionItemList;
    }

    @OneToMany(mappedBy = "transaction")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.ALL)
    @JsonManagedReference("transaction")
    public List<TransactionPayment> getTransactionPaymentList() {
        return transactionPaymentList;
    }

    public void setTransactionPaymentList(List<TransactionPayment> transactionPaymentList) {
        this.transactionPaymentList = transactionPaymentList;
    }

    @Override
    @Transient
    public Double getPrice() {
        Double price = 0.0;
        for (TransactionItem transactionItem : transactionItemList){
            price += transactionItem.getPrice();
        }
        return DoubleUtils.round(price, 2);
    }

    @Override
    @Transient
    public Double getDiscountAmount() {
        Double discount = 0.0;
        for (TransactionItem transactionItem : transactionItemList){
            discount += transactionItem.getDiscountAmount();
        }
        return DoubleUtils.round(discount, 2);
    }

    @Override
    @Transient
    public Double getTaxesAmount() {
        Double taxes = 0.0;
        for (TransactionItem transactionItem : transactionItemList){
            taxes += transactionItem.getTaxesAmount();
        }
        return DoubleUtils.round(taxes, 2);
    }

    @Override
    @Transient
    public Double getTotalAmount() {
        Double totalAmount = 0.0;
        for(TransactionItem transactionItem : transactionItemList){
            totalAmount += transactionItem.getTotalAmount();
        }
        return DoubleUtils.round(totalAmount, 2);
    }

    @Override
    @Transient
    public Double getRemainingAmount() {
        Double result = getTotalAmount();
        for (TransactionPayment transactionPayment : transactionPaymentList) {
            result -= transactionPayment.getAmount();
        }
        return DoubleUtils.round(result, 2);
    }
}
