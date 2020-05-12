package egovframework.let.cop.bbs.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
public class BoardMasterVO extends BoardMaster implements Serializable {
	
	
	/*SAP LIST*/
	private String bktxt = "";
	public void setbktxt(String bktxt) {
		this.bktxt = bktxt;
	}
	public String getbktxt() {
		return bktxt;
	}
	
	private String zfimdno = "";
	public void setzfimdno(String zfimdno) {
		this.zfimdno = zfimdno;
	}
	public String getzfimdno() {
		return zfimdno;
	}
	
	private String cond_type = "";
	public void setcond_type(String cond_type) {
		this.cond_type = cond_type;
	}
	public String getcond_type() {
		return cond_type;
	}
	
	private String cond_type1 = "";
	public void setcond_type1(String cond_type1) {
		this.cond_type1 = cond_type1;
	}
	public String getcond_type1() {
		return cond_type1;
	}
	
	private String vtext = "";
	public void setvtext(String vtext) {
		this.vtext = vtext;
	}
	public String getvtext() {
		return vtext;
	}
	
	private String spras = "";
	public void setcspras(String spras) {
		this.spras = spras;
	}
	public String getspras() {
		return spras;
	}
	
	private String wrbtr = "";
	public void setwrbtr(String wrbtr) {
		this.wrbtr = wrbtr;
	}
	public String getwrbtr() {
		return wrbtr;
	}
	private String dmbtr = "";
	public void setdmbtr(String dmbtr) {
		this.dmbtr = dmbtr;
	}
	public String getdmbtr() {
		return dmbtr;
	}
	private String kursf = "";
	public void setkursf(String kursf) {
		this.kursf = kursf;
	}
	public String getkursf() {
		return kursf;
	}
	
	private String waers = "";
	public void setwaers(String waers) {
		this.waers = waers;
	}
	public String getwaers() {
		return waers;
	}
	
	private String zfacdo = "";
	public void setzfacdo(String zfacdo) {
		this.zfacdo = zfacdo;
	}
	public String getzfacdo() {
		return zfacdo;
	}
	
	private String belnr = "";
	public void setbelnr(String belnr) {
		this.belnr = belnr;
	}
	public String getbelnr() {
		return belnr;
	}

	private String bldat = "";
	public void setbldat(String bldat) {
		this.bldat = bldat;
	}
	public String getbldat() {
		return bldat;
	}
	
	private String budat = "";
	public void setbudat(String budat) {
		this.budat = budat;
	}
	public String getbudat() {
		return budat;
	}
	
	
	private String lifnr = "";
	public void setlifnr(String lifnr) {
		this.lifnr = lifnr;
	}
	public String getlifnr() {
		return lifnr;
	}
	
	private String zfcstgrp = "";
	public void setzfcstgrp(String zfcstgrp) {
		this.zfcstgrp = zfcstgrp;
	}
	public String getzfcstgrp() {
		return zfcstgrp;
	}
	
	private String mwskz = "";
	public void setmwskz(String mwskz) {
		this.mwskz = mwskz;
	}
	public String getmwskz() {
		return mwskz;
	}
	
	private int filecnt = 0;
	public void setfilecnt(int filecnt) {
		this.filecnt = filecnt;
	}
	public int getfilecnt() {
		return filecnt;
	}
	private String filetype = "";
	public void setfiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getfiletype() {
		return filetype;
	}
	private String zfcd = "";
	public void setzfcd(String zfcd) {
		this.zfcd = zfcd;
	}
	public String getzfcd() {
		return zfcd;
	}
	
	private String n16 = "";
	public void setn16(String n16) {
		this.n16 = n16;
	}
	public String getn16() {
		return n16;
	}
	
	private String name1 = "";
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName1() {
		return name1;
	}
	private String stcd2 = "";
	public void setStcd2(String stcd2) {
		this.stcd2 = stcd2;
	}
	public String getStcd2() {
		return stcd2;
	}
	private String zterm = "";
	public void setzterm(String zterm) {
		this.zterm = zterm;
	}
	public String getzterm() {
		return zterm;
	}
	
	private String wmwst = "";
	public void setwmwst(String wmwst) {
		this.wmwst = wmwst;
	}
	public String getwmwst() {
		return wmwst;
	}
	
	private String sapcheck = "";
	public void setsapcheck(String sapcheck) {
		this.sapcheck = sapcheck;
	}
	public String getsapcheck() {
		return sapcheck;
	}
	
	private String sapcolor = "";
	public void setsapcolor(String sapcolor) {
		this.sapcolor = sapcolor;
	}
	public String getsapcolor() {
		return sapcolor;
	}
	
	
	
	/**  서브항목 */
	private String c1yn = "";
	public void setc1yn(String c1yn) {
		this.c1yn = c1yn;
	}
	public String getc1yn() {
		return c1yn;
	}
	
	private String c2yn = "";
	public void setc2yn(String c2yn) {
		this.c2yn = c2yn;
	}
	public String getc2yn() {
		return c2yn;
	}
	
	private String c3yn = "";
	public void setc3Yn(String c3yn) {
		this.c3yn = c3yn;
	}
	public String getc3yn() {
		return c3yn;
	}
	
	private String c4yn = "";
	public void setc4Yn(String c4yn) {
		this.c4yn = c4yn;
	}
	public String getc4yn() {
		return c4yn;
	}
	
	private String c5yn = "";
	public void setc5Yn(String c5yn) {
		this.c5yn = c5yn;
	}
	public String getc5yn() {
		return c5yn;
	}
	
	private String c6yn = "";
	public void setc6Yn(String c6yn) {
		this.c6yn = c6yn;
	}
	public String getc6yn() {
		return c6yn;
	}
	
	private String c7yn = "";
	public void setc7yn(String c7yn) {
		this.c7yn = c7yn;
	}
	public String getc7yn() {
		return c7yn;
	}
	
	private String c8yn = "";
	public void setc8yn(String c8yn) {
		this.c8yn = c8yn;
	}
	public String getc8yn() {
		return c8yn;
	}
	
	private String c9yn = "";
	public void setc9yn(String c9yn) {
		this.c9yn = c9yn;
	}
	public String getc9yn() {
		return c9yn;
	}
	
	private String c10yn = "";
	public void setc10yn(String c10yn) {
		this.c10yn = c10yn;
	}
	public String getc10yn() {
		return c10yn;
	}
	
	
	/**  엑셀5 송장 */
	
	 	
	private String t4GwanliNo = "";
	public String gett4GwanliNo() {
		return t4GwanliNo;
	}
	public void sett4GwanliNo(String t4GwanliNo) {
		this.t4GwanliNo = t4GwanliNo;
	}
	
	//BL번호 있음
	
	private String t3JBDt = "";
	public String gett3JBDt() {
		return t3JBDt;
	}
	public void sett3JBDt(String t3JBDt) {
		this.t3JBDt = t3JBDt;
	}
	
	private String t3JGDt = "";
	public String gett3JGDt() {
		return t3JGDt;
	}
	public void sett3JGDt(String t3JGDt) {
		this.t3JGDt = t3JGDt;
	}
	
	private String t3JGJk = "";
	public String gett3JGJk() {
		return t3JGJk;
	}
	public void sett3JGJk(String t3JGJk) {
		this.t3JGJk = t3JGJk;
	}
	
	/**  엑셀4 통관 */
	

	private String t4TgYcDt = "";
	public String gett4TgYcDt() {
		return t4TgYcDt;
	}
	public void settt4TgYcDt(String t4TgYcDt) {
		this.t4TgYcDt = t4TgYcDt;
	}

	private String t4SinGoJa = "";
	public String gett4SinGoJa() {
		return t4SinGoJa;
	}
	public void sett4SinGoJa(String t4SinGoJa) {
		this.t4SinGoJa = t4SinGoJa;
	}

	
	
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
    
    private String item = "";
    public String getItem() {
    	return item;
    }
    public void setItem(String item) {
    	this.item = item;
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
    private String blamt = "";
    
    public String getBlamt() {
    	return blamt;
    }
    
    public void setBlamt(String blamt) {
    	this.blamt = blamt;
    }
    
    /** 무역 진행단계1 */
    private String taxamt = "";
    
    public String getTaxamt() {
    	return taxamt;
    }
    
    public void setTaxamt(String taxamt) {
    	this.taxamt = taxamt;
    }
    
    /** 무역 진행단계1 */
    private String wrbtrp = "";
    
    public String getWrbtrp() {
    	return wrbtrp;
    }
    
    public void setWrbtrp(String wrbtrp) {
    	this.wrbtrp = wrbtrp;
    }
    
    
    /** 무역 진행단계1 */
    private String hwaer = "";
    
    public String getHwaer() {
    	return hwaer;
    }
    
    public void setHwaer(String hwaer) {
    	this.hwaer = hwaer;
    }
    
    /** 무역 진행단계1 */
    private String lastday = "";
    
    public String getLastday() {
    	return lastday;
    }
    
    public void setLastday(String lastday) {
    	this.lastday = lastday;
    }
    
    /** 무역 진행단계1 */
    private String indat = "";
    
    public String getIndat() {
    	return indat;
    }
    
    public void setIndat(String indat) {
    	this.indat = indat;
    }
    
    /** 무역 진행단계1 */
    private String per = "";
    
    public String getPer() {
    	return per;
    }
    
    public void setPer(String per) {
    	this.per = per;
    }
    
    
    
    /** 무역 진행단계1 */
    private String t3_confirm = "";
    
    public String gett3_confirm() {
    	return t3_confirm;
    }
    
    public void sett3_confirm(String t3_confirm) {
    	this.t3_confirm = t3_confirm;
    }
    
    /** 무역 진행단계1 */
    private String t3_preconfirm = "";
    
    public String gett3_preconfirm() {
    	return t3_preconfirm;
    }
    
    public void sett3_preconfirm(String t3_preconfirm) {
    	this.t3_preconfirm = t3_preconfirm;
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
    private String t3_regflag = "";
    
    public String gett3_regflag() {
	return t3_regflag;
    }
    public void sett3_regflag(String t3_regflag) {
	this.t3_regflag = t3_regflag;
    }
    
    /** 무역 진행단계1 */
    private String t3_seq = "";
    
    public String gett3_seq() {
	return t3_seq;
    }
    public void sett3_seq(String t3_seq) {
	this.t3_seq = t3_seq;
    }
    
    /** 무역 진행단계1 */
    private String t4_company = "";
    
    public String gett4_company() {
	return t4_company;
    }
    public void sett4_company(String t4_company) {
	this.t4_company = t4_company;
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
    
    
    /** 무역 진행단계1 */
    private String t1_color = "";
    
    public String gett1_color() {
	return t1_color;
    }
    public void sett1_coler(String t1_color) {
	this.t1_color = t1_color;
    }
	
    /** 무역 진행단계2 */
    private String t2_color = "";
    
    public String gett2_color() {
	return t2_color;
    }
    public void sett2_color(String t2_color) {
	this.t2_color = t2_color;
    }
	
    /** 무역 진행단계3 */
    private String t3_color = "";
    
    public String gett3_color() {
	return t3_color;
    }
    public void sett3_color(String t3_color) {
	this.t3_color = t3_color;
    }
	
    /** 무역 진행단계4 */
    private String t4_color = "";
    
    public String gett4_color() {
	return t4_color;
    }
    public void sett4_color(String t4_color) {
	this.t4_color = t4_color;
    }
    
    /**
	 *  serialVersion UID
	 */
	private static final long serialVersionUID = -8070768280461816170L;

	/** 검색시작일 */
    private String searchBgnDe = "";

    /** 검색조건 */
    private String searchCnd = "";
    
    /** 검색조건 */
    private String searchNowCnd = "";
    
    /** 검색조건 */
    private String searchPreCnd = "";

    /** 검색종료일 */
    private String searchEndDe = "";

    /** 검색단어 */
    private String searchWrd = "";

    /** 정렬순서(DESC,ASC) */
    private String sortOrdr = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /** rowNo */
    private int rowNo = 0;

    /** 최초 등록자명 */
    private String frstRegisterNm = "";

    /** 게시판유형 코드명 */
    private String bbsTyCodeNm = "";

    /** 게시판속성 코드명 */
    private String bbsAttrbCodeNm = "";

    /** 템플릿 명 */
    private String tmplatNm = "";

    /** 최종 수정자명 */
    private String lastUpdusrNm = "";

    /** 권한지정 여부 */
    private String authFlag = "";

    /** 템플릿경로 */
    private String tmplatCours = "";

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
    
    /** 검색시작일 */
    private String searchExtraBgnDe = "";
    /**
     * searchBgnDe attribute를 리턴한다.
     *
     * @return the searchBgnDe
     */
    public String getSearchExtraBgnDe() {
	return searchExtraBgnDe;
    }

    /**
     * searchBgnDe attribute 값을 설정한다.
     *
     * @param searchBgnDe
     *            the searchBgnDe to set
     */
    public void setSearchExtraBgnDe(String searchExtraBgnDe) {
	this.searchExtraBgnDe = searchExtraBgnDe;
    }
    
    /** 검색시작일 */
    private String searchExtraEndDe = "";
    /**
     * searchBgnDe attribute를 리턴한다.
     *
     * @return the searchBgnDe
     */
    public String getSearchExtraEndDe() {
	return searchExtraEndDe;
    }

    /**
     * searchBgnDe attribute 값을 설정한다.
     *
     * @param searchBgnDe
     *            the searchBgnDe to set
     */
    public void setSearchExtraEndDe(String searchExtraEndDe) {
	this.searchExtraEndDe = searchExtraEndDe;
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
    public void setSearchNowCnd(String searchNowCnd) {
	this.searchNowCnd = searchNowCnd;
    }

    /**
     * searchCnd attribute를 리턴한다.
     *
     * @return the searchCnd
     */
    public String getSearchNowCnd() {
	return searchNowCnd;
    }

    /**
     * searchCnd attribute 값을 설정한다.
     *
     * @param searchCnd
     *            the searchCnd to set
     */
    public void setSearchPreCnd(String searchPreCnd) {
	this.searchPreCnd = searchPreCnd;
    }

    /**
     * searchCnd attribute를 리턴한다.
     *
     * @return the searchCnd
     */
    public String getSearchPreCnd() {
	return searchPreCnd;
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
     * pageIndex attribute를 리턴한다.
     *
     * @return the pageIndex
     */
    public int getPageIndex() {
	return pageIndex;
    }

    /**
     * pageIndex attribute 값을 설정한다.
     *
     * @param pageIndex
     *            the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
	this.pageIndex = pageIndex;
    }

    /**
     * pageUnit attribute를 리턴한다.
     *
     * @return the pageUnit
     */
    public int getPageUnit() {
	return pageUnit;
    }

    /**
     * pageUnit attribute 값을 설정한다.
     *
     * @param pageUnit
     *            the pageUnit to set
     */
    public void setPageUnit(int pageUnit) {
	this.pageUnit = pageUnit;
    }

    /**
     * pageSize attribute를 리턴한다.
     *
     * @return the pageSize
     */
    public int getPageSize() {
	return pageSize;
    }

    /**
     * pageSize attribute 값을 설정한다.
     *
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    /**
     * firstIndex attribute를 리턴한다.
     *
     * @return the firstIndex
     */
    public int getFirstIndex() {
	return firstIndex;
    }

    /**
     * firstIndex attribute 값을 설정한다.
     *
     * @param firstIndex
     *            the firstIndex to set
     */
    public void setFirstIndex(int firstIndex) {
	this.firstIndex = firstIndex;
    }

    /**
     * lastIndex attribute를 리턴한다.
     *
     * @return the lastIndex
     */
    public int getLastIndex() {
	return lastIndex;
    }

    /**
     * lastIndex attribute 값을 설정한다.
     *
     * @param lastIndex
     *            the lastIndex to set
     */
    public void setLastIndex(int lastIndex) {
	this.lastIndex = lastIndex;
    }

    /**
     * recordCountPerPage attribute를 리턴한다.
     *
     * @return the recordCountPerPage
     */
    public int getRecordCountPerPage() {
	return recordCountPerPage;
    }

    /**
     * recordCountPerPage attribute 값을 설정한다.
     *
     * @param recordCountPerPage
     *            the recordCountPerPage to set
     */
    public void setRecordCountPerPage(int recordCountPerPage) {
	this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * rowNo attribute를 리턴한다.
     *
     * @return the rowNo
     */
    public int getRowNo() {
	return rowNo;
    }

    /**
     * rowNo attribute 값을 설정한다.
     *
     * @param rowNo
     *            the rowNo to set
     */
    public void setRowNo(int rowNo) {
	this.rowNo = rowNo;
    }

    /**
     * frstRegisterNm attribute를 리턴한다.
     *
     * @return the frstRegisterNm
     */
    public String getFrstRegisterNm() {
	return frstRegisterNm;
    }

    /**
     * frstRegisterNm attribute 값을 설정한다.
     *
     * @param frstRegisterNm
     *            the frstRegisterNm to set
     */
    public void setFrstRegisterNm(String frstRegisterNm) {
	this.frstRegisterNm = frstRegisterNm;
    }

    /**
     * bbsTyCodeNm attribute를 리턴한다.
     *
     * @return the bbsTyCodeNm
     */
    public String getBbsTyCodeNm() {
	return bbsTyCodeNm;
    }

    /**
     * bbsTyCodeNm attribute 값을 설정한다.
     *
     * @param bbsTyCodeNm
     *            the bbsTyCodeNm to set
     */
    public void setBbsTyCodeNm(String bbsTyCodeNm) {
	this.bbsTyCodeNm = bbsTyCodeNm;
    }

    /**
     * bbsAttrbCodeNm attribute를 리턴한다.
     *
     * @return the bbsAttrbCodeNm
     */
    public String getBbsAttrbCodeNm() {
	return bbsAttrbCodeNm;
    }

    /**
     * bbsAttrbCodeNm attribute 값을 설정한다.
     *
     * @param bbsAttrbCodeNm
     *            the bbsAttrbCodeNm to set
     */
    public void setBbsAttrbCodeNm(String bbsAttrbCodeNm) {
	this.bbsAttrbCodeNm = bbsAttrbCodeNm;
    }

    /**
     * tmplatNm attribute를 리턴한다.
     *
     * @return the tmplatNm
     */
    public String getTmplatNm() {
	return tmplatNm;
    }

    /**
     * tmplatNm attribute 값을 설정한다.
     *
     * @param tmplatNm
     *            the tmplatNm to set
     */
    public void setTmplatNm(String tmplatNm) {
	this.tmplatNm = tmplatNm;
    }

    /**
     * lastUpdusrNm attribute를 리턴한다.
     *
     * @return the lastUpdusrNm
     */
    public String getLastUpdusrNm() {
	return lastUpdusrNm;
    }

    /**
     * lastUpdusrNm attribute 값을 설정한다.
     *
     * @param lastUpdusrNm
     *            the lastUpdusrNm to set
     */
    public void setLastUpdusrNm(String lastUpdusrNm) {
	this.lastUpdusrNm = lastUpdusrNm;
    }

    /**
     * authFlag attribute를 리턴한다.
     *
     * @return the authFlag
     */
    public String getAuthFlag() {
	return authFlag;
    }

    /**
     * authFlag attribute 값을 설정한다.
     *
     * @param authFlag
     *            the authFlag to set
     */
    public void setAuthFlag(String authFlag) {
	this.authFlag = authFlag;
    }

    /**
     * tmplatCours attribute를 리턴한다.
     *
     * @return the tmplatCours
     */
    public String getTmplatCours() {
	return tmplatCours;
    }

    /**
     * tmplatCours attribute 값을 설정한다.
     *
     * @param tmplatCours
     *            the tmplatCours to set
     */
    public void setTmplatCours(String tmplatCours) {
	this.tmplatCours = tmplatCours;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
    
/*SAP리스트*/
    /** 조회월 */
    private String extra_month = "";
    
    public String getExtra_month() {
    	return extra_month;
    }
    
    public void setExtra_month(String extra_month) {
    	this.extra_month = extra_month;
    }
    
    /** 업체명 */
    private String extra_company = "";
    
    public String getExtra_company() {
    	return extra_company;
    }
    
    public void setExtra_company(String extra_company) {
    	this.extra_company = extra_company;
    }
    
    /** 세부구분 */
    private String extra_detail = "";
    
    public String getExtra_detail() {
    	return extra_detail;
    }
    
    public void setExtra_detail(String extra_detail) {
    	this.extra_detail = extra_detail;
    }
    
    /** 관세사 */
    private String extra_custom = "";
    
    public String getExtra_custom() {
    	return extra_custom;
    }
    
    public void setExtra_custom(String extra_custom) {
    	this.extra_custom = extra_custom;
    }
    
    /** 공급가 */
    private String extra_price = "";
    
    public String getExtra_price() {
    	return extra_price;
    }
    
    public void setExtra_price(String extra_price) {
    	this.extra_price = extra_price;
    }
    
    /** 부가세 */
    private String extra_tax = "";
    
    public String getExtra_tax() {
    	return extra_tax;
    }
    
    public void setExtra_tax(String extra_tax) {
    	this.extra_tax = extra_tax;
    }
    
    /** 합계 */
    private String extra_total = "";
    
    public String getExtra_total() {
    	return extra_total;
    }
    
    public void setExtra_total(String extra_total) {
    	this.extra_total = extra_total;
    }
    
    /** 스타일 */
    private String income_gubun = "";
    
    public String getIncome_gubun() {
    	return income_gubun;
    }
    
    public void setIncome_gubun(String income_gubun) {
    	this.income_gubun = income_gubun;
    }
    
    private String income_gubun1 = "";
    
    public String getIncome_gubun1() {
    	return income_gubun1;
    }
    
    public void setIncome_gubun1(String income_gubun1) {
    	this.income_gubun1 = income_gubun1;
    }
    
    private String income_gubun2 = "";
    
    public String getIncome_gubun2() {
    	return income_gubun2;
    }
    
    public void setIncome_gubun2(String income_gubun2) {
    	this.income_gubun2 = income_gubun2;
    }
    
    private String income_gubun3 = "";
    
    public String getIncome_gubun3() {
    	return income_gubun3;
    }
    
    public void setIncome_gubun3(String income_gubun3) {
    	this.income_gubun3 = income_gubun3;
    }
    
    /** 스타일 */
    private String extra_style = "";
    
    public String getExtra_style() {
    	return extra_style;
    }
    
    public void setExtra_style(String extra_style) {
    	this.extra_style = extra_style;
    }
    
    /** 스타일 */
    private String income_key = "";
    
    public String getIncome_key() {
    	return income_key;
    }
    
    public void setIncome_key(String income_key) {
    	this.income_key = income_key;
    }
    
    /** 스타일 */
    private String extra_bldate = "";
    
    public String getExtra_bldate() {
    	return extra_bldate;
    }
    
    public void setExtra_bldate(String extra_bldate) {
    	this.extra_bldate = extra_bldate;
    }

    /** 업체명 */
    private String extra1_company = "";
    
    public String getExtra1_company() {
    	return extra1_company;
    }
    
    public void setExtra1_company(String extra1_company) {
    	this.extra1_company = extra1_company;
    }
    
    /** 세부구분 */
    private String extra1_detail = "";
    
    public String getExtra1_detail() {
    	return extra1_detail;
    }
    
    public void setExtra1_detail(String extra1_detail) {
    	this.extra1_detail = extra1_detail;
    }
    
    /** 관세사 */
    private String extra1_custom = "";
    
    public String getExtra1_custom() {
    	return extra1_custom;
    }
    
    public void setExtra1_custom(String extra1_custom) {
    	this.extra1_custom = extra1_custom;
    }
    
    /** 공급가 */
    private String extra1_price = "";
    
    public String getExtra1_price() {
    	return extra1_price;
    }
    
    public void setExtra1_price(String extra1_price) {
    	this.extra1_price = extra1_price;
    }
    
    /** 부가세 */
    private String extra1_tax = "";
    
    public String getExtra1_tax() {
    	return extra1_tax;
    }
    
    public void setExtra1_tax(String extra1_tax) {
    	this.extra1_tax = extra1_tax;
    }
    
    /** 합계 */
    private String extra1_total = "";
    
    public String getExtra1_total() {
    	return extra1_total;
    }
    
    public void setExtra1_total(String extra1_total) {
    	this.extra1_total = extra1_total;
    }
    
    /** 스타일 */
    private String extra1_style = "";
    
    public String getExtra1_style() {
    	return extra1_style;
    }
    
    public void setExtra1_style(String extra1_style) {
    	this.extra1_style = extra1_style;
    }
    
    /** 스타일 */
    private String income1_gubun = "";
    
    public String getIncome1_gubun() {
    	return income1_gubun;
    }
    
    public void setIncome1_gubun(String income1_gubun) {
    	this.income1_gubun = income1_gubun;
    }
    
    private String income1_gubun1 = "";
    
    public String getIncome1_gubun1() {
    	return income1_gubun1;
    }
    
    public void setIncome1_gubun1(String income1_gubun1) {
    	this.income1_gubun1 = income_gubun1;
    }
    
    private String income1_gubun2 = "";
    
    public String getIncome1_gubun2() {
    	return income1_gubun2;
    }
    
    public void setIncome1_gubun2(String income1_gubun2) {
    	this.income1_gubun2 = income1_gubun2;
    }
    
    private String income1_gubun3 = "";
    
    public String getIncome1_gubun3() {
    	return income1_gubun3;
    }
    
    public void setIncome1_gubun3(String income1_gubun3) {
    	this.income1_gubun3 = income1_gubun3;
    }
    
    private String processflag = "";
    
    public String getProcessflag() {
    	return processflag;
    }
    
    public void setProcessflag(String processflag) {
    	this.processflag = processflag;
    }
    
    private String send_dt = "";
    
    public String getSend_dt() {
    	return send_dt;
    }
    
    public void setSend_dt(String send_dt) {
    	this.send_dt = send_dt;
    }
    
    private String file_id = "";
    
    public String getFile_id() {
    	return file_id;
    }
    
    public void setFile_id(String file_id) {
    	this.file_id = file_id;
    }
    
    private String file_gubun = "";
    
    public String getFile_gubun() {
    	return file_gubun;
    }
    
    public void setFile_gubun(String file_gubun) {
    	this.file_gubun = file_gubun;
    }
    
    /**  bl번호 vo에도 있음??? */
	private String searchBlNo = "";
	
	public String getSearchBlNo() {
		return searchBlNo;
	}
	public void setSearchBlNo(String searchBlNo) {
		this.searchBlNo = searchBlNo;
	}
	
	/**  서치wrd로 조회가 안되서 바꿈 */
	private String searchBl = "";
	
	public String getSearchBl() {
		return searchBl;
	}
	public void setSearchBl(String searchBl) {
		this.searchBl = searchBl;
	}
	
	/**  bl번호 vo에도 있음??? */
	private String searchFileCn = "";
	
	public String getSearchFileCn() {
		return searchFileCn;
	}
	public void setSearchFileCn(String searchFileCn) {
		StringBuilder sb = new StringBuilder();
		sb.append("%");
		sb.append(searchFileCn);
		sb.append("%");
		
		this.searchFileCn = sb.toString();
	}
	
	/**  bl번호 vo에도 있음??? */
	private String searchAtchFileId = "";
	
	public String getSearchAtchFileId() {
		return searchAtchFileId;
	}
	public void setSearchAtchFileId(String searchAtchFileId) {
		this.searchAtchFileId = searchAtchFileId;
	}
	
	/**  서치wrd로 조회가 안되서 바꿈 */
	private String searchWaers = "";
	
	public String getSearchWaers() {
		return searchWaers;
	}
	public void setSearchWaers(String searchWaers) {

		this.searchWaers = searchWaers;
	}
	
	/**  서치wrd로 조회가 안되서 바꿈 */
	private String searchCom = "";
	
	public String getSearchCom() {
		return searchCom;
	}
	public void setSearchCom(String searchCom) {
		StringBuilder sb = new StringBuilder();
		sb.append("%");
		sb.append(searchCom);
		sb.append("%");

		this.searchCom = searchCom;
	}
	
	/**  서치wrd로 조회가 안되서 바꿈 */
	private String searchGubun = "";
	
	public String getSearchGubun() {
		return searchGubun;
	}
	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
	}
	
	/**  서치wrd로 조회가 안되서 바꿈 */
	private String searchConfirm = "";
	
	public String getSearchConfirm() {
		return searchConfirm;
	}
	public void setSearchConfirm(String searchConfirm) {
		this.searchConfirm = searchConfirm;
	}
	
	/**  서치wrd로 조회가 안되서 바꿈 */
	private String searchType = "";
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {

		this.searchType = searchType;
	}
	
	/**  서치wrd로 조회가 안되서 바꿈 */
	private String searchSendDt = "";
	
	public String getSearchSendDt() {
		return searchSendDt;
	}
	public void setSearchSendDt(String searchSendDt) {

		this.searchSendDt = searchSendDt;
	}
	
	private List<String> keyList;
	
	public List<String> getKeyList() {
	  return keyList;
	 }
	 
	 public void setKeyList(List<String> keyList) {
	  this.keyList = keyList;
	 }
	
	
	private String statement_key;
	private String yyyymm;
	private String gubun;
	private String gubun_nm;
	private String num;
	private String zfidrno;
	private String zfinrc;
	private String zfblno;
	private String zfapnm;
	private long twrbtr;
	private long wrbtr_tax;
	private String waers1;
	private long swrbtr;
	private String waers2;
	private String zfcarnm;
	private String confirm_yn;
	private String reg_flag;
	private String reg_dt;
	
	public String getStatement_key(){
		return statement_key;
	}
	public String getYyyymm(){
		return yyyymm;
	}
	public String getGubun(){
		return gubun;
	}
	public String getGubun_nm(){
		return gubun_nm;
	}
	public String getNum(){
		return num;
	}
	public String getZfidrno(){
		return zfidrno;
	}
	public String getZfinrc(){
		return zfinrc;
	}
	public String getZfblno(){
		return zfblno;
	}
	public String getZfapnm(){
		return zfapnm;
	}
	public long getTwrbtr(){
		return twrbtr;
	}
	public long getWrbtr_tax(){
		return wrbtr_tax;
	}
	public String getWaers1(){
		return waers1;
	}
	public long getSwrbtr(){
		return swrbtr;
	}
	public String getWaers2(){
		return waers2;
	}
	public String getZfcarnm(){
		return zfcarnm;
	}
	public String getConfirm_yn(){
		return confirm_yn;
	}
	public String getReg_flag(){
		return reg_flag;
	}
	public String getReg_dt(){
		return reg_dt;
	}
	
	public void setStatement_key(String statement_key) {

		this.statement_key = statement_key;
	}
	public void setYyyymm(String yyyymm) {

		this.yyyymm = yyyymm;
	}
	public void setGubun(String gubun) {

		this.gubun = gubun;
	}
	public void setGubun_nm(String gubun_nm) {

		this.gubun_nm = gubun_nm;
	}
	public void setNum(String num) {

		this.num = num;
	}
	public void setZfacdo(String zfidrno) {

		this.zfidrno = zfidrno;
	}
	public void setZfinrc(String zfinrc) {

		this.zfinrc = zfinrc;
	}
	public void setZfblno(String zfblno) {

		this.zfblno = zfblno;
	}
	public void setZfapnm(String zfapnm) {

		this.zfapnm = zfapnm;
	}
	public void setTwrbtr(long twrbtr) {

		this.twrbtr = twrbtr;
	}
	public void setWrbtr_tax(long wrbtr_tax) {

		this.wrbtr_tax = wrbtr_tax;
	}
	public void setWaers1(String waers1) {
	
		this.waers1 = waers1;
	}
	public void setSwrbtr(long swrbtr) {

		this.swrbtr = swrbtr;
	}
	public void setWaers2(String waers2) {

		this.waers2 = waers2;
	}
	public void setZfcarnm(String zfcarnm) {

		this.zfcarnm = zfcarnm;
	}
	public void setConfirm_yn(String confirm_yn) {

		this.confirm_yn = confirm_yn;
	}
	public void setReg_flag(String reg_flag) {

		this.reg_flag = reg_flag;
	}
	public void setreg_dt(String reg_dt) {

		this.reg_dt = reg_dt;
	}
			
}
