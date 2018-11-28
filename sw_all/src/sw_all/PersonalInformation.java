package sw_all;

import java.util.Scanner;
//import sw_e.Main_Contacts;
import sw_all.main_memo;
//import sw_e.Main_Appointment;

public class PersonalInformation {
   public static void main(String[] args) {
      int option_choice = 0; // while문으로 들어가기 위한 초기값 그냥 0으로 설정했음, 1 ~ 5 기능 중 유저가 선택한 값
      Scanner scanner = new Scanner(System.in);
      
      while(option_choice != 5) {
         System.out.println("원하는 기능을 숫자로 입력하세요.\n1. Contacts 2. To-do list 3. Appointment 4. Memo 5. Exit");
             option_choice = scanner.nextInt();
         switch (option_choice) {
            case 1:
               Main_Contacts.main(args);
               //Contacts
               break;
               
            case 2:
               //To-do list
               break;
               
            case 3:
               //Main_Appointment.main(args);
               //Appointment
               break;
               
            case 4:
               main_memo memo = new main_memo();
               memo.main(args);
               break;
         
            case 5://종료의 경우
               System.out.println("프로그램을 종료합니다.");
               break;
               default:
                  System.out.print("잘못된 입력입니다.");
         }
      }   
      scanner.close();
   }

}