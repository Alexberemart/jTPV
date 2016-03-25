package tpv.model.dao.impl;

import tpv.model.dao.TransactionDAO;
import tpv.model.dao.base.hibernate.spring.impl.GenericHibernateSpringDAOImpl;
import tpv.model.vo.Transaction;

public class TransactionDAOImpl extends GenericHibernateSpringDAOImpl<Transaction, String> implements TransactionDAO {

    public TransactionDAOImpl() {
        super(Transaction.class);
    }

}
