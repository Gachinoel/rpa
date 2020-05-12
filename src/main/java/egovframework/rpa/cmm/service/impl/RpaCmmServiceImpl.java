
package egovframework.rpa.cmm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rpa.cmm.dao.RpaCmmDAO;
import egovframework.rpa.cmm.service.RpaCmmService;
import egovframework.rpa.cmm.vo.RpaCmmVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("rpaCmmService")
public class RpaCmmServiceImpl extends EgovAbstractServiceImpl implements RpaCmmService{
	
    @Resource(name = "rpaCmmDAO")
    private RpaCmmDAO rpaCmmDAO;
    
    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

	public List<RpaCmmVO> selectBLNoInfs(RpaCmmVO searchVO) throws Exception {
		
		return rpaCmmDAO.selectBLNoInfs(searchVO);
		
	}

	public List<RpaCmmVO> selectCompanyInfs(RpaCmmVO searchVO) throws Exception {
		
		return rpaCmmDAO.selectCompanyInfs(searchVO);
		
	}

	@SuppressWarnings({ "unchecked" })
  	public int insertFileSapInf(MultipartHttpServletRequest multiRequest,
            HttpServletRequest request) throws Exception {
	  
		String storePath = request.getParameter("savePath");
		String saveDir = request.getParameter("saveDir");

		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();  
		String blNo = request.getParameter("blNo");
		String fileGubun = request.getParameter("fileGubun");
		String fileGubunNm = request.getParameter("fileGubunNm");
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("blNo", blNo);
		 
		RpaCmmVO result = null;
		 
		String atchFileId = "";
		 
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		int process = -1;
		
		if(!files.isEmpty()){
			atchFileId = rpaCmmDAO.selectFileSapMax(map);
			// 파일 객체, 구분 값, 파일 순번, 파일ID, 저장경로
			result = fileUtil.parseFileCmmInf(files, blNo, 0, atchFileId, storePath,saveDir);
			
			Map<String, Object> filemap = BeanUtils.describe(result);
			
			for(int i = 0; i < request.getParameterValues("fileSn").length ; i++){
				Map<String, Object> filecnMap = new HashMap<String, Object>();
				filecnMap.put("fileSn",request.getParameterValues("fileSn")[i]);
				fileList.add(filecnMap);
			}
			
			//Map<String, Object> filemap = new HashMap<String, Object>();
			
			filemap.putAll(filemap);
			filemap.put("dirGubun", saveDir.toUpperCase());
			filemap.put("fileGubun", fileGubun);
			filemap.put("fileGubunNm", fileGubunNm);
			filemap.put("fileList", fileList);
			
			//파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			process = rpaCmmDAO.insertFileSapInf(filemap);  
		}
		 
		//attrbMngDAO.insertFileSapInf(fvo);
		
		return process;
	}


	public Map<String, Object> selectFileSap(RpaCmmVO vo) throws Exception {
    	
    	List<RpaCmmVO> result = rpaCmmDAO.selectFileSap(vo);
    	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("resultList", result);
		//map.put("resultCnt", Integer.toString(cnt));
	
		return map;
    }
	
	public RpaCmmVO selectFileSapDetail(RpaCmmVO vo) throws Exception {
    	
		RpaCmmVO result = rpaCmmDAO.selectFileSapDetail(vo);
    	
	
		return result;
    }
	
	public Map<String, Object> deleteFileSap(RpaCmmVO vo) throws Exception {
		
		int process = (Integer)rpaCmmDAO.deleteFileSap(vo);
		
		String fileGubun = vo.getFile_gubun();
		
		StringBuilder sb = new StringBuilder();
		sb.append("%");
		sb.append(fileGubun);
		sb.append("%");
		
		vo.setFile_gubun(sb.toString());
		
		int FileCnt = rpaCmmDAO.selectFileSapCnt(vo);
			
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("FileCnt", FileCnt);
		resultMap.put("process", process);
    	return resultMap;
    }

}

