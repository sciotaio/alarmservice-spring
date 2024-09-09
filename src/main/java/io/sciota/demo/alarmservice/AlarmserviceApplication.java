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
import io.sciota.demo.alarmservice.persistence.Schedule;
import io.sciota.demo.alarmservice.persistence.ScheduleRepository;

@SpringBootApplication
public class AlarmserviceApplication {

	private static final Logger log = LoggerFactory.getLogger(AlarmserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlarmserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner init_db_data(RoomRepository roomRepository, ScheduleRepository scheduleRepository) {
		return (args) -> {
			// ADD ROOM(S)
			log.info("### Inserting room(s) ...");
			IntStream.range(0, 100).forEach((val) -> {
				roomRepository.save(new Room(String.format("room_%4s", ("" + val)).replace(" ", "0")));
			});
			log.info("  --> done.");

			// ADD SCHEDULE(S)
			log.info("### Inserting schedule(s) ...");
			var room_ref = roomRepository.findById(1);
			var sched = new Schedule();
			sched.setBeginMinsOfDay(0); // 00:00
			sched.setEndMinsOfDay(1439); // 23:59
			sched.setRoom(room_ref); // room_0000
			scheduleRepository.save(sched);
			log.info("  --> done.");
		};
	}

}
