package volunteer.model.vo;

import java.sql.Date;

public class VoluntaryRegister {

	private int no;				//공고번호
	private String code;		//보호소코드
	private String title;		//봉사활동 공고 제목
	private String volunDate;	//봉사 날짜
	private String volunTime;	//봉사 시간
	private int person;			//봉사 가능 인원 수
	private String detail;		//봉사 상세설명
	private int status;			//모집상태 (1:모집중/2:마감)
	private int applyNum;		//신청한 인원 수
	private String filename;	//이미지 명
	private String filepath;	//이미지 경로
	private Date enrollDate;	//공고 등록일
	
	public VoluntaryRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoluntaryRegister(int no, String code, String title, String volunDate, String volunTime, int person,
			String detail, int status, int applyNum, String filename, String filepath, Date enrollDate) {
		super();
		this.no = no;
		this.code = code;
		this.title = title;
		this.volunDate = volunDate;
		this.volunTime = volunTime;
		this.person = person;
		this.detail = detail;
		this.status = status;
		this.applyNum = applyNum;
		this.filename = filename;
		this.filepath = filepath;
		this.enrollDate = enrollDate;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVolunDate() {
		return volunDate;
	}

	public void setVolunDate(String volunDate) {
		this.volunDate = volunDate;
	}

	public String getVolunTime() {
		return volunTime;
	}

	public void setVolunTime(String volunTime) {
		this.volunTime = volunTime;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getStatus() {
		//return status;
		if(person > applyNum) {
			return status; //모집 중
		}else {
			return 2; //마감
		}
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
}
