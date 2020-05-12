package egovframework.let.cop.bbs.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
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
public interface EgovBBSAttributeManageService {

	/**
	 * 등록된 게시판 속성정보를 삭제한다.
	 * @param ExcelMaster
	 * 
	 * @param boardMaster
	 * @exception Exception Exception
	 */
	public void deleteBBSMasterInf(BoardMaster boardMaster)
	  throws Exception;

	/**
	 * 신규 게시판 속성정보를 생성한다.
	 * @param ExcelMaster
	 * 
	 * @param boardMaster
	 * @exception Exception Exception
	 */
	public String insertBBSMastetInf(BoardMaster boardMaster)
	  throws Exception;

	/**
	 * 유효한 게시판 마스터 정보를 호출한다.
	 * @param searchVO
	 * @return
	 * 
	 * @param vo
	 * @exception Exception Exception
	 */
	public List<BoardMasterVO> selectAllBBSMasteInf(BoardMasterVO vo)
	  throws Exception;

	/**
	 * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록을 전체조회 한다.
	 * @return
	 * 
	 * @param vo
	 * @exception Exception Exception
	 */
	public List<BoardMasterVO> selectAllBdMstrByTrget(BoardMasterVO vo)
	  throws Exception;

	/**
	 * 게시판 속성정보 한 건을 상세조회한다.
	 * @param ExcelMasterVO
	 * 
	 * @param searchVO
	 * @exception Exception Exception
	 */
	public BoardMasterVO selectBBSMasterInf(BoardMaster searchVO)
	  throws Exception;

	//public Map<String, Object> selectInvoiceList(BoardMasterVO searchVO)
	public List<BoardMasterVO> selectInvoiceList(BoardMaster searchVO)
			throws Exception;
	
	//public Map<String, Object> selectInvoiceList(BoardMasterVO searchVO)
	public List<BoardMasterVO> selectCargoList(BoardMaster searchVO)
				throws Exception;
	
	public List<BoardMasterVO> selectBlList(BoardMaster searchVO)
			throws Exception;	
	
	public List<BoardMasterVO> selectPassList(BoardMaster searchVO)
			throws Exception;	
	
	public List<BoardMasterVO> selectDocList(BoardMaster searchVO)
			throws Exception;
	
	public List<BoardMasterVO> selectSapList(BoardMaster searchVO)
			throws Exception;
	
	public List<BoardMasterVO> selectBBSSAPBktxtInfs(BoardMaster searchVO)
			throws Exception;
	
	public List<BoardMasterVO> selectBBSSAPLifnrInfs(BoardMaster searchVO)
			throws Exception;
	
	public Map<String, Object> selectSapSubList(BoardMasterVO searchVO)
			throws Exception;
	
	public Map<String, Object> selectSapSubBLList(BoardMasterVO searchVO)
			throws Exception;


	public Map<String, Object> selectSapSubBLSPList(BoardMasterVO searchVO)
			throws Exception;
	
	public Map<String, Object> selectExtraSPList(BoardMaster searchVO)
			throws Exception;

	public Map<String, Object> selectExtraSPList1(BoardMaster searchVO)
			throws Exception;
	
	public Map<String, Object> selectYetArrivedList(BoardMaster searchVO)
			throws Exception;
	
	public List<BoardMasterVO> selectExtraSPFinishList(BoardMasterVO searchVO)
			throws Exception;
	
	public List<BoardMasterVO> selectExtraTax(BoardMasterVO searchVO)
			throws Exception;
	

	/**
	 * 게시판 속성 정보의 목록을 조회 한다.
	 * @param ExcelMasterVO
	 * 
	 * @param searchVO
	 * @exception Exception Exception
	 */
	public Map<String, Object> selectBBSMasterInfs(BoardMasterVO searchVO)
			throws Exception;
	
	

	/**
	 * 사용중인 게시판 속성 정보의 목록을 조회 한다.
	 * @param ExcelMasterVO
	 * 
	 * @param vo
	 * @exception Exception Exception
	 */
	public Map<String, Object> selectBdMstrListByTrget(BoardMasterVO vo)
	  throws Exception;

	/**
	 * 사용중이지 않은 게시판 속성 정보의 목록을 조회 한다.
	 * @return
	 * 
	 * @param vo
	 * @exception Exception Exception
	 */
	public Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO vo)
	  throws Exception;

	/**
	 * 게시판 속성정보를 수정한다.
	 * @param ExcelMaster
	 * 
	 * @param boardMaster
	 * @exception Exception Exception
	 */
	public void updateBBSMasterInf(BoardMaster boardMaster)
	  throws Exception;
	
	/**
	 * 게시판 속성정보를 수정한다.
	 * @param ExcelMaster
	 * 
	 * @param boardMaster
	 * @exception Exception Exception
	 */
	public int finishBBSMasterInf(BoardMasterVO vo)
	  throws Exception;

	/**
	 * 게시판 속성정보를 수정한다.
	 * @param ExcelMaster
	 * 
	 * @param boardMaster
	 * @exception Exception Exception
	 */
	public void insertBBSMasterSAPInf(BoardMaster boardMaster, BoardMasterVO boardMasterVO)
	  throws Exception;
	
	/**
	 * 게시판 속성정보를 수정한다.
	 * @param ExcelMaster
	 * 
	 * @param boardMaster
	 * @exception Exception Exception
	 */
	public int deleteBBSMasterSAPInf(BoardMaster boardMaster)
	  throws Exception;
	
	/**
	 * 템플릿의 유효여부를 점검한다.
	 * @param ExcelMasterVO
	 * 
	 * @param searchVO
	 * @exception Exception Exception
	 */
	public void validateTemplate(BoardMasterVO searchVO)
	  throws Exception;

	/**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvo
     * @throws Exception
     */
    public int insertFileSapInf(MultipartHttpServletRequest multiRequest,
            HttpServletRequest request) throws Exception;	
    

    public Map<String, Object> selectFileSap(BoardMasterVO boardMasterVO)
			throws Exception;
    
    public BoardSapFileVO selectFileSapDetail(BoardMaster searchVO)
			throws Exception;
    
    public Map<String, Object> deleteFileSap(BoardSapFileVO searchVO)
			throws Exception;
    
    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvo
     * @throws Exception
     */
    public int insertForward(BoardMasterVO boardMasterVO) throws Exception;	
    
    public void deleteForward(BoardMasterVO boardMasterVO)
    		  throws Exception;
    
    public SXSSFWorkbook buildExcelMergeXSS(String sheetTitle, Map<String, Object> excelInfpMap, List<BoardMasterVO> rows, List<BoardMasterVO> rows1, long nTotla,int nMon)
					throws Exception ;
    public SXSSFWorkbook buildExcelMergeXSS1(String sheetTitle, Map<String, Object> excelInfpMap, List<BoardMasterVO> rows)
			throws Exception ;
    
    public SXSSFWorkbook buildExcelXSS(String sheetTitle, Map<String, Object> excelInfpMap, List<BoardMasterVO> rows, boolean excelOption)
			throws Exception ;
    		
    public SXSSFWorkbook buildExcelSubXSS(String sheetTitle, Map<String, Object> excelInfpMap, List<BoardMasterVO> rows)
			throws Exception ;
    
    public SXSSFWorkbook buildExcelDownXSS(String sheetTitle, Map<String, Object> excelInfpMap, List<BoardMasterVO> rows)
			throws Exception ;   
    
    
    public int insertRpaIncome(BoardMasterVO boardMasterVO) throws Exception;
    
    
    /* *******************************************************************************************
     * 함수  제목 : 사용자 정보 수정
     * 작  성  자 : 안원해      작  성  일 : 2019-11-05
     * 내      용 : 전체 목록 및 갯수
     * 수  정  자 :             수  정  일 :
     * 수정  내용 :
     * ******************************************************************************************* */
    public int insertRpaExcel(MultipartHttpServletRequest multiRequest,
            HttpServletRequest request) throws Exception;
    
    public Map<String, Object> selectStatement(BoardMasterVO searchVO)
			throws Exception;  
    
    public void confirmStatement(BoardMasterVO boardMasterVO) throws Exception;
    
    public int insertExtraTax(BoardMasterVO vo) throws Exception ;
    
    public Map<String, Object> selectExtraTaxSum(BoardMasterVO searchVO)
			throws Exception;  
    
    public Map<String, Object> selectTTSendList(BoardMasterVO searchVO)
			throws Exception;  
    
}