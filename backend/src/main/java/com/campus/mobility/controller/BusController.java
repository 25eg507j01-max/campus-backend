package com.campus.mobility.controller;
import com.campus.mobility.model.Bus;
import com.campus.mobility.repo.BusRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/buses")
public class BusController {
 private final BusRepository repo;
 public BusController(BusRepository repo){this.repo=repo;}
 public record BusInput(@NotBlank String busNumber,String registrationNumber,@Min(1) int capacity,String status,String driverName,String driverPhone){}
 @GetMapping public List<Bus> all(){return repo.findAll();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Bus create(@Valid @RequestBody BusInput x){
  Bus b=new Bus(); apply(b,x); return repo.save(b);
 }
 @PutMapping("/{id}") public Bus update(@PathVariable Long id,@Valid @RequestBody BusInput x){
  Bus b=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Bus not found")); apply(b,x); return repo.save(b);
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){repo.deleteById(id);}
 private void apply(Bus b,BusInput x){b.setBusNumber(x.busNumber());b.setRegistrationNumber(x.registrationNumber());b.setCapacity(x.capacity());b.setStatus(x.status()==null?"ACTIVE":x.status());b.setDriverName(x.driverName());b.setDriverPhone(x.driverPhone());}
}
