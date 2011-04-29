/* 
 * Project name: InteliMed Intermediate Core
 * Class name: IntelimedIntermediateCore.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core;

import intelimed.intermediate.core.exception.BusinessException;
import intelimed.intermediate.core.exception.SystemException;
import intelimed.intermediate.core.model.api.IIntelimedModel;
import intelimed.intermediate.core.model.api.IUserModel;
import intelimed.intermediate.core.model.entity.IIntelimedEntity;
import intelimed.intermediate.core.model.entity.User;
import intelimed.intermediate.core.model.impl.UserModel;

import java.util.HashMap;
import java.util.List;

/**
 * <Insert_class_comment>
 * 
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * 
 */
@SuppressWarnings("unchecked")
public class IntelimedIntermediateCore implements IIntelimedIntermediateCore {

	private IUserModel userModel;

	private HashMap<Class<? extends IIntelimedEntity>, IIntelimedModel> models;

	public IntelimedIntermediateCore() {
		initModels();
	}

	private void initModels() {
		userModel = new UserModel();

		models = new HashMap<Class<? extends IIntelimedEntity>, IIntelimedModel>();

		models.put(User.class, userModel);
	}

	public User authUser(User user) throws BusinessException, SystemException {
		return ((IUserModel) models.get(User.class)).authUser(user);
	}

	public List<? extends IIntelimedEntity> findAll(
			Class<? extends IIntelimedEntity> entityClass)
			throws SystemException {
		return models.get(entityClass).findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public IIntelimedEntity findEntityById(
			Class<? extends IIntelimedEntity> entityClass, Long id)
			throws SystemException {
		return models.get(entityClass).findById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean insert(Class<? extends IIntelimedEntity> entityClass,
			IIntelimedEntity entity) throws BusinessException, SystemException {
		return models.get(entityClass).insert(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean remove(Class<? extends IIntelimedEntity> entityClass,
			IIntelimedEntity entity) throws BusinessException, SystemException {
		return models.get(entityClass).remove(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean update(Class<? extends IIntelimedEntity> entityClass,
			IIntelimedEntity entity) throws BusinessException, SystemException {
		return models.get(entityClass).update(entity);
	}

}
