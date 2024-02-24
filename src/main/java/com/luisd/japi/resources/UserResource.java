package com.luisd.japi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisd.japi.domain.UserDomain;
import com.luisd.japi.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
  
  private @Autowired UserService userService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDomain> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(userService.findById(id));
  }
}
