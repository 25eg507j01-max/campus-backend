package com.campus.mobility.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
public class Complaint {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private AppUser submittedBy;
 private String busNumber;
 @Column(nullable=false) private String category;
 @Column(nullable=false,length=2000) private String description;
 private String status="OPEN";
 private Instant createdAt=Instant.now();
 public Complaint() {}
 public Long getId(){return id;} public AppUser getSubmittedBy(){return submittedBy;}
 public String getBusNumber(){return busNumber;} public String getCategory(){return category;}
 public String getDescription(){return description;} public String getStatus(){return status;}
 public Instant getCreatedAt(){return createdAt;}
 public void setSubmittedBy(AppUser v){submittedBy=v;} public void setBusNumber(String v){busNumber=v;}
 public void setCategory(String v){category=v;} public void setDescription(String v){description=v;}
 public void setStatus(String v){status=v;}
}
