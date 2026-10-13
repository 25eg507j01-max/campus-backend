package com.campus.mobility.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "app_users")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true) private String email;
    @Column(nullable=false) private String passwordHash;
    @Column(nullable=false) private String fullName;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role = Role.STUDENT;
    private Instant createdAt = Instant.now();
    public AppUser() {}
    public AppUser(String email, String passwordHash, String fullName, Role role) {
        this.email=email; this.passwordHash=passwordHash; this.fullName=fullName; this.role=role;
    }
    public Long getId(){return id;} public String getEmail(){return email;}
    public String getPasswordHash(){return passwordHash;} public String getFullName(){return fullName;}
    public Role getRole(){return role;} public Instant getCreatedAt(){return createdAt;}
    public void setEmail(String v){email=v;} public void setPasswordHash(String v){passwordHash=v;}
    public void setFullName(String v){fullName=v;} public void setRole(Role v){role=v;}
}
