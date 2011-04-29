/* 
 * Project name: InteliMed Intermediate Core
 * Class name: IIntelimedModel.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.model.api;

import java.io.Serializable;
import java.util.List;

import intelimed.intermediate.core.exception.BusinessException;
import intelimed.intermediate.core.exception.SystemException;
import intelimed.intermediate.core.model.entity.IIntelimedEntity;

/**
 * Generic model interface for business layer. All model interfaces should
 * extend this interface.
 * 
 * @author Jefferson Amorim (jsa@dsc.upe.br)
 * 
 */
public interface IIntelimedModel<T extends IIntelimedEntity> extends
		Serializable {
	/**
	 * 
	 * @return
	 * @throws SystemException
	 */
	public abstract List<T> findAll() throws SystemException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public abstract T findById(long id) throws SystemException;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public abstract boolean insert(T entity) throws BusinessException,
			SystemException;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public abstract boolean remove(T entity) throws BusinessException,
			SystemException;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws SystemException
	 * @throws BusinessException
	 */
	public abstract boolean update(T entity) throws BusinessException,
			SystemException;

}
