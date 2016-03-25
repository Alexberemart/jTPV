package tpv.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import tpv.model.dao.TransactionItemDAO;
import tpv.model.dao.base.hibernate.spring.impl.GenericHibernateSpringDAOImpl;
import tpv.model.vo.item.impl.TransactionItem;

import java.util.List;

public class TransactionItemDAOImpl extends GenericHibernateSpringDAOImpl<TransactionItem, String> implements TransactionItemDAO {

    public TransactionItemDAOImpl() {
        super(TransactionItem.class);
    }

    public List<TransactionItem> getTransactionItemsByItemCode(String transactionID, String itemCode) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TransactionItem.class);
        detachedCriteria.createCriteria("item", "i");
        detachedCriteria.createCriteria("transaction", "t");
        detachedCriteria.add(Restrictions.eq("i.code", itemCode));
        detachedCriteria.add(Restrictions.eq("t.id", transactionID));
        detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        final List<TransactionItem> result = this.getHibernateTemplate().findByCriteria(detachedCriteria);

        if (result.size() == 0) {
            return null;
        }

        return result;
    }

}
