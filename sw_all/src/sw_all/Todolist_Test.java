package sw_all;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sw_all.Main_Todolist.newTodolist;

class TodolistTest{
	static String createDate;
	static String dueDate;
	static String title;
	static String remainingTime;
	static List<Object> descriptions;
	static int listIndex=1;
	
	@BeforeAll
	static void init() {
		createDate="2018 12 22 15 30";
		dueDate="2018 12 30 12 30";
		title="hi";
		remainingTime=Main_Todolist.calculateRemainingTime(createDate, dueDate);
		descriptions.add("hello\n");
		
		Main_Todolist.todolists[listIndex++]= new newTodolist(createDate, dueDate, title, descriptions, remainingTime);
	}
	
	@Test
	void testCalRemainingTime(){
		assertTrue(Main_Todolist.calculateRemainingTime(createDate, dueDate) == "마감까지 7일 9시간 0분 남았습니다.");
	}
	
	@Test
	void testsaveandCheckTitleOverlap() {
		assertTrue(Main_Todolist.checkTitleOverlap("hi") == true);
	}
}