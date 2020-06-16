package egovframework.batchjob.scheduling;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BatchController {
	//상용자정보 서비스 클래스 호출
    @Autowired
    BatchService batchService;
    
	@RequestMapping(value = "/excel/processCargo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processCargo() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/excelresult");
        try {
        	Map<String, Object> resultMap = batchService.processCargo();
        	
        	if (Integer.parseInt(resultMap.get("result").toString()) < 0) {
    			mv.addObject("pageMessage", resultMap.get("resultMessage")); //변수값
    			mv.setViewName("/error");
        	}
        } catch (Exception e) {
            e.printStackTrace();
			mv.addObject("pageMessage", "오류"); //변수값
			mv.setViewName("/error");
        }
        return mv;

    }
	
	@RequestMapping(value = "/excel/processForward.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processForward() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/excelresult");
        try {
        	Map<String, Object> resultMap = batchService.processForward();
        	
        	if (Integer.parseInt(resultMap.get("result").toString()) < 0) {
    			mv.addObject("pageMessage", resultMap.get("resultMessage")); //변수값
    			mv.setViewName("/error");
        	}
        } catch (Exception e) {
            e.printStackTrace();
			mv.addObject("pageMessage", "오류"); //변수값
			mv.setViewName("/error");
        }
        return mv;

    }
	
	@RequestMapping(value = "/excel/processImport.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processImport() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/excelresult");
        try {
        	Map<String, Object> resultMap = batchService.processImport();
        	
        	if (Integer.parseInt(resultMap.get("result").toString()) < 0) {
    			mv.addObject("pageMessage", resultMap.get("resultMessage")); //변수값
    			mv.setViewName("/error");
        	}
        } catch (Exception e) {
            e.printStackTrace();
			mv.addObject("pageMessage", "오류"); //변수값
			mv.setViewName("/error");
        }
        return mv;

    }
	
	@RequestMapping(value = "/excel/processCustom.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processCustom() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/excelresult");
        try {
        	Map<String, Object> resultMap = batchService.processCustom();
        	
        	if (Integer.parseInt(resultMap.get("result").toString()) < 0) {
    			mv.addObject("pageMessage", resultMap.get("resultMessage")); //변수값
    			mv.setViewName("/error");
        	}
        } catch (Exception e) {
            e.printStackTrace();
			mv.addObject("pageMessage", "오류"); //변수값
			mv.setViewName("/error");
        }
        return mv;

    }
}
