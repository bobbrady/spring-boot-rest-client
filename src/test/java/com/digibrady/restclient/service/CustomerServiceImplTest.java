package com.digibrady.restclient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.digibrady.restclient.RestclientApplication;
import com.digibrady.restclient.config.RestTemplateConfig;
import com.digibrady.restclient.model.Customer;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;


@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = {RestTemplateConfig.class, RestclientApplication.class})
class CustomerServiceImplTest {

  private static Logger log = LoggerFactory.getLogger(CustomerServiceImplTest.class);

  @Autowired
  private CustomerService customerService;

  @Test
  void getCustomers() {
    List<Customer> customers = customerService.getCustomers();
    assertEquals(10, customers.size());
  }

  @Test
  void getCustomerById() {
    List<Customer> customers = customerService.getCustomers();
    customers.forEach(customer -> {
        Long customerId = customerService.getCustomerId(customer);
        log.info("Found customer with ID {}", customerId);
        assertCustomerById(customer, customerId, customerService.getCustomerById(customerId));
    });
  }

  @Test
  void save() {
    Customer customer = new Customer("John", "Doe", null,  null);
    Customer newCustomer = customerService.save(customer);
    assertEquals(customer.getFirstName(), newCustomer.getFirstName());
    assertEquals(customer.getLastName(), newCustomer.getLastName());
    assertEquals(customer.getFirstName(), newCustomer.getFirstName());
  }

  @Test
  void update() {
    String newLastName = "UpdatedTestLastName";
    List<Customer> customers = customerService.getCustomers();
    Customer origCustomer = customers.get(0);
    Customer updatedCustomer = new Customer(origCustomer.getFirstName(), newLastName, origCustomer.getCustomerUrl(), origCustomer.getOrdersUrl());
    customerService.update(updatedCustomer);
    Long customerId = customerService.getCustomerId(origCustomer);
    updatedCustomer = customerService.getCustomerById(customerId);
    assertEquals(origCustomer.getFirstName(), updatedCustomer.getFirstName());
    assertEquals(newLastName, updatedCustomer.getLastName());
    assertEquals(origCustomer.getCustomerUrl(), updatedCustomer.getCustomerUrl());
  }

  @Test
  void delete() {
    Customer customer = new Customer("John", "Doe", null,  null);
    Customer newCustomer = customerService.save(customer);
    Long customerId = customerService.getCustomerId(newCustomer);
    boolean success = customerService.delete(customerId);
    HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> customerService.getCustomerById(customerId));
    assertTrue(success);
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
  }

  public void assertCustomerById(Customer custFromGetAll, Long id, Customer custById) {
      assertEquals(custFromGetAll.getFirstName(), custById.getFirstName());
      assertEquals(custFromGetAll.getLastName(), custById.getLastName());
      String expectedOrdersUrl = String.format("/shop/customers/%d/orders/", id);
      assertEquals(expectedOrdersUrl, custById.getOrdersUrl());
  }
}