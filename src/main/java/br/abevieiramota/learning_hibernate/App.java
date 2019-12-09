package br.abevieiramota.learning_hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.abevieiramota.learning_hibernate.model.User;

public class App {
	private static class EMF {

		private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
				.createEntityManagerFactory("hello_world");

		public static EntityManager buildManager() {
			return ENTITY_MANAGER_FACTORY.createEntityManager();
		}

	}

	public static void main(String[] args) {
		
		EntityManager manager = EMF.buildManager();
		
		Query q = manager.createQuery("from User");
		
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
		
		for(User u: users) {
			System.out.println(u);
		}
	}
}
