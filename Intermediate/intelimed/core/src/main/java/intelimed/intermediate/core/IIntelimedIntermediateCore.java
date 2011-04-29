/* 
 * Project name: InteliMed Intermediate Core
 * Class name: IIntelimedCoreFacade.java
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
import intelimed.intermediate.core.model.entity.IIntelimedEntity;
import intelimed.intermediate.core.model.entity.User;

import java.util.List;

/**
 * <Insert_class_comment>
 *
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 *
 */
public interface IIntelimedIntermediateCore {
	
	// TODO: Retornar User daqui é bonito?
	public abstract User authUser(User user) throws BusinessException, SystemException;
	
	
	 /**
     * 
     * @param entityClass
     * @return
     * @throws SystemException
     */
    public abstract List<? extends IIntelimedEntity> findAll(Class<? extends IIntelimedEntity> entityClass)
            throws SystemException;

    /**
     * 
     * @param entityClass
     * @param id
     * @return
     * @throws SystemException
     */
    public abstract IIntelimedEntity findEntityById(Class<? extends IIntelimedEntity> entityClass, Long id)
            throws SystemException;

    /**
     * 
     * @param entityClass
     * @param entity
     * @return
     * @throws BusinessException
     * @throws SystemException
     */
    public abstract boolean insert(Class<? extends IIntelimedEntity> entityClass, IIntelimedEntity entity)
            throws BusinessException, SystemException;

    /**
     * 
     * @param entityClass
     * @param entity
     * @return
     * @throws BusinessException
     * @throws SystemException
     */
    public abstract boolean remove(Class<? extends IIntelimedEntity> entityClass, IIntelimedEntity entity)
            throws BusinessException, SystemException;

    /**
     * 
     * @param entityClass
     * @param entity
     * @return
     * @throws BusinessException
     * @throws SystemException
     */
    public abstract boolean update(Class<? extends IIntelimedEntity> entityClass, IIntelimedEntity entity)
            throws BusinessException, SystemException;
}
