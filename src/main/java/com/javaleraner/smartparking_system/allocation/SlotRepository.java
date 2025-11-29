package com.javaleraner.smartparking_system.allocation;

import com.javaleraner.smartparking_system.event.VehicleExitedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    Optional<Slot> findFirstByAvailableTrue();

    Optional<Slot> findByVehicleNumber(String vehicleNumber);
}
