package com.haisia.backend.blog.entity;

import jakarta.persistence.*;

@Entity
public class TestPost {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testpost_seq_gen")
  @SequenceGenerator(name = "testpost_seq_gen", sequenceName = "testpost_seq", allocationSize = 1)
  private Long id;

  private String title;

  @Lob
  @Column(columnDefinition = "CLOB")
  private String content;
}
