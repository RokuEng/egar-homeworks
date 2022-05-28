package dao;

import entity.Persistent;
import util.EMFFactory;
import util.TriConsumer;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Dao<E extends Persistent<ID>, ID> {

	Optional<E> findById(ID id);

	List<E> findAll();

	default Optional<E> findById(Class<E> clazz, ID id) {
		return useEntityManager(em -> Optional.of(em.find(clazz, id)));
	}

	default List<E> findAll(Class<E> clazz) {
		return findByCriteria(clazz, (cb, query, root) -> query.where(cb.isNotNull(root.get("id"))));
	}

	default List<E> findByCriteria(Class<E> clazz, TriConsumer<CriteriaBuilder, CriteriaQuery<E>, Root<E>> function) {
		return useCriteriaQuery(clazz, null, function);
	}

	default Optional<E> findByIdEntityGraph(ID id, Class<E> clazz, Consumer<EntityGraph<E>> consumer) {
		return findByIdEntityGraph(id, GraphType.FETCH, clazz, consumer);
	}

	default List<E> findAllEntityGraph(Class<E> clazz, Consumer<EntityGraph<E>> consumer) {
		return findAllEntityGraph(GraphType.FETCH, clazz, consumer);
	}

	default E save(E e) {
		return useTransaction(entityManager -> {
			if (e.getId() == null && !entityManager.contains(e)) {
				entityManager.persist(e);
				return e;
			} else {
				return entityManager.merge(e);
			}
		});
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	default <T> T useEntityManager(Function<EntityManager, T> function) {
		return useEntityManager(FetchType.LAZY, function);
	}

	default <T> T useEntityGraph(GraphType type, Class<E> clazz, Function<EntityGraph<E>, T> function) {
		return useEntityManager(em -> {
			EntityGraph<E> entityGraph = em.createEntityGraph(clazz);
			return function.apply(entityGraph);
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

	default List<E> useCriteriaQuery(Class<E> clazz, Map<String, Object> hint, TriConsumer<CriteriaBuilder, CriteriaQuery<E>, Root<E>> function) {
		return useEntityManager(em ->
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<E> query = cb.createQuery(clazz);

			Root<E> root = query.from(clazz);

			function.accept(cb, query, root);

			if (hint == null) {
				return em.createQuery(query).getResultList();
			} else {
				Map.Entry<String, Object> hintEntry = hint.entrySet().stream().findFirst().orElseThrow();
				return em.createQuery(query).setHint(hintEntry.getKey(), hintEntry.getValue()).getResultList();
			}
		});
	}

	default <T> T useEntityManager(FetchType fetchType, Function<EntityManager, T> function) {
		EntityManager em = EMFFactory.entityManagerFactory().createEntityManager();

		T result = function.apply(em);

		if (fetchType == FetchType.EAGER) {
			em.close();
		}

		return result;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	default List<E> findAllEntityGraph(GraphType type, Class<E> clazz, Consumer<EntityGraph<E>> consumer) {
		return useEntityManager(em -> {
			Map<String, Object> p = getEntityGraphProperties(type, clazz, consumer);
			return useCriteriaQuery(clazz, p,(cb, query, root) -> {
				query.where(cb.isNotNull(root.get("id")));
			});
		});
	}

	default Optional<E> findByIdEntityGraph(ID id, GraphType type, Class<E> clazz, Consumer<EntityGraph<E>> consumer) {
		return useEntityManager(em -> {
			Map<String, Object> p = getEntityGraphProperties(type, clazz, consumer);
			return Optional.of(em.find(clazz, id, p));
		});
	}

	default Map<String, Object> getEntityGraphProperties(GraphType type, Class<E> clazz, Consumer<EntityGraph<E>> function) {
		return useEntityManager(em ->
			useEntityGraph(type, clazz, eg -> {
					function.accept(eg);

					Map<String, Object> p = new HashMap<>();
					p.put(type.property, eg);

					return p;
				}
			));
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
