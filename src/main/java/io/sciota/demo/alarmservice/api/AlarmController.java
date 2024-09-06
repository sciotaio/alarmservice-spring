package io.sciota.demo.alarmservice.api;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmController {

	private final AtomicLong counter = new AtomicLong();

	// @GetMapping("/greeting")
	// public Greeting greeting(@RequestParam(value = "name", defaultValue =
	// "World") String name) {
	// return new Greeting(counter.incrementAndGet(), String.format(template,
	// name));
	// }
	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Ok #" + counter.getAndIncrement() + " from '" + name + "'";
	}
}