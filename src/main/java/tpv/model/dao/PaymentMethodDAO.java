package tpv.model.dao;

import tpv.model.dao.base.hibernate.GenericHibernateDAO;
import tpv.model.vo.PaymentMethod;

public interface PaymentMethodDAO extends GenericHibernateDAO<PaymentMethod, String> {

    PaymentMethod getPaymentMethodCode(String paymentMethodCode);
}
