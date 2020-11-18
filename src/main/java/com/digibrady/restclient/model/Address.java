package com.digibrady.restclient.model;

import lombok.Value;

@Value
public class Address {
  private String street;
  private String suite;
  private String city;
  private String zipcode;
  private Geo geo;
}
