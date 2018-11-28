package sw_all;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main_Todolist {
		static Scanner scanner = new Scanner(System.in);
		public static int listIndex = 0;
		public static newTodolist [] todolists= new newTodolist[100];	
		
		static class newTodolist{
			private String createDate;
			private String dueDate;
			private String title;
			private String[] description;
			private String remainingTime;
			
			public newTodolist(String createDate, String dueDate, String title, String[] description, String remainingTime) {
				this.createDate = createDate;
				this.dueDate = dueDate;
				this.title = title;
				this.description = description;
				this.remainingTime = remainingTime;
			}
			
			private void showDescription() {
				for(int i=0; i<this.description.length; i++) {
					System.out.println(description[i]);
				}
			}
		}
		
		static void CreateTodolist() {
			String createDate = GetCurrentDate();
			
			System.out.println("생성할 todolist의 제목을 입력해주세요: ");
			String title = scanner.next();
			
			System.out.println("생성할 todolist의 내용을 입력해주세요\n(입력을 종료하고 싶으시면 내용의 맨 마지막 줄에 그만이라고 적고 enter를 눌러주세요)");
			String description[] = SaveDescription();
			
			System.out.println("생성할 todolist의 마감 날짜를 입력해주세요(년도 월 일 시 분): ");
			String dueDate = scanner.nextLine();
			
			String remainingTime = CalculateRemainingTime(createDate, dueDate);
			
			todolists[listIndex++]= new newTodolist(createDate, dueDate, title, description, remainingTime);
		}


		static String GetCurrentDate() {
			GregorianCalendar today = new GregorianCalendar();
			int year = today.get(today.YEAR);
			int month = today.get(today.MONTH) + 1;
			int day = today.get(today.DATE);
			int hour = today.get(today.HOUR_OF_DAY);
			int minute = today.get(today.MINUTE);
			String createDate = year + " " + month + " " + day + " " + hour + " " + minute;
			return createDate;
		}
		
		
		static String[] SaveDescription() {
			String lines[] = new String[100];
			String line="";
			int n=0;
			while(!(line = scanner.nextLine()).equals("그만")) 
				lines[n++]=line;
			
			return lines;
		}
		
		
		static String CalculateRemainingTime(String createDate, String dueDate) {
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
					if (remainDays != 0) {
						remainHours = (remain % remainDays) / 60*60*1000;
						if (remainHours != 0) {
							remainMinutes = (remainDays % remainHours) / 60*1000;
						}
					}	
				}	
				remainingTime = "마감까지 " + remainDays + "일 " + remainHours + "시간 " + remainMinutes + "분 남았습니다.";
			}
			catch(ParseException e) {
				System.out.println(e);
			}
			return remainingTime;
		}
		
		
		static void ViewTodolistsFull() {
			System.out.println("--------Todolist 전체 목록---------");
			for(int i=0; i<todolists.length; i++) {
				System.out.print("제목: ");
				System.out.println(todolists[i].title);
				System.out.print("내용: ");
				todolists[i].showDescription();
				System.out.print("\n생성 날짜: ");
				System.out.println(todolists[i].createDate);
				System.out.print("마감 날짜: ");
				System.out.println(todolists[i].dueDate);
				System.out.print("남은 기간: ");
				System.out.println(todolists[i].remainingTime);
				System.out.print("\n\n");
				}
			}
		
		
		static void ViewTodolistsTitle() {
			System.out.println("--------Todolists---------");
			for(int i=0; i<todolists.length; i++) {
				System.out.print("제목: ");
				System.out.println(todolists[i].title); }
		}
		
		
		static void ViewDetails(String title) {
			int searchResult = SearchTodolist(title);
			System.out.print("제목: ");
			System.out.println(todolists[searchResult].title);
			System.out.print("내용: ");
			todolists[searchResult].showDescription();
			System.out.print("\n생성 날짜: ");
			System.out.println(todolists[searchResult].createDate);
			System.out.print("마감 날짜: ");
			System.out.println(todolists[searchResult].dueDate);
			System.out.print("남은 기간: ");
			System.out.println(todolists[searchResult].remainingTime);
			System.out.print("-----------------------------");
		}
		
		static int SearchTodolist(String title) {
			if (todolists.length == 0) 
				return 0;
			else {
				for(int i=0; i<todolists.length; i++) 
					if (todolists[i].equals(title))
						return i;
				
				return -1;
			}
		}
		
		
		static void UpdateTodolist() {
			
		}
		
		
		static void DeleteTodolist() {
			
		}
		
		
		public static void main(String args[]) {
			int menuNum=0;
			
			while(menuNum!=5) {
				System.out.println("\n1번: Todolist 생성하기\n2번: Todolist 보기"
						+ "\n3번: Todolist 수정하기\n4번: Todolist 삭제하기\n5번: 메인 메뉴로 이동");
				System.out.print("원하시는 기능에 해당하는 숫자를 입력해주세요: ");
				menuNum = scanner.nextInt();
				
				switch(menuNum) {
				case 1:
					CreateTodolist();
					break;
				case 2:
					ViewTodolistsFull();
					break;
				case 3:
					UpdateTodolist();
					break;
				case 4:
					DeleteTodolist();
					break;
				case 5:
					System.out.println("Todolist 기능을 종료합니다.");
					break;
				}
			}
		}
	}
