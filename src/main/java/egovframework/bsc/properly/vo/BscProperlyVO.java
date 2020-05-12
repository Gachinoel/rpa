
package egovframework.bsc.properly.vo;

import java.io.Serializable;
import java.util.List;

public class BscProperlyVO  implements Serializable {
	
	/**
	 * <code>serialVersionUID</code>
	 * TODO Description
	 */
	
	private static final long serialVersionUID = 5811300261457801811L;
	
	/* *******************************************************************************************
	* 검색월
	* ******************************************************************************************* */
	private String searchMonth;

	public String getSearchMonth() {
		return searchMonth;
	}

	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}

	/* *******************************************************************************************
	* 검색구분
	* ******************************************************************************************* */
	private String searchGubun;

	public String getSearchGubun() {
		return searchGubun;
	}

	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
	}

	/* *******************************************************************************************
	* 검색아이템
	* ******************************************************************************************* */
	private String searchItem;

	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}

	/* *******************************************************************************************
	* 항목
	* ******************************************************************************************* */
	private String item;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	/* *******************************************************************************************
	* 항목명
	* ******************************************************************************************* */
	private String item_nm;

	public String getItem_nm() {
		return item_nm;
	}

	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}

	/* *******************************************************************************************
	* 자재유형
	* ******************************************************************************************* */
	private String spart_nm;

	public String getSpart_nm() {
		return spart_nm;
	}

	public void setSpart_nm(String spart_nm) {
		this.spart_nm = spart_nm;
	}

	/* *******************************************************************************************
	* 아이템유형
	* ******************************************************************************************* */
	private String mtart_nm;

	public String getMtart_nm() {
		return mtart_nm;
	}

	public void setMtart_nm(String mtart_nm) {
		this.mtart_nm = mtart_nm;
	}

	/* *******************************************************************************************
	* 목표금액
	* ******************************************************************************************* */
	private String goal_amt;

	public String getGoal_amt() {
		return goal_amt;
	}

	public void setGoal_amt(String goal_amt) {
		this.goal_amt = goal_amt;
	}

	/* *******************************************************************************************
	* 안전재고
	* ******************************************************************************************* */
	private String safe_amt;

	public String getSafe_amt() {
		return safe_amt;
	}

	public void setSafe_amt(String safe_amt) {
		this.safe_amt = safe_amt;
	}

	/* *******************************************************************************************
	* 가용재고
	* ******************************************************************************************* */
	private String use_amt;

	public String getUse_amt() {
		return use_amt;
	}

	public void setUse_amt(String use_amt) {
		this.use_amt = use_amt;
	}

	/* *******************************************************************************************
	* 재고금액
	* ******************************************************************************************* */
	private String inven_amt;

	public String getInven_amt() {
		return inven_amt;
	}

	public void setInven_amt(String inven_amt) {
		this.inven_amt = inven_amt;
	}

	/* *******************************************************************************************
	* 실재고금액
	* ******************************************************************************************* */
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/* *******************************************************************************************
	* 달성율
	* ******************************************************************************************* */
	private String achieve_rate;

	public String getAchieve_rate() {
		return achieve_rate;
	}

	public void setAchieve_rate(String achieve_rate) {
		this.achieve_rate = achieve_rate;
	}
	
	/* *******************************************************************************************
	* 배경색
	* ******************************************************************************************* */
	private String bgcolor;

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

}

