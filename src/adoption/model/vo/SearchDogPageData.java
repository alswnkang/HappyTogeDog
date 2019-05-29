package adoption.model.vo;

import java.util.ArrayList;

public class SearchDogPageData {
	ArrayList<DogList> list;
	String pageNavi;
	public SearchDogPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchDogPageData(ArrayList<DogList> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<DogList> getList() {
		return list;
	}
	public void setList(ArrayList<DogList> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
