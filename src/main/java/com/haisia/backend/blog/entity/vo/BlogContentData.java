package com.haisia.backend.blog.entity.vo;

import com.haisia.backend.common.vo.BaseVo;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class BlogContentData extends BaseVo {
  private String title;

  @Lob
  @Column(columnDefinition = "CLOB")
  private String content;

  public static BlogContentData of(String title, String content) {
    return new BlogContentData(title, content);
  }
}
