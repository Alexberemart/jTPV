package tpv.model.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import tpv.model.dao.ItemDAO;
import tpv.model.dao.base.hibernate.spring.impl.GenericHibernateSpringDAOImpl;
import tpv.model.vo.item.Item;

import java.util.List;

public class ItemDAOImpl extends GenericHibernateSpringDAOImpl<Item, String> implements ItemDAO {

    public ItemDAOImpl() {
        super(Item.class);
    }

    @Override
    public Item getItemByCode(String itemCode) {

        DetachedCriteria detachedCriteria = DetachedCriteria
                .forClass(Item.class)
                .add(Restrictions.eq("code", itemCode));

        final List<Item> result = this.getHibernateTemplate().findByCriteria(detachedCriteria);

        if (result.size() == 0) {
            return null;
        }

        return result.get(0);
    }
}
