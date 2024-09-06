package io.sciota.demo.alarmservice;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.sciota.demo.alarmservice.persistence.Room;
import io.sciota.demo.alarmservice.persistence.RoomRepository;

@SpringBootApplication
public class AlarmserviceApplication {

	private static final Logger log = LoggerFactory.getLogger(AlarmserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlarmserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertRooms(RoomRepository repository) {
		return (args) -> {
			log.info("### Inserting rooms ...");
			IntStream.range(0, 100).forEach((val) -> {
				repository.save(new Room(String.format("room_%4s", ("" + val)).replace(" ", "0")));
			});
			log.info("  --> done.");
		};
	}

	public static String LPad(String str, Integer length, char car) {
		return (str + String.format("%" + length + "s", "").replace(" ", String.valueOf(car))).substring(0, length);
	}
}
