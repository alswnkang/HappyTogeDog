package adoption.model.vo;

public class Shelter {
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private String name;
	private String phone;
	private String addr;
	public Shelter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shelter(String name, String phone, String addr,String code) {
		super();
		this.name = name;
		this.phone = phone;
		this.addr = addr;
		this.code= code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	

}