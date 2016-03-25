package tpv.services;

import org.springframework.beans.factory.annotation.Autowired;
import tpv.model.dao.TransactionDAO;
import tpv.model.vo.Transaction;
import tpv.util.ApplicationContextProvider;

public class TransactionServices {

    @Autowired
    TransactionDAO transactionDAO;

    public static TransactionServices getInstance() {
        return (TransactionServices) ApplicationContextProvider.getInstance().getBean("transactionServices");
    }
    public Transaction makePersistent(Transaction transaction) {
        return transactionDAO.makePersistent(transaction);
    }

    public Transaction getById(String transactionId) {
        return transactionDAO.getById(transactionId, false);
    }

}
