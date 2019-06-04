package sponsorship.model.vo;

public class ProductVO {
	
	private String prdCode;
	private String prdName;
	private String prdImg;
	private String prdPrice;
	private String prdCon;
	public ProductVO() {
		super();
	}
	public ProductVO(String prdCode, String prdName, String prdImg, String prdPrice, String prdCon) {
		super();
		this.prdCode = prdCode;
		this.prdName = prdName;
		this.prdImg = prdImg;
		this.prdPrice = prdPrice;
		this.prdCon = prdCon;
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
	public String getPrdCon() {
		return prdCon;
	}
	public void setPrdCon(String prdCon) {
		this.prdCon = prdCon;
	}
	
	
	
	
	

}
