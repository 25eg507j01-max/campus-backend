package com.campus.mobility.controller;
import com.campus.mobility.model.*;
import com.campus.mobility.repo.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.Instant;
import java.util.*;
@RestController @RequestMapping("/api/tracking")
public class TrackingController {
 private final BusRepository buses; private final BusLocationRepository locations; private final RouteRepository routes;
 public TrackingController(BusRepository buses,BusLocationRepository locations,RouteRepository routes){this.buses=buses;this.locations=locations;this.routes=routes;}
 public record LocationInput(double latitude,double longitude,double speedKph){}
 @PostMapping("/{busId}") @ResponseStatus(HttpStatus.CREATED) public BusLocation update(@PathVariable Long busId,@RequestBody LocationInput x){
  if(x.latitude() < -90 || x.latitude()>90 || x.longitude() < -180 || x.longitude()>180) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid coordinates");
  Bus b=buses.findById(busId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Bus not found"));
  BusLocation l=new BusLocation();l.setBus(b);l.setLatitude(x.latitude());l.setLongitude(x.longitude());l.setSpeedKph(Math.max(0,x.speedKph()));return locations.save(l);
 }
 @GetMapping public List<Map<String,Object>> all(){
  List<Map<String,Object>> out=new ArrayList<>();
  for(Bus b:buses.findAll()) locations.findTopByBusIdOrderByRecordedAtDesc(b.getId()).ifPresent(l->{
   double km=routes.findAll().stream().filter(r->r.getBus()!=null&&r.getBus().getId().equals(b.getId())).mapToDouble(TransportRoute::getDistanceKm).findFirst().orElse(0);
   double speed=Math.max(10,l.getSpeedKph()); long eta=Math.round((km/speed)*60);
   out.add(Map.of("busId",b.getId(),"busNumber",b.getBusNumber(),"latitude",l.getLatitude(),"longitude",l.getLongitude(),"speedKph",l.getSpeedKph(),"recordedAt",l.getRecordedAt().toString(),"estimatedEtaMinutes",eta));
  });
  return out;
 }
}
