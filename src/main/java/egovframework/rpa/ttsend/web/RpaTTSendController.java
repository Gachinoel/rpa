
package egovframework.rpa.ttsend.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import egovframework.rpa.ttsend.service.RpaTTSendService;
import egovframework.rpa.ttsend.vo.RpaTTSendVO;
import egovframework.rpa.util.service.RpaUtilService;

@Controller
public class RpaTTSendController {
	Logger log = Logger.getLogger(this.getClass());
	
	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
   
	@Resource(name="rpaUtilService")
	RpaUtilService rpaUtilService;   
   
   
	/** EgovMessageSource */
	@Resource(name="rpaTTSendService")
	RpaTTSendService rpaTTSendService;
	
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
    @SuppressWarnings("unchecked")
	@RequestMapping("/rpa/ttsend/selectTTSendList.do")
    public String selectTTSendList(@ModelAttribute("searchVO") RpaTTSendVO vo, ModelMap model) throws Exception {
    	
    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	
    	try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
	    	Calendar cal = Calendar.getInstance();
	    	
	    	String firstDate = vo.getSearchBgnDe();
	    	String lastDate = vo.getSearchEndDe();
	    	String send_dt = vo.getSearchSendDt();    	
	    	
	    	String searchDate = vo.getSearchBgnDe();
	    	
	    	if ((vo.getSearchGubun() == null) || (vo.getSearchGubun().equals(""))) {
	    		vo.setSearchGubun("BT");
	    	}
	    	if ((vo.getSearchBgnDe() == null) || (vo.getSearchBgnDe().equals(""))) {
	        	cal.set(Calendar.DATE, 1);
	       	 
	            firstDate = formatter.format(cal.getTime());
	            
	            vo.setSearchBgnDe(firstDate);
	    	}
	    	if ((vo.getSearchEndDe() == null) || (vo.getSearchEndDe().equals(""))) {
	        	cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
	        	
	        	lastDate = formatter.format(cal.getTime());
	        	vo.setSearchEndDe(lastDate);
	    	}
	    	
	    	if ((vo.getSearchSendDt() == null) || (vo.getSearchSendDt().equals(""))) {
	        	
	    		cal.set(Calendar.DATE, 1);
	          	 
	    		send_dt = formatter.format(cal.getTime());
	            
	    		vo.setSearchSendDt(send_dt);
	    	}
	    	 
	    	if ((searchDate != null) && !(searchDate.equals(""))) {
	        	Map<String, Object> map = rpaTTSendService.selectTTSendList(vo);
	        	
	        	List<RpaTTSendVO> data =  (List<RpaTTSendVO>) map.get("resultList");
	        	
	        	//GROUP BY된 데이터를 받을 MAP
	        	Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();
	
	        	for(int i=0; i<data.size(); i++){
	        		String orderNumber = data.get(i).getWaers().toString(); //KEY VALUE
	        		if(resultMap.containsKey(orderNumber)){
	        			//KEY값이 존재하면 해당 키값의 해당되는 가격을 가져와 더해줌
	        			resultMap.get(orderNumber).put("wrbtr", Double.parseDouble(resultMap.get(orderNumber).get("wrbtr").toString()) 
	        			+ Double.parseDouble(data.get(i).getWrbtr().toString()));
	        		}else{
	        			//KEY값이 존재하지 않으면 MAP에 데이터를 넣어줌
	        			Map<String, Object> dataMap = new HashMap<String, Object>();
	        			dataMap.put("wrbtr", Double.parseDouble(data.get(i).getWrbtr().toString()));
	        			resultMap.put(orderNumber, dataMap);
	        		}
	        	}
	
	        	
	        	
	    		model.addAttribute("resultList", map.get("resultList"));
	    		model.addAttribute("totalList", resultMap);
	    	}
	    	

    	}
		catch(Exception e){
			log.error(e.toString());
			throw e;
	 	} 
		return "rpa/ttsend/TTSendList";
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody    
    @RequestMapping("/rpa/ttsend/selectTTSendExcel.do")
    public Map<Object, Object> selectTTSendExcel(@ModelAttribute("searchVO") RpaTTSendVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Map<Object, Object> returnMap = new HashMap<Object, Object>();
    	
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
			String send_dt = vo.getSearchSendDt();  
			String searchCom = vo.getSearchCom();  
			
			Date tempDate = formatter.parse(send_dt);
			
			String send_date = formatter1.format(tempDate);
			
			String excelTitle = "TT 송금 내역서";
			
			Map<String, Object> map = rpaTTSendService.selectTTSendList(vo);
        	
        	List<RpaTTSendVO> lstResult =  (List<RpaTTSendVO>) map.get("resultList");
        	List<Object> objResult = (List<Object>) map.get("resultList");
        	
        	//GROUP BY된 데이터를 받을 MAP
        	Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();

        	for(int i=0; i<lstResult.size(); i++){
        		String orderNumber = lstResult.get(i).getWaers().toString(); //KEY VALUE
        		if(resultMap.containsKey(orderNumber)){
        			//KEY값이 존재하면 해당 키값의 해당되는 가격을 가져와 더해줌
        			resultMap.get(orderNumber).put("wrbtr", Double.parseDouble(resultMap.get(orderNumber).get("wrbtr").toString()) 
        			+ Double.parseDouble(lstResult.get(i).getWrbtr().toString()));
        		}else{
        			//KEY값이 존재하지 않으면 MAP에 데이터를 넣어줌
        			Map<String, Object> dataMap = new HashMap<String, Object>();
        			dataMap.put("wrbtr", Double.parseDouble(lstResult.get(i).getWrbtr().toString()));
        			resultMap.put(orderNumber, dataMap);
        		}
        	}
        	
        	Set set = resultMap.entrySet();
        	Iterator iterator = set.iterator();

        	while(iterator.hasNext()){
        	  Map.Entry entry = (Map.Entry)iterator.next();
        	  String key = (String)entry.getKey();
        	  Map<String, Object> valueMap = (Map<String, Object>) entry.getValue();
        	  
       	  
        	  RpaTTSendVO vo1 = new RpaTTSendVO();
        	  vo1.setNum("합계");
        	  vo1.setBldat(key + " 합계");
        	  //vo1.setBktxt(key + " 합계");
              vo1.setWaers(key);
              vo1.setWrbtr(valueMap.get("wrbtr").toString());
              lstResult.add(vo1);

        	}
        	
			Map<String, Object> tempMap = new HashMap<String, Object>();
    	
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> headerMap = new HashMap<String, Object>();
			
			headerMap.put("sRow", "1");
			headerMap.put("eRow", "1");
			headerMap.put("sCol", "0");
			headerMap.put("eCol", "6");
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
			unitMap.put("eRow", "3");
			unitMap.put("sCol", "0");
			unitMap.put("eCol", "6");
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
			tempMap.put("sCol", "6");
			tempMap.put("eCol", "6");
			tempMap.put("fontType", "unitLine");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "left");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "none");
			tempMap.put("title", "송금처 : " + searchCom);
			unitList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("sRow", "3");
			tempMap.put("eRow", "3");
			tempMap.put("sCol", "6");
			tempMap.put("eCol", "6");
			tempMap.put("fontType", "unitLine");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "left");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "none");
			tempMap.put("title", "송금일 : " + send_date);
			unitList.add(tempMap);
			
			
			Map<String, Object> titleStyleMap = new HashMap<String, Object>();
			
			titleStyleMap.put("sRow", "4");
			titleStyleMap.put("eRow", "4");
			titleStyleMap.put("sCol", "0");
			titleStyleMap.put("eCol", "6");
			titleStyleMap.put("fontType", "subtitle");
			titleStyleMap.put("fontColor", "000000");
			titleStyleMap.put("styleColor", "ECF5FC");
			titleStyleMap.put("textAlign", "center");
			titleStyleMap.put("textVAlign", "center");
			titleStyleMap.put("line", "line");
			
			List<Map<String, Object>> fieldInfoList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "num");
			tempMap.put("cellTitle", "NO");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 7*256);
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
			tempMap.put("cellTitle", "BL일자");
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
			tempMap.put("cellTitle", "BL 번호");
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
			tempMap.put("field", "zfidrno");
			tempMap.put("cellTitle", "수입신고번호");
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
			
			/*tempMap = new HashMap<String, Object>();
			tempMap.put("field", "send_dt");
			tempMap.put("cellTitle", "송금일");
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
			*/
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "waers");
			tempMap.put("cellTitle", "통화단위");
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
			tempMap.put("field", "wrbtr");
			tempMap.put("cellTitle", "결재금액/외화금액");
			tempMap.put("fileType", "Int");
			tempMap.put("cellWidth", 18*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "right");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-");
			fieldInfoList.add(tempMap);			

			/*tempMap = new HashMap<String, Object>();
			tempMap.put("field", "name1");
			tempMap.put("cellTitle", "송금처");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 25*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "left");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			*/

			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "kursf");
			tempMap.put("cellTitle", "비고(환율)");
			tempMap.put("fileType", "String");
			tempMap.put("cellWidth", 25*256);
			tempMap.put("fontType", "content");
			tempMap.put("fontColor", "000000");
			tempMap.put("styleColor", "FFFFFF");
			tempMap.put("textAlign", "left");
			tempMap.put("textVAlign", "center");
			tempMap.put("line", "dot");
			tempMap.put("fomule", "");
			fieldInfoList.add(tempMap);
			
			List<Map<String, Object>> mergeCellList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "num");
			tempMap.put("fieldNum", "0");
			mergeCellList.add(tempMap);
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "bldat");
			tempMap.put("fieldNum", "1");
			mergeCellList.add(tempMap);
			
			List<Map<String, Object>> sumCellList = new ArrayList<Map<String, Object>>();
			
			tempMap = new HashMap<String, Object>();
			tempMap.put("field", "num");
			tempMap.put("fieldNm", "합계");
			tempMap.put("sCell", "0");
			tempMap.put("eCell", "0");
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

