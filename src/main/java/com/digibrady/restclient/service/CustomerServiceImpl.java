package com.digibrady.restclient.service;

import com.digibrady.restclient.model.Customer;
import com.digibrady.restclient.model.CustomerData;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

  private RestTemplate restTemplate;

  @Value("${customer.api.url}")
  private String restEndpoint;

  @Autowired
  public CustomerServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Customer save(Customer customer) {
    return null;
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
    return null;
  }

  @Override
  public Customer delete(Long id) {
    return null;
  }
}
