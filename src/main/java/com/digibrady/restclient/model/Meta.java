package com.digibrady.restclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Meta {
  private Long count;
  private Long limit;
  private Long page;
  @JsonProperty("next_url")
  private String nextUrl;
}
