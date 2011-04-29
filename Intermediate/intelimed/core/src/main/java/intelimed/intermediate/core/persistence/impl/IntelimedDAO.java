/* 
 * Project name: InteliMed Intermediate Core
 * Class name: IntelimedDAO.java
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

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import intelimed.intermediate.core.exception.SystemException;
import intelimed.intermediate.core.model.entity.IntelimedEntity;
import intelimed.intermediate.core.persistence.api.IIntelimedDAO;
import intelimed.intermediate.core.util.HibernateUtil;

/**
 * <Insert_class_comment>
 * 
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * 
 */
public class IntelimedDAO<T extends IntelimedEntity> implements
		IIntelimedDAO<T> {

	private Session session;

	private Class<T> persistentClass;

	/**
	 * 
	 * @param persistentClass
	 */
	@SuppressWarnings("unchecked")
	protected IntelimedDAO() {
		ParameterizedType p = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.persistentClass = (Class<T>) p.getActualTypeArguments()[0];
	}

	private void openConnection() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	/** {@inheritDoc} */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/** {@inheritDoc} */
	public boolean insert(T entity) throws SystemException {
		try {
			this.openConnection();
			this.session.save(entity);
		} catch (Exception e) {
			throw new SystemException(e.getMessage(), e);
		}
		return true;
	}

	/** {@inheritDoc} */
	public boolean update(T entity) throws SystemException {

		try {

			this.openConnection();

			this.session.merge(entity);

		} catch (Exception e) {
			throw new SystemException(e.getMessage(), e);
		}

		return true;

	}

	/** {@inheritDoc} */
	public boolean remove(T entity) throws SystemException {

		try {

			this.openConnection();
			this.session.delete(entity);
			this.session.flush();

		} catch (Exception e) {
			throw new SystemException(e.getMessage(), e);
		}

		return true;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	public T findById(Long id) throws SystemException {
		T entity;
		try {

			this.openConnection();

			entity = (T) session.load(getPersistentClass(), id);

		} catch (Exception e) {
			throw new SystemException(e.getMessage(), e);
		}

		return entity;
	}

	/** {@inheritDoc} */
	public List<T> findAll() throws SystemException {
		return findByCriteria();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion)
			throws SystemException {

		Criteria crit = null;
		List<T> list = null;
		try {

			this.openConnection();

			crit = this.session.createCriteria(getPersistentClass());
			for (Criterion c : criterion) {
				crit.add(c);
			}

			list = crit.list();

		} catch (Exception e) {
			throw new SystemException(e.getMessage(), e);
		}
		return list;
	}

	/** {@inheritDoc} */
	public void flush() throws SystemException {
		try {
			this.openConnection();
			this.session.flush();

		} catch (Exception e) {
			throw new SystemException(e.getMessage(), e);
		}

	}

	/** {@inheritDoc} */
	public void clear() throws SystemException {
		try {

			this.openConnection();
			this.session.clear();

		} catch (Exception e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

}
