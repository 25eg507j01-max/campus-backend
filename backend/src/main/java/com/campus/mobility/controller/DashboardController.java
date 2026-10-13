package com.campus.mobility.controller;
import com.campus.mobility.repo.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/dashboard")
public class DashboardController {
 private final BusRepository buses; private final RouteRepository routes; private final RegistrationRepository registrations; private final ComplaintRepository complaints;
 public DashboardController(BusRepository b,RouteRepository r,RegistrationRepository g,ComplaintRepository c){buses=b;routes=r;registrations=g;complaints=c;}
 @GetMapping("/summary") public Map<String,Object> summary(){
  return Map.of("totalBuses",buses.count(),"activeBuses",buses.countByStatusIgnoreCase("ACTIVE"),"totalRoutes",routes.count(),"registeredStudents",registrations.count(),"openComplaints",complaints.countByStatus("OPEN"));
 }
}
