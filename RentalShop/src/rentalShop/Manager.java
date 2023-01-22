package rentalShop;

public class Manager {
	private int max=1000;
	private Product[] pdList=new Product[max];
	private User[] urList=new User[max];
	
	private int sales;
	private int pdindex=0; //상품 인덱스
	private int urindex=0; //유저 인덱스
		
	User user=new User();
	//===================================================
	//물품추가
	public void addPd(Product r) throws Exception{
		//중복검색
		for(int i=0; i<pdindex;i++) {
			if(pdList[i].getProductCode().equals(r.getProductCode())) {
				throw new Exception("예외처리");

			}
		}
		//상품추가
		pdList[pdindex++]=r;

	}		
	//물품삭제
	public void deletePd(String productCode) throws Exception{
		//search 함수 사용
		int num=searchPd(productCode);
		for(int i=num;i<pdindex-1;i++) 
			pdList[i]=pdList[i+1];			
		pdindex--;
	}
	//물품검색 코드와 일치하는 인덱스 리턴함
	public int searchPd(String productCode) throws Exception{
		for(int i=0;i<pdindex;i++) {
			if(pdList[i].getProductCode().equals(productCode)) {
				return i;
			}
		}
		throw new Exception("상품 서치 함수에서 예외처리");
	}
	
	// 상품 배열 i번째 리턴
	public Product productAt(int i)
	{
		return pdList[i];
	}
	
	//물품 index 값 반환
	public int getPdIndex() {
		return pdindex;
	}
	
//=====================================================
	//사용자추가(체크인)
	public void addUr(User u) throws Exception{
		//중복검색
		for(int i=0; i<urindex;i++) {
			if(urList[i].getPhone().equals(u.getPhone())) {
				throw new Exception("체크인 함수에서 예외처리");

			}
		}	
		// 재고 개수에서 대여 개수 제외하기
		for(int i = 0; i < u.getRentalIndex(); i++)
		{
			String rentProduct = u.userRentListAt(i); // 해당 User 객체의 i번째 대여 물품 코드
			int searchNum = searchPd(rentProduct); // productList에서 해당 코드의 인덱스 번호 검색
			Product p2 = pdList[searchNum]; //해당 인덱스의 product 객체
			p2.ableRental(); // 대여가 가능한지 확인 후 빌리기
		}
		//유져추가
		urList[urindex++]=u;
	}
	
	//유저찾기
	public int searchUr(String phone) throws Exception{
		for(int i=0;i<urindex;i++) {
			if(urList[i].getPhone().equals(phone)) {
				return i;
			}
		}
		throw new Exception("예외처리");
	}
	
	
	
	//체크아웃(유저삭제)
	public void deleteUr(String phone) throws Exception{
		int num=searchUr(phone);
		for(int i = num; i < urindex; i++) {
			for(int k = 0; k < userListAt(i).getRentalIndex(); k++) {
				// productList에서 해당 코드의 인덱스 번호 검색
				int searchNum = searchPd(userListAt(i).userRentListAt(k));
				Product p3 = pdList[searchNum]; //해당 인덱스의 product 객체
				p3.ableReturn();
			}
		}	
		for(int i=num;i<urindex-1;i++)			
			urList[i]=urList[i+1];			
		urindex--;
		
	}
	//==========================================================
	//날짜까지 계산한 돈 총매출에 넣기 위해 만든 함수
	public void calculate(String phone) throws Exception {
		int num=searchUr(phone);
		int totalmoney=urList[num].payTotal();
		//하루 매출에 넣기
		this.sales+=totalmoney;		
	}
	
	//날짜까지 계산한 돈 보여주기 위해 만든 함수
	public int Showcalculate(String phone) throws Exception {
		int num=searchUr(phone);
		int totalmoney=urList[num].payTotal();
		//전체 금액 반환
		return totalmoney;
	}
	//==========================================================
	
	//유저 index 값 반환
	public int getUrIndex() {
		return urindex;
	}
	//유저 배열 i번째 리턴
	public User userListAt(int i)
	{
		return urList[i];
	}


//===================================================
	//하루매출
	public int getSales() {
		return sales;
	}
}
