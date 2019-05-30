package sponsorship.model.vo;

public class ProductVO {
	
	private String prdCode;
	private String prdName;
	private String prdImg;
	private String prdPrice;
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductVO(String prdCode, String prdName, String prdImg, String prdPrice) {
		super();
		this.prdCode = prdCode;
		this.prdName = prdName;
		this.prdImg = prdImg;
		this.prdPrice = prdPrice;
	}
	public String getPrdCode() {
		return prdCode;
	}
	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getPrdImg() {
		return prdImg;
	}
	public void setPrdImg(String prdImg) {
		this.prdImg = prdImg;
	}
	public String getPrdPrice() {
		return prdPrice;
	}
	public void setPrdPrice(String prdPrice) {
		this.prdPrice = prdPrice;
	}
	
	
	
	

}
