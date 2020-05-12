
package egovframework.rpa.bill;

import java.io.Serializable;

public class RpaBillVO  implements Serializable {

	
	/**
	 * <code>serialVersionUID</code>
	 * TODO Description
	 */
	
	private static final long serialVersionUID = 8570541919552568665L;
	
	/* *******************************************************************************************
	* 전표번호
	* ******************************************************************************************* */
	private String belnr;

	public String getBelnr() {
		return belnr;
	}

	public void setBelnr(String belnr) {
		this.belnr = belnr;
	}

	/* *******************************************************************************************
	* 지정
	* ******************************************************************************************* */
	private String appoint;

	public String getAppoint() {
		return appoint;
	}

	public void setAppoint(String appoint) {
		this.appoint = appoint;
	}

	/* *******************************************************************************************
	* umskz
	* ******************************************************************************************* */
	private String umskz;

	public String getUmskz() {
		return umskz;
	}

	public void setUmskz(String umskz) {
		this.umskz = umskz;
	}

	/* *******************************************************************************************
	* 어음만기일
	* ******************************************************************************************* */
	private String zfbdt;

	public String getZfbdt() {
		return zfbdt;
	}

	public void setZfbdt(String zfbdt) {
		this.zfbdt = zfbdt;
	}

	/* *******************************************************************************************
	* 어음발행일
	* ******************************************************************************************* */
	private String bldat;

	public String getBldat() {
		return bldat;
	}

	public void setBldat(String bldat) {
		this.bldat = bldat;
	}

	/* *******************************************************************************************
	* 어음발행액
	* ******************************************************************************************* */
	private String pswbt;

	public String getPswbt() {
		return pswbt;
	}

	public void setPswbt(String pswbt) {
		this.pswbt = pswbt;
	}

	/* *******************************************************************************************
	* 통화단위
	* ******************************************************************************************* */
	private String pswsl;

	public String getPswsl() {
		return pswsl;
	}

	public void setPswsl(String pswsl) {
		this.pswsl = pswsl;
	}

	/* *******************************************************************************************
	* 거래처코드
	* ******************************************************************************************* */
	private String lifnr;

	public String getLifnr() {
		return lifnr;
	}

	public void setLifnr(String lifnr) {
		this.lifnr = lifnr;
	}

	/* *******************************************************************************************
	* AUGBL
	* ******************************************************************************************* */
	private String augbl;

	public String getAugbl() {
		return augbl;
	}

	public void setAugbl(String augbl) {
		this.augbl = augbl;
	}

	/* *******************************************************************************************
	* 어음수취인
	* ******************************************************************************************* */
	private String name1;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	/* *******************************************************************************************
	* 사업자번호
	* ******************************************************************************************* */
	private String stcd2;

	public String getStcd2() {
		return stcd2;
	}

	public void setStcd2(String stcd2) {
		this.stcd2 = stcd2;
	}

	/* *******************************************************************************************
	* 어음수취인도시
	* ******************************************************************************************* */
	private String mcod3;

	public String getMcod3() {
		return mcod3;
	}

	public void setMcod3(String mcod3) {
		this.mcod3 = mcod3;
	}

	/* *******************************************************************************************
	* 비고
	* ******************************************************************************************* */
	private String xragl;

	public String getXragl() {
		return xragl;
	}

	public void setXragl(String xragl) {
		this.xragl = xragl;
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
	* 반재여부
	* ******************************************************************************************* */
	private String searchXragl;

	public String getSearchXragl() {
		return searchXragl;
	}

	public void setSearchXragl(String searchXragl) {
		this.searchXragl = searchXragl;
	}
}

