package sw_all;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Appointment_Test {

	@Test
	void CreatePush_test() {
		Main_Appointment.number = 0;
		assertEquals(1,Main_Appointment.createPush("테스트 제목",2018,01,02,03,04,"사람","학교"));
		assertEquals(2,Main_Appointment.createPush("테스트 제목2",2018,01,05,03,11,"강아지","공원"));
	}
	
	@Test
	void DeleteOut_test() {
		Main_Appointment.number = 0;
		Main_Appointment.delete_count = 0;
		Main_Appointment.last_delete = -1;
		Main_Appointment.createPush("Please_Delete", 2018, 05, 07, 04, 52, "freind", "park");
		Main_Appointment.createPush("Please_Delete2", 2018, 03, 01, 02, 11, "human", "school");
		
		assertEquals(-1,Main_Appointment.deleteOut("Don't have this appointment"));
		assertEquals(1,Main_Appointment.deleteOut("Please_Delete2"));
		assertEquals(0,Main_Appointment.deleteOut("Please_Delete"));
	}

}
