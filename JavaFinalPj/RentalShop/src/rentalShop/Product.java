package rentalShop;

public class Product {
	private String productCode; //��ǰ�ڵ�
	private String productName; //��ǰ�̸�
	private int productCount; //��ǰ����
	private int productPrice; //��ǰ����
	//private String rentalDate; //������¥(��ĥ����)
	
	
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
	// ��� �˻� �Լ� (��� ������ true, �ƴϸ� false ��ȯ)
	public boolean searchStock()
	{
		if(productCount > 0)
			return true;
		else
			return false;
	}
	
	//��ǰ �뿩 �������� Ȯ��
	public void ableRental() throws Exception{
		if(productCount < 1) // ��� ������ 1���� ���� ��� 
			throw new Exception("�뿩 �Ұ�."); // �ͼ��� �߻�
		else // ��� ���� ���ڰ� 1�̻��� ���
			productCount--; // ��� �� 1�� ����
	}
	
	public void ableReturn() {
		productCount++;
	}


	
}
