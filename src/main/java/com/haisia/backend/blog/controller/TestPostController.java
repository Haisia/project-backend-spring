package com.haisia.backend.blog.controller;

import com.haisia.backend.blog.entity.TestPost;
import com.haisia.backend.blog.repository.TestPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestPostController {

  private final TestPostRepository testPostRepository;

  @GetMapping("/post")
  public List<TestPost> getTestPosts() {
    List<TestPost> all = testPostRepository.findAll();
    System.out.println();
    return all;
  }
}
