package br.com.microservice.greeting.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.greeting.configuration.GreetingConfiguration;
import br.com.microservice.greeting.model.Greeting;

@RestController
public class GreetingController {

	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private GreetingConfiguration configuration;

	@RequestMapping("/greeting")
	public Greeting greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {

		if (name.isEmpty())
			name = configuration.getDefaultValue();

		return new Greeting(
				counter.incrementAndGet(),
				String.format(template, configuration.getGreeting(), name));
	}

	
}
