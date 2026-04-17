package com.example.test_app_apring.domain.user.service.impl;

import com.example.test_app_apring.domain.user.model.MUser;
import com.example.test_app_apring.domain.user.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserService service;

  public UserDetailsServiceImpl(UserService service) {
    this.service= service;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //ユーザー情報取得
      MUser loginUser = service.getLoginUser(username);

      //ユーザーが存在しない場合
      if (loginUser == null) {
          throw new UsernameNotFoundException("user not found");
      }

      //権限List作成
      GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(authority);

      //Userdetails作成
      UserDetails userDetails = (UserDetails) new User(loginUser.getUserId(),
              loginUser.getPassword(),
              authorities);

      return userDetails;
  }
}