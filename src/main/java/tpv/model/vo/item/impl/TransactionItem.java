package tpv.model.vo.item.impl;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import tpv.model.vo.Transaction;
import tpv.model.vo.item.Item;
import tpv.model.vo.item.ValuableItem;

import javax.persistence.*;

@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "transaction_items")
@Entity
@JsonAutoDetect
public class TransactionItem extends ValuableItem {

    protected Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "transactionId", nullable = false)
    @JsonBackReference("transaction")
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @ManyToOne
    @JoinColumn(name = "itemId", nullable = false)
    @JsonManagedReference("item")
    public Item getItem() {
        return item;
    }

    @Column(nullable = false)
    public Integer getQuantity() {
        return Quantity;
    }
}
