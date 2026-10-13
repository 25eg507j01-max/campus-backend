package com.campus.mobility.config;
import com.campus.mobility.model.*;
import com.campus.mobility.repo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DemoDataConfig {
 @Bean CommandLineRunner seed(UserRepository users,BusRepository buses,RouteRepository routes,PasswordEncoder encoder,
   @Value("${app.seed-demo-data:true}") boolean enabled) {
  return args -> {
   if(!enabled) return;
   if(users.findByEmailIgnoreCase("admin@campus.local").isEmpty())
    users.save(new AppUser("admin@campus.local",encoder.encode("ChangeMe123!"),"Campus Administrator",Role.ADMIN));
   if(buses.count()==0) {
    Bus b=new Bus();b.setBusNumber("BUS-21");b.setRegistrationNumber("TS09AB1234");b.setCapacity(50);b.setStatus("ACTIVE");b.setDriverName("Ravi Kumar");buses.save(b);
    Bus b2=new Bus();b2.setBusNumber("BUS-17");b2.setRegistrationNumber("TS09CD5678");b2.setCapacity(40);b2.setStatus("ACTIVE");b2.setDriverName("Suresh");buses.save(b2);
    TransportRoute r=new TransportRoute();r.setName("Route 5 - Main Road");r.setStartPoint("Wanaparthy Bus Stand");r.setEndPoint("College Campus");r.setDistanceKm(12);r.setFeeAmount(18000);r.setBus(b);routes.save(r);
    TransportRoute r2=new TransportRoute();r2.setName("Route 2 - North");r2.setStartPoint("North Colony");r2.setEndPoint("College Campus");r2.setDistanceKm(8);r2.setFeeAmount(15000);r2.setBus(b2);routes.save(r2);
   }
  };
 }
}
