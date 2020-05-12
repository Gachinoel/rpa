
package egovframework.rpa.tax.web;

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
import egovframework.rpa.tax.service.RpaTaxService;
import egovframework.rpa.tax.vo.RpaTaxVO;
import egovframework.rpa.util.service.RpaUtilService;

@Controller
public class RpaTaxController {
	Logger log = Logger.getLogger(this.getClass());
	
	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
   
	@Resource(name="rpaUtilService")
	RpaUtilService rpaUtilService;   
   
   
	/** EgovMessageSource */
	@Resource(name="rpaTaxService")
	RpaTaxService rpaTaxService;
	
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
    @RequestMapping("/rpa/tax/selectTaxList.do")
    public String selectTaxList(@ModelAttribute("searchVO") RpaTaxVO vo, ModelMap model) throws Exception {
    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	try {
	    	SimpleDateFormat formatterMon = new SimpleDateFormat("MM", Locale.KOREA);
	
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
	    	Calendar cal = Calendar.getInstance();
	    	
	    	String firstMon = vo.getSearchMonth();
	    	String firstDate = vo.getSearchBgnDe();
	    	String lastDate = vo.getSearchEndDe();
	    	if ((vo.getSearchMonth() == null) || (vo.getSearchMonth().equals(""))) {
	        	cal.set(Calendar.DATE, 1);
	        	
	        	firstMon = formatterMon.format(cal.getTime());
	    	    
	        	vo.setSearchMonth(String.valueOf(Integer.parseInt(firstMon)));
	       	 
	    	}
	    	
	    	if ((vo.getSearchBgnDe() == null) || (vo.getSearchBgnDe().equals(""))) {
	        	cal.set(Calendar.DATE, 1);
	        	
	        	firstMon = formatterMon.format(cal.getTime());
	    	    
	    		//boardMasterVO.setSearchMonth(String.valueOf(Integer.parseInt(firstMon)));
	       	 
	            firstDate = formatter.format(cal.getTime());
	            
	            vo.setSearchBgnDe(firstDate);
	    	}
	    	else {
	    		Date date = new SimpleDateFormat("yyyyMMdd").parse(firstDate);
	    		String newstring = new SimpleDateFormat("yyyyMMdd").format(date);
	    		cal.set(Calendar.YEAR, Integer.parseInt(newstring.substring(0, 4)));
	    		cal.set(Calendar.MONTH, Integer.parseInt(newstring.substring(4, 6)) - 1);
	    		cal.set(Calendar.DATE, Integer.parseInt(newstring.substring(6, 8)));
	
	    	}
	    	if ((vo.getSearchEndDe() == null) || (vo.getSearchEndDe().equals(""))) {
	        	cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
	        	
	        	lastDate = formatter.format(cal.getTime());
	        	vo.setSearchEndDe(lastDate);
	    	}
	    	String firstExtraDate = vo.getSearchExtraBgnDe();
	    	String lastExtraDate = vo.getSearchExtraEndDe();
	
	    	if ((vo.getSearchExtraBgnDe() == null) || (vo.getSearchExtraBgnDe().equals(""))) {
	        	cal.set(Calendar.DATE, 1);
	       	 
	            firstExtraDate = formatter.format(cal.getTime());
	            
	            vo.setSearchExtraBgnDe(firstExtraDate);
	    	}
	    	if ((vo.getSearchExtraEndDe() == null) || (vo.getSearchExtraEndDe().equals(""))) {
	        	cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
	        	
	        	lastExtraDate = formatter.format(cal.getTime());
	        	vo.setSearchExtraEndDe(lastExtraDate);
	    	}
	    	
	    	Map<String, Object> map = rpaTaxService.selectExtraTaxSum(vo);
	    	
	    	
	    	model.addAttribute("resultList", map.get("resultList"));
    	}
		catch(Exception e){
			log.error(e.toString());
			throw e;
	 	} 

		return "rpa/tax/TaxFinish";
    }
    
    
    @SuppressWarnings({ "unchecked" })
	@ResponseBody    
    @RequestMapping("/rpa/tax/selectTaxListExcel.do")
    public Map<Object, Object> selectTaxListExcel(@ModelAttribute("searchVO") RpaTaxVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Map<Object, Object> returnMap = new HashMap<Object, Object>();
    	
		try {
	    	String getSearchMonth = vo.getSearchMonth();
			String excelTitle = getSearchMonth + "월 관부가세 마감 내역";
			
			Map<String, Object> map = rpaTaxService.selectExtraTaxSum(vo);
	    	
	    	
			@SuppressWarnings("unused")
			List<RpaTaxVO> lstResult = (List<RpaTaxVO>) map.get("resultList");
			List<Object> objResult = (List<Object>) map.get("resultList");
			
			Map<String, Object> tempMap = new HashMap<String, Object>();
    	
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> headerMap = new HashMap<String, Object>();
			
			headerMap.put("sRow", "1");
			headerMap.put("eRow", "1");
			headerMap.put("sCol", "0");
			headerMap.put("eCol", "5");
			headerMap.put("fontType", "titleLine");
			headerMap.put("fontColor", "000000");
			headerMap.put("styleColor", "FFFFFF");
			headerMap.put("textAlign", "center");
			headerMap.put("textVAlign", "center");
			headerMap.put("line", "none");
			headerMap.put("title", excelTitle);
			
			Map<String, Object> titleStyleMap = new HashMap<String, Object>();
			
			titleStyleMap.put("sRow", "3");
			titleStyleMap.put("eRow", "4");
			titleStyleMap.put("sCol", "0");
			titleStyleMap.put("eCol", "5");
			titleStyleMap.put("fontType", "subtitle");
			titleStyleMap.put("fontColor", "000000");
			titleStyleMap.put("styleColor", "ECF5FC");
			titleStyleMap.put("textAlign", "center");
			titleStyleMap.put("textVAlign", "center");
			titleStyleMap.put("line", "line");
			
			List<Map<String, Object>> titleCellList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "3");
			tempMap.put("eRow", "4");
			tempMap.put("sCol", "0");
			tempMap.put("eCol", "0");
			tempMap.put("cellWidth", 35*256);
			tempMap.put("cellTitle", "관세사");
			titleCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "3");
			tempMap.put("eRow", "4");
			tempMap.put("sCol", "1");
			tempMap.put("eCol", "1");
			tempMap.put("cellWidth", 25*256);
			tempMap.put("cellTitle", "세관");
			titleCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "3");
			tempMap.put("eRow", "3");
			tempMap.put("sCol", "2");
			tempMap.put("eCol", "4");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("cellTitle", "금액(\\)");
			titleCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "4");
			tempMap.put("eRow", "4");
			tempMap.put("sCol", "2");
			tempMap.put("eCol", "2");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("cellTitle", "관세");
			titleCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "4");
			tempMap.put("eRow", "4");
			tempMap.put("sCol", "3");
			tempMap.put("eCol", "3");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("cellTitle", "부가세");
			titleCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "4");
			tempMap.put("eRow", "4");
			tempMap.put("sCol", "4");
			tempMap.put("eCol", "4");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("cellTitle", "Total");
			titleCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "3");
			tempMap.put("eRow", "4");
			tempMap.put("sCol", "5");
			tempMap.put("eCol", "5");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("cellTitle", "지급일자");
			titleCellList.add(tempMap);
			
			List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "zfapnm");
			tempMap.put("cellTitle", "관세사");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 55*256);
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
			tempMap.put("cellTitle", "세관");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 35*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "dmbtr");
			tempMap.put("cellTitle", "관세");
			tempMap.put("fileType", "Int");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "right");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "wrbtr");
			tempMap.put("cellTitle", "부가세");
			tempMap.put("fileType", "Int");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "right");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "twrbtr");
			tempMap.put("cellTitle", "Total");
			tempMap.put("fileType", "Int");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "right");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "bldat");
			tempMap.put("cellTitle", "지급일자");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 13*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			Map<String, Object> mergeCellMap = new HashMap<String, Object>();
			
			mergeCellMap.put("zfapnm", "1");
			mergeCellMap.put("name1", "2");
			
			List<Map<String, Object>> mergeCellList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "zfapnm");
			tempMap.put("fieldNum", "0");
			mergeCellList.add(tempMap);
			
			List<Map<String, Object>> sumCellList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "zfapnm");
			tempMap.put("fieldNm", "총계");
			tempMap.put("sCell", "0");
			tempMap.put("eCell", "1");
			tempMap.put("cellColor", "c4c4c4");
			sumCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "name1");
			tempMap.put("fieldNm", "합계");
			tempMap.put("sCell", "1");
			tempMap.put("eCell", "1");
			tempMap.put("cellColor", "f4f4f4");
			sumCellList.add(tempMap);

			Map<String, Object> excelInfpMap = new HashMap<String, Object>();
			excelInfpMap.put("headerMap", headerMap);		
			excelInfpMap.put("titleStyleMap", titleStyleMap);
			excelInfpMap.put("mergeCellList", mergeCellList);
			excelInfpMap.put("sumCellList", sumCellList);
			excelInfpMap.put("titleCellList", titleCellList);
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

