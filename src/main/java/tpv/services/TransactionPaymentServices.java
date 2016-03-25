package tpv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tpv.model.dao.PaymentMethodDAO;
import tpv.model.dao.TransactionPaymentDAO;
import tpv.model.vo.PaymentMethod;
import tpv.model.vo.Transaction;
import tpv.model.vo.TransactionPayment;
import tpv.util.ApplicationContextProvider;

import java.util.ArrayList;
import java.util.List;

@Component("transactionPaymentServices")
public class TransactionPaymentServices {

    @Autowired
    TransactionPaymentDAO transactionPaymentDAO;

    public static TransactionPaymentServices getInstance() {
        return (TransactionPaymentServices) ApplicationContextProvider.getInstance().getBean("transactionPaymentServices");
    }

    public Transaction addPayment(String paymentMethod, String transactionId, Float amount) {
        Transaction transaction = TransactionServices.getInstance().getById(transactionId);
        PaymentMethod paymentMethod1 = PaymentMethodServices.getInstance().getByPaymentMethodCode(paymentMethod);
        TransactionPayment transactionPayment = new TransactionPayment();
        transactionPayment.setTransaction(transaction);
        transactionPayment.setAmount(amount);
        transactionPayment.setPaymentMethod(paymentMethod1);
        transactionPaymentDAO.makePersistent(transactionPayment);
        return TransactionServices.getInstance().getById(transactionId);
    }
}
