/* 
 * Project name: InteliMed Intermediate Core
 * Class name: IUserModel.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Marinho
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.model.api;

import intelimed.intermediate.core.exception.BusinessException;
import intelimed.intermediate.core.model.entity.User;

/**
 * <Insert_class_comment>
 *
 * @author Marinho
 *
 */
public interface IUserModel extends IIntelimedModel<User> {
	public User authUser(User user) throws BusinessException;
}
