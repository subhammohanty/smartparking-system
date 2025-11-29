package com.javaleraner.smartparking_system.notification;

import com.javaleraner.smartparking_system.event.VehicleEnteredEvent;
import com.javaleraner.smartparking_system.event.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @EventListener
    public void notifyOnVehicleEntry(VehicleEnteredEvent event){
        System.out.println("Notification: Vehicle "  +event.vehicleNumber() +
                "entered at "+ event.entryTime()+ " Welcome !");
    }

    @EventListener
    public void notifyOnVehicleExit(VehicleExitedEvent event){
        System.out.println("Notification: Vehicle "  +event.vehicleNumber() +
                "exited at "+ event.entryTime()+ " Thank You !");
    }
}
