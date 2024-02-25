package com.luisd.japi.service;

import java.util.List;

import com.luisd.japi.domain.UserDomain;

public interface UserService {
  
  UserDomain findById(Integer id);

  List<UserDomain> findAll();
}
