package com.haisia.backend.blog.projectlog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CreateProjectLogCategoryRequest {
  private String title;
  private Long projectLogId;
}
