package com.luisd.japi.service.impl;

import org.springframework.stereotype.Service;

import com.luisd.japi.domain.UserDomain;
import com.luisd.japi.service.UserService;

@Service
public class UserServiceImpl implements UserService{

  @Override
  public UserDomain findById(Integer id) {
    return null;
  }
  
}
