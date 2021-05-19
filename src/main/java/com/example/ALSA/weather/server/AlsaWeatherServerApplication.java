package com.example.ALSA.weather.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
public class AlsaWeatherServerApplication {

	public static void main(String[] args) {
		System.out.println("Server Started");
		SpringApplication.run(AlsaWeatherServerApplication.class, args);
	}

}
