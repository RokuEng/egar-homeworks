package dao;

import entity.Persistent;
import util.EMFFactory;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import java.util.List;
import java.util.function.Function;

public interface Dao<E extends Persistent<ID>, ID> {

	E findById(ID id);

	List<E> findAll();

	default E save(E e) {
		return useTransaction(entityManager -> {
			if (e.getId() == null) {
				entityManager.persist(e);
				return e;
			} else {
				return entityManager.merge(e);
			}
		});
	}

	default <T> T useTransaction(Function<EntityManager, T> function) {
		return useEntityManager(FetchType.EAGER, entityManager -> {
			entityManager.getTransaction().begin();

			T result = function.apply(entityManager);

			entityManager.getTransaction().commit();
			return result;
		});
	}

	default <T> T useEntityManager(FetchType fetchType, Function<EntityManager, T> function) {
		EntityManager entityManager = EMFFactory.entityManagerFactory().createEntityManager();

		T result = function.apply(entityManager);

		if (fetchType == FetchType.EAGER) {
			entityManager.close();
		}
		return result;
	}
}
