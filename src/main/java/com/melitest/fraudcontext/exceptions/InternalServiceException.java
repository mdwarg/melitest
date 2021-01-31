package com.melitest.fraudcontext.exceptions;

public class InternalServiceException extends RuntimeException {

  public InternalServiceException() {
    super();
  }

  public InternalServiceException(String message, Throwable exception) {
    super(message, exception);
  }

}
