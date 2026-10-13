package com.campus.mobility.controller;
import com.campus.mobility.model.*;
import com.campus.mobility.repo.ComplaintRepository;
import com.campus.mobility.service.CurrentUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/complaints")
public class ComplaintController {
 private final ComplaintRepository repo; private final CurrentUserService current;
 public ComplaintController(ComplaintRepository repo,CurrentUserService current){this.repo=repo;this.current=current;}
 public record Input(String busNumber,@NotBlank String category,@NotBlank String description){}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Complaint create(@Valid @RequestBody Input x){Complaint c=new Complaint();c.setSubmittedBy(current.get());c.setBusNumber(x.busNumber());c.setCategory(x.category());c.setDescription(x.description());return repo.save(c);}
 @GetMapping public List<Complaint> all(){return repo.findAll();}
 @PatchMapping("/{id}/status") public Complaint status(@PathVariable Long id,@RequestBody StatusInput x){
  if(!List.of("OPEN","IN_PROGRESS","RESOLVED").contains(x.status())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid status");
  Complaint c=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Complaint not found"));c.setStatus(x.status());return repo.save(c);
 }
 public record StatusInput(String status){}
}
