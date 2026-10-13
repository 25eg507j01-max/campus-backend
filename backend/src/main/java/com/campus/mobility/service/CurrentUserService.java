package com.campus.mobility.service;
import com.campus.mobility.model.AppUser;
import com.campus.mobility.repo.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public class CurrentUserService {
 private final UserRepository users;
 public CurrentUserService(UserRepository users){this.users=users;}
 public AppUser get(){
  String email=SecurityContextHolder.getContext().getAuthentication().getName();
  return users.findByEmailIgnoreCase(email).orElseThrow(()->new IllegalStateException("Authenticated user not found"));
 }
}
