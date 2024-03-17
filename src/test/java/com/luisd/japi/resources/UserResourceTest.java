package com.luisd.japi.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
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

  private void startUser() {
    user = new UserDomain(ID, NAME, EMAIL,  PASSWORD);
    userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
  }
}
