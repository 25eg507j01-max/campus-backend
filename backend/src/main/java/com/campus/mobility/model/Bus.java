package com.campus.mobility.model;
import jakarta.persistence.*;
@Entity
public class Bus {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true) private String busNumber;
 private String registrationNumber;
 private int capacity=50;
 private String status="ACTIVE";
 private String driverName;
 private String driverPhone;
 public Bus() {}
 public Long getId(){return id;} public String getBusNumber(){return busNumber;}
 public String getRegistrationNumber(){return registrationNumber;} public int getCapacity(){return capacity;}
 public String getStatus(){return status;} public String getDriverName(){return driverName;}
 public String getDriverPhone(){return driverPhone;}
 public void setBusNumber(String v){busNumber=v;} public void setRegistrationNumber(String v){registrationNumber=v;}
 public void setCapacity(int v){capacity=v;} public void setStatus(String v){status=v;}
 public void setDriverName(String v){driverName=v;} public void setDriverPhone(String v){driverPhone=v;}
}
