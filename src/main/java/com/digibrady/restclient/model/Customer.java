package com.digibrady.restclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Customer {
  @JsonProperty("firstname")
  private String firstName;

  @JsonProperty("lastname")
  private String lastName;

  @JsonProperty("customer_url")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String customerUrl;

  @JsonProperty("orders_url")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String ordersUrl;

}
