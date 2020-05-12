
package egovframework.rpa.importer.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rpa.importer.service.RpaImporterService;
import egovframework.rpa.util.service.RpaUtilService;

@Controller
public class RpaImporterController {
	Logger log = Logger.getLogger(this.getClass());
	
	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
   
	@Resource(name="rpaUtilService")
	RpaUtilService rpaUtilService;   
   
   
	/** EgovMessageSource */
	@Resource(name="rpaImporterService")
	RpaImporterService rpaImporterService;
	
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

}

