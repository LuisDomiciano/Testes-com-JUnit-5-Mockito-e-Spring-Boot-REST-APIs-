package com.luisd.japi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisd.japi.domain.UserDomain;
import com.luisd.japi.repostories.UserRepository;
import com.luisd.japi.service.UserService;

@Service
public class UserServiceImpl implements UserService{

  private @Autowired UserRepository userRepository;
  @Override
  public UserDomain findById(Integer id) {
    Optional<UserDomain> obj = userRepository.findById(id); 
    return obj.orElse(null);
  }
  
}
