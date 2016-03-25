package tpv.model.dao;

import tpv.model.dao.base.hibernate.GenericHibernateDAO;
import tpv.model.vo.item.Item;

public interface ItemDAO extends GenericHibernateDAO<Item, String> {

    Item getItemByCode(String itemCode);

}
