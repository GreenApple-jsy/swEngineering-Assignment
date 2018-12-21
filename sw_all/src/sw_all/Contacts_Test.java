package sw_all;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Contacts_Test {

	@Test
	void CreateGoTest() {
		Main_Contacts.index = 0;
		assertEquals(1,Main_Contacts.createGo("Title","119","naver@.com"));
		assertEquals(2,Main_Contacts.createGo("Title2","118","naver@.com"));
	}

	@Test
	void SearchTest() {
		Main_Contacts.index = 0;
		Main_Contacts.createGo("Test1", "010","naver");
		Main_Contacts.createGo("Test2", "018","kakao");
		Main_Contacts.createGo("Test3", "011","daum");
		assertEquals(1,Main_Contacts.search("Test2"));
		assertEquals(2,Main_Contacts.search("Test3"));
	}
}
