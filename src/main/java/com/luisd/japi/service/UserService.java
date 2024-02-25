package com.luisd.japi.service;

import java.util.List;

import com.luisd.japi.domain.UserDomain;
import com.luisd.japi.domain.dto.UserDTO;

public interface UserService {
  
  UserDomain findById(Integer id);
  List<UserDomain> findAll();
  UserDomain create(UserDTO obj);
}
