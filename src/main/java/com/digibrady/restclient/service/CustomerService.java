package com.digibrady.restclient.service;

import com.digibrady.restclient.model.Customer;
import java.util.List;

public interface CustomerService {

  Customer save(Customer customer);

  List<Customer> getCustomers();

  Customer getCustomerById(Long id);

  Customer update(Customer customer);

  boolean delete(Long id);

  Long getCustomerId(Customer customer);
}
