
package egovframework.rpa.tax.vo;

import java.io.Serializable;

public class RpaTaxVO  implements Serializable {
	
	/**
	 * <code>serialVersionUID</code>
	 * TODO Description
	 */
	
	private static final long serialVersionUID = -1835207117023775382L;
	/* *******************************************************************************************
	* 관세사
	* ******************************************************************************************* */
	private String zfapnm;

	public String getZfapnm() {
		return zfapnm;
	}

	public void setZfapnm(String zfapnm) {
		this.zfapnm = zfapnm;
	}

	/* *******************************************************************************************
	* 세관
	* ******************************************************************************************* */
	private String name1;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	/* *******************************************************************************************
	* 관세
	* ******************************************************************************************* */
	private String dmbtr;

	public String getDmbtr() {
		return dmbtr;
	}

	public void setDmbtr(String dmbtr) {
		this.dmbtr = dmbtr;
	}

	/* *******************************************************************************************
	* 부가세
	* ******************************************************************************************* */
	private String wrbtr;

	public String getWrbtr() {
		return wrbtr;
	}

	public void setWrbtr(String wrbtr) {
		this.wrbtr = wrbtr;
	}

	/* *******************************************************************************************
	* 합계
	* ******************************************************************************************* */
	private String twrbtr;

	public String getTwrbtr() {
		return twrbtr;
	}

	public void setTwrbtr(String twrbtr) {
		this.twrbtr = twrbtr;
	}

	/* *******************************************************************************************
	* 지급일자
	* ******************************************************************************************* */
	private String bldat;

	public String getBldat() {
		return bldat;
	}

	public void setBldat(String bldat) {
		this.bldat = bldat;
	}
	
	/* *******************************************************************************************
	* 셀스타일
	* ******************************************************************************************* */
	private String extra_style;

	public String getExtra_style() {
		return extra_style;
	}

	public void setExtra_style(String extra_style) {
		this.extra_style = extra_style;
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
	* 부가세검색시작일자
	* ******************************************************************************************* */
	private String searchExtraBgnDe;

	public String getSearchExtraBgnDe() {
		return searchExtraBgnDe;
	}

	public void setSearchExtraBgnDe(String searchExtraBgnDe) {
		this.searchExtraBgnDe = searchExtraBgnDe;
	}

	/* *******************************************************************************************
	* 부가세검색종료일자
	* ******************************************************************************************* */
	private String searchExtraEndDe;

	public String getSearchExtraEndDe() {
		return searchExtraEndDe;
	}

	public void setSearchExtraEndDe(String searchExtraEndDe) {
		this.searchExtraEndDe = searchExtraEndDe;
	}
	
	/* *******************************************************************************************
	* 부가세검색월
	* ******************************************************************************************* */
	private String searchMonth;

	public String getSearchMonth() {
		return searchMonth;
	}

	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	


}

