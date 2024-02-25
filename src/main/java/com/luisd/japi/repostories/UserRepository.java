package com.luisd.japi.repostories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisd.japi.domain.UserDomain;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, Integer> {
  Optional<UserDomain> findByEmail(String email);
}
