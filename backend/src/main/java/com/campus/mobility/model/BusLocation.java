package com.campus.mobility.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
public class BusLocation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Bus bus;
 private double latitude;
 private double longitude;
 private double speedKph;
 private Instant recordedAt=Instant.now();
 public BusLocation() {}
 public Long getId(){return id;} public Bus getBus(){return bus;}
 public double getLatitude(){return latitude;} public double getLongitude(){return longitude;}
 public double getSpeedKph(){return speedKph;} public Instant getRecordedAt(){return recordedAt;}
 public void setBus(Bus v){bus=v;} public void setLatitude(double v){latitude=v;}
 public void setLongitude(double v){longitude=v;} public void setSpeedKph(double v){speedKph=v;}
}
