package tpv.model.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import tpv.model.dao.PaymentMethodDAO;
import tpv.model.dao.base.hibernate.spring.impl.GenericHibernateSpringDAOImpl;
import tpv.model.vo.PaymentMethod;
import tpv.model.vo.item.Item;

import java.util.List;

public class PaymentMethodDAOImpl extends GenericHibernateSpringDAOImpl<PaymentMethod, String> implements PaymentMethodDAO {

    public PaymentMethodDAOImpl() {
        super(PaymentMethod.class);
    }

    @Override
    public PaymentMethod getPaymentMethodCode(String paymentMethodCode) {

        DetachedCriteria detachedCriteria = DetachedCriteria
                .forClass(PaymentMethod.class)
                .add(Restrictions.eq("code", paymentMethodCode));

        final List<PaymentMethod> result = this.getHibernateTemplate().findByCriteria(detachedCriteria);

        if (result.size() == 0) {
            return null;
        }

        return result.get(0);
    }

}
