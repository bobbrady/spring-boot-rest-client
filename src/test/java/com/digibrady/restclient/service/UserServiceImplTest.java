package com.digibrady.restclient.service;

import static org.junit.jupiter.api.Assertions.assertTrue;


import com.digibrady.restclient.RestclientApplication;
import com.digibrady.restclient.config.RestTemplateConfig;
import com.digibrady.restclient.model.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RestTemplateConfig.class, RestclientApplication.class})
class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @Test
  void getUsers() {
    List<User> users = userService.getUsers();
    assertTrue(users.size() > 0);
  }
}