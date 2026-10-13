package com.campus.mobility.controller;
import com.campus.mobility.model.*;
import com.campus.mobility.repo.*;
import com.campus.mobility.service.CurrentUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/registrations")
public class RegistrationController {
 private final RegistrationRepository regs; private final RouteRepository routes; private final BusRepository buses; private final CurrentUserService current;
 public RegistrationController(RegistrationRepository regs,RouteRepository routes,BusRepository buses,CurrentUserService current){this.regs=regs;this.routes=routes;this.buses=buses;this.current=current;}
 public record Input(Long routeId,String pickupStop){}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public TransportRegistration register(@RequestBody Input x){
  AppUser student=current.get(); TransportRoute route=routes.findById(x.routeId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Route not found"));
  if(regs.existsByStudentIdAndRouteId(student.getId(),route.getId())) throw new ResponseStatusException(HttpStatus.CONFLICT,"Already registered for this route");
  Bus bus=route.getBus(); if(bus!=null && regs.countByRouteId(route.getId())>=bus.getCapacity()) throw new ResponseStatusException(HttpStatus.CONFLICT,"This bus route is full");
  TransportRegistration r=new TransportRegistration();r.setStudent(student);r.setRoute(route);r.setPickupStop(x.pickupStop());return regs.save(r);
 }
 @GetMapping public List<TransportRegistration> all(){return regs.findAll();}
}
