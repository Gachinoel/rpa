
package egovframework.bsc.properly.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.bsc.properly.service.BscProperlyService;
import egovframework.bsc.properly.vo.BscProperlyVO;

@Controller
public class BscProperlyController {
	Logger log = Logger.getLogger(this.getClass());
	
	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
   
	/** EgovMessageSource */
	@Resource(name="bscProperlyService")
	BscProperlyService bscProperlyService;
	
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
	@RequestMapping("/bsc/properly/selectBscProperly.do")
    public String selectBscProperly(@ModelAttribute("searchVO") BscProperlyVO vo, ModelMap model) throws Exception {
    	
    	//if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	
    	try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.KOREA);
	    	Calendar cal = Calendar.getInstance();
	    	
	    	String searchMonth = vo.getSearchMonth();
	    	String searchGubun = vo.getSearchGubun();
	    	String searchItem = vo.getSearchItem();    	
	    	
	    	if ((searchMonth == null) || (searchMonth.equals(""))) {
	        	cal.set(Calendar.DATE, 1);
	       	 
	        	searchMonth = formatter.format(cal.getTime());
	            
	            vo.setSearchMonth(searchMonth);
	    	}
	    	
	    	if ((searchGubun == null) || (searchGubun.equals(""))) {
	    		searchGubun = "ALL";
	    		vo.setSearchGubun(searchGubun);
	    	}
	    	
	    	if ((searchItem == null) || (searchItem.equals(""))) {
	    		vo.setSearchItem("");
	    	}
	    	 
        	Map<String, Object> map = bscProperlyService.selectBscProperlyList(vo);
        	
        	List<BscProperlyVO> data =  (List<BscProperlyVO>) map.get("resultList");
        	
    		model.addAttribute("resultList", data);
    		model.addAttribute("btnGubun", searchGubun);
	    	

    	}
		catch(Exception e){
			log.error(e.toString());
			throw e;
	 	} 
		return "bsc/properly/bscProperlyList";
    }
    
    /**
     *SAP 다운을 위한  목록을 조회한다.
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/bsc/properly/selectBscProperlyM.do")
    public String selectBscProperlyM(@ModelAttribute("searchVO") BscProperlyVO vo, ModelMap model) throws Exception {
    	
    	//if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	
    	try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.KOREA);
	    	Calendar cal = Calendar.getInstance();
	    	
	    	String searchMonth = vo.getSearchMonth();
	    	String searchGubun = vo.getSearchGubun();
	    	String searchItem = vo.getSearchItem();    	
	    	
	    	if ((searchMonth == null) || (searchMonth.equals(""))) {
	        	cal.set(Calendar.DATE, 1);
	       	 
	        	searchMonth = formatter.format(cal.getTime());
	            
	            vo.setSearchMonth(searchMonth);
	    	}
	    	
	    	if ((searchGubun == null) || (searchGubun.equals(""))) {
	    		searchGubun = "PSPART";
	    		vo.setSearchGubun(searchGubun);
	    	}
	    	
	    	if ((searchItem == null) || (searchItem.equals(""))) {
	    		vo.setSearchItem("");
	    	}
	    	 
        	Map<String, Object> map = bscProperlyService.selectBscProperlyList(vo);
        	
        	List<BscProperlyVO> data =  (List<BscProperlyVO>) map.get("resultList");
        	
    		model.addAttribute("resultList", data);
    		model.addAttribute("btnGubun", searchGubun);
	    	

    	}
		catch(Exception e){
			log.error(e.toString());
			throw e;
	 	} 
		return "bsc/properly/bscProperlyList";
    }
    
    /**
     *SAP 다운을 위한  목록을 조회한다.
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/bsc/properly/selectBscProperlyL.do")
    public String selectBscProperlyL(@ModelAttribute("searchVO") BscProperlyVO vo, ModelMap model) throws Exception {
    	
    	//if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	
    	try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.KOREA);
	    	Calendar cal = Calendar.getInstance();
	    	
	    	String searchMonth = vo.getSearchMonth();
	    	String searchGubun = vo.getSearchGubun();
	    	String searchItem = vo.getSearchItem();    	
	    	
	    	if ((searchMonth == null) || (searchMonth.equals(""))) {
	        	cal.set(Calendar.DATE, 1);
	       	 
	        	searchMonth = formatter.format(cal.getTime());
	            
	            vo.setSearchMonth(searchMonth);
	    	}
	    	
	    	if ((searchGubun == null) || (searchGubun.equals(""))) {
	    		searchGubun = "PMTART";
	    		vo.setSearchGubun(searchGubun);
	    	}
	    	
	    	if ((searchItem == null) || (searchItem.equals(""))) {
	    		vo.setSearchItem("");
	    	}
	    	 
        	Map<String, Object> map = bscProperlyService.selectBscProperlyList(vo);
        	
        	List<BscProperlyVO> data =  (List<BscProperlyVO>) map.get("resultList");
        	
    		model.addAttribute("resultList", data);
    		model.addAttribute("btnGubun", searchGubun);
	    	

    	}
		catch(Exception e){
			log.error(e.toString());
			throw e;
	 	} 
		return "bsc/properly/bscProperlyList";
    }

}

