package com.digibrady.restclient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import com.digibrady.restclient.RestclientApplication;
import com.digibrady.restclient.config.RestTemplateConfig;
import com.digibrady.restclient.model.Customer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = {RestTemplateConfig.class, RestclientApplication.class})
class CustomerServiceImplTest {

  private static Logger log = LoggerFactory.getLogger(CustomerServiceImplTest.class);

  private final Pattern idPattern = Pattern.compile(".*/(\\d+)$");

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
      Matcher m = idPattern.matcher(customer.getCustomerUrl());
      if (m.find()) {
        Long customerId = Long.valueOf(m.group(1));
        log.info("Found customer with ID {}", customerId);
        assertCustomerById(customer, customerId, customerService.getCustomerById(customerId));
      } else {
        fail("Unable to find Customer ID in customer_url property");
      }
    });
  }

  public void assertCustomerById(Customer custFromGetAll, Long id, Customer custById) {
      assertEquals(custFromGetAll.getFirstName(), custById.getFirstName());
      assertEquals(custFromGetAll.getLastname(), custById.getLastname());
      String expectedOrdersUrl = String.format("/shop/customers/%d/orders/", id);
      assertEquals(expectedOrdersUrl, custById.getOrdersUrl());
  }

}