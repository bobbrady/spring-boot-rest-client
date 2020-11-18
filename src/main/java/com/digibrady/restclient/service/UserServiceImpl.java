package com.digibrady.restclient.service;

import com.digibrady.restclient.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  @Value("${user.api.url}")
  private String restEndpoint;

  private RestTemplate restTemplate;

  @Autowired
  public UserServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<User> getUsers() {
    List<User> users = restTemplate.getForObject(restEndpoint, List.class);
    log.info("REST user list: {}", users);
    return users;
  }
}
