package com.campus.mobility.repo;
import com.campus.mobility.model.TransportRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RegistrationRepository extends JpaRepository<TransportRegistration,Long> {
 long countByRouteId(Long routeId);
 boolean existsByStudentIdAndRouteId(Long studentId, Long routeId);
}
