package egovframework.batchjob.scheduling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BatchController {
	//상용자정보 서비스 클래스 호출
    @Autowired
    BatchService batchService;
    
	@RequestMapping(value = "/excel/processCargo.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public Map<String, Object> processCargo() throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
        try {
        	Map<String, Object> resultMap = batchService.processCargo();
        	
        	if (Integer.parseInt(resultMap.get("result").toString()) < 0) {
        		msgMap.put("status", 0);
        		msgMap.put("msg", resultMap.get("resultMessage"));
        	}
        	else {
        		msgMap.put("status", 1);
        		msgMap.put("msg", resultMap.get("resultMessage"));
        	}
        } catch (Exception e) {
            e.printStackTrace();
            msgMap.put("status", 0);
            msgMap.put("msg", "오류 : " + e.toString());
        }
        return msgMap;

    }
	
	@RequestMapping(value = "/excel/processForward.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public Map<String, Object> processForward() throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
        try {
        	Map<String, Object> resultMap = batchService.processForward();
        	
        	if (Integer.parseInt(resultMap.get("result").toString()) < 0) {
        		msgMap.put("status", 0);
        		msgMap.put("msg", resultMap.get("resultMessage"));
        	}
        	else {
        		msgMap.put("status", 1);
        		msgMap.put("msg", resultMap.get("resultMessage"));
        	}
        } catch (Exception e) {
            e.printStackTrace();
            msgMap.put("status", 0);
            msgMap.put("msg", "오류 : " + e.toString());
        }
        return msgMap;

    }
	
	@RequestMapping(value = "/excel/processImport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public Map<String, Object> processImport() throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
        try {
        	Map<String, Object> resultMap = batchService.processImport();
        	
        	if (Integer.parseInt(resultMap.get("result").toString()) < 0) {
        		msgMap.put("status", 0);
        		msgMap.put("msg", resultMap.get("resultMessage"));
        	}
        	else {
        		msgMap.put("status", 1);
        		msgMap.put("msg", resultMap.get("resultMessage"));
        	}
        } catch (Exception e) {
            e.printStackTrace();
            msgMap.put("status", 0);
            msgMap.put("msg", "오류 : " + e.toString());
        }
        return msgMap;
    }
	
	@RequestMapping(value = "/excel/processCustom.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public Map<String, Object> processCustom() throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
        try {
        	Map<String, Object> resultMap = batchService.processCustom();
        	
        	if (Integer.parseInt(resultMap.get("result").toString()) < 0) {
        		msgMap.put("status", 0);
        		msgMap.put("msg", resultMap.get("resultMessage"));
        	}
        	else {
        		msgMap.put("status", 1);
        		msgMap.put("msg", resultMap.get("resultMessage"));
        	}
        } catch (Exception e) {
            e.printStackTrace();
            msgMap.put("status", 0);
            msgMap.put("msg", "오류 : " + e.toString());
        }
        return msgMap;

    }
}
