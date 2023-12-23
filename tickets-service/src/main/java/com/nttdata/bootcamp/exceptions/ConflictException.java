package com.nttdata.bootcamp.exceptions;

public class ConflictException extends RuntimeException{

  private static final String DESCRIPTION = "Conflict Exception";
  public ConflictException(String detail) {
    super(DESCRIPTION + ". " + detail);
  }
}
