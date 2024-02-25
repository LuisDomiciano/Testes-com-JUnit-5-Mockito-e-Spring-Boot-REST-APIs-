package com.luisd.japi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luisd.japi.domain.UserDomain;
import com.luisd.japi.repostories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
  
  private @Autowired UserRepository userRepository;

  @Bean
  public CommandLineRunner startDB() {
    return args -> {
      var user1 = new UserDomain(null, "james", "james@mail.com", "ashuas");
      var user2 = new UserDomain(null, "Joimes", "jo@mail.com", "kyasjia12a");
      this.userRepository.saveAll(Arrays.asList(user1, user2));
    };
  }
}
