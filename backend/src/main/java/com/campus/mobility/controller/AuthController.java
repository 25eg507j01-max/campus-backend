package com.campus.mobility.controller;
import com.campus.mobility.model.*;
import com.campus.mobility.repo.UserRepository;
import com.campus.mobility.security.JwtService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthController(UserRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
 public record RegisterRequest(@NotBlank String fullName,@Email @NotBlank String email,@Size(min=8) String password){}
 public record LoginRequest(@Email @NotBlank String email,@NotBlank String password){}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED)
 public Map<String,Object> register(@Valid @RequestBody RegisterRequest r){
  if(users.findByEmailIgnoreCase(r.email()).isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");
  AppUser u=users.save(new AppUser(r.email().toLowerCase(),encoder.encode(r.password()),r.fullName(),Role.STUDENT));
  return Map.of("token",jwt.generate(u),"user",safe(u));
 }
 @PostMapping("/login")
 public Map<String,Object> login(@Valid @RequestBody LoginRequest r){
  AppUser u=users.findByEmailIgnoreCase(r.email()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password"));
  if(!encoder.matches(r.password(),u.getPasswordHash())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");
  return Map.of("token",jwt.generate(u),"user",safe(u));
 }
 private Map<String,Object> safe(AppUser u){return Map.of("id",u.getId(),"email",u.getEmail(),"fullName",u.getFullName(),"role",u.getRole().name());}
}
