package sw_all;

import java.util.Scanner;

public class Main_Appointment {
   static Scanner scanner = new Scanner(System.in);
   static class appointment{
      String title;
      int date[] = new int[5];
      String persons;
      String location;
      public appointment() {
      }
   }
   
   static int number = 0;
   static int delete_count = 0;
   static int last_delete;
   static appointment ap[] = new appointment[100];
   
   public static void main(String[] args) {
      int user_choice = 0;
         while (user_choice != 5 ) {
            System.out.println("\nAppointment 옵션 메뉴입니다.\n1번 : Create\n2번 : View\n3번 : Update\n4번 : Delete\n5번 : 메인 메뉴로 이동");
            System.out.print("원하시는 기능을 숫자로 입력해주세요 : ");
            user_choice = scanner.nextInt();
            
            switch(user_choice) {
            case 1:
               create();
               break;
            case 2:
               view();
               break;
            case 3:
               update();
               break;
            case 4:
               delete();
               break;
            case 5:
               System.out.println("Appointment 기능을 종료합니다.");
               break;
            default:
               System.out.print("잘못된 숫자를 입력하셨습니다. 다시 입력하세요.");
               break;
            }
         }
   }
   
   public static void create() {
      int title_check = 0;
      System.out.print("Appointment의 Title을 입력하세요(옵션 메인으로 돌아가려면 back을 입력하세요): ");
      scanner.nextLine();
      String title = scanner.nextLine();
      if(!(title.equals("back"))) {
    	  for( title_check = 0; title_check < number; title_check++) {
    		  if (ap[title_check].title.equals(title)) {
    			  System.out.println("이미 있는 title입니다. 다시 Create 메뉴를 통해 입력하세요");
    			  break;
    		  }
    	  }
    	  if(title_check == number) {
    	      System.out.print("Appointment의 Date를 입력하세요(2019년 01월 23일 17시 23분의 경우 2019 01 23 17 23와 같이) : ");
    	      int year = scanner.nextInt();
    	      int month = scanner.nextInt();
    	      int date = scanner.nextInt();
    	      int hour = scanner.nextInt();
    	      int minute = scanner.nextInt();
    	      System.out.print("Appointment를 누구와 함께하는지 입력하세요 : ");
    	      scanner.nextLine();
    	      String persons = scanner.nextLine();
    	      System.out.print("Appointment의 장소을 입력하세요 : ");
    	      String  location = scanner.nextLine();
    	      createPush(title,year,month,date,hour,minute,persons,location);
    	  }
      }   
   }
   
   public static void view() {
      if (number == 0)
         System.out.println("Appointment가 존재하지 않습니다.");
      else {
         System.out.println("\nAppointment의 리스트 입니다.");
         for(int i = 0;  i < number; i++) {
            System.out.println(i+1 + "번 : " + ap[i].title);
            System.out.println("Date : "+ap[i].date[0] + "년 "+ap[i].date[1] + "월 "+ap[i].date[2] + "일 "+ap[i].date[3] + "시 " +ap[i].date[4] + "분");
            System.out.println("persons : " + ap[i].persons);
            System.out.println("location : " + ap[i].location);
            System.out.println("\n");
         }
      }
   }

   public static void update() {
      int count = -1;
      int answer = 0;
      scanner.nextLine();
      while(count == -1) {
         System.out.print("\n수정하고 싶은 Appointment의 title을 입력하세요(옵션 메인으로 돌아가려면 back을 입력하세요) : ");
         String title = scanner.nextLine();
         if(title.equals("back"))
            break;
         for(count = 0; count < number; count++) {
            if(ap[count].title.equals(title)) {
               while(answer != 1 && answer != 2 && answer != 3 && answer != 4) {
                  System.out.print("수정하고 싶은 항목을 입력하세요.(1:title, 2:date, 3: persons, 4:location) : ");
                  answer = scanner.nextInt();
                  switch(answer) {
                  case 1:
                     System.out.print("title을 수정합니다. 내용을 입력하세요 : ");
                     scanner.nextLine();
                     String ans = scanner.nextLine();
                     ap[count].title = ans;
                     break;
                  case 2:
                     System.out.print("date를 수정합니다. 내용을 입력하세요(2019년 01월 23일 17시 23분의 경우 2019 01 23 17 23와 같이) : ");
                     int date = scanner.nextInt();
                     ap[count].date[0] = date;
                     date = scanner.nextInt();
                     ap[count].date[1] = date;
                     date = scanner.nextInt();
                     ap[count].date[2] = date; 
                     date = scanner.nextInt();
                     ap[count].date[3] = date;
                     date = scanner.nextInt();
                     ap[count].date[4] = date;
                     break;
                  case 3:
                     System.out.print("persons를  수정합니다. 내용을 입력하세요 : ");
                     scanner.nextLine();
                     String ans2 = scanner.nextLine();
                     ap[count].persons = ans2;
                     break;
                  case 4:
                     System.out.print("location을 수정합니다. 내용을 입력하세요 : ");
                     scanner.nextLine();
                     String ans3 = scanner.nextLine();
                     ap[count].location = ans3;
                     break;
                  default:
                     System.out.println("잘못 입력하셨습니다.");
                     break;
                  }
               }
               System.out.println("수정이 완료되었습니다.");
               break;
            }
         }
         if(number == count) {
            System.out.print("없는 title입니다.");
            count = -1;
         }
      }      
   }
   public static void delete() {
      delete_count = -1;
      System.out.println("<<Title 목록>>");
      for( int i = 0; i < number; i++) {
    	 System.out.println(ap[i].title);
      }
      scanner.nextLine();
      while(delete_count == -1) {
         System.out.print("삭제하고 싶은 Appointment의 title을 입력하세요(옵션 메인으로 돌아가려면 back을 입력하세요) : ");
         String title = scanner.nextLine();
         if(title.equals("back"))
            break;
         deleteOut(title);
      }      
   }
   
   public static int createPush(String title, int year, int month, int date, int hour, int minute, String who, String where) {
	      ap[number] = new appointment();
	      
	      ap[number].title = title;
	      ap[number].date[0] = year;
	      ap[number].date[1] = month;
	      ap[number].date[2] = date; 
	      ap[number].date[3] = hour;
	      ap[number].date[4] = minute; 	  
	      ap[number].persons = who;
	      ap[number].location = where;
	      number++;
	      return number;//생성 성공	     
	   }
   
   public static int deleteOut(String title) {
	   last_delete = -1;   
	         for(delete_count = 0; delete_count < number; delete_count++) {
	            if(ap[delete_count].title.equals(title)) {
	               for(int i = delete_count; i<number - 1; i++)
	                  ap[delete_count] = ap[delete_count + 1];
	               number--;
	               last_delete = 1;
	               return number;//삭제 성공
	            }
	         }
	         if(number == delete_count && last_delete != 1) {
	            System.out.println("없는 title입니다. 다시 입력하세요 : ");
	            delete_count = -1;
	            return -1;//없는 타이틀
	         }
	   return -2;//삭제 실패
   }
   
}