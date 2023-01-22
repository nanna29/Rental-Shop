package rentalShop;

import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class User {
	private String[] rentalList =new String[3]; //사용자가 빌린 물품 저장함
	private int[] priceList =new int[3]; //사용자가 빌린 물품 가격 저장함
	private String phone; //폰번호
	private String name; //이름
	private String rentalCode; //빌린물품코드
	private String returnDate; //반납할 날자
	private String rentalDate; //빌린 날자
	private int rentalIndex=0;
	
	//오늘 날짜를 문자 YYYYMMDD  포맷으로 나오게 하는 방법
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Calendar c1 = Calendar.getInstance();
	
	
	public User(String name,String phone,String returnDate,String strToday) {
		super();
		this.name = name;
		this.phone = phone;
		this.returnDate = returnDate;
		this.rentalDate= strToday;
	}//전체 유저 저장하는 배열은 manager에 있음!
	
	public User() {} //생성자
	
	
	//==================================================================
	
	//사용자의 빌린 품목 저장하는 함수 (최대 3개임)
	public void addUserRentList(String r) {
		rentalList[rentalIndex]=r;
		rentalIndex++;
	}
	
	//사용자의 빌린 품목 가격 저장하는 함수 (최대 3개임)
	public void addUserPriceList(int p) {
		priceList[rentalIndex]=p;
		//rentalIndex++;
	}
		
	//물품 대여 비용 계산(물품만 더한거)
	public int pay() {
		int num1=priceList[0];
		int num2=priceList[1];
		int num3=priceList[2];
		return num1+num2+num3;
	}
	
	//물품 대여비용에 날짜까지 합친 금액 반환하기
	public int payTotal() throws ParseException
	{
		// 물건 금액 합 계산하기
		int money = 0;
		for(int i = 0; i < rentalIndex; i++)
		{
			money += priceList[i];
		}
		
		String realReturnDate = sdf.format(c1.getTime()); //오늘날짜 string으로
		String userRentalDate = rentalDate; //빌린날짜 (자동으로 들어감) string으로 받기
		String userReturnDate = returnDate;//반납하기로 한 날짜 string으로 받기
		
		//startDate<endDate<dday
		Date dday = sdf.parse(realReturnDate);//오늘날짜(반납하는 당일)
		Date startDate = sdf.parse(userRentalDate);//빌린날 (빌릴때 같이 들어감)
		Date endDate = sdf.parse(userReturnDate);//반납할날 (입력받았음)
		//정상적인 반납
		long normalDiffDay=(startDate.getTime() - endDate.getTime()) /(24*60*60*1000);
		
		//지나서 반납 or 미리 반납
		long overDiffDay=(startDate.getTime() - dday.getTime()) /(24*60*60*1000);
		
		//며칠 연체?
		long howOverDay=(endDate.getTime() - dday.getTime()) /(24*60*60*1000);
		
		if(howOverDay==0) {//정상반납
			return  (int) (money*(normalDiffDay+1));
		}
		else if(howOverDay>0) {//미리 반납했음
			return (int) ((money)*(overDiffDay+1)*(0.9));
		}
		else if(howOverDay<0) {//밀렸음
			return (int) (money*(overDiffDay+1)+((-howOverDay)*1000));
		}
		return 0;
				
	}
	
		

	//렌탈한 상품 넣는 배열 인덱스 가져오기
	public int getRentalIndex(){		
		return rentalIndex;
	}

	//렌탈 리스트 배열 가져오기
	public String userRentListAt(int i)
	{
		return rentalList[i];
	}
	
	//가격 리스트 배열 가져오기
	public int userPriceListtAt(int i)
	{
		return priceList[i];
	}
	
	//==================================================================
	
	//이름 가져오기
	public String getName() {
		return name;
	}
	//폰번호 가져오기
	public String getPhone() {
		return phone;
	}
	//렌탈코드 가져오기
	public String getRentalCode() {
		return rentalCode;
	}
	//렌탈일 가져오기(빌린날짜)
	public String getRentalDate() {
		return rentalDate;
	}
	
	//반납일 가져오기(반납하기로 한 날짜)
	public String getReturnDate() {
		return returnDate;
	}
	//=================================
	
	
	
	
}
