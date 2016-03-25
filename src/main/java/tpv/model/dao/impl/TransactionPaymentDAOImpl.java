package tpv.model.dao.impl;

import tpv.model.dao.TransactionPaymentDAO;
import tpv.model.dao.base.hibernate.spring.impl.GenericHibernateSpringDAOImpl;
import tpv.model.vo.TransactionPayment;

public class TransactionPaymentDAOImpl extends GenericHibernateSpringDAOImpl<TransactionPayment, String> implements TransactionPaymentDAO {

    public TransactionPaymentDAOImpl() {
        super(TransactionPayment.class);
    }

}
