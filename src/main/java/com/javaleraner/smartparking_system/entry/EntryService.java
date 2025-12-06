package com.javaleraner.smartparking_system.entry;

import com.javaleraner.smartparking_system.allocation.SlotAllocationService;
import com.javaleraner.smartparking_system.event.VehicleEnteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntryService {

    private ParkingEntryRepository repository;
    private ApplicationEventPublisher publisher;
    private SlotAllocationService slotAllocationService;

    public EntryService(ParkingEntryRepository repository, ApplicationEventPublisher publisher, SlotAllocationService slotAllocationService){
        this.repository = repository;
        this.publisher = publisher;
        this.slotAllocationService = slotAllocationService;
    }

    // save the entry to DB
    //allocate parking slot
    //send notification

    public void vehicleEntry(String vehicleNumber){
        if (slotAllocationService.getAvailableSlot() == null){
            throw new RuntimeException("No Slots Available Slot");
        }
        ParkingEntry parkingEntry = new ParkingEntry(null, vehicleNumber, LocalDateTime.now(), null, true);
        repository.save(parkingEntry);
        //publish an event
        publisher.publishEvent(new VehicleEnteredEvent(vehicleNumber, parkingEntry.getEntryTime()));
    }
}
