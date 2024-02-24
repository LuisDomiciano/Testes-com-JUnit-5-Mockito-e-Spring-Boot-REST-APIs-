package com.luisd.japi.service;

import com.luisd.japi.domain.UserDomain;

public interface UserService {
  
  UserDomain findById(Integer id);
}
