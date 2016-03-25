package tpv.model.dao;

import tpv.model.dao.base.hibernate.GenericHibernateDAO;
import tpv.model.vo.item.impl.TransactionItem;

import java.util.List;

public interface TransactionItemDAO extends GenericHibernateDAO<TransactionItem, String> {

    List<TransactionItem> getTransactionItemsByItemCode(String transactionID, String itemCode);

}
