package com.haisia.backend.common.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class GitMetricsConfiguration {

  private final MeterRegistry meterRegistry;
  private final GitProperties gitProperties;

  @PostConstruct
  public void exposeGitInfoAsMetric() {
    if (gitProperties != null) {
      Gauge.builder("application.info", this, (value) -> 1.0)
        .description("Application Git Information")
        .tag("git_commit_id", gitProperties.getCommitId().substring(0,7))
        .tag("git_branch", gitProperties.getBranch())
        .register(meterRegistry);
    }
  }
}