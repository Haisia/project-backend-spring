package com.haisia.backend.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseJpaEntity {

  @CreatedDate
  @Column(updatable = false)
  public LocalDateTime createdAt;

  @LastModifiedDate
  @Column(insertable = false)
  public LocalDateTime updatedAt;

  @Column(nullable = false)
  protected Boolean isDeleted = false;
}
