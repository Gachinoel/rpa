package wj.rpa.excel.service.impl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import egovframework.let.cop.bbs.service.BoardMaster;
//import egovframework.let.cop.bbs.service.BoardMasterVO;
//import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService;

import wj.rpa.excel.service.ExcelMaster;
import wj.rpa.excel.service.ExcelMasterVO;
import wj.rpa.excel.service.wjRpaManageService;



import egovframework.let.cop.com.service.BoardUseInf;
import egovframework.let.cop.com.service.EgovUserInfManageService;
import egovframework.let.cop.com.service.UserInfVO;
import egovframework.let.cop.com.service.impl.BBSUseInfoManageDAO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.24
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.24  이삼섭          최초 생성
 *  2009.06.26	한성곤		   2단계 기능 추가 (댓글관리, 만족도조사)
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Service("wjRpaManageService")
//public class EgovBBSAttributeManageServiceImpl extends EgovAbstractServiceImpl implements EgovBBSAttributeManageService {
public class wjRpaManageServiceImpl extends EgovAbstractServiceImpl implements wjRpaManageService {

	
	//private static final Logger LOGGER = LoggerFactory.getLogger(EgovBBSAttributeManageServiceImpl.class);
	  private static final Logger LOGGER = LoggerFactory.getLogger(wjRpaManageServiceImpl.class);

    @Resource(name = "BBSAttributeManageDAO")
    private wjRpaManageDAO rpaMngDAO;

    
    
    

    /**
     * RPA메인 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectRpaMainList(ExcelMasterVO searchVO) throws Exception {
    	
    	List<ExcelMasterVO> result = rpaMngDAO.selectRpaMainList(searchVO);
    	
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("resultList", result);
		
		return map;
    }
     
      public List<ExcelMasterVO> selectInvoiceList(ExcelMaster vo) throws Exception {
      	return rpaMngDAO.selectInvoiceList((ExcelMasterVO) vo);
      	  
      }
    
     //엑셀2번째
      public List<ExcelMasterVO> selectCargoList(ExcelMaster vo) throws Exception {
      	
      	return rpaMngDAO.selectCargoList((ExcelMasterVO) vo);
      	  
      }
      
     //엑셀3번째
      public List<ExcelMasterVO> selectBlList(ExcelMaster vo) throws Exception {
      	
      	return rpaMngDAO.selectBlList((ExcelMasterVO) vo);
      	  
      }
      
    //엑셀4번째
      public List<ExcelMasterVO> selectPassList(ExcelMaster vo) throws Exception {
      	
      	return rpaMngDAO.selectPassList((ExcelMasterVO) vo);
      	  
      }
          
          
   
}
