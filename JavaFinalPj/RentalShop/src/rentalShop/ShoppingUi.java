package rentalShop;
import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class ShoppingUi {
	Scanner scan = new Scanner(System.in);
	Manager manager=new Manager();
	User user=new User();
	
	//오늘 날짜를 문자 YYYYMMDD  포맷으로 나오게 하는 방법
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Calendar c1 = Calendar.getInstance();
	
	public void totalService() throws Exception {
		while(true) {
			String strToday = sdf.format(c1.getTime());
			System.out.println("오늘의 날짜:" + strToday+" \n오늘도 행복하세요(*˘*)♡");
			System.out.println("");
			System.out.println("이용자는 누구입니까? 번호를 입력해주세요.");
			System.out.println("1. 관리자");
			System.out.println("2. 사용자");
			System.out.println("0. 나가기");
			System.out.println("-------------------------------");
			System.out.print("입력: ");
			int sel=scan.nextInt();
			
			switch(sel) // 번호에 따른 메소드 실행
			{
			case 1:
				managerService();
				break;
			case 2:
				UserService();
				break;
			case 0:
				System.out.println("이용해주셔서 감사합니다. 안녕히가세요.");
				totalService();
				break;
			//이외에 다른 번호 눌렀을때
			default:
				System.out.println("");
				System.out.println("*****");
				System.out.println("잘못된 번호를 입력했습니다. 다시 입력해주세요."); 
				System.out.println("*****");
				System.out.println("");
				break;
			}
			if(sel==0)
				break;
			
		}
		
		
	}
	
	public void managerService() throws Exception{
		while(true) //반복문
		{
			Menu.managerMenu(); // 메뉴를 보여준다
			int mMenu = scan.nextInt();	// 번호를 입력 받는다
			
			switch(mMenu) // 번호에 따른 메소드 실행
			{
			//상품등록
			case 1:
				System.out.println("");
				System.out.println("*****");
				System.out.println("아래 사항을 입력해 주세요.");
				
				System.out.print("상품이름: ");//상품 이름 입력받기
				scan.nextLine(); //줄 넘김 추가
				String productName=scan.nextLine();
				
				System.out.print("상품개수: ");//상품 개수 입력받기
				int productCount=scan.nextInt();
				
				System.out.print("상품가격: ");//상품 가격 입력받기
				int productPrice=scan.nextInt();
				
				System.out.print("상품코드: ");//상품 코드 입력받기
				String productCode=scan.next();
				
				System.out.println("*****");
				
				Product r=new Product(productName,productCount,
						productPrice,productCode);
				
				try{
					manager.addPd(r);
				}
				catch(Exception e) {
					System.out.println("이미 등록된 상품 코드입니다. 다시 등록해주세요");		
					continue;
					
				}
				
				System.out.println("상품 등록이 완료되었습니다. 등록된 상품이름은 \""+productName+"\" 입니다.");
	 			break;

	 		//상품삭제
			case 2:
				System.out.println("");
				System.out.println("*****");
				System.out.println("삭제할 상품코드를 입력하세요.");
				String delCode=scan.next();
				try {
					manager.deletePd(delCode);
				}
				catch(Exception e) {
					System.out.println("");
					System.out.println("입력한 코드에 해당하는 물품이 존재하지 않습니다");
					continue;
				}
				System.out.println("");
				System.out.println("상품 삭제가 완료되었습니다.");
				System.out.println("*****");
				
	 			break;
	 			

	 		//전체 상품 출력
			case 3:
				System.out.println("");
				System.out.println("*****");
				System.out.println("전체 상품 목록을 출력합니다.");
				
				for(int i=0;i<manager.getPdIndex();i++) {
					System.out.println("["+(i+1)+"번 상품] "+"상품 이름: "+manager.productAt(i).getProductName()+
							" 상품 개수: "+manager.productAt(i).getProductCount()+" 상품 가격: "+manager.productAt(i).getProductPrice()
							+" 상품 코드: "+manager.productAt(i).getProductCode());
				}
				System.out.println("*****");
				break;
			//프로그램 종료
			case 4 :
				System.out.println("");
				System.out.println("*****");
				System.out.println("프로그램을 종료합니다.");
				System.out.println("*****");
				System.out.println("");
				break;
			//고객관리 정보 출력
			case 5:
				if(manager.userListAt(0)==null)//유저가 한명도 등록되어있지 않을경우
					System.out.println("체크인 정보가 존재하지 않습니다.");
				
				else {
					for(int i = 0; i < manager.getUrIndex(); i++)
					{			
						// 출력
						
						System.out.println("["+(i+1)+"번 고객] 이름: "+manager.userListAt(i).getName()+
								" 전화번호: "+manager.userListAt(i).getPhone()+
								" 빌린날짜: "+manager.userListAt(i).getRentalDate()+
								" 반납예정날짜: "+manager.userListAt(i).getReturnDate());
						for(int k = 0; k < manager.userListAt(i).getRentalIndex(); k++)
						{
							String rentalList = manager.userListAt(i).userRentListAt(k);
							System.out.printf("%d번째 대여 물품 코드 : %s ", k+1, rentalList);
							System.out.println(" ");
						}
						System.out.println(" ");
					}		
				}
				break;
			//매출 현황 확인하기
			case 6:
				System.out.println("");
				System.out.println("*****");
				System.out.println("오늘 하루 매출: "+manager.getSales()+"원");
			
				break;
				
			case 0:
				System.out.println("");
				totalService();
				break;
				
				
			//이외에 다른 번호 눌렀을때
			default:
				System.out.println("");
				System.out.println("*****");
				System.out.println("잘못된 번호를 입력했습니다. 다시 입력하세요 "); 
				System.out.println("*****");
				System.out.println("");
				break;
			}
			//반복문 종료
			if(mMenu == 4)						
				break; 
		}
	}
	public void UserService() throws Exception {
		while(true) //반복문
		{
			Menu.userMenu(); // 메뉴를 보여준다
			int uMenu = scan.nextInt();	// 번호를 입력 받는다
			
			switch(uMenu) // 번호에 따른 메소드 실행
			{
			//쇼윈도구경
			case 1:
				System.out.println("");
				System.out.println("*****낭낭 렌탈샵 쇼윈도*****");
				System.out.println("재고가 없는 상품은 진열되어있지 않습니다.");
						
				for(int i=0; i<manager.getPdIndex();i++) {	
					//product 클래스 searchStock()함수 이용
					if((manager.productAt(i).searchStock()==true)) {
						System.out.println("["+(i+1)+"번 상품] "+"상품 이름: "+manager.productAt(i).getProductName()+
								" 상품 개수: "+manager.productAt(i).getProductCount()+" 상품 가격: "+manager.productAt(i).getProductPrice()
								+" 상품 코드: "+manager.productAt(i).getProductCode());
					}
					else {
						//재고가 0이어서 false 가 반환될 경우
						System.out.println("["+(i+1)+"번 상품] 재고가 없습니다.");
					}
				}
				
				
	 			break;
	 		//체크인
			case 2:
				try {
					System.out.println("");
					System.out.println("*****");
					String strToday = sdf.format(c1.getTime());//오늘 날짜
					System.out.println("빌리는 날짜: " + strToday);
					System.out.println("");
					System.out.println("아래 사항을 입력해 주세요.");
					
					System.out.print("이름: ");
					scan.nextLine(); //줄 넘김 추가
					String name=scan.nextLine();//사용자 이름 입력받기
					
					System.out.print("핸드폰번호: ");
					String phone=scan.nextLine(); //사용자 번호 입력받기
									
					System.out.print("반납 예정일: (yyyyMMdd) 형식으로 입력해주세요.");
					String returnDate=scan.nextLine(); //반납할 날짜
					
					// User 객체 생성
					User u1;
					u1 = new User(name, phone, returnDate, strToday);
					
					// 대여할 상품 개수 입력 받기
					System.out.printf("대여할 상품 개수를 적어주세요(최대 3개) : ");
					int rentalNum = scan.nextInt();
					if(rentalNum > 3) {
						System.out.println("최대 3개입니다. 다시 체크인 해주세요.");
						UserService();
					}
					
					scan.nextLine();
					for(int i=0; i<rentalNum; i++) {
						System.out.print("대여할 상품 코드를 입력해주세요: ");
						String r=scan.nextLine(); //상품 코드 입력받기
						
						//상품 코드로 상품 가격 찾기
						int p = manager.productAt(manager.searchPd(r)).getProductPrice();
						//상품 가격 배열에 추가하기
						u1.addUserPriceList(p);
					
						//대여 상품 배열에 넣기
						u1.addUserRentList(r);
						//System.out.println("현재 렌트한 물품 개수: "+(i+1));
						
					}
					

					manager.addUr(u1); //유저객체추가
					System.out.println(name+"님 상품 렌탈이 완료되었습니다.");
				}
				

					
				
				catch(Exception e) {
					if(e.getMessage().equals("상품 서치 함수에서 예외처리")){
						System.out.println("");
						System.out.println("해당 코드에 맞는 물품이 존재하지 않습니다.");	
					}
					if(e.getMessage().equals("대여 불가.")){
						System.out.println("");
						System.out.println("대여가 불가능합니다. 재고가 없습니다");	
					}
					
					if(e.getMessage().equals("체크인 함수에서 예외처리")) {
						System.out.println("");
						System.out.println("이미 등록된 사용자입니다. 다른 번호를 등록해주세요");	
					}
					
				}
	 			break;
	 		//상품반납
			case 3:
				System.out.println("");
				System.out.println("*****");
				System.out.println("반납 금액을 확인하시려면, 핸드폰 번호를 입력해주세요.");
				
				scan.nextLine();
				//사용자 번호 입력받기
				String rentalPhone =scan.nextLine(); 
				
				//날짜 계산은 안하고 빌린 물품의 대여료만 보여줌
				System.out.println("대여하신 물품의 총 금액: "+manager.userListAt(manager.searchUr(rentalPhone)).pay()+"원");
				//총매출에 넣지않고 돈만 보여줌(반납을 안할수도 있으니 전체 금액을 보여줌)
				System.out.println("대여하신 날짜와 합산한 금액: "+manager.Showcalculate(rentalPhone)+"원");
				System.out.println("");
				System.out.println("지불하시겠습니까? (y/n)");
				String answer=scan.nextLine();
				if(answer.equals("y")) {
					try{
						//총매출에 넣음
						manager.calculate(rentalPhone);
						//유저 삭제하고 체크아웃함
						manager.deleteUr(rentalPhone);
					}
					catch(Exception e) {
						System.out.println("");
						System.out.println("입력한 코드에 해당하는 사람이 존재하지 않습니다");
						continue;
					}
					System.out.println("");
					System.out.println("반납이 완료되었습니다. 감사합니다.");
					
					System.out.println("*****");
					System.out.println("");
					
					break;
				}
				else
					break;
			//나가기
			case 4 :
				System.out.println("");
				System.out.println("*****");
				System.out.println("방문해주셔서 감사합니다.");
				System.out.println("*****");
				System.out.println("");
				break;
				
			case 0:
				totalService();
				break;
				
			//이외에 다른 번호 눌렀을때
			default:
				System.out.println("");
				System.out.println("*****");
				System.out.println("잘못된 번호를 입력했습니다. 다시 입력해주세요."); 
				System.out.println("*****");
				System.out.println("");
				break;
			}
			//반복문 종료
			if(uMenu == 4)						
				break; 
		}
	}
}
	




