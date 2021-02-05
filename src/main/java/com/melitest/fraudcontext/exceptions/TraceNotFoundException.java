package com.melitest.fraudcontext.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TraceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -3599710913476736635L;

  public TraceNotFoundException() {
    super();
  }

  public TraceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public TraceNotFoundException(String message) {
    super(message);
  }

}
