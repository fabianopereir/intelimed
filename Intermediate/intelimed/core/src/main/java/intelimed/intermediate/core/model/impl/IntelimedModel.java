/* 
 * Project name: RBT Tools
 * Class name: IntelimedModel.java
 * Creation date: 17/03/2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Jefferson Amorim (jsa@dsc.upe.br)
 * 17/03/2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.model.impl;

import java.util.List;

import intelimed.intermediate.core.exception.BusinessException;
import intelimed.intermediate.core.exception.SystemException;
import intelimed.intermediate.core.model.api.IIntelimedModel;
import intelimed.intermediate.core.model.entity.IntelimedEntity;
import intelimed.intermediate.core.persistence.api.IIntelimedDAO;

/**
 * Generic model class for business layer. All model classes should extend this
 * class.
 * 
 * @author Jefferson Amorim (jsa@dsc.upe.br)
 * 
 */
public abstract class IntelimedModel<T extends IntelimedEntity, E extends IIntelimedDAO<T>> implements IIntelimedModel<T> {

    /**
     * 
     */
    protected E dao;

    /**
     * 
     */
    public IntelimedModel() {
        super();
    }

    /**
     * @param dao
     */
    public IntelimedModel(E dao) {
        super();
        this.dao = dao;
    }

    /** {@inheritDoc} */
    @Override
    public List<T> findAll() throws SystemException {
        return dao.findAll();
    }

    /** {@inheritDoc} */
    @Override
    public T findById(long id) throws SystemException {
        return dao.findById(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean insert(T entity) throws BusinessException, SystemException {
        boolean isInserted = false;
        if (validateInsert(entity)) {
            isInserted = dao.insert(entity);
        }
        return isInserted;
    }

    /** {@inheritDoc} */
    @Override
    public boolean remove(T entity) throws BusinessException, SystemException {
        boolean isRemoved = false;
        if (validateRemove(entity)) {
            isRemoved = dao.remove(entity);
        }
        return isRemoved;
    }

    /** {@inheritDoc} */
    @Override
    public boolean update(T entity) throws BusinessException, SystemException {
        boolean isUpdated = false;
        if (validateUpdate(entity)) {
            isUpdated = dao.update(entity);
        }
        return isUpdated;
    }

    /**
     * 
     * @param entity
     * @return
     * @throws BusinessException
     */
    protected boolean validateUpdate(T entity) throws BusinessException {
        return validateInsertUpdate(entity, true);
    }

    /**
     * 
     * @param entity
     * @return
     * @throws BusinessException
     */
    protected boolean validateInsert(T entity) throws BusinessException {
        return validateInsertUpdate(entity, false);
    }

    /**
     * 
     * @param entity
     * @return
     * @throws BusinessException
     */
    protected abstract boolean validateRemove(T entity) throws BusinessException;

    /**
     * 
     * @param entity
     * @param isUpdate
     * @return
     * @throws BusinessException
     */
    protected abstract boolean validateInsertUpdate(T entity, boolean isUpdate) throws BusinessException;

}
