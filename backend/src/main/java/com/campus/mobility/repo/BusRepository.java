package com.campus.mobility.repo;
import com.campus.mobility.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BusRepository extends JpaRepository<Bus,Long> { long countByStatusIgnoreCase(String status); }
