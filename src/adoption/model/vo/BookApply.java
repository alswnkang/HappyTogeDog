package adoption.model.vo;

import java.sql.Date;

public class BookApply {
	private int no;
	private String code;
	private String id;
	private String name;
	private String phone;
	private Date visitDate;
	private String visitTime;
	private Date applyDate;
	private int status;
	private String yard;
	private String animal;
	private String family;
	private String experience;
	private String avgTime;
	public BookApply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookApply(int no, String code, String id, String name, String phone, Date visitDate, String visitTime,
			Date applyDate, int status, String yard, String animal, String family, String experience, String avgTime) {
		super();
		this.no = no;
		this.code = code;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.visitDate = visitDate;
		this.visitTime = visitTime;
		this.applyDate = applyDate;
		this.status = status;
		this.yard = yard;
		this.animal = animal;
		this.family = family;
		this.experience = experience;
		this.avgTime = avgTime;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getYard() {
		return yard;
	}
	public void setYard(String yard) {
		this.yard = yard;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}
	
}
