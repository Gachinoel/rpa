package egovframework.let.cop.bbs.service.impl;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.*;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.ExcelStyleBuilder;
import egovframework.let.cop.bbs.service.BoardMaster;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.let.cop.bbs.service.BoardSapFileVO;
import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.let.cop.bbs.service.ExcelFileType;
import egovframework.let.cop.bbs.service.ExcelRead;
import egovframework.let.cop.bbs.service.ExcelReadOption;
import egovframework.let.cop.com.service.BoardUseInf;
import egovframework.let.cop.com.service.EgovUserInfManageService;
import egovframework.let.cop.com.service.UserInfVO;
import egovframework.let.cop.com.service.impl.BBSUseInfoManageDAO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
@Service("EgovBBSAttributeManageService")
//public class EgovBBSAttributeManageServiceImpl extends EgovAbstractServiceImpl implements EgovBBSAttributeManageService {
public class EgovBBSAttributeManageServiceImpl extends EgovAbstractServiceImpl implements EgovBBSAttributeManageService {

	
	//private static final Logger LOGGER = LoggerFactory.getLogger(EgovBBSAttributeManageServiceImpl.class);
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBBSAttributeManageServiceImpl.class);
	
    @Resource(name = "BBSAttributeManageDAO")
    private BBSAttributeManageDAO attrbMngDAO;

    @Resource(name = "BBSUseInfoManageDAO")
    private BBSUseInfoManageDAO bbsUseDAO;

    @Resource(name = "EgovUserInfManageService")
    private EgovUserInfManageService userService;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    @Resource(name = "BBSAddedOptionsDAO")
    private BBSAddedOptionsDAO addedOptionsDAO;
    ////-------------------------------

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#deleteBBSMasterInf(egovframework.let.cop.bbs.ExcelMaster.service.BoardMaster)
     */
    public void deleteBBSMasterInf(BoardMaster boardMaster) throws Exception {
	attrbMngDAO.deleteBBSMasterInf(boardMaster);

	BoardUseInf bdUseInf = new BoardUseInf();

	bdUseInf.setBbsId(boardMaster.getBbsId());
	bdUseInf.setLastUpdusrId(boardMaster.getLastUpdusrId());

	bbsUseDAO.deleteBBSUseInfByBoardId(bdUseInf);
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#insertBBSMastetInf(egovframework.let.cop.bbs.ExcelMaster.service.BoardMaster)
     */
    public String insertBBSMastetInf(BoardMaster boardMaster) throws Exception {
	String bbsId = idgenService.getNextStringId();

	boardMaster.setBbsId(bbsId);

	attrbMngDAO.insertBBSMasterInf(boardMaster);

	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
	    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
	}
	////-------------------------------

	if ("Y".equals(boardMaster.getBbsUseFlag())) {
	    BoardUseInf bdUseInf = new BoardUseInf();

	    bdUseInf.setBbsId(bbsId);
	    bdUseInf.setTrgetId(boardMaster.getTrgetId());
	    bdUseInf.setRegistSeCode(boardMaster.getRegistSeCode());
	    bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());
	    bdUseInf.setUseAt("Y");

	    bbsUseDAO.insertBBSUseInf(bdUseInf);

	    UserInfVO userVO = new UserInfVO();
	    userVO.setTrgetId(boardMaster.getTrgetId());

	    List<UserInfVO> tmpList = null;
	    Iterator<UserInfVO> iter = null;

	    if ("REGC05".equals(boardMaster.getRegistSeCode())) {
		tmpList = userService.selectAllClubUser(userVO);
		iter = tmpList.iterator();
		while (iter.hasNext()) {
		    bdUseInf = new BoardUseInf();

		    bdUseInf.setBbsId(bbsId);
		    bdUseInf.setTrgetId(((UserInfVO)iter.next()).getUniqId());
		    bdUseInf.setRegistSeCode("REGC07");
		    bdUseInf.setUseAt("Y");
		    bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());

		    bbsUseDAO.insertBBSUseInf(bdUseInf);
		}
	    } else if ("REGC06".equals(boardMaster.getRegistSeCode())) {
		tmpList = userService.selectAllCmmntyUser(userVO);
		iter = tmpList.iterator();
		while (iter.hasNext()) {
		    bdUseInf = new BoardUseInf();

		    bdUseInf.setBbsId(bbsId);
		    bdUseInf.setTrgetId(((UserInfVO)iter.next()).getUniqId());
		    bdUseInf.setRegistSeCode("REGC07");
		    bdUseInf.setUseAt("Y");
		    bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());

		    bbsUseDAO.insertBBSUseInf(bdUseInf);
		}
	    }
	}
	return bbsId;
    }
    
    
    
    
    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectAllBBSMasteInf(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
     */
    public List<BoardMasterVO> selectAllBBSMasteInf(BoardMasterVO vo) throws Exception {
	return attrbMngDAO.selectAllBBSMasteInf(vo);
    }

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInf(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
     */
    public BoardMasterVO selectBBSMasterInf(BoardMaster searchVO) throws Exception {
	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	//return attrbMngDAO.selectBBSMasterInf(searchVO);

	BoardMasterVO result = attrbMngDAO.selectBBSMasterInf(searchVO);

	String flag = propertyService.getString("Globals.addedOptions");
	if (flag != null && flag.trim().equalsIgnoreCase("true")) {
	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(searchVO);

	    if (options != null) {
		if (options.getCommentAt().equals("Y")) {
		    result.setOption("comment");
		}

		if (options.getStsfdgAt().equals("Y")) {
		    result.setOption("stsfdg");
		}
	    } else {
		result.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
	    }
	}

	return result;
	////-------------------------------

    }

    /**
     * rpa메인 목록을 조회 한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
     */
    public Map<String, Object> selectBBSMasterInfs(BoardMasterVO searchVO) throws Exception {
    	
    	List<BoardMasterVO> result = attrbMngDAO.selectBBSMasterInfs(searchVO);
    	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("resultList", result);
		//map.put("resultCnt", Integer.toString(cnt));
	
		return map;
    }
    
    
      

    /**
     * 게시판 속성정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#updateBBSMasterInf(egovframework.let.cop.bbs.ExcelMaster.service.BoardMaster)
     */
    public void updateBBSMasterInf(BoardMaster boardMaster) throws Exception {
    		attrbMngDAO.updateBBSMasterInf(boardMaster);
	
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#updateBBSMasterInf(egovframework.let.cop.bbs.ExcelMaster.service.BoardMaster)
     */
    public int finishBBSMasterInf(BoardMasterVO searchVO) throws Exception {
    		return (Integer)attrbMngDAO.finishBBSMasterInf(searchVO);
	
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#updateBBSMasterInf(egovframework.let.cop.bbs.ExcelMaster.service.BoardMaster)
     */
    public void insertBBSMasterSAPInf(BoardMaster boardMaster,BoardMasterVO boardMasterVO) throws Exception {
    	try{
	    	attrbMngDAO.updateBBSMasterSAPInf(boardMaster);
	    	attrbMngDAO.insertBBSMasterSAPInf(boardMaster);
	    	attrbMngDAO.deleteBBSMasterSAPInf(boardMaster);
	        attrbMngDAO.finishBBSMasterInf(boardMasterVO);
        
    	}
		catch(Exception e){
			LOGGER.error(e.toString());
			throw e;
	 	} 
	
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#updateBBSMasterInf(egovframework.let.cop.bbs.ExcelMaster.service.BoardMaster)
     */
    public int deleteBBSMasterSAPInf(BoardMaster boardMaster) throws Exception {
    	return (Integer)attrbMngDAO.deleteBBSMasterSAPInf(boardMaster);
	
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     *
     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#validateTemplate(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
     */
    public void validateTemplate(BoardMasterVO searchVO) throws Exception {
    	LOGGER.debug("validateTemplate method ignored...");
    }

    /**
     * 사용중인 게시판 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectBdMstrListByTrget(BoardMasterVO vo) throws Exception {
	List<BoardMasterVO> result = attrbMngDAO.selectBdMstrListByTrget(vo);
	int cnt = attrbMngDAO.selectBdMstrListCntByTrget(vo);

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록을 전체조회 한다.
     */
    public List<BoardMasterVO> selectAllBdMstrByTrget(BoardMasterVO vo) throws Exception {
	return attrbMngDAO.selectAllBdMstrByTrget(vo);
    }

    /**
     * 사용중이지 않은 게시판 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO searchVO) throws Exception {
	List<BoardMasterVO> result = attrbMngDAO.selectNotUsedBdMstrList(searchVO);
	int cnt = attrbMngDAO.selectNotUsedBdMstrListCnt(searchVO);

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }
    
        public List<BoardMasterVO> selectInvoiceList(BoardMaster vo) throws Exception {
          	
          	//List<BoardMasterVO> result = attrbMngDAO.selectInvoiceList(searchVO);
          	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
      		//Map<String, Object> map = new HashMap<String, Object>();
      		//map.put("resultList", result);
      		//map.put("resultCnt", Integer.toString(cnt));
          	//return map;
        		
          	
    		  //kkk
    		  //List<BoardMasterVO> result = attrbMngDAO.selectInvoiceList(searchVO);
    		  //return (BoardMasterVO) result;
        	System.out.println("impal1");
        	return attrbMngDAO.selectInvoiceList((BoardMasterVO) vo);
          	  
          }
        
         //엑셀2번째
          public List<BoardMasterVO> selectCargoList(BoardMaster vo) throws Exception {
          	
          	return attrbMngDAO.selectCargoList((BoardMasterVO) vo);
          	  
          }
          
         //엑셀3번째
          public List<BoardMasterVO> selectBlList(BoardMaster vo) throws Exception {
          	
          	return attrbMngDAO.selectBlList((BoardMasterVO) vo);
          	  
          }
          
        //엑셀4번째
          public List<BoardMasterVO> selectPassList(BoardMaster vo) throws Exception {
          	
          	return attrbMngDAO.selectPassList((BoardMasterVO) vo);
          	  
          }
          
        //엑셀5번째
          public List<BoardMasterVO> selectDocList(BoardMaster vo) throws Exception {
          	
          	return attrbMngDAO.selectDocList((BoardMasterVO) vo);
          	  
          }
          //엑셀6번째
          public List<BoardMasterVO> selectSapList(BoardMaster vo) throws Exception {
          	
          	return attrbMngDAO.selectSapList((BoardMasterVO) vo);
          	  
          }
          
        //엑셀4번째
          public List<BoardMasterVO> selectBBSSAPBktxtInfs(BoardMaster vo) throws Exception {
          	
          	return attrbMngDAO.selectBBSSAPBktxtInfs((BoardMasterVO) vo);
          	  
          }
          
        //엑셀4번째
          public List<BoardMasterVO> selectBBSSAPLifnrInfs(BoardMaster vo) throws Exception {
          	
          	return attrbMngDAO.selectBBSSAPLifnrInfs((BoardMasterVO) vo);
          	  
          }
          
          /**
           * rpa메인 목록을 조회 한다.
           *
           * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
           */
          @SuppressWarnings("unchecked")
		public Map<String, Object> selectSapSubBLSPList(BoardMasterVO searchVO) throws Exception {
          	
        	Map<String, Object> map = new HashMap<String, Object>();
       	    map.put("searchBlNo", searchVO.getSearchBlNo());
       	    map.put("regdata", searchVO.getCheckContType());
       	    map.put("t3_confirm", searchVO.gett3_confirm());
       	    map.put("t3_preconfirm", searchVO.gett3_preconfirm());
       	    map.put("processFlag", searchVO.getProcessflag());
        	
       	    List<BoardMasterVO> result = attrbMngDAO.selectSapSubBLSPList(map);
          	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
          	

      		Map<String, Object> resultmap = new HashMap<String, Object>();
      		
    		//List<BoardMasterVO> resultList = (List<BoardMasterVO>) result.get(0);
    		
    		String confirm_yn = "";
    		String preconfirm_yn = "";
    		if (result.size() > 0) { 
    			confirm_yn = result.get(0).gett3_confirm();
    			preconfirm_yn = result.get(0).gett3_preconfirm();
    		}
    	
    		resultmap.put("resultList", result);
    		resultmap.put("t3_confirm", confirm_yn);
    		resultmap.put("t3_preconfirm", preconfirm_yn);
      	
      		return resultmap;
          }
          
          /**
           * rpa메인 목록을 조회 한다.
           *
           * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
           */
          @SuppressWarnings("unchecked")
		public Map<String, Object> selectExtraSPList(BoardMaster searchVO) throws Exception {
          	
        	Map<String, Object> map = new HashMap<String, Object>();
       	    map.put("chkmonth", searchVO.getSearchMonth());
        	
       	    List<Map<String, Object>> result = attrbMngDAO.selectExtraSPList(map);
          	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
          	

      		Map<String, Object> resultmap = new HashMap<String, Object>();
      		
    		List<BoardMasterVO> extraList = (List<BoardMasterVO>) result.get(0);
    		List<BoardMasterVO> extra1List = (List<BoardMasterVO>) result.get(1);
    		
    		int extraMax = extraList.size();
    		String totalExtra = extraList.get(extraMax-1).getExtra_total();
    		long nTotalExtra = Long.parseLong(totalExtra.replaceAll(",", ""));
    		int extraMax1 = extra1List.size();
    		String totalExtra1 = extra1List.get(extraMax1-1).getExtra1_total();
    		long nTotalExtra1 = Long.parseLong(totalExtra1.replaceAll(",", ""));
    		
    		long nAllTotal = nTotalExtra + nTotalExtra1;
    		
      	
      		resultmap.put("extraList", extraList);
      		resultmap.put("extra1List", extra1List);
      		resultmap.put("resultTotal", nAllTotal);
      	
      		return resultmap;
          }
          
          /**
           * rpa메인 목록을 조회 한다.
           *
           * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
           */
          @SuppressWarnings("unchecked")
		public Map<String, Object> selectExtraSPList1(BoardMaster searchVO) throws Exception {
          	
        	Map<String, Object> map = new HashMap<String, Object>();
       	    map.put("chkmonth", searchVO.getSearchMonth());
        	
       	    List<Map<String, Object>> result = attrbMngDAO.selectExtraSPList1(map);
          	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
          	

      		Map<String, Object> resultmap = new HashMap<String, Object>();
      		
    		List<BoardMasterVO> extraList = (List<BoardMasterVO>) result.get(0);
    		List<BoardMasterVO> extra1List = (List<BoardMasterVO>) result.get(1);
    		
    		int extraMax = extraList.size();
    		String totalExtra = extraList.get(extraMax-1).getExtra_total();
    		long nTotalExtra = Long.parseLong(totalExtra.replaceAll(",", ""));
    		int extraMax1 = extra1List.size();
    		String totalExtra1 = extra1List.get(extraMax1-1).getExtra1_total();
    		long nTotalExtra1 = Long.parseLong(totalExtra1.replaceAll(",", ""));
    		
    		long nAllTotal = nTotalExtra + nTotalExtra1;
    		
      	
      		resultmap.put("extraList", extraList);
      		resultmap.put("extra1List", extra1List);
      		resultmap.put("resultTotal", nAllTotal);
      	
      		return resultmap;
          }
          
          /**
           * rpa메인 목록을 조회 한다.
           *
           * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
           */
          @SuppressWarnings("unchecked")
		public Map<String, Object> selectYetArrivedList(BoardMaster searchVO) throws Exception {
          	
        	Map<String, Object> map = new HashMap<String, Object>();
       	    map.put("chkmonth", searchVO.getSearchMonth());
        	
       	    List<Map<String, Object>> result = attrbMngDAO.selectYetArrivedList(map);
          	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
          	

      		Map<String, Object> resultmap = new HashMap<String, Object>();
      		
    		List<BoardMasterVO> yetList = (List<BoardMasterVO>) result.get(0);
    		List<BoardMasterVO> yetTotal = (List<BoardMasterVO>) result.get(1);
    		
    		//GROUP BY된 데이터를 받을 MAP
        	Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();

        	for(int i=0; i<yetList.size(); i++){
        		String orderNumber = "";
        		if (yetList.get(i).getwaers().toString().equals("") ){
        			orderNumber = yetList.get(i).getHwaer().toString();
        		}
        		else{
        			orderNumber = yetList.get(i).getwaers().toString();
        		}
        		if(resultMap.containsKey(orderNumber)){
        			//KEY값이 존재하면 해당 키값의 해당되는 가격을 가져와 더해줌
        			resultMap.get(orderNumber).put("wrbtr", Double.parseDouble(resultMap.get(orderNumber).get("wrbtr").toString()) 
        			+ Double.parseDouble(yetList.get(i).getwrbtr().toString()));
        			resultMap.get(orderNumber).put("wrbtrp", Double.parseDouble(resultMap.get(orderNumber).get("wrbtrp").toString()) 
        			+ Double.parseDouble(yetList.get(i).getWrbtrp().toString()));
        		}else{
        			//KEY값이 존재하지 않으면 MAP에 데이터를 넣어줌
        			Map<String, Object> dataMap = new HashMap<String, Object>();
        			dataMap.put("wrbtr", Double.parseDouble(yetList.get(i).getwrbtr().toString()));
        			dataMap.put("wrbtrp", Double.parseDouble(yetList.get(i).getWrbtrp().toString()));
        			resultMap.put(orderNumber, dataMap);
        		}
        	}
        	
        	//GROUP BY된 데이터를 받을 MAP
        	Map<String, Map<String, Object>> resultMap2 = new HashMap<String, Map<String, Object>>();

        	for(int i=0; i<yetTotal.size(); i++){
        		String orderNumber = "total";
        		if(resultMap2.containsKey(orderNumber)){
        			//KEY값이 존재하면 해당 키값의 해당되는 가격을 가져와 더해줌
        			resultMap2.get(orderNumber).put("wrbtrp", Double.parseDouble(resultMap2.get(orderNumber).get("wrbtrp").toString()) 
        			+ Double.parseDouble(yetTotal.get(i).getWrbtrp().toString()));
        			resultMap2.get(orderNumber).put("taxamt", Double.parseDouble(resultMap2.get(orderNumber).get("taxamt").toString()) 
        			+ Double.parseDouble(yetTotal.get(i).getTaxamt().toString()));
        			resultMap2.get(orderNumber).put("blamt", Double.parseDouble(resultMap2.get(orderNumber).get("blamt").toString()) 
        			+ Double.parseDouble(yetTotal.get(i).getBlamt().toString()));
        		}else{
        			//KEY값이 존재하지 않으면 MAP에 데이터를 넣어줌
        			Map<String, Object> dataMap = new HashMap<String, Object>();
        			dataMap.put("wrbtrp", Double.parseDouble(yetTotal.get(i).getWrbtrp().toString()));
        			dataMap.put("taxamt", Double.parseDouble(yetTotal.get(i).getTaxamt().toString()));
        			dataMap.put("blamt", Double.parseDouble(yetTotal.get(i).getBlamt().toString()));
        			resultMap2.put(orderNumber, dataMap);
        		}
        	}
    		
      	
      		resultmap.put("yetList", yetList);
      		resultmap.put("yetTotal", yetTotal);
      		resultmap.put("resultMap", resultMap);
      		resultmap.put("resultMap2", resultMap2);
      	
      		return resultmap;
          }
          
          
          /**
           * rpa메인 목록을 조회 한다.
           *
           * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
           */
          @SuppressWarnings("unchecked")
		public List<BoardMasterVO> selectExtraSPFinishList(BoardMasterVO searchVO) throws Exception {
        	List<BoardMasterVO> result = attrbMngDAO.selectExtraSPFinishList(searchVO);
          	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
          	
      		return result;
          }
          
          /**
           * rpa메인 목록을 조회 한다.
           *
           * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
           */
          @SuppressWarnings("unchecked")
		public List<BoardMasterVO> selectExtraTax(BoardMasterVO searchVO) throws Exception {
        	List<BoardMasterVO> result = attrbMngDAO.selectExtraTax(searchVO);
          	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
          	
      		return result;
          }
          
          
          
  		  public Map<String, Object> selectSapSubList(BoardMasterVO searchVO) throws Exception {

        	    List<BoardMasterVO> result = attrbMngDAO.selectSapList((BoardMasterVO) searchVO);
            	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

        		Map<String, Object> map = new HashMap<String, Object>();
        		
        		String confirm_yn = "";
        		String preconfirm_yn = "";
        		if (result.size() > 0) { 
        			confirm_yn = result.get(0).gett3_confirm();
        			preconfirm_yn = result.get(0).gett3_preconfirm();
        		}
        	
        		map.put("resultList", result);
        		map.put("t3_confirm", confirm_yn);
        		map.put("t3_preconfirm", preconfirm_yn);
        		//map.put("resultCnt", Integer.toString(cnt));
        	
        		return map;
          	  
          }
          
  		public Map<String, Object> selectSapSubBLList(BoardMasterVO searchVO) throws Exception {

    	    List<BoardMasterVO> result = attrbMngDAO.selectSapSubBLList((BoardMasterVO) searchVO);
        	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

    		Map<String, Object> map = new HashMap<String, Object>();
    		
    		String confirm_yn = "";
    		String preconfirm_yn = "";
    		if (result.size() > 0) { 
    			confirm_yn = result.get(0).gett3_confirm();
    			preconfirm_yn = result.get(0).gett3_preconfirm();
    		}
    	
    		map.put("resultList", result);
    		map.put("t3_confirm", confirm_yn);
    		map.put("t3_preconfirm", preconfirm_yn);
    		//map.put("resultCnt", Integer.toString(cnt));
    	
    		return map;
      	  
      }
          

          /**
           * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
           *
           * @see egovframework.com.cmm.service.EgovFileMngService#insertFileInf(egovframework.com.cmm.service.FileVO)
           */
          @SuppressWarnings({ "unchecked" })
		@Override
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
				 
				BoardSapFileVO result = null;
				 
				String atchFileId = "";
				 
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				int process = -1;
				
				if(!files.isEmpty()){
					atchFileId = attrbMngDAO.selectFileSapMax(map);
					// 파일 객체, 구분 값, 파일 순번, 파일ID, 저장경로
					result = fileUtil.parseFileSapInf(files, blNo, 0, atchFileId, storePath,saveDir);
					
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
					process = attrbMngDAO.insertFileSapInf(filemap);  
				}
				 
				//attrbMngDAO.insertFileSapInf(fvo);
				
				return process;
          }


		public Map<String, Object> selectFileSap(BoardMasterVO boardMasterVO) throws Exception {
	    	
	    	List<BoardSapFileVO> result = attrbMngDAO.selectFileSap(boardMasterVO);
	    	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

			Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("resultList", result);
			//map.put("resultCnt", Integer.toString(cnt));
		
			return map;
	    }
		
		public BoardSapFileVO selectFileSapDetail(BoardMaster boardMaster) throws Exception {
	    	
	    	BoardSapFileVO result = attrbMngDAO.selectFileSapDetail(boardMaster);
	    	
		
			return result;
	    }
		
		public Map<String, Object> deleteFileSap(BoardSapFileVO vo) throws Exception {
			
			int process = (Integer)attrbMngDAO.deleteFileSap(vo);
			
			String fileGubun = vo.getFileGubun();
			
			StringBuilder sb = new StringBuilder();
			sb.append("%");
			sb.append(fileGubun);
			sb.append("%");
			
			vo.setFileGubun(sb.toString());
			
			int FileCnt = attrbMngDAO.selectFileSapCnt(vo);
				
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("FileCnt", FileCnt);
			resultMap.put("process", process);
	    	return resultMap;
	    }
		
		
		/**
         * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
         *
         * @see egovframework.com.cmm.service.EgovFileMngService#insertFileInf(egovframework.com.cmm.service.FileVO)
         */
		@Override
      	public int insertForward(BoardMasterVO boardMasterVO) throws Exception {
  	  
        	int overlabCnt = attrbMngDAO.selectForwardCnt(boardMasterVO);
        	
        	int process = -1;
        	
        	if (overlabCnt > 0) {
        		process = -1;
        	}
        	else {
	        	//파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
				process = attrbMngDAO.insertForward(boardMasterVO);
        	}
			 
			//attrbMngDAO.insertFileSapInf(fvo);
			
			return process;
        }
		
		public void deleteForward(BoardMasterVO boardMasterVO) throws Exception {
			attrbMngDAO.deleteForward(boardMasterVO);
	    }

		/**
		 * Excel File을 생성하여 Workbook을 반환.
		 *
		 * @param sheetTitle Sheet 제목
		 * @param excelCellInfos Cell의 생성 정보
		 * @param rows 실제 Data
		 * @return XSSFWorkbook
		 * @throws NoSuchMethodException 
		 * @throws InvocationTargetException 
		 * @throws IllegalAccessException 
		 * @throws NoSuchFieldException 
		 */
		@SuppressWarnings("unchecked")
		public SXSSFWorkbook buildExcelMergeXSS(String sheetTitle, Map<String, Object> excelInfpMap
				, List<BoardMasterVO> result, List<BoardMasterVO> result1, long nTotla,int nMon) 
						throws Exception {
			
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			
			ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

			Sheet sheet = workbook.createSheet(sheetTitle);

			sheet.setDisplayGridlines(false);
			
			PrintSetup print = sheet.getPrintSetup();
			//인쇄 용지를 A4로 설정
			print.setPaperSize(PrintSetup.A4_PAPERSIZE);
			 
			//인쇄 방향을 가로로 설정 true면 가로 false면 세로(default)
			print.setLandscape(false);
			 
			//인쇄시 확대/축소 배율
			print.setScale( (short)80 );
			print.setUsePage(true);
			print.setPageStart((short)1);

			Row row = null;
			Cell cell = null;
			
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
			Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
			List<Map<String, Object>> titleCellList = (List<Map<String, Object>>) excelInfpMap.get("titleCellList");
			List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");
			List<Map<String, Object>> fieldStyleList = (List<Map<String, Object>>) excelInfpMap.get("fieldStyleList");
					
			int nRow = 1; 

			if (headerMap != null){
				for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
								, headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
								, headerMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				
					int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
					int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
					int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
					int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(headerMap.get("title").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					row.setHeight((short)600);
				}
				nRow = Integer.parseInt(headerMap.get("eRow").toString());
				
			}
			
			if (titleStyleMap != null){
				for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
								, titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
								, titleStyleMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				}
				
				if (titleCellList != null){
					for(int n = 0; n < titleCellList.size(); n++) {
						int nRow1 = Integer.parseInt(titleCellList.get(n).get("sRow").toString());
						int nRow2 = Integer.parseInt(titleCellList.get(n).get("eRow").toString());
						int nCol1 = Integer.parseInt(titleCellList.get(n).get("sCol").toString());
						int nCol2 = Integer.parseInt(titleCellList.get(n).get("eCol").toString());
						
						row = sheet.getRow(nRow1);
	
						cell = row.getCell(nCol1);
	
						cell.setCellValue(titleCellList.get(n).get("cellTitle").toString());
						
						if ((nRow1 != nRow2) || (nCol1 != nCol2)){
							sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
						}
						//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
						//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
						row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					}
				}
				
				nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());
				
			}
			
			if (titleStyleMap != null){
				
				nRow = Integer.parseInt(titleStyleMap.get("firstRow").toString());
				
				String[] tempField = new String[fieldInfoList.size()];
				for (int n = 0; n < fieldInfoList.size(); n++) {
					tempField[n] = fieldInfoList.get(n).get("field").toString();
				}	
				
				if (result != null){
					int[] arrStartCnt = new int[5];
					int[] arrEndCnt = new int[5];
					String[][] arrField = new String[5][result.size()];
					Arrays.fill(arrStartCnt, nRow);
					Arrays.fill(arrEndCnt, 0);
					for (String[] arrRow: arrField)
						Arrays.fill(arrRow, "");

					int nCnt = 0;
					
					for (BoardMasterVO item : result) {
						Map<String, Object> testMap = new HashMap<String, Object>();
						try {
							testMap = domainToMapWithExcept(item, tempField);
						} catch (Exception e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						//row = sheet.getRow(nRow++);
						row = sheet.createRow(nRow++);
						String fieldTitle = "";
						for (int i = 0; i < fieldInfoList.size(); i++) {
							
							//cell = row.getCell(i);
							cell = row.createCell(i);
							
							if (nCnt > 0) {
								if (fieldInfoList.get(i).get("field").equals("extra_company")
										&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									arrEndCnt[0] = nRow - 1;
								}
							}
							
							
							if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
								if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
								}

							} else {
								cell.setCellValue(" ");
							}
							
							if (i == 0 && testMap.get(tempField[i]).equals("합계")) {
								sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 0, 2));
								fieldTitle = "allTot";
							}
							else if (i == 1 && !testMap.get(tempField[0]).equals("합계") && testMap.get(tempField[i]).equals("합계")) {
								sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 1, 2));
								fieldTitle = "subTot";
							}
							
							if(fieldTitle.equals("allTot")) {
								CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
										, "subtitle", fieldStyleList.get(i).get("fontColor").toString()
										, "c4c4c4"
										, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
										, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
								cell.setCellStyle(titleStyle);
								
							}
							else if(fieldTitle.equals("subTot")) {
								if (i > 0) {
									CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
											, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
											, "f4f4f4"
											, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
											, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
									cell.setCellStyle(titleStyle);
								}
								else {
									CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
											, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
											, fieldStyleList.get(i).get("styleColor").toString()
											, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
											, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
									cell.setCellStyle(titleStyle);
								}
								
							}
							else {
								CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
										, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
										, fieldStyleList.get(i).get("styleColor").toString()
										, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
										, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
								cell.setCellStyle(titleStyle);
							}
							
							//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
							sheet.setColumnWidth(i, Integer.parseInt(fieldInfoList.get(i).get("cellWidth").toString()) );
							if (nCnt > 0) {
								if (fieldInfoList.get(i).get("field").equals("extra_company")
										&& !arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									if (arrStartCnt[0] != 0 && arrEndCnt[0] != 0 && arrEndCnt[0] > arrStartCnt[0]) {
										sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[0], arrEndCnt[0], 0, 0));
									}

									arrStartCnt[0] = nRow - 1;
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								} else if (fieldInfoList.get(i).get("field").equals("extra_company")
										&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								}
							}
							else{
								if (fieldInfoList.get(i).get("field").equals("extra_company")) {
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								}
							}
						}
						nCnt++;
						
					}
					
					for(int nMer = 0; nMer < 1;nMer++) {
						arrEndCnt[nMer] = nRow - 1;
						if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
							sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
						}
					}
					
				}
				
				nRow = Integer.parseInt(titleStyleMap.get("lastRow").toString());
				
			}
			
			
			if (headerMap != null){
				for (int i = Integer.parseInt(headerMap.get("sRow1").toString()); i <= Integer.parseInt(headerMap.get("eRow1").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
								, headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
								, headerMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				
					int nRow1 = Integer.parseInt(headerMap.get("sRow1").toString());
					int nRow2 = Integer.parseInt(headerMap.get("eRow1").toString());
					int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
					int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(headerMap.get("title1").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					row.setHeight((short)600);
				}
				nRow = Integer.parseInt(headerMap.get("eRow1").toString());
				
			}
			
			if (titleStyleMap != null){
				for (int i = Integer.parseInt(titleStyleMap.get("sRow1").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow1").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
								, titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
								, titleStyleMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				}
				
				nRow = Integer.parseInt(titleStyleMap.get("sRow1").toString());
				
				if (titleCellList != null){
					for(int n = 0; n < titleCellList.size(); n++) {
						int nRow1 = nRow + Integer.parseInt(titleCellList.get(n).get("sRow").toString()) - 3;
						int nRow2 = nRow + Integer.parseInt(titleCellList.get(n).get("eRow").toString()) - 3;
						int nCol1 = Integer.parseInt(titleCellList.get(n).get("sCol").toString());
						int nCol2 = Integer.parseInt(titleCellList.get(n).get("eCol").toString());
						
						row = sheet.getRow(nRow1);
	
						cell = row.getCell(nCol1);
	
						cell.setCellValue(titleCellList.get(n).get("cellTitle").toString());
						
						if ((nRow1 != nRow2) || (nCol1 != nCol2)){
							sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
						}
						//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
						//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
						row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					}
				}
				
				nRow = Integer.parseInt(titleStyleMap.get("eRow1").toString());
				
			}
			
			if (titleStyleMap != null){
				
				nRow = Integer.parseInt(titleStyleMap.get("firstRow1").toString());
				
				String[] tempField = new String[fieldInfoList.size()];
				for (int n = 0; n < fieldInfoList.size(); n++) {
					tempField[n] = fieldInfoList.get(n).get("field1").toString();
				}	
				
				if (result1 != null){
					int[] arrStartCnt = new int[5];
					int[] arrEndCnt = new int[5];
					String[][] arrField = new String[5][result1.size()];
					Arrays.fill(arrStartCnt, nRow);
					Arrays.fill(arrEndCnt, 0);
					for (String[] arrRow: arrField)
						Arrays.fill(arrRow, "");

					int nCnt = 0;
					
					for (BoardMasterVO item : result1) {
						Map<String, Object> testMap = new HashMap<String, Object>();
						try {
							testMap = domainToMapWithExcept(item, tempField);
						} catch (Exception e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						//row = sheet.getRow(nRow++);
						row = sheet.createRow(nRow++);
						String fieldTitle = "";
						for (int i = 0; i < fieldInfoList.size(); i++) {
							
							//cell = row.getCell(i);
							cell = row.createCell(i);
							
							if (nCnt > 0) {
								if (fieldInfoList.get(i).get("field1").equals("extra1_company")
										&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field1")).toString())) {
									arrEndCnt[0] = nRow - 1;
								}
							}
							
							
							if ((testMap.get(fieldInfoList.get(i).get("field1")) != null) && (!testMap.get(fieldInfoList.get(i).get("field1")).equals(""))) {

								if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field1")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field1")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field1")).toString());
								}

							} else {
								cell.setCellValue(" ");
							}
							
							if (i == 0 && testMap.get(tempField[i]).equals("합계")) {
								sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 0, 2));
								fieldTitle = "allTot";
							}
							else if (i == 1 && !testMap.get(tempField[0]).equals("합계") && testMap.get(tempField[i]).equals("합계")) {
								sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 1, 2));
								fieldTitle = "subTot";
							}
							
							if(fieldTitle.equals("allTot")) {
								CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
										, "subtitle", fieldStyleList.get(i).get("fontColor").toString()
										, "c4c4c4"
										, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
										, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
								cell.setCellStyle(titleStyle);
								
							}
							else if(fieldTitle.equals("subTot")) {
								if (i > 0) {
									CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
											, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
											, "f4f4f4"
											, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
											, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
									cell.setCellStyle(titleStyle);
								}
								else {
									CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
											, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
											, fieldStyleList.get(i).get("styleColor").toString()
											, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
											, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
									cell.setCellStyle(titleStyle);
								}
								
							}
							else {
								CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
										, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
										, fieldStyleList.get(i).get("styleColor").toString()
										, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
										, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
								cell.setCellStyle(titleStyle);
							}
							
							//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
							//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
							sheet.setColumnWidth(i, Integer.parseInt(fieldInfoList.get(i).get("cellWidth").toString()) );
							if (nCnt > 0) {
								if (fieldInfoList.get(i).get("field1").equals("extra1_company")
										&& !arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field1")).toString())) {
									if (arrStartCnt[0] != 0 && arrEndCnt[0] != 0 && arrEndCnt[0] > arrStartCnt[0]) {
										sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[0], arrEndCnt[0], 0, 0));
									}

									arrStartCnt[0] = nRow - 1;
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field1")).toString();
								} else if (fieldInfoList.get(i).get("field1").equals("extra1_company")
										&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field1")).toString())) {
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field1")).toString();
								}
							}
							else{
								if (fieldInfoList.get(i).get("field1").equals("extra1_company")) {
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field1")).toString();
								}
							}
						}
						nCnt++;
						
					}
					
					for(int nMer = 0; nMer < 1;nMer++) {
						arrEndCnt[nMer] = nRow - 1;
						if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
							sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
						}
					}
					
				}
				
				nRow = Integer.parseInt(titleStyleMap.get("lastRow1").toString());
				
			}
			
			row = sheet.createRow(nRow);
			for (int i = 0; i < fieldInfoList.size(); i++) {
				cell = row.createCell(i);
				if (i < 3) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(nMon + "월 수입 부대비용 합계");
				}
				else {
					double d = Double.parseDouble(Long.toString(nTotla));
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(d);
				}
				
				CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
						, "subtitle", fieldStyleList.get(i).get("fontColor").toString()
						, "FEFFE8"
						, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
						, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
				cell.setCellStyle(titleStyle);
			}				
			sheet.addMergedRegion(new CellRangeAddress(nRow, nRow, 0, 2));
			sheet.addMergedRegion(new CellRangeAddress(nRow, nRow, 3, 5));
			row.setHeight((short)500);
			
			
			
			//sheet.createFreezePane(0, 4, 8, 4);
			//sheet.setAutoFilter(new CellRangeAddress(3, 3, 0, 8));
			return workbook;
		}
		
		/**
		 * Excel File을 생성하여 Workbook을 반환.
		 *
		 * @param sheetTitle Sheet 제목
		 * @param excelCellInfos Cell의 생성 정보
		 * @param rows 실제 Data
		 * @return XSSFWorkbook
		 * @throws NoSuchMethodException 
		 * @throws InvocationTargetException 
		 * @throws IllegalAccessException 
		 * @throws NoSuchFieldException 
		 */
		@SuppressWarnings("unchecked")
		public SXSSFWorkbook buildExcelMergeXSS1(String sheetTitle, Map<String, Object> excelInfpMap
				, List<BoardMasterVO> result) 
						throws Exception {
				
				SXSSFWorkbook workbook = new SXSSFWorkbook();
				
				ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

				Sheet sheet = workbook.createSheet(sheetTitle);

				sheet.setDisplayGridlines(false);
				
				Row row = null;
				Cell cell = null;
				
				//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
				Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
				Map<String, Object> unitMap = (Map<String, Object>) excelInfpMap.get("unitMap");
				Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
				List<Map<String, Object>> titleCellList = (List<Map<String, Object>>) excelInfpMap.get("titleCellList");
				List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");
				List<Map<String, Object>> fieldStyleList = (List<Map<String, Object>>) excelInfpMap.get("fieldStyleList");
						
				int nRow = 1; 

				if (headerMap != null){
					for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
						row = sheet.createRow(i);
						for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
							cell = row.createCell(j);
							CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
									, headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
									, headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
									, headerMap.get("line").toString(),"");
							cell.setCellStyle(titleStyle);
						}
					
						int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
						int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
						int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
						int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());
						
						row = sheet.getRow(nRow1);

						cell = row.getCell(nCol1);

						cell.setCellValue(headerMap.get("title").toString());
						
						if ((nRow1 != nRow2) || (nCol1 != nCol2)){
							sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
						}
						//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
						//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
						row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
						row.setHeight((short)600);
					}
					nRow = Integer.parseInt(headerMap.get("eRow").toString());
					
				}
				
				if (unitMap != null){
					for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
						row = sheet.createRow(i);
						for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
							cell = row.createCell(j);
							CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
									, unitMap.get("fontType").toString(), unitMap.get("fontColor").toString(), unitMap.get("styleColor").toString()
									, unitMap.get("textAlign").toString(), unitMap.get("textVAlign").toString()
									, unitMap.get("line").toString(),"");
							cell.setCellStyle(titleStyle);
						}
					
						int nRow1 = Integer.parseInt(unitMap.get("sRow").toString());
						int nRow2 = Integer.parseInt(unitMap.get("eRow").toString());
						int nCol1 = Integer.parseInt(unitMap.get("sCol").toString());
						int nCol2 = Integer.parseInt(unitMap.get("eCol").toString());
						
						row = sheet.getRow(nRow1);

						cell = row.getCell(nCol1);

						cell.setCellValue(unitMap.get("title").toString());
						
						if ((nRow1 != nRow2) || (nCol1 != nCol2)){
							sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
						}
						//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
						//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
						row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
						row.setHeight((short)300);
					}
					nRow = Integer.parseInt(unitMap.get("eRow").toString());
					
				}
				
				
				if (titleStyleMap != null){
					for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
						row = sheet.createRow(i);
						for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
							cell = row.createCell(j);
							CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
									, titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
									, titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
									, titleStyleMap.get("line").toString(),"");
							cell.setCellStyle(titleStyle);
						}
					}
					
					if (titleCellList != null){
						for(int n = 0; n < titleCellList.size(); n++) {
							int nRow1 = Integer.parseInt(titleCellList.get(n).get("sRow").toString());
							int nRow2 = Integer.parseInt(titleCellList.get(n).get("eRow").toString());
							int nCol1 = Integer.parseInt(titleCellList.get(n).get("sCol").toString());
							int nCol2 = Integer.parseInt(titleCellList.get(n).get("eCol").toString());
							
							row = sheet.getRow(nRow1);
		
							cell = row.getCell(nCol1);
		
							cell.setCellValue(titleCellList.get(n).get("cellTitle").toString());
							
							if ((nRow1 != nRow2) || (nCol1 != nCol2)){
								sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
							}
							//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
							//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
							row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
						}
					}
					
					nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());
					
				}
				
				if (titleStyleMap != null){
					
					nRow = Integer.parseInt(titleStyleMap.get("firstRow").toString());
					
					String[] tempField = new String[fieldInfoList.size()];
					for (int n = 0; n < fieldInfoList.size(); n++) {
						tempField[n] = fieldInfoList.get(n).get("field").toString();
					}	
					
					if (result != null){
						int[] arrStartCnt = new int[5];
						int[] arrEndCnt = new int[5];
						String[][] arrField = new String[5][result.size()];
						Arrays.fill(arrStartCnt, nRow);
						Arrays.fill(arrEndCnt, 0);
						for (String[] arrRow: arrField)
							Arrays.fill(arrRow, "");

						int nCnt = 0;
						
						for (BoardMasterVO item : result) {
							Map<String, Object> testMap = new HashMap<String, Object>();
							try {
								testMap = domainToMapWithExcept(item, tempField);
							} catch (Exception e) {
								
								// TODO Auto-generated catch block
								e.printStackTrace();
								
							}
							//row = sheet.getRow(nRow++);
							row = sheet.createRow(nRow++);
							String fieldTitle = "";
							for (int i = 0; i < fieldInfoList.size(); i++) {
								
								//cell = row.getCell(i);
								cell = row.createCell(i);
								
								if (nCnt > 0) {
									
									if (fieldInfoList.get(i).get("field").equals("extra_detail")
											&& arrField[0][nCnt-1].equals(testMap.get("extra_company").toString())
											&& arrField[1][nCnt-1].equals(testMap.get("extra_custom").toString())
											&& arrField[2][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
										arrEndCnt[2] = nRow - 1;
									} else if (fieldInfoList.get(i).get("field").equals("extra_company")
											&& arrField[0][nCnt-1].equals(testMap.get("extra_custom").toString())
											&& arrField[1][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
										arrEndCnt[1] = nRow - 1;
									}
									else if (fieldInfoList.get(i).get("field").equals("extra_custom")
											&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
										arrEndCnt[0] = nRow - 1;
									}
								}
								
								
								if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
									if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
										double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
										cell.setCellType(Cell.CELL_TYPE_NUMERIC);
										cell.setCellValue(d);
									} else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
										double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
										cell.setCellType(Cell.CELL_TYPE_NUMERIC);
										cell.setCellValue(d);
									} else {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
									}

								} else {
									cell.setCellValue(" ");
								}
								
								if (i == 0 && testMap.get(tempField[i]).equals("총계")) {
									sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 0, 2));
									fieldTitle = "allTot";
								}
								else if (i == 1 && !testMap.get(tempField[0]).equals("합계") && testMap.get(tempField[i]).equals("합계")) {
									sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 1, 2));
									fieldTitle = "subTot";
								}
								else if (i == 2 && !testMap.get(tempField[0]).equals("합계") && testMap.get(tempField[i]).equals("소계")) {
									fieldTitle = "itemTot";
								}
								
								if(fieldTitle.equals("allTot")) {
									CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
											, "subtitle", fieldStyleList.get(i).get("fontColor").toString()
											, "c4c4c4"
											, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
											, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
									cell.setCellStyle(titleStyle);
									
								}
								else if(fieldTitle.equals("subTot")) {
									if (i > 0) {
										CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
												, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
												, "f4f4f4"
												, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
												, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
										cell.setCellStyle(titleStyle);
									}
									else {
										CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
												, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
												, fieldStyleList.get(i).get("styleColor").toString()
												, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
												, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
										cell.setCellStyle(titleStyle);
									}
									
								}
								else if(fieldTitle.equals("itemTot")) {
									if (i > 0) {
										CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
												, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
												, "F8FBEF"
												, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
												, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
										cell.setCellStyle(titleStyle);
									}
									else {
										CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
												, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
												, fieldStyleList.get(i).get("styleColor").toString()
												, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
												, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
										cell.setCellStyle(titleStyle);
									}
									
								}
								else {
									CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
											, fieldStyleList.get(i).get("fontType").toString(), fieldStyleList.get(i).get("fontColor").toString()
											, fieldStyleList.get(i).get("styleColor").toString()
											, fieldStyleList.get(i).get("textAlign").toString(), fieldStyleList.get(i).get("textVAlign").toString()
											, fieldStyleList.get(i).get("line").toString(), fieldStyleList.get(i).get("fomule").toString());
									cell.setCellStyle(titleStyle);
								}
								
								//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
								sheet.setColumnWidth(i, Integer.parseInt(fieldInfoList.get(i).get("cellWidth").toString()) );
								if (nCnt > 0) {
									if (fieldInfoList.get(i).get("field").equals("extra_detail")
											&& (!arrField[0][nCnt].equals(testMap.get("extra_company").toString())
											|| !arrField[1][nCnt-1].equals(testMap.get("extra_custom").toString())
											|| !arrField[2][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString()))) {
										if (arrStartCnt[2] != 0 && arrEndCnt[2] != 0 && arrEndCnt[2] > arrStartCnt[2]) {
											sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[2], arrEndCnt[2], 2, 2));
										}

										arrStartCnt[2] = nRow - 1;
										arrField[2][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									} else if (fieldInfoList.get(i).get("field").equals("extra_detail")
											&& arrField[0][nCnt-1].equals(testMap.get("extra_company").toString())
											&& arrField[1][nCnt-1].equals(testMap.get("extra_custom").toString())
											&& arrField[2][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
										arrField[2][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									}
									if (fieldInfoList.get(i).get("field").equals("extra_company")
											&& (!arrField[0][nCnt-1].equals(testMap.get("extra_custom").toString())
											|| !arrField[1][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString()))) {
										if (arrStartCnt[1] != 0 && arrEndCnt[1] != 0 && arrEndCnt[1] > arrStartCnt[1]) {
											sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[1], arrEndCnt[1], 1, 1));
										}

										arrStartCnt[1] = nRow - 1;
										arrField[1][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									} else if (fieldInfoList.get(i).get("field").equals("extra_company")
											&& arrField[0][nCnt-1].equals(testMap.get("extra_custom").toString())
											&& arrField[1][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
										arrField[1][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									}
									
									if (fieldInfoList.get(i).get("field").equals("extra_custom")
											&& !arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
										if (arrStartCnt[0] != 0 && arrEndCnt[0] != 0 && arrEndCnt[0] > arrStartCnt[0]) {
											sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[0], arrEndCnt[0], 0, 0));
										}

										arrStartCnt[0] = nRow - 1;
										arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									} else if (fieldInfoList.get(i).get("field").equals("extra_custom")
											&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
										arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									}
								}
								else{
									if (fieldInfoList.get(i).get("field").equals("extra_detail")) {
										arrField[2][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									}
									if (fieldInfoList.get(i).get("field").equals("extra_company")) {
										arrField[1][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									}
									if (fieldInfoList.get(i).get("field").equals("extra_custom")) {
										arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									}
								}
							}
							nCnt++;
							
						}
						
						for(int nMer = 0; nMer < 3;nMer++) {
							arrEndCnt[nMer] = nRow - 1;
							if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
								sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
							}
						}
						
					}
					
					nRow = Integer.parseInt(titleStyleMap.get("lastRow").toString());
					
				}
				

				return workbook;
			}
		
		
		/**
		 * Excel File을 생성하여 Workbook을 반환.
		 *
		 * @param sheetTitle Sheet 제목
		 * @param excelCellInfos Cell의 생성 정보
		 * @param rows 실제 Data
		 * @return XSSFWorkbook
		 * @throws NoSuchMethodException 
		 * @throws InvocationTargetException 
		 * @throws IllegalAccessException 
		 * @throws NoSuchFieldException 
		 */
		@SuppressWarnings("unchecked")
		public SXSSFWorkbook buildExcelXSS(String sheetTitle, Map<String, Object> excelInfpMap
				, List<BoardMasterVO> result, boolean excelOption) 
						throws Exception {
			
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			
			ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

			Sheet sheet = workbook.createSheet(sheetTitle);

			sheet.setDisplayGridlines(false);
			
			Row row = null;
			Cell cell = null;
			
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
			Map<String, Object> unitMap = (Map<String, Object>) excelInfpMap.get("unitMap");
			List<Map<String, Object>> unitList = (List<Map<String, Object>>) excelInfpMap.get("unitList");
			Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
			Map<String, Object> mergeCellMap = (Map<String, Object>) excelInfpMap.get("mergeCellMap");			
			List<Map<String, Object>> titleCellList = (List<Map<String, Object>>) excelInfpMap.get("titleCellList");
			List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");
					
			int nRow = 1;
			
			if (headerMap != null){
				for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
								, headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
								, headerMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				
					int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
					int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
					int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
					int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(headerMap.get("title").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					row.setHeight((short)600);
				}
				nRow = Integer.parseInt(headerMap.get("eRow").toString());
				
			}
			
			
			
			if (unitList != null){
				for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, unitMap.get("fontType").toString(), unitMap.get("fontColor").toString(), unitMap.get("styleColor").toString()
								, unitMap.get("textAlign").toString(), unitMap.get("textVAlign").toString()
								, unitMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				}
				
				for(int n = 0; n < unitList.size(); n++) {
					int nRow1 = Integer.parseInt(unitList.get(n).get("sRow").toString());
					int nRow2 = Integer.parseInt(unitList.get(n).get("eRow").toString());
					int nCol1 = Integer.parseInt(unitList.get(n).get("sCol").toString());
					int nCol2 = Integer.parseInt(unitList.get(n).get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(unitList.get(n).get("title").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
				}
				
				nRow = Integer.parseInt(unitMap.get("eRow").toString());
				
			}
			else if (unitMap != null){
				for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, unitMap.get("fontType").toString(), unitMap.get("fontColor").toString(), unitMap.get("styleColor").toString()
								, unitMap.get("textAlign").toString(), unitMap.get("textVAlign").toString()
								, unitMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				
					int nRow1 = Integer.parseInt(unitMap.get("sRow").toString());
					int nRow2 = Integer.parseInt(unitMap.get("eRow").toString());
					int nCol1 = Integer.parseInt(unitMap.get("sCol").toString());
					int nCol2 = Integer.parseInt(unitMap.get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(unitMap.get("title").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					row.setHeight((short)300);
				}
				nRow = Integer.parseInt(unitMap.get("eRow").toString());
				
			}
			
			
			if (titleStyleMap != null){
				for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
								, titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
								, titleStyleMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				}
				
				if (titleCellList != null){
					for(int n = 0; n < titleCellList.size(); n++) {
						int nRow1 = Integer.parseInt(titleCellList.get(n).get("sRow").toString());
						int nRow2 = Integer.parseInt(titleCellList.get(n).get("eRow").toString());
						int nCol1 = Integer.parseInt(titleCellList.get(n).get("sCol").toString());
						int nCol2 = Integer.parseInt(titleCellList.get(n).get("eCol").toString());
						
						row = sheet.getRow(nRow1);
	
						cell = row.getCell(nCol1);
	
						cell.setCellValue(titleCellList.get(n).get("cellTitle").toString());
						
						if ((nRow1 != nRow2) || (nCol1 != nCol2)){
							sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
						}
						//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
						//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
						row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
						sheet.setColumnWidth(n, Integer.parseInt(titleCellList.get(n).get("cellWidth").toString()) );
					}
				}
				else {
					if (fieldInfoList != null){
						for(int n = 0; n < fieldInfoList.size(); n++) {
							int nRow1 = Integer.parseInt(titleStyleMap.get("sRow").toString());
							int nRow2 = Integer.parseInt(titleStyleMap.get("eRow").toString());
							int nCol1 = n;
							int nCol2 = n;
							
							row = sheet.getRow(nRow1);
		
							cell = row.getCell(nCol1);
		
							cell.setCellValue(fieldInfoList.get(n).get("cellTitle").toString());
							
							if ((nRow1 != nRow2) || (nCol1 != nCol2)){
								sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
							}
							//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
							//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
							row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
							sheet.setColumnWidth(n, Integer.parseInt(fieldInfoList.get(n).get("cellWidth").toString()) );
						}
					}
				}
				nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());
				
			}
			
			
			if (titleStyleMap != null){
				
				nRow = nRow + 1;
				
				String[] tempField = new String[fieldInfoList.size()];
				for (int n = 0; n < fieldInfoList.size(); n++) {
					tempField[n] = fieldInfoList.get(n).get("field").toString();
				}	
				
				if (result != null){
					
					int[] arrStartCnt = new int[5];
					int[] arrEndCnt = new int[5];
					String[][] arrField = new String[5][result.size()];
					Arrays.fill(arrStartCnt, nRow);
					Arrays.fill(arrEndCnt, 0);
					for (String[] arrRow: arrField)
						Arrays.fill(arrRow, "");

					int nCnt = 0;
					
					for (BoardMasterVO item : result) {
						Map<String, Object> testMap = new HashMap<String, Object>();
						try {
							testMap = domainToMapWithExcept(item, tempField);
						} catch (Exception e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						row = sheet.createRow(nRow++);
						String fieldTitle = "";

						for (int i = 0; i < fieldInfoList.size(); i++) {
							
							cell = row.createCell(i);
							
							if (nCnt > 0) {
								if (fieldInfoList.get(i).get("field").equals(tempField[0])
										&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									arrEndCnt[0] = nRow - 1;
								}
							}
							
							if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
								if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
								}

							} else {
								cell.setCellValue(" ");
							}
							
							if (i == 0 && testMap.get(tempField[i]).equals("총계")) {
								sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 0, 1));
								fieldTitle = "allTot";
							}
							else if (i == 1 && !testMap.get(tempField[0]).equals("합계") && testMap.get(tempField[i]).equals("합계")) {
								sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 1, 1));
								fieldTitle = "subTot";
							}
							
							if(fieldTitle.equals("allTot")) {
								CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
										, "subtitle", fieldInfoList.get(i).get("fontColor").toString()
										, "c4c4c4"
										, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
										, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
								cell.setCellStyle(titleStyle);
								
							}
							else if(fieldTitle.equals("subTot")) {
								if (i > 0) {
									CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
											, fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
											, "f4f4f4"
											, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
											, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
									cell.setCellStyle(titleStyle);
								}
								else {
									CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
											, fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
											, fieldInfoList.get(i).get("styleColor").toString()
											, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
											, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
									cell.setCellStyle(titleStyle);
								}
								
							}
							else {
								CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
										, fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
										, fieldInfoList.get(i).get("styleColor").toString()
										, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
										, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
								cell.setCellStyle(titleStyle);
							}
							
							
							
							if (nCnt > 0) {
								if (fieldInfoList.get(i).get("field").equals(tempField[0])
										&& !arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									if (arrStartCnt[0] != 0 && arrEndCnt[0] != 0 && arrEndCnt[0] > arrStartCnt[0]) {
										sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[0], arrEndCnt[0], 0, 0));
									}

									arrStartCnt[0] = nRow - 1;
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								} else if (fieldInfoList.get(i).get("field").equals(tempField[0])
										&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								}
							}
							else{
								if (fieldInfoList.get(i).get("field").equals(tempField[0])) {
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								}
							}
							
							
							//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
							//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
							
						}
						nCnt++;
						
						
					}
					
					for(int nMer = 0; nMer < 1;nMer++) {
						arrEndCnt[nMer] = nRow - 1;
						if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
							sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
						}
					}
					
				}
				
				
			}
			
			if (excelOption) {
				sheet.createFreezePane(0, Integer.parseInt(titleStyleMap.get("sRow").toString())+1, Integer.parseInt(titleStyleMap.get("eCol").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString())+1);
				sheet.setAutoFilter(new CellRangeAddress(Integer.parseInt(titleStyleMap.get("sRow").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString()), 0, Integer.parseInt(titleStyleMap.get("eCol").toString())));
			}
			return workbook;
		}
		
		
		/**
		 * Excel File을 생성하여 Workbook을 반환.
		 *
		 * @param sheetTitle Sheet 제목
		 * @param excelCellInfos Cell의 생성 정보
		 * @param rows 실제 Data
		 * @return XSSFWorkbook
		 * @throws NoSuchMethodException 
		 * @throws InvocationTargetException 
		 * @throws IllegalAccessException 
		 * @throws NoSuchFieldException 
		 */
		@SuppressWarnings("unchecked")
		public SXSSFWorkbook buildExcelSubXSS(String sheetTitle, Map<String, Object> excelInfpMap
				, List<BoardMasterVO> result) 
						throws Exception {
			
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			
			ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

			Sheet sheet = workbook.createSheet(sheetTitle);

			sheet.setDisplayGridlines(false);
			
			Row row = null;
			Cell cell = null;
			
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
			Map<String, Object> unitMap = (Map<String, Object>) excelInfpMap.get("unitMap");
			Map<String, Object> mergeMap = (Map<String, Object>) excelInfpMap.get("mergeMap");
			Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
			List<Map<String, Object>> titleCellList = (List<Map<String, Object>>) excelInfpMap.get("titleCellList");
			List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");
					
			int nRow = 1; 

			if (headerMap != null){
				for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
								, headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
								, headerMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				
					int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
					int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
					int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
					int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(headerMap.get("title").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					row.setHeight((short)600);
				}
				nRow = Integer.parseInt(headerMap.get("eRow").toString());
				
			}
			
			if (unitMap != null){
				for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, unitMap.get("fontType").toString(), unitMap.get("fontColor").toString(), unitMap.get("styleColor").toString()
								, unitMap.get("textAlign").toString(), unitMap.get("textVAlign").toString()
								, unitMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				
					int nRow1 = Integer.parseInt(unitMap.get("sRow").toString());
					int nRow2 = Integer.parseInt(unitMap.get("eRow").toString());
					int nCol1 = Integer.parseInt(unitMap.get("sCol").toString());
					int nCol2 = Integer.parseInt(unitMap.get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(unitMap.get("title").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					row.setHeight((short)300);
				}
				nRow = Integer.parseInt(unitMap.get("eRow").toString());
				
			}
			
			
			if (titleStyleMap != null){
				for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
								, titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
								, titleStyleMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				}
				
				if (titleCellList != null){
					for(int n = 0; n < titleCellList.size(); n++) {
						int nRow1 = Integer.parseInt(titleCellList.get(n).get("sRow").toString());
						int nRow2 = Integer.parseInt(titleCellList.get(n).get("eRow").toString());
						int nCol1 = Integer.parseInt(titleCellList.get(n).get("sCol").toString());
						int nCol2 = Integer.parseInt(titleCellList.get(n).get("eCol").toString());
						
						row = sheet.getRow(nRow1);
	
						cell = row.getCell(nCol1);
	
						cell.setCellValue(titleCellList.get(n).get("cellTitle").toString());
						
						if ((nRow1 != nRow2) || (nCol1 != nCol2)){
							sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
						}
						//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
						//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
						row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					}
				}
				else {
				
					if (fieldInfoList != null){
						for(int n = 0; n < fieldInfoList.size(); n++) {
							int nRow1 = Integer.parseInt(titleStyleMap.get("sRow").toString());
							int nRow2 = Integer.parseInt(titleStyleMap.get("eRow").toString());
							int nCol1 = n;
							int nCol2 = n;
							
							row = sheet.getRow(nRow1);
		
							cell = row.getCell(nCol1);
		
							cell.setCellValue(fieldInfoList.get(n).get("cellTitle").toString());
							
							if ((nRow1 != nRow2) || (nCol1 != nCol2)){
								sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
							}
							//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
							//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
							row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
						}
					}
				}
				
				nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());
				
			}
			
			if (titleStyleMap != null){
				
				nRow = nRow + 1;
				
				String[] tempField = new String[fieldInfoList.size()+1];
				for (int n = 0; n < fieldInfoList.size(); n++) {
					tempField[n] = fieldInfoList.get(n).get("field").toString();
				}
				tempField[fieldInfoList.size()] = "sapcolor";
				
				if (result != null){
					
					int[] arrStartCnt = new int[5];
					int[] arrEndCnt = new int[5];
					String[][] arrField = new String[5][result.size()];
					Arrays.fill(arrStartCnt, nRow);
					Arrays.fill(arrEndCnt, 0);
					for (String[] arrRow: arrField)
						Arrays.fill(arrRow, "");

					int nCnt = 0;
					
					for (BoardMasterVO item : result) {
						Map<String, Object> testMap = new HashMap<String, Object>();
						try {
							testMap = domainToMapWithExcept(item, tempField);
						} catch (Exception e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						row = sheet.createRow(nRow++);

						for (int i = 0; i < fieldInfoList.size(); i++) {
							
							cell = row.createCell(i);
							
							if (nCnt > 0) {
								if (fieldInfoList.get(i).get("field").equals(tempField[0])
										&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									arrEndCnt[0] = nRow - 1;
								}
							}
							
							if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
								if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
								}

							} else {
								cell.setCellValue(" ");
							}
							
							CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
									, fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
									, testMap.get("sapcolor").toString()
									, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
									, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
							cell.setCellStyle(titleStyle);
							
							if (nCnt > 0) {
								if (fieldInfoList.get(i).get("field").equals(tempField[0])
										&& !arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									if (arrStartCnt[0] != 0 && arrEndCnt[0] != 0 && arrEndCnt[0] > arrStartCnt[0]) {
										sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[0], arrEndCnt[0], 0, 0));
									}

									arrStartCnt[0] = nRow - 1;
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								} else if (fieldInfoList.get(i).get("field").equals(tempField[0])
										&& arrField[0][nCnt-1].equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								}
							}
							else{
								if (fieldInfoList.get(i).get("field").equals(tempField[0])) {
									arrField[0][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
								}
							}
							
							//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
							sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
							
						}
						nCnt++;
						
					}
					for(int nMer = 0; nMer < 1;nMer++) {
						arrEndCnt[nMer] = nRow - 1;
						if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
							sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
						}
					}
					
				}
				
			}

			return workbook;
		}
		
		
		
		/**
		 * 특정 변수를 제외해서 vo를 map형식으로 변환해서 반환.
		 * @param vo VO
		 * @param arrExceptList 제외할 property 명 리스트
		 * @return
		 * @throws Exception
		 */
		public static Map<String, Object> domainToMapWithExcept(Object vo, String[] arrAccepttList) throws Exception {
		    Map<String, Object> result = new HashMap<String, Object>();
		    BeanInfo info = Introspector.getBeanInfo(vo.getClass());
		    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
		        Method reader = pd.getReadMethod();
		        if (reader != null) {
		            if(arrAccepttList != null && arrAccepttList.length > 0 && isContain(arrAccepttList, pd.getName()))
		            	result.put(pd.getName(), reader.invoke(vo));
		            else
		            	continue;
		            
		        }
		    }
		    return result;
		}
		public static Boolean isContain(String[] arrList, String name) {
		    for (String arr : arrList) {
		        if (StringUtils.contains(arr, name))
		            return true;
		    }
		    return false;
		}

		@Override
		public int insertRpaIncome(BoardMasterVO boardMasterVO) throws Exception {
			int process = 0;
			try{
				process += attrbMngDAO.deleteRpaIncome(boardMasterVO);
				process += attrbMngDAO.insertRpaIncome(boardMasterVO);
	        
	    	}
			catch(Exception e){
				LOGGER.error(e.toString());
				throw e;
		 	} 
			return process;
			
		}


		/**
		 * Excel File을 생성하여 Workbook을 반환.
		 *
		 * @param sheetTitle Sheet 제목
		 * @param excelCellInfos Cell의 생성 정보
		 * @param rows 실제 Data
		 * @return XSSFWorkbook
		 * @throws NoSuchMethodException 
		 * @throws InvocationTargetException 
		 * @throws IllegalAccessException 
		 * @throws NoSuchFieldException 
		 */
		@SuppressWarnings("unchecked")
		public SXSSFWorkbook buildExcelDownXSS(String sheetTitle, Map<String, Object> excelInfpMap,
				List<BoardMasterVO> result) throws Exception {
			
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			
			ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

			Sheet sheet = workbook.createSheet(sheetTitle);

			sheet.setDisplayGridlines(false);
			
			Row row = null;
			Cell cell = null;
			
			//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
			Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
			Map<String, Object> unitMap = (Map<String, Object>) excelInfpMap.get("unitMap");
			Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
			List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");
					
			int nRow = 1; 

			if (headerMap != null){
				for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
								, headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
								, headerMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				
					int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
					int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
					int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
					int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(headerMap.get("title").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					row.setHeight((short)600);
				}
				nRow = Integer.parseInt(headerMap.get("eRow").toString());
				
			}
			
			if (unitMap != null){
				for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, unitMap.get("fontType").toString(), unitMap.get("fontColor").toString(), unitMap.get("styleColor").toString()
								, unitMap.get("textAlign").toString(), unitMap.get("textVAlign").toString()
								, unitMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				
					int nRow1 = Integer.parseInt(unitMap.get("sRow").toString());
					int nRow2 = Integer.parseInt(unitMap.get("eRow").toString());
					int nCol1 = Integer.parseInt(unitMap.get("sCol").toString());
					int nCol2 = Integer.parseInt(unitMap.get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(unitMap.get("title").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					row.setHeight((short)300);
				}
				nRow = Integer.parseInt(unitMap.get("eRow").toString());
				
			}
			
			
			if (titleStyleMap != null){
				for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
					row = sheet.createRow(i);
					for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
						cell = row.createCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
								, titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
								, titleStyleMap.get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				}
				
				if (fieldInfoList != null){
					for(int n = 0; n < fieldInfoList.size(); n++) {
						int nRow1 = Integer.parseInt(titleStyleMap.get("sRow").toString());
						int nRow2 = Integer.parseInt(titleStyleMap.get("eRow").toString());
						int nCol1 = n;
						int nCol2 = n;
						
						row = sheet.getRow(nRow1);
	
						cell = row.getCell(nCol1);
	
						cell.setCellValue(fieldInfoList.get(n).get("cellTitle").toString());
						
						if ((nRow1 != nRow2) || (nCol1 != nCol2)){
							sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
						}
						//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
						//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
						row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					}
				}
				
				nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());
				
			}
			
			if (titleStyleMap != null){
				
				nRow = nRow + 1;
				
				String[] tempField = new String[fieldInfoList.size()+1];
				for (int n = 0; n < fieldInfoList.size(); n++) {
					tempField[n] = fieldInfoList.get(n).get("field").toString();
				}
				tempField[fieldInfoList.size()] = "sapcolor";
				
				if (result != null){
					
					for (BoardMasterVO item : result) {
						Map<String, Object> testMap = new HashMap<String, Object>();
						try {
							testMap = domainToMapWithExcept(item, tempField);
						} catch (Exception e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						row = sheet.createRow(nRow++);

						for (int i = 0; i < fieldInfoList.size(); i++) {
							
							cell = row.createCell(i);
							
							
							if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
								if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
									double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(d);
								} else {
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
								}

							} else {
								cell.setCellValue(" ");
							}
							
							CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
									, fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
									, testMap.get("sapcolor").toString()
									, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
									, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
							cell.setCellStyle(titleStyle);
							
							
							sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
							sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
							
						}
						
					}
					
				}
				
			}

			return workbook;
		}
		

		@SuppressWarnings("unchecked")
		@Override
	    public int insertRpaExcel(MultipartHttpServletRequest multiRequest,
                HttpServletRequest request) throws Exception {
			
	        int returnValue = 0;
	        try {
	        	String storePath = request.getParameter("savePath");
				String regYearMon = request.getParameter("regYearMon");
				
				BoardSapFileVO result = null;
				 
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				result = fileUtil.parseFileSapInf(files, regYearMon, 0, regYearMon, storePath,"extra");
				
				String storePathString = result.getFileStreCours();

					
	            String originalFileName = result.getStreFileNm();
	            String realName = new File(originalFileName).getName();
	            File excelFile = new File(storePathString + originalFileName);

	            InputStream is = new FileInputStream(excelFile);
	            byte[] byteFile = FileUtils.readFileToByteArray(excelFile);
	            
	            Workbook wb = ExcelFileType.getWorkbook(originalFileName, byteFile);
	            
	            // 엑셀 파일에서 첫번째 시트를 가지고 온다.
	            
	            Sheet sheet = wb.getSheetAt(0);

	            //System.out.println("Sheet 이름: "+ wb.getSheetName(0));
	            //System.out.println("데이터가 있는 Sheet의 수 :" + wb.getNumberOfSheets());
	            
	            //sheet에서 유효한(데이터가 있는) 행의 개수를 가져온다.
	            
	            String sheetName = wb.getSheetName(0);

	            ExcelReadOption excelReadOption = new ExcelReadOption();
	            excelReadOption.setFilePath(originalFileName);
	            excelReadOption.setFileByte(byteFile);
	            excelReadOption.setSheetName(sheetName);
	            excelReadOption.setSheetNum(0);
	            excelReadOption.setStartRow(2);

	            Map<String, String> map = new HashMap<String, String>(); // 어떠한 자료형이 와도
	            map.put("번호", "NUM");
	            map.put("전표번호", "ZFACDO");
	            map.put("통관요청/입고요청 관리번호", "ZFIMDNO");
	            map.put("수입신고번호", "ZFIDRNO");
	            map.put("신고지 세", "ZFINRC");
	            map.put("이름 1", "NAME1");
	            map.put("공급업체", "LIFNR");
	            map.put("사업자등록번호", "STCD2");
	            map.put("House B/L No", "ZFBLNO");
	            map.put("신고자상호", "ZFAPNM");
	            map.put("총관세", "TWRBTR");
	            map.put("총부가세", "WRBTR_TAX");
	            map.put("원화통화1", "WAERS1");
	            map.put("과세표준", "SWRBTR");
	            map.put("원화통화2", "WAERS2");
	            map.put("신고수리일", "BUDAT");
	            map.put("자재구분", "ZFCSTGRP");
	            map.put("선기명", "ZFCARNM");
	            map.put("전기일", "BLDAT");

	            excelReadOption.setOutputHeadColumns(map);

	            Map<String, String> headMap = ExcelRead.headRead(excelReadOption);

	            List<Map<String, Object>> excelContent = new ArrayList<Map<String, Object>>();
	            List<String> strKeyColumn = new ArrayList<String>();
	            Map<String, Object> strOutputColumn = new HashMap<String, Object>();

	            TreeMap<String,String> tm = new TreeMap<String,String>(headMap);
	            //Set<String> keyset = headMap.keySet();
	            Iterator<String> keyiterator = tm.keySet( ).iterator( ); //키값 오름차순 정렬
	            // Iterator<string> keyiterator = tm.descendingKeySet().iterator(); //키값 내림차순 정렬
	            String k; String v;
	            while(keyiterator.hasNext()) {
	                k = (String)keyiterator.next();
	                v = (String)tm.get(k);
	                strKeyColumn.add(k);
	                strOutputColumn.put(k,v);
	            }

	            //for (Map.Entry<String, String> entry : headMap.entrySet()) {
	            //    strKeyColumn.add(entry.getKey());
	            //    strOutputColumn.add(entry.getValue());
	            //}

	            excelReadOption.setOutputColumns(strKeyColumn);

	            excelReadOption.setOutputObjColumns(strOutputColumn);
	            excelContent = ExcelRead.staticRead(excelReadOption);
	            
	            HashMap<String, Object> arrParam = new HashMap<String, Object>();

	            if (excelContent == null) {
	                returnValue = -1;
	            } else {
	            	if (excelContent.size() > 0) {
	                	List<Map<String, Object>> excelContent1 = new ArrayList<Map<String, Object>>();
	                	int insertCnt = 0;

	                	for(int i=0, totalSize = excelContent.size(); i < totalSize; i++) {
	                    	Map<String, Object> paramMap = excelContent.get(i);
	                    	regYearMon = excelContent.get(i).get("BLDAT").toString();
	                    	regYearMon = regYearMon.replace(".", "");
	                    	regYearMon = regYearMon.substring(0, 6);
	                    			
	                    	excelContent1.add(paramMap);
	                    	if(insertCnt == 9 || totalSize==(i+1)) {
	                    		arrParam.put("yyyymm", regYearMon);
	    	                	arrParam.put("List", excelContent1);
	    	                    returnValue += attrbMngDAO.insertMultiStatement(arrParam);
	                    		excelContent1 = new ArrayList<Map<String, Object>>();
	                            insertCnt = 0;
	                        } else {
	                            insertCnt++;
	                        }
	                    }
	                } else {
	                    returnValue = -1;
	                }
	            }
	            
	        }
	         catch(Exception e){

	            throw e;
	        }

	        return returnValue;
	    }

		/**
	     * rpa메인 목록을 조회 한다.
	     *
	     * @see egovframework.let.cop.bbs.wjRpaManageService.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.let.cop.bbs.ExcelMasterVO.service.BoardMasterVO)
	     */
	    public Map<String, Object> selectStatement(BoardMasterVO searchVO) throws Exception {
	    	
	    	List<BoardMasterVO> result = attrbMngDAO.selectStatement(searchVO);
	    	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

			Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("resultList", result);
			//map.put("resultCnt", Integer.toString(cnt));
		
			return map;
	    }

		@Override
		public void confirmStatement(BoardMasterVO boardMasterVO) throws Exception {
			
			String  t3_confirm = boardMasterVO.gett3_confirm();
	    	
	    	if (t3_confirm.equals("Y")) {
	    		String  checkContType = boardMasterVO.getSearchType();
	    		
	    		String[] words = checkContType.split(",");
	    		List<String> keyList = new ArrayList<String>();
	            for (String wo : words ){
	            	keyList.add(wo);
	            }
	            
	            boardMasterVO.setConfirm_yn("N");
	            attrbMngDAO.updateStatementConfirm(boardMasterVO);
	            boardMasterVO.setKeyList(keyList);
	            boardMasterVO.setConfirm_yn("Y");
		    	attrbMngDAO.updateMultiStatementConfirm(boardMasterVO);
	    	}
	    	else if (t3_confirm.equals("D")) {
	    		String  checkContType = boardMasterVO.getSearchType();
	    		
	    		String[] words = checkContType.split(",");
	    		List<String> keyList = new ArrayList<String>();
	            for (String wo : words ){
	            	keyList.add(wo);
	            }
	            
	            boardMasterVO.setKeyList(keyList);
	            attrbMngDAO.deleteStatement(boardMasterVO);
	    	}
			
		}

		@Override
		public int insertExtraTax(BoardMasterVO vo) throws Exception {
			
			int process = 0;
			try{
				process += attrbMngDAO.insertExtraTax(vo);
	        
	    	}
			catch(Exception e){
				LOGGER.error(e.toString());
				throw e;
		 	} 
			return process;
			
		}

	
		public Map<String, Object> selectExtraTaxSum(BoardMasterVO searchVO) throws Exception {
	    	
	    	List<BoardMasterVO> result = attrbMngDAO.selectExtraTaxSum(searchVO);
	    	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

			Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("resultList", result);
			//map.put("resultCnt", Integer.toString(cnt));
		
			return map;
		}
		
		public Map<String, Object> selectTTSendList(BoardMasterVO searchVO) throws Exception {
	    	
	    	List<BoardMasterVO> result = attrbMngDAO.selectTTSendList(searchVO);
	    	//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

			Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("resultList", result);
			//map.put("resultCnt", Integer.toString(cnt));
		
			return map;
		}

		
		
		

}
