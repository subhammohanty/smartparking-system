package com.javaleraner.smartparking_system.entry;

import com.javaleraner.smartparking_system.event.VehicleEnteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntryService {

    private ParkingEntryRepository repository;
    private ApplicationEventPublisher publisher;

    public EntryService(ParkingEntryRepository repository, ApplicationEventPublisher publisher){
        this.repository = repository;
        this.publisher = publisher;
    }

    // save the entry to DB
    //allocate parking slot
    //send notification

    public void vehicleEntry(String vehicleNumber){
        ParkingEntry parkingEntry = new ParkingEntry(null, vehicleNumber, LocalDateTime.now(), null, true);
        repository.save(parkingEntry);
        //publish an event
        publisher.publishEvent(new VehicleEnteredEvent(vehicleNumber, parkingEntry.getEntryTime()));
    }
}
