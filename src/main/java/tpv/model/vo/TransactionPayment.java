package tpv.model.vo;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import tpv.model.vo.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "transaction_payments")
@Entity
@JsonAutoDetect
public class TransactionPayment extends BaseEntity {

    protected Transaction transaction;
    protected PaymentMethod paymentMethod;
    protected Float amount;

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
    @JoinColumn(name = "paymentMethodCode", nullable = false)
    @JsonManagedReference("paymentMethod")
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
