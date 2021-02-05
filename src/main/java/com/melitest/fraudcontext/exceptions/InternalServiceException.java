package com.melitest.fraudcontext.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServiceException extends RuntimeException {

  private static final long serialVersionUID = -8875000506140455896L;

  public InternalServiceException() {
    super();
  }

  public InternalServiceException(String message) {
    super(message);
  }

  public InternalServiceException(String message, Throwable exception) {
    super(message, exception);
  }

}
