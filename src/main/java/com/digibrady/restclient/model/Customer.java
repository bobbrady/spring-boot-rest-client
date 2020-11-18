package com.digibrady.restclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Customer {
  @JsonProperty("firstname")
  private String firstName;

  @JsonProperty("lastname")
  private String lastname;

  @JsonProperty("customer_url")
  private String customerUrl;

  @JsonProperty("orders_url")
  private String ordersUrl;
}
