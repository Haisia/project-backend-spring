package com.haisia.backend.blog.dto.devnews;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class BlogGetAllYearMonthResponse {
  public List<YearMonthProjection> allUniqueYearMonth;
}
