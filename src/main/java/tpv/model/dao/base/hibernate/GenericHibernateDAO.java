package tpv.model.dao.base.hibernate;


import tpv.model.dao.base.GenericDAO;

import java.io.Serializable;


/**
 * GenericDAO extension for Hibernate based implementations
 *
 */
public interface GenericHibernateDAO<T, ID extends Serializable> extends GenericDAO<T, ID> {
	//
}
