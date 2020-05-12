package egovframework.let.cop.bbs.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.let.cop.bbs.service.BoardMaster;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.let.cop.bbs.service.BoardSapFileVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 게시판 속성정보 관리를 위한 데이터 접근 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  이삼섭          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Repository("BBSAttributeManageDAO")
public class BBSAttributeManageDAO extends EgovAbstractDAO {

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     *
     * @param ExcelMaster
     */
    public void deleteBBSMasterInf(BoardMaster boardMaster) throws Exception {
	update("BBSAttributeManageDAO.deleteBBSMasterInf", boardMaster);
	
    }

    /**
     * 신규 게시판 속성정보를 등록한다.
     *
     * @param ExcelMaster
     */
    public String insertBBSMasterInf(BoardMaster boardMaster) throws Exception {
	return (String)insert("BBSAttributeManageDAO.insertBBSMasterInf", boardMaster);
    }

    /**
     * 게시판 속성정보 한 건을 상세조회 한다.
     *
     * @param ExcelMasterVO
     */
    public BoardMasterVO selectBBSMasterInf(BoardMaster vo) throws Exception {
	return (BoardMasterVO)select("BBSAttributeManageDAO.selectBBSMasterInf", vo);
    }

    /**
     * 게시판 속성정보 목록을 조회한다.
     *
     * @param ExcelMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectBBSMasterInfs(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectBBSMasterInfs", vo);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectInvoiceList(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectInvoiceList", vo);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectCargoList(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectCargoList", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectBlList(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectBlList", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectPassList(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectPassList", vo);
    }

    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectDocList(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectDocList", vo);
    }

    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectSapList(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectSapList", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectSapSubBLList(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectSapSubBLList", vo);
    }

    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectBBSSAPBktxtInfs(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectBBSSAPBktxtInfs", vo);
    }

    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectBBSSAPLifnrInfs(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectBBSSAPLifnrInfs", vo);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectSapSubBLSPList(Map<String, Object> map) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectSapSubBLSPList", map);
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectExtraSPList(Map<String, Object> map) throws Exception {
    	System.out.println("dao");
	return (List<Map<String, Object>>) list("BBSAttributeManageDAO.selectExtraSPList", map);
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectExtraSPList1(Map<String, Object> map) throws Exception {
    	System.out.println("dao");
	return (List<Map<String, Object>>) list("BBSAttributeManageDAO.selectExtraSPList1", map);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectYetArrivedList(Map<String, Object> map) throws Exception {
    	System.out.println("dao");
	return (List<Map<String, Object>>) list("BBSAttributeManageDAO.selectYetArrivedList", map);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectExtraSPFinishList(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectExtraSPFinishList", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectExtraTax(BoardMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectExtraTax", vo);
    }
    
    
    
    
    
    /**
     * 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectBBSMasterInfsCnt(BoardMasterVO vo) throws Exception {
	return (Integer)select("BBSAttributeManageDAO.selectBBSMasterInfsCnt", vo);
    }

    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public void updateBBSMasterInf(BoardMaster boardMaster) throws Exception {
	update("BBSAttributeManageDAO.updateBBSMasterInf", boardMaster);
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public int finishBBSMasterInf(BoardMasterVO vo) throws Exception {
    	return (Integer)update("BBSAttributeManageDAO.finishBBSMasterInf", vo);
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public void insertBBSMasterSAPInf(BoardMaster boardMaster) throws Exception {
	update("BBSAttributeManageDAO.insertBBSMasterSAPInf", boardMaster);
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public int updateBBSMasterSAPInf(BoardMaster boardMaster) throws Exception {
    	return (Integer)update("BBSAttributeManageDAO.updateBBSMasterSAPInf", boardMaster);
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public int deleteBBSMasterSAPInf(BoardMaster boardMaster) throws Exception {
    	return (Integer)delete("BBSAttributeManageDAO.deleteBBSMasterSAPInf", boardMaster);
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     *
     * @param ExcelMasterVO
     */
    public boolean validateTemplate(BoardMasterVO vo) throws Exception {
	return true;
    }

    /**
     * 유효한 게시판 목록을 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectAllBBSMasteInf(BoardMasterVO vo) throws Exception {
	// 커뮤니티, 동호회의 게시판이 나오지 않도록 LETTNBBSUSE 테이블과 Join 필요
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectAllBBSMaster", vo);
    }

    /**
     * 사용중인 게시판 속성정보 목록을 조회한다.
     *
     * @param ExcelMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectBdMstrListByTrget(BoardMasterVO vo) throws Exception {
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectBdMstrListByTrget", vo);
    }

    /**
     * 사용중인 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectBdMstrListCntByTrget(BoardMasterVO vo) throws Exception {
	return (Integer)select("BBSAttributeManageDAO.selectBdMstrListCntByTrget", vo);
    }

    /**
     * 커뮤니티, 동호회등 게시판 사용등록이 된 게시판 목록 전체를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectAllBdMstrByTrget(BoardMasterVO vo) throws Exception {
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectAllBdMstrByTrget", vo);
    }

    /**
     * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
     *
     * @param ExcelMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectNotUsedBdMstrList(BoardMasterVO vo) throws Exception {
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectNotUsedBdMstrList", vo);
    }
    
    
    /**
     * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
	public int selectNotUsedBdMstrListCnt(BoardMasterVO vo) throws Exception {
	return (Integer)select("BBSAttributeManageDAO.selectNotUsedBdMstrListCnt", vo);
    }
	
	
	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param vo
	 * @throws Exception
	 */
	public int insertFileSapInf(Map<String, Object> map) throws Exception {
		int process = -1;
		process += (int)update("BBSAttributeManageDAO.insertFileSapMaster", map);
		process += (int)update("BBSAttributeManageDAO.insertFileSapDetailMulti", map);
		return process;
	}
	
	/**
     * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
	public String selectFileSapMax(Map<String, Object> map) throws Exception {
	return (String)select("BBSAttributeManageDAO.selectFileSapMax", map);
    }
	
	/**
     * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
	public int selectFileSapCnt(BoardSapFileVO vo) throws Exception {
	return (int)select("BBSAttributeManageDAO.selectFileSapCnt", vo);
    }

	
	/**
     * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
     *
     * @param ExcelMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<BoardSapFileVO> selectFileSap(BoardMasterVO boardMasterVO) throws Exception {
	return (List<BoardSapFileVO>) list("BBSAttributeManageDAO.selectFileSap", boardMasterVO);
    }
    
    /**
     * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
     *
     * @param ExcelMasterVO
     */
    public BoardSapFileVO selectFileSapDetail(BoardMaster boardMaster) throws Exception {
	return (BoardSapFileVO) select("BBSAttributeManageDAO.selectFileSapDetail", boardMaster);
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public int deleteFileSap(BoardSapFileVO vo) throws Exception {
    	return (Integer)delete("BBSAttributeManageDAO.deleteFileSap", vo);
    }
    
    /**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param vo
	 * @throws Exception
	 */
	public int insertForward(BoardMasterVO boardMasterVO) throws Exception {
		int process = (int)update("BBSAttributeManageDAO.insertForward", boardMasterVO);
		return process;
	}
	
	/**
     * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
	public int selectForwardCnt(BoardMasterVO vo) throws Exception {
	return (int)select("BBSAttributeManageDAO.selectForwardCnt", vo);
    }
	
	/**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public int deleteForward(BoardMasterVO vo) throws Exception {
    	return (Integer)delete("BBSAttributeManageDAO.deleteForward", vo);
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public int insertRpaIncome(BoardMasterVO vo) throws Exception {
    	return (Integer)update("BBSAttributeManageDAO.insertRpaIncome", vo);
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public int deleteRpaIncome(BoardMasterVO vo) throws Exception {
    	return (Integer)delete("BBSAttributeManageDAO.deleteRpaIncome", vo);
    }
    
    public int insertMultiStatement(HashMap<String, Object> vo) {

    	return (Integer)update("BBSAttributeManageDAO.insertMultiStatement", vo);

    }
    
    /**
     * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
     *
     * @param ExcelMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectStatement(BoardMasterVO vo) throws Exception {
	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectStatement", vo);
    }
    
    public int updateStatementConfirm(BoardMasterVO vo) throws Exception {
    	return (Integer)update("BBSAttributeManageDAO.updateStatementConfirm", vo);
    }
    
    public int updateMultiStatementConfirm(BoardMasterVO vo) throws Exception {
    	return (Integer)update("BBSAttributeManageDAO.updateMultiStatementConfirm", vo);
    }
    
    public int deleteStatement(BoardMasterVO vo) throws Exception {
    	return (Integer)delete("BBSAttributeManageDAO.deleteStatement", vo);
    }
    
    public int insertExtraTax(BoardMasterVO vo) throws Exception {

    	return (Integer)update("BBSAttributeManageDAO.insertExtraTax", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectExtraTaxSum(BoardMasterVO vo) throws Exception {
    	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectExtraTaxSum", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<BoardMasterVO> selectTTSendList(BoardMasterVO vo) throws Exception {
    	return (List<BoardMasterVO>) list("BBSAttributeManageDAO.selectTTSendList", vo);
    }
    
    
    
    
    
	
}
