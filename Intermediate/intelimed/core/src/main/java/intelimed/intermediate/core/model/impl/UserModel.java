/* 
 * Project name: InteliMed Intermediate Core
 * Class name: UserModelT.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Marinho
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.model.impl;

import java.util.List;

import intelimed.intermediate.core.exception.BusinessException;
import intelimed.intermediate.core.exception.SystemException;
import intelimed.intermediate.core.model.api.IUserModel;
import intelimed.intermediate.core.model.entity.User;
import intelimed.intermediate.core.persistence.api.IUserDAO;
import intelimed.intermediate.core.persistence.impl.UserDAO;

/**
 * <Insert_class_comment>
 *
 * @author Marinho
 *
 */
public class UserModel extends IntelimedModel<User, IUserDAO> implements IUserModel {

    public UserModel() {
        super();
        dao = new UserDAO();
    }
    
    public User authUser(User user) throws BusinessException {
    	try {
			User u = dao.findByName(user.getName());
			if(user.equals(u)){
				return u;
			} else {
				// TODO: TRATAMENTO AQUÊ
				return null;
			}
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return user;
    }
	
	/** {@inheritDoc} */
	@Override
	protected boolean validateRemove(User entity) throws BusinessException {
		// TODO Auto-generated method stub
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean validateInsertUpdate(User entity, boolean isUpdate)
			throws BusinessException {
		// TODO Auto-generated method stub
		return true;
	}

	
}
