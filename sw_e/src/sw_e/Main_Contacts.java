package sw_e;

import java.util.Scanner;

public class Main_Contacts {
	static class ContactM {
		String name, phone, email;
		public ContactM(String name, String phone, String email) {
			this.name = name;
			this.phone = phone;
			this.email = email;
		}
	}
	
	public static Scanner sc= new Scanner(System.in); 
	public static final int CREATE = 1;
	public static final int VIEW = 2;
	public static final int UPDATE = 3;
	public static final int DELETE = 4;
	public static final int MAIN_MENU = 5;
	public static int index = 0;
	public static ContactM [] contact = new ContactM[100];
	
	public static void create() {
		System.out.println("-------연락처 생성-------");
		System.out.print("이름>> ");
		String name = sc.next();
		System.out.print("전화번호>> ");
		String phone = sc.next();
		System.out.print("이메일>> ");
		String email = sc.next();
		contact[index] = new ContactM(name, phone, email);
		index++;
	}
	
	public static void view() {
		System.out.println("-------연락처 보기-------");
		if(index==0) {
			System.out.println("저장된 연락처가 없습니다.");
		}
		else if(index>0){
			System.out.print("이름>> ");
			String name = sc.next();
			if(name.equals("ALL")) {
				for(int i=0; i<index; i++) {
					System.out.println("연락처["+i+"]");
					System.out.println("이름: "+contact[i].name);
					System.out.println("전화번호: "+contact[i].phone);
					System.out.println("이메일: "+contact[i].email);
				
				}
			}
			else if(search(name)>=0) {
				System.out.println("찾으시는 "+name+ "의 연락처");
				System.out.println("이름: "+contact[search(name)].name);
				System.out.println("전화번호: "+contact[search(name)].phone);
				System.out.println("이메일: "+contact[search(name)].email);
			}
			else if(search(name)==-1) {
				System.out.println("찾으시는 "+name+ "의 연락처는 존재하지 않습니다.");
			}
		}
	}
	
	public static void update() {
		System.out.println("-------연락처 수정-------");
		System.out.print("이름>> ");
		String name = sc.next();
		if(search(name)==-1) {
			System.out.println("찾으시는 "+name+ "의 연락처는 존재하지 않습니다.");
		}
		else {
			System.out.println("수정하고 싶은 항목");
			System.out.println("1)이름   2)전화번호   3)이메일");
			int num = sc.nextInt();
			if(num==1) {
				System.out.print("이름>> ");
				String rname = sc.next();
				contact[search(name)].name = rname;
			}
			else if(num==2) {
				System.out.print("전화번호>> ");
				String rphone = sc.next();
				contact[search(name)].phone = rphone;
			}
			else if(num==3) {
				System.out.print("이메일>> ");
				String remail = sc.next();
				contact[search(name)].email = remail;
			}
		}
	}
	
	public static void delete() {
		System.out.println("-------연락처 삭제-------");
		System.out.print("이름>> ");
		String name = sc.next();
		
		if(search(name)==-1) {
			System.out.println("찾으시는 "+name+ "의 연락처는 존재하지 않습니다.");
		}
		else {
		
			System.out.println("삭제하려는 "+name+ "의 연락처");
			System.out.println("이름: "+contact[search(name)].name);
			System.out.println("전화번호: "+contact[search(name)].phone);
			System.out.println("이메일: "+contact[search(name)].email);
			System.out.println("1)예   2)아니오");
			int num = sc.nextInt();
			if(num==1) {
				for(int i=search(name); i<index-1; i++) {
					contact[i].name = contact[i+1].name;
					contact[i].phone = contact[i+1].phone;
					contact[i].email = contact[i+1].email;
				}
				index -= 1;
			}
			else if(num==2) {
				System.out.println(name+ "의 연락처를 삭제하지 않고 메뉴로 이동합니다.");
			}
		}
	}
	
	public static int search(String name) {
		int ind=-1;
		for(int i=0; i<index; i++)
			if(name.equals(contact[i].name))
				ind = i;
		return ind;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String select = "a";
		while (!select.equals("5")) {
			System.out.println();
			System.out.println("-----------");
			System.out.println("연락처 관리 메뉴");
			System.out.println("-----------");
			System.out.println("1)생성");
			System.out.println("2)보기");
			System.out.println("3)수정");
			System.out.println("4)삭제");
			System.out.println("5)뒤로 가기");
			System.out.println("-----------");
			System.out.println("선택>>");
			
			select = sc.next();
			
			if(select.equals("1"))
				Main_Contacts.create();
			else if(select.equals("2"))
				Main_Contacts.view();
			else if(select.equals("3"))
				Main_Contacts.update();
			else if(select.equals("4"))
				Main_Contacts.delete();
			else if(select.equals("5"))
				System.out.println("메인 메뉴로 돌아갑니다.");
				
		}
		sc.close();
	}
}
