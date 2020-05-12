
package egovframework.rpa.ttsend.vo;

import java.io.Serializable;

public class RpaTTSendVO  implements Serializable {
	
	/**
	 * <code>serialVersionUID</code>
	 * TODO Description
	 */
	
	private static final long serialVersionUID = 6653377803459152565L;
	/* *******************************************************************************************
	* 번호
	* ******************************************************************************************* */
	private String num;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	/* *******************************************************************************************
	* BL일자
	* ******************************************************************************************* */
	private String bldat;

	public String getBldat() {
		return bldat;
	}

	public void setBldat(String bldat) {
		this.bldat = bldat;
	}

	/* *******************************************************************************************
	* BL번호
	* ******************************************************************************************* */
	private String bktxt;

	public String getBktxt() {
		return bktxt;
	}

	public void setBktxt(String bktxt) {
		this.bktxt = bktxt;
	}

	/* *******************************************************************************************
	* 수입신고번호
	* ******************************************************************************************* */
	private String zfidrno;

	public String getZfidrno() {
		return zfidrno;
	}

	public void setZfidrno(String zfidrno) {
		this.zfidrno = zfidrno;
	}

	/* *******************************************************************************************
	* 전송일
	* ******************************************************************************************* */
	private String send_dt;

	public String getSend_dt() {
		return send_dt;
	}

	public void setSend_dt(String send_dt) {
		this.send_dt = send_dt;
	}

	/* *******************************************************************************************
	* 통화단위
	* ******************************************************************************************* */
	private String waers;

	public String getWaers() {
		return waers;
	}

	public void setWaers(String waers) {
		this.waers = waers;
	}

	/* *******************************************************************************************
	* 결제금액
	* ******************************************************************************************* */
	private String wrbtr;

	public String getWrbtr() {
		return wrbtr;
	}

	public void setWrbtr(String wrbtr) {
		this.wrbtr = wrbtr;
	}

	/* *******************************************************************************************
	* 공급업체
	* ******************************************************************************************* */
	private String name1;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	/* *******************************************************************************************
	* 환율
	* ******************************************************************************************* */
	private String kursf;

	public String getKursf() {
		return kursf;
	}

	public void setKursf(String kursf) {
		this.kursf = kursf;
	}

	/* *******************************************************************************************
	* 검색BL번호
	* ******************************************************************************************* */
	private String searchBlNo;

	public String getSearchBlNo() {
		return searchBlNo;
	}

	public void setSearchBlNo(String searchBlNo) {
		this.searchBlNo = searchBlNo;
	}

	/* *******************************************************************************************
	* 검색공급업체
	* ******************************************************************************************* */
	private String searchCom;

	public String getSearchCom() {
		return searchCom;
	}

	public void setSearchCom(String searchCom) {
		this.searchCom = searchCom;
	}

	/* *******************************************************************************************
	* 검색통화단위
	* ******************************************************************************************* */
	private String searchWaers;

	public String getSearchWaers() {
		return searchWaers;
	}

	public void setSearchWaers(String searchWaers) {
		this.searchWaers = searchWaers;
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
	* 검색시작일자
	* ******************************************************************************************* */
	private String searchBgnDe;

	public String getSearchBgnDe() {
		return searchBgnDe;
	}

	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	/* *******************************************************************************************
	* 검색종료일자
	* ******************************************************************************************* */
	private String searchEndDe;

	public String getSearchEndDe() {
		return searchEndDe;
	}

	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}
	
	/* *******************************************************************************************
	* 검색종료일자
	* ******************************************************************************************* */
	private String searchSendDt;

	public String getSearchSendDt() {
		return searchSendDt;
	}

	public void setSearchSendDt(String searchSendDt) {
		this.searchSendDt = searchSendDt;
	}

	

}

