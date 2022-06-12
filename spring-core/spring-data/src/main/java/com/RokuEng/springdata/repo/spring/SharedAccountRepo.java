package com.RokuEng.springdata.repo.spring;

import com.RokuEng.springdata.entity.account.SharedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedAccountRepo extends JpaRepository<SharedAccount, Long> {
}
