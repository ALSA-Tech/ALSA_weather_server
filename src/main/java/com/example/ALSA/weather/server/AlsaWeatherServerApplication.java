package com.example.ALSA.weather.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
public class AlsaWeatherServerApplication {

	@Value("my.test")
	private static String msg;

	public static void main(String[] args) {
		System.out.println("Server Started");
		System.out.println(msg);
		SpringApplication.run(AlsaWeatherServerApplication.class, args);

	}

}
