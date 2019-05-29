package sponsorship.model.vo;

public class SearchVO {
	
	private int reqPage;
	private String startDay;
	private String endDay;
	private String status;
	private String payMethod;
	private String searchType;
	private String searchVal;
	public SearchVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchVO(int reqPage, String startDay, String endDay, String status, String payMethod, String searchType,
			String searchVal) {
		super();
		this.reqPage = reqPage;
		this.startDay = startDay;
		this.endDay = endDay;
		this.status = status;
		this.payMethod = payMethod;
		this.searchType = searchType;
		this.searchVal = searchVal;
	}
	public int getReqPage() {
		return reqPage;
	}
	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchVal() {
		return searchVal;
	}
	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}
	
	
	

}
