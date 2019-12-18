package br.abevieiramota.learning_hibernate;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import br.abevieiramota.learning_hibernate.model.User;

public class App {
	private static class SF {

		private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

		private static final SessionFactory buildSessionFactory() {
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			try {
				return new MetadataSources(registry).buildMetadata().buildSessionFactory();
			} catch (Exception ex) {
				StandardServiceRegistryBuilder.destroy(registry);
				throw ex;
			}
		}

		public static Session openSession() {
			return SESSION_FACTORY.openSession();
		}

	}

	public static void main(String[] args) {

		Session session = SF.openSession();
		
		session.beginTransaction();
		
		User uNew = new User();
		uNew.setId(1000);
		uNew.setName("Hibernate in action");
		
		session.save(uNew);
		
		uNew.setName("Hibernate in action [DIRTY]");
		
		session.getTransaction().commit();
		
		session.close();
		
		session = SF.openSession();

		Query q = session.createQuery("from User");

		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();

		for (User u : users) {
			System.out.println("Nome: "+ u);
		}
		
		session.close();
	}
}
