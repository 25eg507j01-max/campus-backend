package com.campus.mobility.controller;
import com.campus.mobility.model.*;
import com.campus.mobility.repo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/routes")
public class RouteController {
 private final RouteRepository routes; private final BusRepository buses;
 public RouteController(RouteRepository routes,BusRepository buses){this.routes=routes;this.buses=buses;}
 public record RouteInput(@NotBlank String name,@NotBlank String startPoint,@NotBlank String endPoint,double distanceKm,double feeAmount,Long busId){}
 @GetMapping public List<TransportRoute> all(){return routes.findAll();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public TransportRoute create(@Valid @RequestBody RouteInput x){TransportRoute r=new TransportRoute();apply(r,x);return routes.save(r);}
 @PutMapping("/{id}") public TransportRoute update(@PathVariable Long id,@Valid @RequestBody RouteInput x){
  TransportRoute r=routes.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Route not found"));apply(r,x);return routes.save(r);
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){routes.deleteById(id);}
 private void apply(TransportRoute r,RouteInput x){r.setName(x.name());r.setStartPoint(x.startPoint());r.setEndPoint(x.endPoint());r.setDistanceKm(Math.max(0,x.distanceKm()));r.setFeeAmount(Math.max(0,x.feeAmount()));r.setBus(x.busId()==null?null:buses.findById(x.busId()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bus not found")));}
}

