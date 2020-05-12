
package egovframework.rpa.cmm.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class RpaCmmVO   implements Serializable {
	
	/**
	 * <code>serialVersionUID</code>
	 * TODO Description
	 */
	
	private static final long serialVersionUID = -1844862624805817764L;
	/* *******************************************************************************************
	* 파일아이디
	* ******************************************************************************************* */
	private String atch_file_id;

	public String getAtch_file_id() {
		return atch_file_id;
	}

	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}

	/* *******************************************************************************************
	* 디렉토리구분
	* ******************************************************************************************* */
	private String dir_gubun;

	public String getDir_gubun() {
		return dir_gubun;
	}

	public void setDir_gubun(String dir_gubun) {
		this.dir_gubun = dir_gubun;
	}

	/* *******************************************************************************************
	* bl 번호
	* ******************************************************************************************* */
	private String blno;

	public String getBlno() {
		return blno;
	}

	public void setBlno(String blno) {
		this.blno = blno;
	}

	/* *******************************************************************************************
	* 파일구분
	* ******************************************************************************************* */
	private String file_gubun;

	public String getFile_gubun() {
		return file_gubun;
	}

	public void setFile_gubun(String file_gubun) {
		this.file_gubun = file_gubun;
	}

	/* *******************************************************************************************
	* 파일구분명
	* ******************************************************************************************* */
	private String file_gubun_nm;

	public String getFile_gubun_nm() {
		return file_gubun_nm;
	}

	public void setFile_gubun_nm(String file_gubun_nm) {
		this.file_gubun_nm = file_gubun_nm;
	}

	/* *******************************************************************************************
	* 파일경로
	* ******************************************************************************************* */
	private String file_stre_cours;

	public String getFile_stre_cours() {
		return file_stre_cours;
	}

	public void setFile_stre_cours(String file_stre_cours) {
		this.file_stre_cours = file_stre_cours;
	}

	/* *******************************************************************************************
	* 저장파일명
	* ******************************************************************************************* */
	private String stre_file_nm;

	public String getStre_file_nm() {
		return stre_file_nm;
	}

	public void setStre_file_nm(String stre_file_nm) {
		this.stre_file_nm = stre_file_nm;
	}

	/* *******************************************************************************************
	* 원본파일명
	* ******************************************************************************************* */
	private String orignl_file_nm;

	public String getOrignl_file_nm() {
		return orignl_file_nm;
	}

	public void setOrignl_file_nm(String orignl_file_nm) {
		this.orignl_file_nm = orignl_file_nm;
	}

	/* *******************************************************************************************
	* 파일확장자
	* ******************************************************************************************* */
	private String file_extsn;

	public String getFile_extsn() {
		return file_extsn;
	}

	public void setFile_extsn(String file_extsn) {
		this.file_extsn = file_extsn;
	}

	/* *******************************************************************************************
	* 파일크기
	* ******************************************************************************************* */
	private String file_size;

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String string) {
		this.file_size = string;
	}

	/* *******************************************************************************************
	* 생성일
	* ******************************************************************************************* */
	private String creat_dt;

	public String getCreat_dt() {
		return creat_dt;
	}

	public void setCreat_dt(String creat_dt) {
		this.creat_dt = creat_dt;
	}

	/* *******************************************************************************************
	* 사용여부
	* ******************************************************************************************* */
	private String use_at;

	public String getUse_at() {
		return use_at;
	}

	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}

	/* *******************************************************************************************
	* 파일일련번호
	* ******************************************************************************************* */
	private String file_sn;

	public String getFile_sn() {
		return file_sn;
	}

	public void setFile_sn(String file_sn) {
		this.file_sn = file_sn;
	}
	
	/* *******************************************************************************************
	* 파일설명
	* ******************************************************************************************* */
	private String file_cn;

	public String getFile_cn() {
		return file_cn;
	}

	public void setFile_cn(String file_cn) {
		this.file_cn = file_cn;
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
	* 공급업체명
	* ******************************************************************************************* */
	private String bl_company;

	public String getBl_company() {
		return bl_company;
	}

	public void setBl_company(String bl_company) {
		this.bl_company = bl_company;
	}
	
	/* *******************************************************************************************
	* 검색어
	* ******************************************************************************************* */
	private String searchKeyword;

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
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
	* 검색파일설명
	* ******************************************************************************************* */
	private String searchFileCn;

	public String getSearchFileCn() {
		return searchFileCn;
	}

	public void setSearchFileCn(String searchFileCn) {
		this.searchFileCn = searchFileCn;
	}

	
	/* *******************************************************************************************
	* 검색파일아이디
	* ******************************************************************************************* */
	private String searchAtchFileId;

	public String getSearchAtchFileId() {
		return searchAtchFileId;
	}

	public void setSearchAtchFileId(String searchAtchFileId) {
		this.searchAtchFileId = searchAtchFileId;
	}
	
	

}

