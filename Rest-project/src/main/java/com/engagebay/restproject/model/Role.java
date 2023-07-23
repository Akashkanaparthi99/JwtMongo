package com.engagebay.restproject.model;

public enum Role {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER");

  final  String type;
  Role(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type ;
  }
}
