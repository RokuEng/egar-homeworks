package com.RokuEng.homeworks.les6.repos;

import com.RokuEng.homeworks.les6.domain.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
