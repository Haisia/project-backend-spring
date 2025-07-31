package com.haisia.backend.blog.common.vo;

import com.haisia.backend.common.vo.BaseVo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class BlogContentData extends BaseVo {
  private String title;

  @Lob
  @JdbcTypeCode(Types.LONGVARCHAR)
  private String content;

  public static BlogContentData of(String title, String content) {
    return new BlogContentData(title, content);
  }
}
