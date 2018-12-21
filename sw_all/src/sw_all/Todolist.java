package sw_all;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Todolist {
	static Scanner scanner = new Scanner(System.in);
	public static int listIndex = 1;
	public static int searchResult=0;
	//public static boolean titleOverlap=true;
	public static newTodolist [] todolists= new newTodolist[100];	
	static class newTodolist{
		private String createDate;
		private String dueDate;
		private String title;
		private List<Object> descriptions;
		private String remainingTime;
		
		public newTodolist(String createDate, String dueDate, String title, List<Object> descriptions, String remainingTime) {
			this.createDate = createDate;
			this.dueDate = dueDate;
			this.title = title;
			this.descriptions = descriptions;
			this.remainingTime = remainingTime;
		}
		
		private void showDescription() {
			for(int i=0; i<this.descriptions.size(); i++) {
				System.out.println(descriptions.get(i));
			}
		}
		
	}
	
	
	static int menuNum;
	public static void main(String args[]) {
		menuNum=0;
		
		while(menuNum!=5) {
			System.out.println("\n1번: Todolist 생성하기\n2번: Todolist 보기"	+ "\n3번: Todolist 수정하기\n4번: Todolist 삭제하기\n5번: 메인 메뉴로 이동");
			System.out.print("원하시는 기능에 해당하는 숫자를 입력해주세요: ");
			menuNum = scanner.nextInt();
			
			switch(menuNum) {
			case 1:
				createTodolist();
				break;
			case 2:
				viewTodolistsFull();
				break;
			case 3:
				updateTodolist();
				break;
			case 4:
				deleteTodolist();
				break;
			case 5:
				back();
				break;
			}
		}
	}
	
	
	static void createTodolist() {
		String createDate = getCurrentDate();
		System.out.println("생성할 todolist의 제목을 입력해주세요.");
		String title = saveandcheckTitleOverlap();
		System.out.println("생성할 todolist의 내용을 입력해주세요.\n(입력을 종료하고 싶으시면 내용의 맨 마지막 줄에 그만이라고 적고 enter 키를 눌러주세요)");
		List<Object> descriptions = saveDescriptions();
		System.out.println("생성할 todolist의 마감 날짜를 입력해주세요.\n(yyyy MM dd hh mm 형식으로): ");
		String dueDate = scanner.nextLine();
		String remainingTime = calculateRemainingTime(createDate, dueDate);
		
		todolists[listIndex++]= new newTodolist(createDate, dueDate, title, descriptions, remainingTime);
	}
	

	static String getCurrentDate() {
		GregorianCalendar today = new GregorianCalendar();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int day = today.get(Calendar.DATE);
		int hour = today.get(Calendar.HOUR_OF_DAY);
		int minute = today.get(Calendar.MINUTE);
		String createDate = year + " " + month + " " + day + " " + hour + " " + minute;
		return createDate;
	}
	
	
	static List<Object> saveDescriptions() {
		List<Object> lines = new ArrayList<Object>();
		Object line;
		while(!(line = scanner.nextLine()).equals("그만")) 
			lines.add(line);
		return lines;
	}
	
	
	static String calculateRemainingTime(String createDate, String dueDate) {
		long remainDays = 0;
		long remainHours = 0;
		long remainMinutes = 0;
		String remainingTime="";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd hh mm");
			Date create = format.parse(createDate);
			Date due = format.parse(dueDate);
			long remain = due.getTime() - create.getTime();
			if (remain != 0) {
				remainDays = remain / (24*60*60*1000);
				remainHours = (remain - remainDays*24*60*60*1000) / (60*60*1000);
				remainMinutes = (remain - remainDays*24*60*60*1000 - remainHours*60*60*1000) / (60*1000);	
			}
			remainingTime = "마감까지 " + remainDays + "일 " + remainHours + "시간 " + remainMinutes + "분 남았습니다.";
			}
		catch(ParseException e) { System.out.println(e);  }
		return remainingTime;
	}

	
	static String saveandcheckTitleOverlap() {
		String title = scanner.next();
		if(listIndex>1) {
			boolean titleOverlap=checkTitleOverlap(title);
			while(titleOverlap) {
				title=scanner.next();
				titleOverlap=checkTitleOverlap(title);
			}
		}
		return title;
	}
	
	 static boolean checkTitleOverlap(String title) {
		searchTodolist(title);
		if (searchResult < 1) {
			return false;
		}
		else {
			System.out.println("지금 입력하신 title은 이미 있는 title입니다. 다시 입력해주세요.");
			return true;
		}
	}
	
	static void viewTodolistsFull() {
		if (listIndex==1)
			System.out.println("현재 생성된 todolist가 없습니다. todolist를 먼저 생성해주세요");
		else {
			System.out.println("=========Todolist 전체 목록=========");
			for(int index=1; index<listIndex; index++) {
				viewTodolistDetails(index);
				System.out.print("\n");
				}
		}
	}
	
	
	static void viewTodolistsTitle() {
		System.out.println("=========Todolists=========");
		for(int i=1; i<listIndex; i++) {
			System.out.print("제목: ");
			System.out.println(todolists[i].title); }
		System.out.println("===========================");
	}
	
	
	static void viewTodolistDetails(int index) {
		System.out.print("제목: ");
		System.out.println(todolists[index].title);
		System.out.print("내용: ");
		todolists[index].showDescription();
		System.out.print("생성 날짜: ");
		System.out.println(todolists[index].createDate);
		System.out.print("마감 날짜: ");
		System.out.println(todolists[index].dueDate);
		System.out.print("남은 기간: ");
		System.out.println(todolists[index].remainingTime);
		System.out.println("----------------------------------------------------------");
	}

	
	static void updateTodolist() {
		if (listIndex==1) {
			System.out.println("현재 생성된 todolist가 없습니다. todolist를 먼저 생성해주세요");
		}
		else {
			viewTodolistsTitle();
			System.out.print("위의 목록에서 수정하고 싶은 todolist의 제목을 입력해주세요 : ");
			scanner.nextLine();
			String title = scanner.nextLine();
			searchTodolist(title);
			if (searchResult<=0) {
				System.out.println("입력하신 제목에 해당하는 todolist가 없습니다. 수정을 종료합니다.");
				return;
			}
			viewTodolistDetails(searchResult);
			System.out.print("수정하고 싶은 항목을 입력해주세요 : ");
			String DetailToUpdate = scanner.nextLine();
			dealUpdateDetails(searchResult, DetailToUpdate);
		}
	}
	
	
	static void searchTodolist(String title) {
		for(int i=1; i<listIndex; i++) {
			if (todolists[i].title.equals(title)) {
				searchResult=i;
				return;
				}
		}
		searchResult=-1;
	}
	
	
	static void dealUpdateDetails(int searchResult, String DetailToUpdate) {
		if(DetailToUpdate.equals("제목")) {
			System.out.print("수정하고 싶은 내용을 입력해주세요 : ");
			todolists[searchResult].title = scanner.nextLine();
			}
		else if(DetailToUpdate.equals("내용")) {
			System.out.print("수정하고 싶은 내용을 입력해주세요 : ");
			System.out.println("(입력을 종료하고 싶으시면 내용의 맨 마지막 줄에 그만이라고 적고 enter 키를 눌러주세요)");
			todolists[searchResult].descriptions = saveDescriptions();
		}
		else if(DetailToUpdate.equals("생성 날짜")) {
			System.out.print("수정하고 싶은 내용을 입력해주세요 : ");
			System.out.println("\n(yyyy MM dd hh mm 형식으로 입력해주세요)");
			todolists[searchResult].createDate = scanner.nextLine();
			todolists[searchResult].remainingTime = calculateRemainingTime(todolists[searchResult].createDate, todolists[searchResult].dueDate);
		}
		else if(DetailToUpdate.equals("마감 날짜")) {
			System.out.print("수정하고 싶은 내용을 입력해주세요 : ");
			System.out.println("\n(yyyy MM dd hh mm 형식으로 입력해주세요)");
			todolists[searchResult].dueDate = scanner.nextLine();
			todolists[searchResult].remainingTime = calculateRemainingTime(todolists[searchResult].createDate, todolists[searchResult].dueDate);
		}
		else {
			System.out.println("잘못 입력하셨습니다. 수정을 종료합니다.");
			return;
		}
		System.out.println("수정이 완료되었습니다.");
	}
	

	static void deleteTodolist() {
		if (listIndex==1) 
			System.out.println("현재 생성된 todolist가 없습니다. todolist를 먼저 생성해주세요");
		
		else {
			viewTodolistsTitle();
			System.out.print("위의 목록에서 삭제하고 싶은 todolist의 제목을 입력해주세요 : ");
			scanner.nextLine();
			String title = scanner.nextLine();
			searchTodolist(title);
			if (searchResult==-1)
				return;
			viewTodolistDetails(searchResult);
			System.out.println("이 todolist를 정말로 삭제하시겠습니까? (y/n)");
			String answer = scanner.nextLine();
			if(answer.equals("y")) {
				for(int i=searchResult; i<listIndex; i++) {
					todolists[i]=todolists[i+1];
				}
				listIndex--;
			}
			else {
				System.out.println("삭제 작업을 취소하고 Todolist기능의 메인으로 돌아갑니다.");
				return;
				}
		}
	}
	
	
	static void back() {
		System.out.println("정말로 Todolist 기능을 종료하시겠습니까? (y/n)");
		String answer = scanner.next();
		if (answer.equals("y"))
			System.out.println("Todolist 기능을 종료합니다.");
		else {
			System.out.println("Todolist의 메인으로 돌아갑니다.");
			menuNum=0;
		}
	}
}
