package com.javaleraner.smartparking_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class SmartparkingSystemApplicationTests {

	@Test
	void contextLoads() {

		ApplicationModules modules = ApplicationModules.of(SmartparkingSystemApplication.class).verify();
		new Documenter(modules).writeDocumentation();
	}

}
