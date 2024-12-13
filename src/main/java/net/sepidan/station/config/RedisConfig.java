package net.sepidan.station.config;

import jakarta.annotation.Resource;
import java.io.IOException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

  @Value("${spring.data.redis.host}")
  private String redisHost;

  @Value("${spring.data.redis.port}")
  private int redisPort;

  @Value("${spring.data.redis.password}")
  private String redisPassword;

  @Value("${spring.data.redis.database}")
  private int redisDatabase;

  @Bean
  public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
    return new RedissonConnectionFactory(redisson);
  }

  @Bean(destroyMethod = "shutdown")
  public RedissonClient redisson() throws IOException {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort)
        .setPassword(redisPassword).setDatabase(redisDatabase);
    return Redisson.create(config);
  }
}
