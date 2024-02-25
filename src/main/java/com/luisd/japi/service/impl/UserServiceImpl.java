package com.luisd.japi.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisd.japi.domain.UserDomain;
import com.luisd.japi.domain.dto.UserDTO;
import com.luisd.japi.repostories.UserRepository;
import com.luisd.japi.service.UserService;
import com.luisd.japi.service.exceptions.DataIntegratyViolationException;
import com.luisd.japi.service.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService{

  private @Autowired UserRepository userRepository;
  private @Autowired ModelMapper mapper;

  @Override
  public UserDomain findById(Integer id) {
    Optional<UserDomain> obj = userRepository.findById(id); 
    return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
  }
  
  public List<UserDomain> findAll() {
    return userRepository.findAll();
  }

  @Override
  public UserDomain create(UserDTO obj) {
    findByEmail(obj);
    return userRepository.save(mapper.map(obj, UserDomain.class));
  }

  private void findByEmail(UserDTO obj) {
    Optional<UserDomain> user = userRepository.findByEmail(obj.getEmail());
    if (user.isPresent()) {
      throw new DataIntegratyViolationException("E-mail already in use");
    }
  }
}
