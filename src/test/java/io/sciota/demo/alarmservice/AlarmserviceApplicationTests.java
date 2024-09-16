package io.sciota.demo.alarmservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.sciota.demo.alarmservice.api.AlarmController;
import jakarta.annotation.Resource;

@SpringBootTest
class AlarmserviceApplicationTests {

	@Resource
	AlarmController ac;

	@Test
	void contextLoads() {
		var rooms = ac.getRooms();
		assertEquals(rooms.size(), 100, "Should find 100 rooms");
	}

}
