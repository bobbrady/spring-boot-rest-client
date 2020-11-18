package com.digibrady.restclient.service;

import com.digibrady.restclient.model.Customer;
import com.digibrady.restclient.model.CustomerData;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

  private final Pattern idPattern = Pattern.compile(".*/(\\d+)$");

  private RestTemplate restTemplate;

  @Value("${customer.api.url}")
  private String restEndpoint;

  @Autowired
  public CustomerServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Customer save(Customer customer) {
    Customer newCustomer = restTemplate.postForObject(restEndpoint, customer, Customer.class);
    log.info("New Customer saved: {}", newCustomer);
    return newCustomer;
  }

  @Override
  public List<Customer> getCustomers() {
    CustomerData data = restTemplate.getForObject(restEndpoint, CustomerData.class);
    log.info("CustomerData: {}", data);
    return data.getCustomers();
  }

  @Override
  public Customer getCustomerById(Long id) {
    String customerEndpoint = String.format("%s/%d", restEndpoint, id);
    Customer customer = restTemplate.getForObject(customerEndpoint, Customer.class);
    log.info("Customer: {}", customer);
    return customer;
  }

  @Override
  public Customer update(Customer customer) {
    Long id = getCustomerId(customer);
    String customerEndpoint = String.format("%s/%d", restEndpoint, id);
    restTemplate.put(customerEndpoint, customer);
    return customer;
  }

  @Override
  public boolean delete(Long id) {
    String customerEndpoint = String.format("%s/%d", restEndpoint, id);
    try {
      restTemplate.delete(customerEndpoint);
    } catch (Exception e) {
      log.error("Exception deleting Customer w/ID {}: {}", id, e.getMessage());
      return false;
    }
    return true;
  }

  @Override
  public Long getCustomerId(Customer customer) {
    Matcher m = idPattern.matcher(customer.getCustomerUrl());
    if (m.find()) {
      Long customerId = Long.valueOf(m.group(1));
      log.info("Found customer with ID {}", customerId);
      return customerId;
    } else {
      throw new IllegalArgumentException("Customer has no ID");
    }
  }
}
