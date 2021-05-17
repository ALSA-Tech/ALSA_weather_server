package com.example.ALSA.weather.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AlsaWeatherServerApplicationTests {

	@Test
	void alwaysPassTest() {
		String message = "Hello there!";
		assertNotNull(message);
	}

}
