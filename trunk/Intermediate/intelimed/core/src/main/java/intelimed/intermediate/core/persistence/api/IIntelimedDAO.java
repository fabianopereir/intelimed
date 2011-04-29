/* 
 * Project name: InteliMed Intermediate Core
 * Class name: IIntelimedDAO.java
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
import intelimed.intermediate.core.model.entity.IIntelimedEntity;

import java.io.Serializable;
import java.util.List;


/**
 * <Insert_class_comment>
 *
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 *
 */
public interface IIntelimedDAO<T extends IIntelimedEntity> extends Serializable{

	    /**
	     * 
	     * @param entity
	     * @return
	     * @throws SystemException
	     */
	    public abstract boolean insert(T entity) throws SystemException;

	    /**
	     * 
	     * @param entity
	     * @return
	     * @throws SystemException
	     */
	    public abstract boolean update(T entity) throws SystemException;

	    /**
	     * 
	     * @param entity
	     * @return
	     * @throws SystemException
	     */
	    public abstract boolean remove(T entity) throws SystemException;

	    /**
	     * 
	     * @param id 
	     * @return
	     * @throws SystemException
	     */
	    public abstract T findById(Long id) throws SystemException;

	    /**
	     * 
	     * @return
	     * @throws SystemException
	     */
	    public abstract List<T> findAll() throws SystemException;

	    /**
	     * @throws SystemException 
	     * 
	     */
	    public abstract void flush() throws SystemException;

	    /**
	     * @throws SystemException 
	     * 
	     */
	    public abstract void clear() throws SystemException;


}
