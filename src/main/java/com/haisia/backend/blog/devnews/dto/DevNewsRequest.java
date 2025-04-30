package com.haisia.backend.blog.devnews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Getter
public class DevNewsRequest {
  public String title;
  public String content;
}
