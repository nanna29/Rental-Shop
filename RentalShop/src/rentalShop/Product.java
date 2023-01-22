package rentalShop;

public class Product {
	private String productCode; //물품코드
	private String productName; //물품이름
	private int productCount; //물품개수
	private int productPrice; //상품가격
	//private String rentalDate; //빌린날짜(며칠인지)
	
	
	public Product(String productName,int productCount,int productPrice,
			String productCode) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice= productPrice;
		this.productCount = productCount;
	}
	
	public Product() {}

	public String getProductName() {
		return productName;
	}
	public int getProductCount() {
		return productCount;
	}
	public String getProductCode() {
		return productCode;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductCount(int productCount) {
		this.productCount=productCount;
	}	
	// 재고 검색 함수 (재고가 있으면 true, 아니면 false 반환)
	public boolean searchStock()
	{
		if(productCount > 0)
			return true;
		else
			return false;
	}
	
	//상품 대여 가능한지 확인
	public void ableRental() throws Exception{
		if(productCount < 1) // 재고 개수가 1보다 작을 경우 
			throw new Exception("대여 불가."); // 익셉션 발생
		else // 재고 물건 숫자가 1이상일 경우
			productCount--; // 재고 수 1개 감소
	}
	
	public void ableReturn() {
		productCount++;
	}


	
}
