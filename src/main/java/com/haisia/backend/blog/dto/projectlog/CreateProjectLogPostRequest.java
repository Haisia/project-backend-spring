package com.haisia.backend.blog.dto.projectlog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CreateProjectLogPostRequest {
  public Long categoryId;
  public String title;
  public String content;
}
