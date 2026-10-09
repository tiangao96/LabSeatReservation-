package com.lab.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisWarmupRunner
implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(RedisWarmupRunner.class);
    private static final int MAX_ATTEMPTS = 5;
    private static final long RETRY_INTERVAL_MS = 500L;
    private final StringRedisTemplate redis;

    public void run(ApplicationArguments args) {
        for (int attempt = 1; attempt <= 5; ++attempt) {
            try {
                this.redis.opsForValue().get((Object)"warmup");
                log.info("Redis warmup OK (attempt {}/{}): shared connection established.", (Object)attempt, (Object)5);
                return;
            }
            catch (Exception e) {
                log.warn("Redis warmup attempt {}/{} failed: {}", new Object[]{attempt, 5, e.getMessage()});
                if (attempt >= 5) continue;
                try {
                    Thread.sleep(500L);
                    continue;
                }
                catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        log.warn("Redis warmup exhausted {} attempts; first business request may fail (lettuce will retry).", (Object)5);
    }

    public RedisWarmupRunner(StringRedisTemplate redis) {
        this.redis = redis;
    }
}

