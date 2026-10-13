package com.campus.mobility.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
@Table(name="transport_registrations", uniqueConstraints=@UniqueConstraint(columnNames={"student_id","route_id"}))
public class TransportRegistration {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private AppUser student;
 @ManyToOne(optional=false) private TransportRoute route;
 private String pickupStop;
 private String feeStatus="PENDING";
 private Instant createdAt=Instant.now();
 public TransportRegistration() {}
 public Long getId(){return id;} public AppUser getStudent(){return student;}
 public TransportRoute getRoute(){return route;} public String getPickupStop(){return pickupStop;}
 public String getFeeStatus(){return feeStatus;} public Instant getCreatedAt(){return createdAt;}
 public void setStudent(AppUser v){student=v;} public void setRoute(TransportRoute v){route=v;}
 public void setPickupStop(String v){pickupStop=v;} public void setFeeStatus(String v){feeStatus=v;}
}
