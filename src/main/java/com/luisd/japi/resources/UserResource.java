package com.luisd.japi.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisd.japi.domain.dto.UserDTO;
import com.luisd.japi.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
  
  private @Autowired UserService userService;
  private @Autowired ModelMapper mapper;

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<UserDTO> listDTO = userService.findAll().stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);
  }
}
