package com.luisd.japi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.luisd.japi.domain.UserDomain;
import com.luisd.japi.domain.dto.UserDTO;
import com.luisd.japi.repostories.UserRepository;
import com.luisd.japi.service.exceptions.DataIntegratyViolationException;
import com.luisd.japi.service.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {

  private static final Integer ID = 1;
  private static final String NAME = "Zoloide";
  private static final String EMAIL = "zo@mail.com";
  private static final String PASSWORD = "aiP2j7Is";
  private static final Integer INDEX = 0;
  
  private @InjectMocks UserServiceImpl userServiceImpl;
  private @Mock UserRepository userRepository;
  private @Mock ModelMapper modelMapper;
  private UserDomain user;
  private UserDTO userDTO;
  private Optional<UserDomain> optionalUser;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startUser();
  }

  @Test
  void whenCreateThenReturnSuccess() {
    when(userRepository.save(any())).thenReturn(user);
    var response = userServiceImpl.create(userDTO);
    assertNotNull(response);
    assertEquals(UserDomain.class, response.getClass());
    assertEquals(ID, response.getId());
  }

  @Test
  void whenCreateThenReturnADataIntegrityViolationException() {
    when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
    try {
      optionalUser.get().setId(2);
      userServiceImpl.create(userDTO);
    } catch (Exception ex) {
      assertEquals(DataIntegratyViolationException.class, ex.getClass());
      assertEquals("E-mail already in use", ex.getMessage());
    }
  }

  @Test
  void testDelete() {

  }

  @Test
  void whenFindAllThenReturnAListOfUsers() {
    when(userRepository.findAll()).thenReturn(List.of(user));
    List<UserDomain> response = userServiceImpl.findAll();
    assertNotNull(response);
    assertEquals(1, response.size());
    assertEquals(UserDomain.class, response.get(INDEX).getClass());
    assertEquals(ID, response.get(INDEX).getId());
    assertEquals(NAME, response.get(INDEX).getName());
    assertEquals(EMAIL, response.get(INDEX).getEmail());
    assertEquals(PASSWORD, response.get(INDEX).getPassword());
  }

  @Test
  void whenFindByIdThenReturnAnUserInstance() {
    when(userRepository.findById(anyInt())).thenReturn(optionalUser);
    UserDomain response = userServiceImpl.findById(ID);
    assertNotNull(response);
    assertEquals(UserDomain.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NAME, response.getName());
    assertEquals(EMAIL, response.getEmail());
    assertEquals(PASSWORD, response.getPassword());
  }

  @Test
  void whenFindByIdThenReturnAnObjectNotFoundException() {
    when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Object not found"));
    try {
      userServiceImpl.findById(ID);
    } catch (Exception ex) {
      assertEquals(ObjectNotFoundException.class, ex.getClass());
      assertEquals("Object not found", ex.getMessage());
    }
  }

  @Test
  void whenUpdateThenReturnSuccess() {
    when(userRepository.save(any())).thenReturn(user);
    var response = userServiceImpl.update(userDTO);
    assertNotNull(response);
    assertEquals(UserDomain.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NAME, response.getName());
    assertEquals(EMAIL, response.getEmail());
    assertEquals(PASSWORD, response.getPassword());
  }

  private void startUser() {
    user = new UserDomain(ID, NAME, EMAIL,  PASSWORD);
    userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    optionalUser = Optional.of(new UserDomain(ID, NAME, EMAIL, PASSWORD));
}
}
