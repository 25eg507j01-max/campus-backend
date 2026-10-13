package com.campus.mobility.repo;
import com.campus.mobility.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ComplaintRepository extends JpaRepository<Complaint,Long> { long countByStatus(String status); }
