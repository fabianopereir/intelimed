/* 
 * Project name: InteliMed Intermediate Core
 * Class name: HibernateSessionManager.aj
 * Creation date: 14/09/2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * 14/09/2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.aspect;

import intelimed.intermediate.core.util.HibernateUtil;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 * <Insert_aspect_comment>
 *
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 *
 */
public aspect HibernateSessionManager {
	pointcut transaction() : call(* intelimed.intermediate.core.IIntelimedIntermediateCore.*(..));
	
	private Transaction transaction = null;
	
	before() : transaction() {
		System.out.println("testando antes da transaction");
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = s.beginTransaction();
	}
	
	after() returning() : transaction() {
		transaction.commit();
		System.out.println("testando adispois da transaction");
	}
}
