package com.javaleraner.smartparking_system.entry;

import com.javaleraner.smartparking_system.event.VehicleEnteredEvent;
import com.javaleraner.smartparking_system.event.VehicleExitedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExitService {

    private ParkingEntryRepository repository;
    private ApplicationEventPublisher publisher;

    public ExitService(ParkingEntryRepository repository, ApplicationEventPublisher publisher){
        this.repository = repository;
        this.publisher = publisher;
    }



    public void vehicleExit(String vehicleNumber){
        //get vehicle details from DB
        //update exit time
        //save to db
        //publish vehicle exit event
        ParkingEntry entry = repository.findByVehicleNumberAndActiveTrue(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("No Active entry found for vehicle: " + vehicleNumber));
        entry.setExitTime(LocalDateTime.now());
        entry.setActive(false);
        repository.save(entry);
        publisher.publishEvent(new VehicleExitedEvent(vehicleNumber, entry.getEntryTime(), entry.getExitTime()));

    }
}
