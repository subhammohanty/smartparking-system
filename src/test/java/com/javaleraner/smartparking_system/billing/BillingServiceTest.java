package com.javaleraner.smartparking_system.billing;

import com.javaleraner.smartparking_system.event.VehicleExitedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationModuleTest
@DirtiesContext
public class BillingServiceTest {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private BillingRecordRepository billingRecordRepository;

    @Test
    void shouldGenerateBillingRecord(){

        VehicleExitedEvent event = new VehicleExitedEvent("OD-05-AA-1234", LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        applicationEventPublisher.publishEvent(event);

        List<BillingRecord> billingRecords = billingRecordRepository.findAll();
    }
}
