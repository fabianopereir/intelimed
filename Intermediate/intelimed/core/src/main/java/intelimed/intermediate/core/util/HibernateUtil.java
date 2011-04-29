/* 
 * Project name: InteliMed Intermediate Core
 * Class name: HibernateUtil.java
 * Creation date: Sep 12, 2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * Sep 12, 2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * <Insert_class_comment>
 * 
 * @author Francisco Marinho (fmarinho.rodrigues@gmail.com)
 * 
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure(
					HibernateUtil.class.getResource("/META-INF/hibernate.cfg.xml")).buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
