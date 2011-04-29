/* 
 * Project name: InteliMed Intermediate Core
 * Class name: UserDAO.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import intelimed.intermediate.core.exception.SystemException;
import intelimed.intermediate.core.model.entity.User;
import intelimed.intermediate.core.persistence.api.IUserDAO;
import intelimed.intermediate.core.util.HibernateUtil;

/**
 * <Insert_class_comment>
 *
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 *
 */
public class UserDAO extends IntelimedDAO<User> implements IUserDAO {

	public User findByEmail(String email) throws SystemException {
        Criteria criteria = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.like("email", email));
        return (User) criteria.uniqueResult();
	}
	
	public User findByName(String name) throws SystemException {
        Criteria criteria = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.like("name", name));
        return (User) criteria.uniqueResult();
	}

}
