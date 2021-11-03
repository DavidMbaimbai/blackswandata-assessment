package com.blackswan.dm.usermanager.repo;

import com.blackswan.dm.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
