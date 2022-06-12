package com.RokuEng.springdata.repo.custom;

import com.RokuEng.springdata.entity.client.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class EagerClientRepoImpl  implements EagerClientRepo {

	@PersistenceContext
	private EntityManager entityManager;
	private final Class<Client> clazz = Client.class;

	public Optional<Client> findByIdWithAccounts(Integer id) {
		return findByIdEntityGraph(id, clazz, eg -> {
			eg.addAttributeNodes("accounts");
		});
	}

	@Override
	public Optional<Client> findByIdWithFamily(Integer id) {
		return findByIdEntityGraph(id, clazz, eg -> {
			eg.addAttributeNodes("family");
		});
	}

	@Override
	public Optional<Client> findByIdWithFamilyAndAccounts(Integer id) {
		return findByIdEntityGraph(id, clazz, eg -> {
			eg.addAttributeNodes("accounts");
			eg.addAttributeNodes("family");
		});
	}

	@Override
	public Optional<Client> findById(Integer integer) {
		return findById(clazz,integer);
	}

	@Override
	public List<Client> findAll() {
		return findAll(clazz);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
