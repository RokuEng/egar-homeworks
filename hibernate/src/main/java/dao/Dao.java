package dao;

import entity.Persistent;
import util.EMFFactory;
import util.TriFunction;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface Dao<E extends Persistent<ID>, ID> {

	E findById(ID id);
	List<E> findAll();

	default E findById(Class<E> clazz, ID id) {
		return useEntityManager(em -> em.find(clazz, id));
	}

	default List<E> findAll(Class<E> clazz) {
		return useCriteriaQuery(clazz,(cb, query, root) -> query.where(cb.isNotNull(root.get("id"))));
	}

	default E findByEntityGraph(ID id, GraphType type, Class<E> clazz, Function<EntityGraph<E>, EntityGraph<E>> function) {
		return useEntityManager(em -> {
			EntityGraph<E> entityGraph = em.createEntityGraph(clazz);

			entityGraph = function.apply(entityGraph);

			Map<String, Object> p = new HashMap<>();
			p.put(type.property, entityGraph);

			return em.find(clazz,id);
		});
	}

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
		return useEntityManager(FetchType.EAGER, em -> {
			em.getTransaction().begin();

			T result = function.apply(em);

			em.getTransaction().commit();

			return result;
		});
	}

	default <T> T useEntityManager(Function<EntityManager, T> function) {
		return useEntityManager(FetchType.LAZY, function);
	}

	default <T> T useEntityManager(FetchType fetchType, Function<EntityManager, T> function) {
		EntityManager em = EMFFactory.entityManagerFactory().createEntityManager();

		T result = function.apply(em);

		if (fetchType == FetchType.EAGER) {
			em.close();
		}

		return result;
	}

	default List<E> useCriteriaQuery(Class<E> clazz, TriFunction<CriteriaBuilder, CriteriaQuery<E>, Root<E>, CriteriaQuery<E>> function) {
		return useEntityManager(em ->
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<E> query = cb.createQuery(clazz);

			Root<E> root = query.from(clazz);

			query = function.apply(cb, query, root);

			return em.createQuery(query).getResultList();
		});
	}

	enum GraphType {
		LOAD("javax.persistence.loadgraph"),
		FETCH("javax.persistence.fetchgraph"),
		;

		private final String property;

		GraphType(String property) {
			this.property = property;
		}
	}
}
