package com.luisd.japi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luisd.japi.domain.dto.UserDTO;
import com.luisd.japi.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
  
  private @Autowired UserService userService;
  private @Autowired ModelMapper mapper;

  private static final String ID = "/{id}";

  @GetMapping(value = ID)
  public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<UserDTO> listDTO = userService.findAll().stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);
  }

  @PostMapping
  public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
    URI uri =  ServletUriComponentsBuilder
      .fromCurrentRequest().path(ID)
      .buildAndExpand(userService.create(user).getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping (value = ID)
  public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj) {
    obj.setId(id);
    return ResponseEntity.ok().body(mapper.map(userService.update(obj), UserDTO.class));
  }

  @DeleteMapping(value = ID)
  public ResponseEntity<UserDTO> delete(@PathVariable Integer id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
