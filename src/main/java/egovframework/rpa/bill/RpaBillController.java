
package egovframework.rpa.bill;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rpa.util.service.RpaUtilService;

@Controller
public class RpaBillController {
Logger log = Logger.getLogger(this.getClass());
	
	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
   
	@Resource(name="rpaUtilService")
	RpaUtilService rpaUtilService;   
   
   
	/** EgovMessageSource */
	@Resource(name="rpaBillService")
	RpaBillService rpaBillService;
	
	/**
    * 운영자 권한을 확인한다.(로그인 여부를 확인한다.)
    *
    * @param boardMaster
    * @throws EgovBizException
    */
   	protected boolean checkAuthority(ModelMap model) throws Exception {
   		// 사용자권한 처리
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
		   	return false;
		}else{
			return true;
		}
   	}
   	
   	/**
     *SAP 다운을 위한  목록을 조회한다.
     */
    @RequestMapping("/rpa/bill/selectBillList.do")
    public String selectBillList(@ModelAttribute("searchVO") RpaBillVO vo, ModelMap model) throws Exception {
    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	try {
	    	SimpleDateFormat formatterMon = new SimpleDateFormat("yyyyMM", Locale.KOREA);
	
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
	    	Calendar cal = Calendar.getInstance();
	    	
	    	String firstMon = vo.getSearchMonth();
	    	String searchXragl = vo.getSearchXragl();
	    	
	    	if ((vo.getSearchMonth() == null) || (vo.getSearchMonth().equals(""))) {
	        	cal.set(Calendar.DATE, 1);
	        	
	        	firstMon = formatterMon.format(cal.getTime());
	    	    
	        	vo.setSearchMonth(firstMon);
	       	 
	    	}
	    	
	    	Map<String, Object> map = rpaBillService.selectBillList(vo);
	    	
	    	
	    	model.addAttribute("resultList", map.get("resultList"));
    	}
		catch(Exception e){
			log.error(e.toString());
			throw e;
	 	} 

		return "rpa/bill/bill_payable";
    }
    
    
    @SuppressWarnings({ "unchecked" })
	@ResponseBody    
    @RequestMapping("/rpa/bill/selectBillListExcel.do")
    public Map<Object, Object> selectBillListExcel(@ModelAttribute("searchVO") RpaBillVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Map<Object, Object> returnMap = new HashMap<Object, Object>();
    	
		try {
	    	
	    	String getSearchMonth = vo.getSearchMonth();
			String excelTitle = String.valueOf(Integer.parseInt(getSearchMonth.substring(4,6))) + "월 지급어음리스트";
			
			Map<String, Object> map = rpaBillService.selectBillList(vo);
	    	
	    	
			@SuppressWarnings("unused")
			List<RpaBillVO> lstResult = (List<RpaBillVO>) map.get("resultList");
			List<Object> objResult = (List<Object>) map.get("resultList");
			
			Map<String, Object> tempMap = new HashMap<String, Object>();
    	
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> headerMap = new HashMap<String, Object>();
			
			headerMap.put("sRow", "1");
			headerMap.put("eRow", "1");
			headerMap.put("sCol", "0");
			headerMap.put("eCol", "10");
			headerMap.put("fontType", "titleLine");
			headerMap.put("fontColor", "000000");
			headerMap.put("styleColor", "FFFFFF");
			headerMap.put("textAlign", "center");
			headerMap.put("textVAlign", "center");
			headerMap.put("line", "none");
			headerMap.put("title", excelTitle);
			
			Map<String, Object> titleStyleMap = new HashMap<String, Object>();
			
			titleStyleMap.put("sRow", "3");
			titleStyleMap.put("eRow", "3");
			titleStyleMap.put("sCol", "0");
			titleStyleMap.put("eCol", "9");
			titleStyleMap.put("fontType", "subtitle");
			titleStyleMap.put("fontColor", "000000");
			titleStyleMap.put("styleColor", "ECF5FC");
			titleStyleMap.put("textAlign", "center");
			titleStyleMap.put("textVAlign", "center");
			titleStyleMap.put("line", "line");
			

			List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "belnr");
			tempMap.put("cellTitle", "전표번호");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 15*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "appoint");
			tempMap.put("cellTitle", "지정");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 20*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "zfbdt");
			tempMap.put("cellTitle", "어음만기일");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 15*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "bldat");
			tempMap.put("cellTitle", "어음발행일");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 15*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "pswbt");
			tempMap.put("cellTitle", "어음발행액");
			tempMap.put("fileType", "Int");
			tempMap.put("cellWidth", 15*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "right");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "lifnr");
			tempMap.put("cellTitle", "거래처코드");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 10*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "stcd2");
			tempMap.put("cellTitle", "사업자번호");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 15*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "name1");
			tempMap.put("cellTitle", "어음수취인");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 30*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "mcod3");
			tempMap.put("cellTitle", "어음수취인도시");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 30*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "xragl");
			tempMap.put("cellTitle", "비고");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 15*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			List<Map<String, Object>> sumCellList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "belnr");
			tempMap.put("fieldNm", "합계");
			tempMap.put("sCell", "0");
			tempMap.put("eCell", "0");
			tempMap.put("cellColor", "f4f4f4");
			sumCellList.add(tempMap);

			Map<String, Object> excelInfpMap = new HashMap<String, Object>();
			excelInfpMap.put("headerMap", headerMap);	
			excelInfpMap.put("titleStyleMap", titleStyleMap);
			excelInfpMap.put("sumCellList", sumCellList);
//			excelInfpMap.put("titleCellList", titleCellList);
			excelInfpMap.put("fieldInfoList", fieldInfoList);
			
			
			SXSSFWorkbook workbook = rpaUtilService.buildExcelXSS(excelTitle, excelInfpMap, objResult, false);

			String realExcelFilename = null;
			realExcelFilename = URLEncoder.encode(excelTitle, "UTF-8");
			realExcelFilename = realExcelFilename.replaceAll("\\+", " ");

			/*
			 * HTTP Header 설정.
			 */
			response.setContentType("application/vnd.ms-excel; name=\"" + realExcelFilename + ".xlsx\"");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + realExcelFilename + ".xlsx\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
//            response.setHeader("Content-Length", Long.toString(fileDownLoadInputVO.getlFileSize()));
			response.setHeader("Cache-Control", "no-cahe, no-store, must-revalidate\r\n");
			response.setHeader("Connection", "close");

			//FileOutputStream fileOut = new FileOutputStream(realExcelFilename + ".xlsx");
			//OutputStream out = response.getOutputStream();
			OutputStream fileOut = response.getOutputStream();

			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}


		return returnMap;
    }

}

