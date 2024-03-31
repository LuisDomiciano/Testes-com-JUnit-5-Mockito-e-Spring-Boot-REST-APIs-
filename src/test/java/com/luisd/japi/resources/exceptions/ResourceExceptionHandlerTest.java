package com.luisd.japi.resources.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.luisd.japi.service.exceptions.DataIntegratyViolationException;
import com.luisd.japi.service.exceptions.ObjectNotFoundException;

public class ResourceExceptionHandlerTest {

  private @InjectMocks ResourceExceptionHandler resourceExceptionHandler;
  
  private static final String OBJECT_NOT_FOUND = "Object not found";
  private static final String EMAIL_ALREADY_IN_USE = "E-mail already in use";

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testDataIntegratyViolationException() {
    ResponseEntity<StandardError> response = resourceExceptionHandler.dataIntegratyViolationException(new DataIntegratyViolationException(EMAIL_ALREADY_IN_USE), new MockHttpServletRequest());

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(StandardError.class, response.getBody().getClass());
    assertEquals(EMAIL_ALREADY_IN_USE, response.getBody().getError());
    assertEquals(400, response.getBody().getStatus());
  }

  @Test
  void whenObjectNotFoundExceptionThenReturnResponseEntity() {
    ResponseEntity<StandardError> response = resourceExceptionHandler.objectNotFound(new ObjectNotFoundException("Object not found"), new MockHttpServletRequest());

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(StandardError.class, response.getBody().getClass());
    assertEquals(OBJECT_NOT_FOUND, response.getBody().getError());
    assertEquals(404, response.getBody().getStatus());
  }
}
