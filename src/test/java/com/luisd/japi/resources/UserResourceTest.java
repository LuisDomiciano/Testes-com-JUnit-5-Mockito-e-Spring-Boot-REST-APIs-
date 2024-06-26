package com.luisd.japi.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.luisd.japi.domain.UserDomain;
import com.luisd.japi.domain.dto.UserDTO;
import com.luisd.japi.service.impl.UserServiceImpl;

@SpringBootTest
public class UserResourceTest {
  
  private @InjectMocks UserResource userResource;
  private @Mock UserServiceImpl userServiceImpl;  
  private @Mock ModelMapper mapper;

  private static final Integer ID = 1;
  private static final String NAME = "Zoloide";
  private static final String EMAIL = "zo@mail.com";
  private static final String PASSWORD = "aiP2j7Is";
  private static final Integer INDEX = 0;

  private UserDomain user;
  private UserDTO userDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startUser();
  }

  @Test
  void whenFindByIdThenReturnSuccess() {
    when(userServiceImpl.findById(anyInt())).thenReturn(user);
    when(mapper.map(any(), any())).thenReturn(userDTO);
    
    ResponseEntity<UserDTO> response = userResource.findById(ID);
    
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(UserDTO.class, response.getBody().getClass());
    
    assertEquals(ID, response.getBody().getId());
    assertEquals(NAME, response.getBody().getName());
    assertEquals(EMAIL, response.getBody().getEmail());
    assertEquals(PASSWORD, response.getBody().getPassword());
  }

  @Test
  void whenFindAllThenReturnAListOfUserDTO() {
    when(userServiceImpl.findAll()).thenReturn(List.of(user));
    when(mapper.map(any(), any())).thenReturn(userDTO);

    ResponseEntity<List<UserDTO>> response = userResource.findAll();

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(ArrayList.class, response.getBody().getClass());
    assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());
    
    assertEquals(ID, response.getBody().get(INDEX).getId());
    assertEquals(NAME, response.getBody().get(INDEX).getName());
    assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
    assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
  }

  @Test
  void whenCreateThenReturnCreated() {
    when(userServiceImpl.create(any())).thenReturn(user);

    ResponseEntity<UserDTO> response = userResource.create(userDTO);
    
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getHeaders().get("Location"));
  }

  @Test
  void whenUpdateThenReturnSuccess() {
    when(userServiceImpl.update(userDTO)).thenReturn(user);
    when(mapper.map(any(), any())).thenReturn(userDTO);

    ResponseEntity<UserDTO> response = userResource.update(ID, userDTO);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(UserDTO.class, response.getBody().getClass());

    assertEquals(ID, response.getBody().getId());
    assertEquals(NAME, response.getBody().getName());
    assertEquals(EMAIL, response.getBody().getEmail());
  }

  @Test
  void whenDeteleThenReturnSuccess() {
    doNothing().when(userServiceImpl).delete(anyInt());

    ResponseEntity<UserDTO> response = userResource.delete(ID);

    assertNotNull(response);
    assertEquals(ResponseEntity.class, response.getClass());
    verify(userServiceImpl, times(1)).delete(anyInt());
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  private void startUser() {
    user = new UserDomain(ID, NAME, EMAIL,  PASSWORD);
    userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
  }
}
