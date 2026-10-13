package com.campus.mobility.repo;
import com.campus.mobility.model.TransportRoute;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RouteRepository extends JpaRepository<TransportRoute,Long> {}
