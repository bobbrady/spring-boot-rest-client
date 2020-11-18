package com.digibrady.restclient.model;

import lombok.Value;

@Value
public class User {
  private Long id;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;
}
