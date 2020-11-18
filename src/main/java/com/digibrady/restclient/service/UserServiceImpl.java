package com.digibrady.restclient.service;

import com.digibrady.restclient.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

  private RestTemplate restTemplate;

  @Autowired
  public UserServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<User> getUsers() {
    List<User> users = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", List.class);
    return users;
  }
}
