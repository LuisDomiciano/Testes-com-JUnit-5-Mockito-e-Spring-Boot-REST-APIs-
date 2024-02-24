package com.luisd.japi.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisd.japi.domain.UserDomain;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, Integer> {
  
}
