package wj.rpa.excel.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 게시판 속성 정보를 관리하기 위한 VO  클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  이삼섭          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
public class ExcelMasterVO extends ExcelMaster implements Serializable {
	/**  엑셀4 통관 */
	private String t4SingoNo = "";
	public String gett4SingoNo() {
		return t4SingoNo;
	}
	public void sett4SingoNo(String t4SingoNo) {
		this.t4SingoNo = t4SingoNo;
	}

	private String t4SGSG = "";
	public String gett4SGSG() {
		return t4SGSG;
	}
	public void sett4SGSG(String t4SGSG) {
		this.t4SGSG = t4SGSG;
	}

	private String t4SingoDt = "";
	public String gett4SingoDt() {
		return t4SingoDt;
	}
	public void sett4SingoDt(String t4SingoDt) {
		this.t4SingoDt = t4SingoDt;
	}

	private String t4CifUsd = "";
	public String gett4CifUsd() {
		return t4CifUsd;
	}
	public void sett4CifUsd(String t4CifUsd) {
		this.t4CifUsd = t4CifUsd;
	}
	private String t4CifKrw = "";
	public String gett4CifKrw() {
		return t4CifKrw;
	}
	public void sett4CifKrw(String t4CifKrw) {
		this.t4CifKrw = t4CifKrw;
	}
	private String t4BGSGP = "";
	public String gett4BGSGP() {
		return t4BGSGP;
	}
	public void sett4BGSGP(String t4BGSGP) {
		this.t4BGSGP = t4BGSGP;
	}
	
	private String t4GwanSe = "";
	public String gett4GwanSe() {
		return t4GwanSe;
	}
	public void sett4GwanSe(String t4GwanSe) {
		this.t4GwanSe = t4GwanSe;
	}
	private String t4Bugase = "";
	public String gett4Bugase() {
		return t4Bugase;
	}
	public void sett4Bugase(String t4Bugase) {
		this.t4Bugase = t4Bugase;
	}
	private String t4LanNo2 = "";
	public String gett4LanNo2() {
		return t4LanNo2;
	}
	public void sett4LanNo2(String t4LanNo2) {
		this.t4LanNo2 = t4LanNo2;
	}
	
	private String t4HeangNo = "";
	public String gett4HeangNo() {
		return t4HeangNo;
	}
	public void sett4HeangNo(String t4HeangNo) {
		this.t4HeangNo = t4HeangNo;
	}
	
	private String t4GSY = "";
	public String gett4GSY() {
		return t4GSY;
	}
	public void sett4GSY(String t4GSY) {
		this.t4GSY = t4GSY;
	}
	private String t4SJGSE = "";
	public String gett4SJGSE(){
		return t4SJGSE;
	}
	public void sett4SJGSE(String t4SJGSE) {
		this.t4SJGSE = t4SJGSE;
	}
	
	
	
	
	
	
	
	
	
	/**  엑셀3 BL */
	private String t3HblNo = "";
	public String gett3HblNo() {
		return t3HblNo;
	}
	public void sett3HblNo(String t3HblNo) {
		this.t3HblNo = t3HblNo;
	}

	private String t3BSDt = "";
	public String gett3BSDt() {
		return t3BSDt;
	}
	public void sett3BSDt(String t3BSDt) {
		this.t3BSDt = t3BSDt;
	}
	private String t3EtdDt = "";
	public String gett3EtdDt() {
		return t3EtdDt;
	}
	public void sett3EtdDt(String t3EtdDt) {
		this.t3EtdDt = t3EtdDt;
	}
	private String t3EtaDt = "";
	public String gett3EtaDt() {
		return t3EtaDt;
	}
	public void sett3EtaDt(String t3EtaDt) {
		this.t3EtaDt = t3EtaDt;
	}
	private String t3SJGCd = "";
	public String gett3SJGCd() {
		return t3SJGCd;
	}
	public void sett3SJGCd(String t3SJGCd) {
		this.t3SJGCd = t3SJGCd;
	}
	private String t3DJGCd = "";
	public String gett3DJGCd() {
		return t3DJGCd;
	}
	public void sett3DJGCd(String t3DJGCd) {
		this.t3DJGCd = t3DJGCd;
	}
	private String t3SJHCd = "";
	public String gett3SJHCd() {
		return t3SJHCd;
	}
	public void sett3SJHCd(String t3SJHCd) {
		this.t3SJHCd = t3SJHCd;
	}
	private String t3DCHCd = "";
	public String gett3DCHCd() {
		return t3DCHCd;
	}
	public void sett3DCHCd(String t3DCHCd) {
		this.t3DCHCd = t3DCHCd;
	}
	private String t3FVNm = "";
	public String gett3FVNm() {
		return t3FVNm;
	}
	public void sett3FVNm(String t3FVNm) {
		this.t3FVNm = t3FVNm;
	}
	private String t3C20Qt = "";
	public String gett3C20Qt() {
		return t3C20Qt;
	}
	public void sett3C20Qt(String t3C20Qt) {
		this.t3C20Qt = t3C20Qt;
	}
	private String t3C40Qt = "";
	public String gett3C40Qt() {
		return t3C40Qt;
	}
	public void sett3C40Qt(String t3C40Qt) {
		this.t3C40Qt = t3C40Qt;
	}
	
	private String t3USgb = "";
	public String gett3USgb() {
		return t3USgb;
	}
	public void sett3USgb(String t3USgb) {
		this.t3USgb = t3USgb;
	}
	private String t3CPJEa = "";
	public String gett3CPJEa() {
		return t3CPJEa;
	}
	public void sett3CPJEa(String t3CPJEa) {
		this.t3CPJEa = t3CPJEa;
	}
	
	private String t3NWg = "";
	public String gett3NWg() {
		return t3NWg;
	}
	public void sett3NWg(String t3NWg) {
		this.t3NWg = t3NWg;
	}
	private String t3GWg = "";
	public String gett3GWg() {
		return t3GWg;
	}
	public void sett3GWg(String t3GWg) {
		this.t3GWg = t3GWg;
	}
	private String t3SLSBDt = "";
	public String gett3SLSBDt() {
		return t3SLSBDt;
	}
	public void sett3SLSBDt(String t3SLSBDt) {
		this.t3SLSBDt = t3SLSBDt;
	}
	
	private String t3SJSLSBCCd = "";
	public String gett3SJSLSBCCd() {
		return t3SJSLSBCCd;
	}
	public void settt3SJSLSBCCd(String t3SJSLSBCCd) {
		this.t3SJSLSBCCd = t3SJSLSBCCd;
	}
	
	private String t3SIHDt = "";
	public String gett3SIHDt() {
		return t3SIHDt;
	}
	public void sett3SIHDt(String t3SIHDt) {
		this.t3SIHDt = t3SIHDt;
	}
	
	private String t3IGYJDt = "";
	public String gett3IGYJDt() {
		return t3IGYJDt;
	}
	public void sett3IGYJDt(String t3IGYJDt) {
		this.t3IGYJDt = t3IGYJDt;
	}
	
	
	/**  엑셀2 cargo */
	private String t3BlDt = "";
	public String gett3BlDt() {
		return t3BlDt;
	}
	public void sett3BlDt(String t3BlDt) {
		this.t3BlDt = t3BlDt;
	}
	
	//private String t3SSDt = "";
	private String t2StockNo = "";
	public String gett2StockNo() {
		return t2StockNo;
	}
	public void sett2StockNo(String t2StockNo) {
		this.t2StockNo = t2StockNo;
	}
	
	private String t2JyRt = "";
	public String gett2JyRt() {
		return t2JyRt;
	}
	public void sett2JyRt(String t2JyRt) {
		this.t2JyRt = t2JyRt;
	}
	private String t4AACUERt = "";
	public String gett4AACUERt() {
		return t4AACUERt;
	}
	public void sett4AACUERt(String t4AACUERt) {
		this.t4AACUERt = t4AACUERt;
	}
	private String t4AACDw = "";
	public String gett4AACDw() {
		return t4AACDw;
	}
	public void sett4AACDw(String t4AACDw) {
		this.t4AACDw = t4AACDw;
	}
	//private String t3SSDt = "";
	private String t2CKEIFee = "";
	public String gett2CKEIFee() {
		return t2CKEIFee;
	}
	public void sett2CKEIFee(String t2CKEIFee) {
		this.t2CKEIFee = t2CKEIFee;
	}
	private String t3InNo = "";
	public String gett3InNo() {
		return t3InNo;
	}
	public void sett3InNo(String t3InNo) {
		this.t3InNo = t3InNo;
	}
	//private String t3HBlNo = "";
		
	
	/** 인보이스 엑셀 */
    private String t1InNo = "";
    public String gett1InNo() {
    	return t1InNo;
    }
    public void sett1InNo(String t1InNo) {
    	this.t1InNo = t1InNo;
    }
	
    private String t1Carry = "";
    public String gett1Carry() {
    	return t1Carry;
    }
    public void sett1Carry(String t1Carry) {
    	this.t1Carry = t1Carry;
    }
	
    /** 인보이스 엑셀 */
    private String t3SSDt = "";
    public String gett3SSDt() {
    	return t3SSDt;
    }
    public void sett3SSDt(String t3SSDt) {
    	this.t3SSDt = t3SSDt;
    }
	
    
    private String t1PoNo = "";
    public String gett1PoNo() {
    	return t1PoNo;
    }
    public void settt1PoNo(String t1PoNo) {
    	this.t1PoNo = t1PoNo;
    }
	
    private String t1PoLineNo = "";
    public String gett1PoLineNo() {
    	return t1PoLineNo;
    }
    public void sett1PoLineNo(String t1PoLineNo) {
    	this.t1PoLineNo = t1PoLineNo;
    }
    
    
    
    private String t1ItemCd = "";
    public String gett1ItemCd() {
    	return t1ItemCd;
    }
    public void sett1ItemCd(String t1ItemCd) {
    	this.t1ItemCd = t1ItemCd;
    }
	
    private String t1Qty = "";
    public String gett1Qty() {
    	return t1Qty;
    }
    public void sett1Qty(String t1Qty) {
    	this.t1Qty = t1Qty;
    }
	
    private String t4DanGa = "";
    public String gett4DanGa() {
    	return t4DanGa;
    }
    public void sett4DanGa(String t4DanGa) {
    	this.t4DanGa = t4DanGa;
    }
	
  
  private String t4DanGa2 = "";
  public String gett4DanGa2() {
  	return t4DanGa2;
  }
  public void sett4DanGa2(String t4DanGa2) {
  	this.t4DanGa2 = t4DanGa2;
  }
	
  private String t4GeumAeg = "";
  public String gett4GeumAeg() {
  	return t4GeumAeg;
  }
  public void sett4GeumAeg(String t4GeumAeg) {
  	this.t4GeumAeg = t4GeumAeg;
  }
  
  private String t4JeCurDw = "";
  public String gett4JeCurDw() {
  	return t4JeCurDw;
  }
  public void sett4JeCurDw(String t4JeCurDw) {
  	this.t4JeCurDw = t4JeCurDw;
  }
	
  private String t4SebeonCd = "";
  public String gett4SebeonCd() {
  	return t4SebeonCd;
  }
  public void sett4SebeonCd(String t4SebeonCd) {
  	this.t4SebeonCd = t4SebeonCd;
  }
  
  private String t3GgCoCd2 = "";
  public String gett3GgCoCd2() {
  	return t3GgCoCd2;
  }
  public void sett3GgCoCd2(String t3GgCoCd2) {
  	this.t3GgCoCd2 = t3GgCoCd2;
  }
  
  private String t3HBlNo = "";
  public String gett3HBlNo() {
  	return t3HBlNo;
  }
  public void sett3HBlNo(String t3HBlNo) {
  	this.t3HBlNo = t3HBlNo;
  }
  
  private String t4IndoJk = "";
  public String gett4IndoJk() {
  	return t4IndoJk;
  }
  public void sett4IndoJk(String t4IndoJk) {
  	this.t4IndoJk = t4IndoJk;
  }
 
  /** 인보이스 끝 */
  
  
  
  
  
  
  
  
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 포워딩 인보이스번호 */
    private String t3_inno = "";
    
    public String gett3_inno() {
    	return t3_inno;
    }
    
    public void sett3_inno(String t3_inno) {
    	this.t3_inno = t3_inno;
    }
	
	/** 무역 진행단계1 */
    private String t3_blno = "";
    
    public String gett3_blno() {
    	return t3_blno;
    }
    
    public void sett3_blno(String t3_blno) {
    	this.t3_blno = t3_blno;
    }
	
    /** 무역 진행단계1 */
    private String t3_sjdt = "";
    
    public String gett3_sjdt() {
	return t3_sjdt;
    }
    public void sett3_sjdt(String t3_sjdt) {
	this.t3_sjdt = t3_sjdt;
    }
	
		
	/** 무역 진행단계1 */
    private String t1_yn = "";
    
    public String gett1_yn() {
	return t1_yn;
    }
    public void setbl_date(String t1_yn) {
	this.t1_yn = t1_yn;
    }
	
    /** 무역 진행단계2 */
    private String t2_yn = "";
    
    public String gett2_yn() {
	return t2_yn;
    }
    public void sett2_yn(String t2_yn) {
	this.t2_yn = t2_yn;
    }
	
    /** 무역 진행단계3 */
    private String t3_yn = "";
    
    public String gett3_yn() {
	return t3_yn;
    }
    public void sett3_yn(String t3_yn) {
	this.t3_yn = t3_yn;
    }
	
    /** 무역 진행단계4 */
    private String t4_yn = "";
    
    public String gett4_yn() {
	return t4_yn;
    }
    public void sett4_yn(String t4_yn) {
	this.t4_yn = t4_yn;
    }
    
    
    
    
    /**
	 *  serialVersion UID
	 */
	private static final long serialVersionUID = -8070768280461816170L;

	/** 검색시작일 */
    private String searchBgnDe = "";

    /** 검색조건 */
    private String searchCnd = "";

    /** 검색종료일 */
    private String searchEndDe = "";

    /** 검색단어 */
    private String searchWrd = "";

    /** 정렬순서(DESC,ASC) */
    private String sortOrdr = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    

    /**
     * searchBgnDe attribute를 리턴한다.
     *
     * @return the searchBgnDe
     */
    public String getSearchBgnDe() {
	return searchBgnDe;
    }

    /**
     * searchBgnDe attribute 값을 설정한다.
     *
     * @param searchBgnDe
     *            the searchBgnDe to set
     */
    public void setSearchBgnDe(String searchBgnDe) {
	this.searchBgnDe = searchBgnDe;
    }

    /**
     * searchCnd attribute를 리턴한다.
     *
     * @return the searchCnd
     */
    public String getSearchCnd() {
	return searchCnd;
    }

    /**
     * searchCnd attribute 값을 설정한다.
     *
     * @param searchCnd
     *            the searchCnd to set
     */
    public void setSearchCnd(String searchCnd) {
	this.searchCnd = searchCnd;
    }

    /**
     * searchEndDe attribute를 리턴한다.
     *
     * @return the searchEndDe
     */
    public String getSearchEndDe() {
	return searchEndDe;
    }

    /**
     * searchEndDe attribute 값을 설정한다.
     *
     * @param searchEndDe
     *            the searchEndDe to set
     */
    public void setSearchEndDe(String searchEndDe) {
	this.searchEndDe = searchEndDe;
    }

    /**
     * searchWrd attribute를 리턴한다.
     *
     * @return the searchWrd
     */
    public String getSearchWrd() {
	return searchWrd;
    }

    /**
     * searchWrd attribute 값을 설정한다.
     *
     * @param searchWrd
     *            the searchWrd to set
     */
    public void setSearchWrd(String searchWrd) {
	this.searchWrd = searchWrd;
    }

    /**
     * sortOrdr attribute를 리턴한다.
     *
     * @return the sortOrdr
     */
    public String getSortOrdr() {
	return sortOrdr;
    }

    /**
     * sortOrdr attribute 값을 설정한다.
     *
     * @param sortOrdr
     *            the sortOrdr to set
     */
    public void setSortOrdr(String sortOrdr) {
	this.sortOrdr = sortOrdr;
    }

    /**
     * searchUseYn attribute를 리턴한다.
     *
     * @return the searchUseYn
     */
    public String getSearchUseYn() {
	return searchUseYn;
    }

    /**
     * searchUseYn attribute 값을 설정한다.
     *
     * @param searchUseYn
     *            the searchUseYn to set
     */
    public void setSearchUseYn(String searchUseYn) {
	this.searchUseYn = searchUseYn;
    }

    

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
