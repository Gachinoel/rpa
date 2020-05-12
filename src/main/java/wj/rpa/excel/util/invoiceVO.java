package wj.rpa.excel.util;

public class invoiceVO {
	
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
    
    /** 포워딩 인보이스번호 */
    private String searchWrd = "";
    
    public String getsearchWrd() {
    	return searchWrd;
    }
    
    public void setsearchWrdo(String searchWrd) {
    	this.searchWrd = searchWrd;
    }
    

}
