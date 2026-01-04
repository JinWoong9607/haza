package com.gachi.haza.repository;

import com.gachi.haza.entity.Users;
import com.gachi.haza.entity.enums.Providers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByProviderAndProviderId(Providers provider, String providerId);
    Optional<Users> findByUserName(String userName);
    Optional<Users> findByEmail(String email);
    boolean existsByUserName(String userName);
}
