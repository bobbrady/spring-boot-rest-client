package com.digibrady.restclient.model;

import java.util.List;
import lombok.Value;

@Value
public class CustomerData {
  private Meta meta;
  private List<Customer> customers;
}
