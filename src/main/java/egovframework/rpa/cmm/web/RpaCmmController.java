
package egovframework.rpa.cmm.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.MediaUtils;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.rpa.cmm.service.RpaCmmService;
import egovframework.rpa.cmm.vo.RpaCmmVO;
import egovframework.rpa.util.service.RpaUtilService;

@Controller
public class RpaCmmController {
	Logger log = Logger.getLogger(this.getClass());
	
	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
   
	@Resource(name="rpaUtilService")
	RpaUtilService rpaUtilService;   
   
   
	/** EgovMessageSource */
	@Resource(name="rpaCmmService")
	RpaCmmService rpaCmmService;
	
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
   	
   	@SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/rpa/cmm/selectBLNoInfs.do", method = {RequestMethod.GET, RequestMethod.POST})
    public void selectBLNoInfs(@ModelAttribute("searchVO") RpaCmmVO vo, HttpServletResponse response) throws Exception {
    	
    	List<RpaCmmVO> list = rpaCmmService.selectBLNoInfs(vo);
    	
    	// 응답해야 하는 문자열 : [{label:~,value:~},{label:~,value:~}]
        JSONArray array = new JSONArray();
        for(RpaCmmVO dto : list) {
            JSONObject obj = new JSONObject();
            obj.put("label", dto.getBktxt());
            obj.put("value", dto.getBktxt());
            array.add(obj);
        }
        PrintWriter out = response.getWriter();
        
        out.print(array.toString());
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/rpa/cmm/selectCompanyInfs.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void selectCompanyInfs(@ModelAttribute("searchVO") RpaCmmVO vo, HttpServletResponse response)
    		throws Exception {
    	
    	List<RpaCmmVO> list = rpaCmmService.selectCompanyInfs(vo);
    	
        
    	// 응답해야 하는 문자열 : [{label:~,value:~},{label:~,value:~}]
        JSONArray array = new JSONArray();
        for(RpaCmmVO dto : list) {
            JSONObject obj = new JSONObject();
        	// 한글깨짐 방지를 위해 인코딩하기

            obj.put("label", dto.getBl_company());
            obj.put("value", dto.getBl_company());
            array.add(obj);
        }
        
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        out.print(array.toString());
    }
    
    /**
     *RPA 목록을 조회한다.
     */
    @RequestMapping("/rpa/cmm/popupExtraExcel.do")
    public String popupExtraExcel(@ModelAttribute("searchVO") RpaCmmVO vo, ModelMap model) throws Exception {
    	
    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	
		return "rpa/common/popupExtraExcel";
    }
    
    
    /**
     *RPA 목록을 조회한다.
     */
    @RequestMapping("/rpa/cmm/uploadSapFile.do")
    public String uploadSapFile(@ModelAttribute("searchVO") RpaCmmVO vo, ModelMap model) throws Exception {
    	
    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	
		return "rpa/common/EgovBoardSapFile";
    }
    
    @RequestMapping(value = "/rpa/cmm/fileUpDownloadSap.do")
    public String daumEditor(ModelMap model) throws Exception {
    	
        return "copycoding/util/FileUpDownload";
    }

    

    @RequestMapping(value = "/rpa/cmm/fileUploadSap.do")
    @ResponseBody
    public Map<String, Object> uploadSingleFile(
                         MultipartHttpServletRequest multiRequest,
                         HttpServletRequest request,
                         SessionVO sessionVO,
                         ModelMap model,
                         SessionStatus status) throws Exception{
              
    	//파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
    	int process = rpaCmmService.insertFileSapInf(multiRequest,request);  
        
     // 요청에 응답하기 위한 맵 객체를 성한다.
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (process > -1) {
            resultMap.put("status", 0);
            resultMap.put("msg", "정상적으로 파일이 등록되었습니다.");
        }
        else{
            resultMap.put("status", 1);
            resultMap.put("msg", "파일 등록에 실패했습니다.");
        }
        
        return resultMap;

    }
    
    /**
     *RPA 목록을 조회한다.
     */
    @RequestMapping("/rpa/cmm/selectFileSap.do")
    public String selectFileSap(@ModelAttribute("searchVO") RpaCmmVO vo, ModelMap model) throws Exception {
    	
    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	

    	Map<String, Object> map = rpaCmmService.selectFileSap(vo);
    	
    	
    	model.addAttribute("resultList", map.get("resultList"));

		return "rpa/common/EgovBoardSapFileList";
    }
    
    /**
     *RPA 목록을 조회한다.
     */
    @RequestMapping("/rpa/cmm/selectFileView.do")
    public String selectFileView(@ModelAttribute("searchVO") RpaCmmVO vo, ModelMap model) throws Exception {
    	
    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	

    	Map<String, Object> map = rpaCmmService.selectFileSap(vo);
    	
    	
    	model.addAttribute("resultList", map.get("resultList"));

		return "rpa/common/popupFileView";
    }
    
    /**
     *RPA 목록을 수정한다 kkk.
     */
    @RequestMapping(value="/rpa/cmm/deleteFileSap.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteFileSap(
            MultipartHttpServletRequest multiRequest,
            HttpServletRequest request,
            SessionVO sessionVO,
            ModelMap model,
            SessionStatus status) throws Exception {
	
    	String atchFileId = request.getParameter("atchFileId");
    	String blNo = request.getParameter("searchBlNo");
    	String fileGubun = request.getParameter("searchFileCn");
    	String filepath = request.getParameter("pathurl");    	
    	
    	RpaCmmVO result = new RpaCmmVO();
	    result.setAtch_file_id(atchFileId);
	    result.setBlno(blNo);
	    result.setFile_gubun(fileGubun);
	    
	    
   	 // 요청에 응답하기 위한 맵 객체를 성한다.
        Map<String, Object> map = new HashMap<String, Object>();
	    
    	
        map = rpaCmmService.deleteFileSap(result);
		
        
    	 // 요청에 응답하기 위한 맵 객체를 성한다.
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (map.get("process").toString().equals("0")) {
        	resultMap.put("filecnt", -1);
            resultMap.put("status", 1);
            resultMap.put("msg", "파일 삭제에 실패했습니다.");
        }
        else{
        	File file = new File(filepath); 
        	if( file.exists() ){ 
        		if(file.delete()){ 
        			log.info("파일삭제 성공"); 
        			}
        		else{ 
        			log.info("파일삭제 실패"); 
        			} 
        		}
        	else{ 
        		log.info("파일이 존재하지 않습니다."); 
        		} 

        		
            resultMap.put("filecnt", Integer.parseInt(map.get("FileCnt").toString()));
            resultMap.put("status", 0);
            resultMap.put("msg", "정상적으로 파일이 삭제되었습니다.");
        }
        
        return resultMap;
    }
    
    /**
     *RPA 목록을 조회한다.
     */
    @RequestMapping("/rpa/cmm/pdfView.do")
    public String pdfView(@ModelAttribute("searchVO") RpaCmmVO vo, ModelMap model) throws Exception {
    	model.addAttribute("atchFileId", vo.getAtch_file_id());
		return "rpa/common/pdfview";
    }
    

    /**
     *RPA 목록을 조회한다.
     */
    @RequestMapping("/rpa/cmm/pdfFileView.do")
    public String pdfFileView(@ModelAttribute("searchVO") RpaCmmVO searchVO, ModelMap model) throws Exception {
    	RpaCmmVO vo = rpaCmmService.selectFileSapDetail(searchVO);
		
		String fileStreCours = vo.getFile_stre_cours();
		String orignlFileNm = vo.getOrignl_file_nm();
		String streFileNm = vo.getStre_file_nm();
		String pathurl = vo.getFile_stre_cours();
		pathurl = pathurl.replace("/homepage", "");
		
		model.addAttribute("pdfurl", pathurl+streFileNm);
    	//model.addAttribute("filename", boardMaster.getStreFileNm());
    	//model.addAttribute("realname", boardMaster.getOrignlFileNm());
		return "rpa/common/pdffileview";
    }
    
    /**
     *RPA 목록을 조회한다.
     */
    @RequestMapping("/rpa/cmm/fileView.do")
    public String fileView(@ModelAttribute("searchVO") RpaCmmVO searchVO, ModelMap model) throws Exception {
    	model.addAttribute("filepath", "/homepage/upload/sap/");
    	model.addAttribute("filename", searchVO.getStre_file_nm());
    	model.addAttribute("realname", searchVO.getOrignl_file_nm());
		return "rpa/common/filedown";
    }
    
    /**
     *RPA 목록을 조회한다.
     */
    @RequestMapping("/rpa/cmm/imgView.do")
    public String imgView(@ModelAttribute("searchVO") RpaCmmVO vo, ModelMap model) throws Exception {
    	model.addAttribute("atchFileId", vo.getAtch_file_id());
		return "rpa/common/imgview";
    }
    
    @RequestMapping(value = "/rpa/cmm/display.do", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(@ModelAttribute("searchVO") RpaCmmVO searchVO, HttpServletRequest request)throws Exception{
		RpaCmmVO vo = rpaCmmService.selectFileSapDetail(searchVO);
		
		String fileStreCours = vo.getFile_stre_cours();
		String orignlFileNm = vo.getOrignl_file_nm();
		String streFileNm = vo.getStre_file_nm();
		
		//fileStreCours = fileStreCours.replace("/homepage", "");
		//fileStreCours = "d:/" + fileStreCours;
	//	streFileNm = streFileNm.replace(".pdf","");
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		//logger.info("FILE NAME : " + fileName);
		try {
			String formatName = streFileNm.substring(streFileNm.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(fileStreCours+streFileNm);
			
			//step: change HttpHeader ContentType
			if(mType != null) {
				//image file(show image)
				headers.setContentType(mType);
			}else {
				String client = request.getHeader("User-Agent");
				//fileName = fileName.substring(fileName.indexOf("_")+1);//original file Name
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				// IE
	            if(client.indexOf("MSIE") != -1){
	            	String realname = new String(orignlFileNm.getBytes("KSC5601"),"iso-8859-1");
	            	headers.add("Content-Disposition", "attachment; filename=\"" + realname+"\"");
	                
	            }else{
	                // 한글 파일명 처리
	            	String realname = new String(orignlFileNm.getBytes("utf-8"),"iso-8859-1");
	            	headers.add("Content-Disposition", "attachment; filename=\"" + realname+"\""); 
	            }
	            
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
			return entity;
		
	}
	
	
	@RequestMapping(value = "/rpa/cmm/pdfdown.do", method = RequestMethod.GET)
	public ResponseEntity<byte[]> pdfdown(@ModelAttribute("searchVO") RpaCmmVO searchVO, HttpServletRequest request)throws Exception{
		RpaCmmVO vo = rpaCmmService.selectFileSapDetail(searchVO);
		
		String fileStreCours = vo.getFile_stre_cours();
		String orignlFileNm = vo.getOrignl_file_nm();
		String streFileNm = vo.getStre_file_nm();
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		//logger.info("FILE NAME : " + fileName);
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(fileStreCours+streFileNm);
			
		
			String client = request.getHeader("User-Agent");
			//fileName = fileName.substring(fileName.indexOf("_")+1);//original file Name
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			// IE
            if(client.indexOf("MSIE") != -1){
            	String realname = new String(orignlFileNm.getBytes("KSC5601"),"iso-8859-1");
            	headers.add("Content-Disposition", "attachment; filename=\"" + realname+"\"");
                
            }else{
                // 한글 파일명 처리
            	String realname = new String(orignlFileNm.getBytes("utf-8"),"iso-8859-1");
            	headers.add("Content-Disposition", "attachment; filename=\"" + realname+"\""); 
            }
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
			return entity;
		
	}

}

