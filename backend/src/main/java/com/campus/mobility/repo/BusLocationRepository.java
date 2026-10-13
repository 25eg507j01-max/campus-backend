package com.campus.mobility.repo;
import com.campus.mobility.model.BusLocation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BusLocationRepository extends JpaRepository<BusLocation,Long> {
 Optional<BusLocation> findTopByBusIdOrderByRecordedAtDesc(Long busId);
}
