package com.javaleraner.smartparking_system.allocation;

import com.javaleraner.smartparking_system.event.VehicleEnteredEvent;
import com.javaleraner.smartparking_system.event.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SlotAllocationService {

    private final SlotRepository slotRepository;

    public SlotAllocationService(SlotRepository slotRepository){
        this.slotRepository = slotRepository;
    }

    @EventListener
    public void handleVehicleEntry(VehicleEnteredEvent event){
        //find the available slot to allocate
        Slot slot = slotRepository.findFirstByAvailableTrue().
                orElseThrow(() -> new RuntimeException("No Slots Found !"));
        slot.setAvailable(false);
        slot.setVehicleNumber(event.vehicleNumber());
        slotRepository.save(slot);
        System.out.println("Allocated Slot : " + slot.getSlotCode()+" to vehicle " +slot.getVehicleNumber());
    }

    @EventListener
    public void handleVehicleExit(VehicleExitedEvent event) {
        slotRepository.findByVehicleNumber(event.vehicleNumber())
                .ifPresentOrElse(slot -> {
                            slot.setAvailable(true);
                            slot.setVehicleNumber(null);
                            slotRepository.save(slot);
                    System.out.println("Freed Slot : "+slot.getSlotCode());
                        }, () -> {
                            throw new RuntimeException("No Slot Found For Vehicle " + event.vehicleNumber());
                        }
                );
    }
}
