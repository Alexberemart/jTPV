package tpv.services;

import org.springframework.beans.factory.annotation.Autowired;
import tpv.model.dao.TransactionItemDAO;
import tpv.model.vo.item.impl.TransactionItem;
import tpv.util.ApplicationContextProvider;

import java.util.List;

//TODO: Hacer con component
public class TransactionITemServices {

    @Autowired
    TransactionItemDAO transactionItemDAO;

    public static TransactionITemServices getInstance() {
        return (TransactionITemServices) ApplicationContextProvider.getInstance().getBean("transactionItemServices");
    }

    public TransactionItem makePersistent(TransactionItem transactionItem) {
        return transactionItemDAO.makePersistent(transactionItem);
    }

    public void makeTransient(TransactionItem transactionItem) {
        transactionItemDAO.makeTransient(transactionItem);
    }

    public TransactionItem getById(String transactionId) {
        return transactionItemDAO.getById(transactionId, false);
    }

    public List<TransactionItem> getTransactionItemsByItemCode(String transactionID, String itemCode){
        return transactionItemDAO.getTransactionItemsByItemCode(transactionID, itemCode);
    }

}
