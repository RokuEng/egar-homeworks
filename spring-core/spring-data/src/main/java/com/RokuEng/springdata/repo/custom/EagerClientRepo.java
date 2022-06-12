package com.RokuEng.springdata.repo.custom;

import com.RokuEng.springdata.entity.client.Client;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface EagerClientRepo extends CustomRepo<Client, Integer> {
	Optional<Client> findByIdWithAccounts(Integer id);

	Optional<Client> findByIdWithFamily(Integer id);

	Optional<Client> findByIdWithFamilyAndAccounts(Integer id);

}
