package util;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@UtilityClass
public class EMFFactory {
	private EntityManagerFactory emf;

	public EntityManagerFactory entityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("persistence");
		}

		return emf;
	}
}
