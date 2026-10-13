package com.campus.mobility.model;
import jakarta.persistence.*;
@Entity
@Table(name="transport_routes")
public class TransportRoute {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(nullable=false) private String startPoint;
 @Column(nullable=false) private String endPoint;
 private double distanceKm;
 private double feeAmount;
 @ManyToOne(fetch=FetchType.EAGER) private Bus bus;
 public TransportRoute() {}
 public Long getId(){return id;} public String getName(){return name;}
 public String getStartPoint(){return startPoint;} public String getEndPoint(){return endPoint;}
 public double getDistanceKm(){return distanceKm;} public double getFeeAmount(){return feeAmount;}
 public Bus getBus(){return bus;}
 public void setName(String v){name=v;} public void setStartPoint(String v){startPoint=v;}
 public void setEndPoint(String v){endPoint=v;} public void setDistanceKm(double v){distanceKm=v;}
 public void setFeeAmount(double v){feeAmount=v;} public void setBus(Bus v){bus=v;}
}
