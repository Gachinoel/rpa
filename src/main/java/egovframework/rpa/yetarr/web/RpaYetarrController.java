
package egovframework.rpa.yetarr.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
import egovframework.rpa.yetarr.service.RpaYetarrService;
import egovframework.rpa.yetarr.vo.RpaYetarrVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

@Controller
public class RpaYetarrController {
	Logger log = Logger.getLogger(this.getClass());
	
	 /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="rpaUtilService")
    RpaUtilService rpaUtilService;   
    
    
    /** EgovMessageSource */
    @Resource(name="rpaYetarrService")
    RpaYetarrService rpaYetarrService;
	
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
	
	@RequestMapping("/rpa/yetarr/selectYetArrivedList.do")
    public String selectYetArrivedList(@ModelAttribute("searchVO") RpaYetarrVO vo, ModelMap model) throws Exception {
    	try {
	    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
	    	
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.KOREA);
	    	Calendar cal = Calendar.getInstance();
	    	cal.add(Calendar.MONTH , -1);
	
	    	String firstDate = vo.getSearchMonth();
	    	 
	    	if ((vo.getSearchMonth() == null) || (vo.getSearchMonth().equals(""))) {
	    		cal.set(Calendar.DATE, 1);
	    		 
	    	    firstDate = formatter.format(cal.getTime());
	    	    
	    	    vo.setSearchMonth(firstDate);
	    	}
	
	    	Map<String, Object> map = rpaYetarrService.selectYetArrivedList(vo);
	    	
	    	model.addAttribute("yetList", map.get("yetList"));
	    	model.addAttribute("yetTotal", map.get("yetTotal"));
	    	model.addAttribute("resultMap", map.get("resultMap"));
	    	model.addAttribute("resultMap2", map.get("resultMap2"));
		}
		catch(Exception e){
			log.error(e.toString());
			throw e;
	 	} 

		return "rpa/yetarr/RpaYetArrivedList";
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody    
    @RequestMapping("/rpa/yetarr/selectYetArrivedExcel.do")
    public Map<Object, Object> selectYetArrivedExcel(@ModelAttribute("searchVO") RpaYetarrVO yetVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Map<Object, Object> returnMap = new HashMap<Object, Object>();
    	
		try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.KOREA);
	    	Calendar cal = Calendar.getInstance();

	    	if ((yetVo.getSearchMonth() == null) || (yetVo.getSearchMonth().equals(""))) {
	    		cal.set(Calendar.DATE, 1);
	    		 
	    		String firstDate = formatter.format(cal.getTime());
	    	    
	    		yetVo.setSearchMonth(firstDate);
	    	}
	    	
	    	Map<String, Object> map = rpaYetarrService.selectYetArrivedList(yetVo);
			
        	List<RpaYetarrVO> lstResult =  (List<RpaYetarrVO>) map.get("yetTotal");
        	List<Object> objResult = (List<Object>) map.get("yetTotal");
        	
        	//GROUP BY된 데이터를 받을 MAP
        	Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();

        	for(int i=0; i<lstResult.size(); i++){
        		String orderNumber = "total";
        		if(resultMap.containsKey(orderNumber)){
        			resultMap.get(orderNumber).put("blamt", Double.parseDouble(resultMap.get(orderNumber).get("blamt").toString()) 
        			+ Double.parseDouble(lstResult.get(i).getBlamt().toString()));
        		}else{
        			//KEY값이 존재하지 않으면 MAP에 데이터를 넣어줌
        			Map<String, Object> dataMap = new HashMap<String, Object>();
        			dataMap.put("blamt", Double.parseDouble(lstResult.get(i).getBlamt().toString()));
        			resultMap.put(orderNumber, dataMap);
        		}
        	}
        	
        	
        	Set set = resultMap.entrySet();
        	Iterator iterator = set.iterator();

        	while(iterator.hasNext()){
        	  Map.Entry entry = (Map.Entry)iterator.next();
        	  Map<String, Object> valueMap = (Map<String, Object>) entry.getValue();
        	  
       	  
        	  RpaYetarrVO vo = new RpaYetarrVO();
        	  vo.setLastday("합계");
        	  vo.setBktxt("합계");

              vo.setBlamt(valueMap.get("blamt").toString());
              lstResult.add(vo);

        	}
        	
			String excelTitle = " 미  착  품  명  세  서 ";

        	
			Map<String, Object> tempMap = new HashMap<String, Object>();
    	
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> headerMap = new HashMap<String, Object>();
			
			headerMap.put("sRow", "1");
			headerMap.put("eRow", "1");
			headerMap.put("sCol", "0");
			headerMap.put("eCol", "4");
			headerMap.put("fontType", "titleLine");
			headerMap.put("fontColor", "000000");
			headerMap.put("styleColor", "FFFFFF");
			headerMap.put("textAlign", "center");
			headerMap.put("textVAlign", "center");
			headerMap.put("line", "none");
			headerMap.put("title", excelTitle);
			
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> unitMap = new HashMap<String, Object>();
			
			unitMap.put("sRow", "2");
			unitMap.put("eRow", "2");
			unitMap.put("sCol", "0");
			unitMap.put("eCol", "4");
			unitMap.put("fontType", "unitLine");
			unitMap.put("fontColor", "000000");
			unitMap.put("styleColor", "FFFFFF");
			unitMap.put("textAlign", "left");
			unitMap.put("textVAlign", "center");
			unitMap.put("line", "none");
			unitMap.put("title", "");
			
			List<Map<String, Object>> unitList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "2");
			tempMap.put("eRow", "2");
			tempMap.put("sCol", "0");
			tempMap.put("eCol", "1");
			tempMap.put("fontType", "unitLine");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "left");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "none");
			tempMap.put("title", "업체명 : 우진공업㈜");
			unitList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "2");
			tempMap.put("eRow", "2");
			tempMap.put("sCol", "4");
			tempMap.put("eCol", "4");
			tempMap.put("fontType", "unitLine");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "right");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "none");
			tempMap.put("title", "(단위 : 원)");
			unitList.add(tempMap);
			
			
			Map<String, Object> titleStyleMap = new HashMap<String, Object>();
			
			titleStyleMap.put("sRow", "3");
			titleStyleMap.put("eRow", "3");
			titleStyleMap.put("sCol", "0");
			titleStyleMap.put("eCol", "4");
			titleStyleMap.put("fontType", "subtitle");
			titleStyleMap.put("fontColor", "000000");
			titleStyleMap.put("styleColor", "ECF5FC");
			titleStyleMap.put("textAlign", "center");
			titleStyleMap.put("textVAlign", "center");
			titleStyleMap.put("line", "line");
			
			List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "lastday");
			tempMap.put("cellTitle", "일   자");
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
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "bktxt");
			tempMap.put("cellTitle", "관리KEY명");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 23*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "item");
			tempMap.put("cellTitle", "적         요");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 25*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "center");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "blamt");
			tempMap.put("cellTitle", "금       액");
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
			tempMap.put("field", "");
			tempMap.put("cellTitle", "비   고");
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
			
			
			List<Map<String, Object>> mergeCellList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "lastday");
			tempMap.put("fieldNum", "0");
			mergeCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "bktxt");
			tempMap.put("fieldNum", "1");
			mergeCellList.add(tempMap);
			
			List<Map<String, Object>> sumCellList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "lastday");
			tempMap.put("fieldNm", "합계");
			tempMap.put("sCell", "0");
			tempMap.put("eCell", "1");
			tempMap.put("cellColor", "f4f4f4");
			sumCellList.add(tempMap);
			
					
			Map<String, Object> excelInfpMap = new HashMap<String, Object>();
			excelInfpMap.put("headerMap", headerMap);		
			excelInfpMap.put("unitMap", unitMap);
			excelInfpMap.put("titleStyleMap", titleStyleMap);
			excelInfpMap.put("mergeCellList", mergeCellList);
			excelInfpMap.put("sumCellList", sumCellList);
			excelInfpMap.put("unitList", unitList);
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

