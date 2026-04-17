package com.example.test_app_apring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .authorizeHttpRequests(auth -> auth
                      .requestMatchers("/login", "/user/signup").permitAll()
                      .requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**", ("/error")).permitAll()
                      .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                      .anyRequest().authenticated()
              ).formLogin(login -> login
                      .loginPage("/login")
                      .loginProcessingUrl("/login")
                      .failureUrl("/login?error")
                      .usernameParameter("userId")
                      .passwordParameter("password")
                      .defaultSuccessUrl("/user/list", true)
              ).logout((logout) -> logout
                      .logoutUrl("/logout")
                      .logoutSuccessUrl("/login?logout")
              );
      //http.csrf().disable();

      return http.build();
  }

  /*
  @Bean
    public UserDetailsService users() {
      PasswordEncoder encoder = passwordEncoder();

      UserDetails admin = User.builder()
              .username("admin")
              .password(encoder.encode("admin"))
              .roles("ADMIN")
              .build();
      UserDetails user = User.builder()
              .username("user")
              .password(encoder.encode("user"))
              .roles("GENERAL")
              .build();

      return new InMemoryUserDetailsManager(admin, user);

  }

       */
}
