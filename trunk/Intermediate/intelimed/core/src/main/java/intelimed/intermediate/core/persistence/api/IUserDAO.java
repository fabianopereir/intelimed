/* 
 * Project name: InteliMed Intermediate Core
 * Class name: IUserDAO.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.persistence.api;

import intelimed.intermediate.core.exception.SystemException;
import intelimed.intermediate.core.model.entity.User;

/**
 * <Insert_class_comment>
 *
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 *
 */
public interface IUserDAO extends IIntelimedDAO<User> {
	User findByEmail(String email) throws SystemException;
	User findByName(String name) throws SystemException;
}
