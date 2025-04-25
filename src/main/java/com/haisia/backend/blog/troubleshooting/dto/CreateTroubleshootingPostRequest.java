package com.haisia.backend.blog.troubleshooting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CreateTroubleshootingPostRequest {
  public Long categoryId;
  public String title;
  public String content;
}
