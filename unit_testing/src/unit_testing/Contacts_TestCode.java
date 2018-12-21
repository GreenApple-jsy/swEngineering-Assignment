package unit_testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Contacts_TestCode {

	@Test
	void CreateTest() {
		assertEquals(1,Main_Contacts.create("Title","119","naver@.com"));
	}

	@Test
	void SearchTest() {
		Main_Contacts.create("Test1", "010","naver");
		Main_Contacts.create("Test2", "018","kakao");
		Main_Contacts.create("Test3", "011","daum");
		assertEquals(2,Main_Contacts.search("Test2"));
		assertEquals(3,Main_Contacts.search("Test3"));
	}
}
