package wj.rpa.excel.service.impl;
import java.util.List;



import wj.rpa.excel.service.ExcelMaster;
import wj.rpa.excel.service.ExcelMasterVO;

//import egovframework.let.cop.bbs.service.BoardMasterVO;
//import egovframework.let.cop.bbs.service.BoardMaster;


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
@Repository("wjRpaManageDAO")
public class wjRpaManageDAO extends EgovAbstractDAO {

    

    @SuppressWarnings("unchecked")
    public List<ExcelMasterVO> selectRpaMainList(ExcelMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<ExcelMasterVO>) list("wjRpaManageDAO.selectRpaMainList", vo);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<ExcelMasterVO> selectInvoiceList(ExcelMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<ExcelMasterVO>) list("wjRpaManageDAO.selectInvoiceList", vo);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<ExcelMasterVO> selectCargoList(ExcelMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<ExcelMasterVO>) list("wjRpaManageDAO.selectCargoList", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<ExcelMasterVO> selectBlList(ExcelMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<ExcelMasterVO>) list("wjRpaManageDAO.selectBlList", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<ExcelMasterVO> selectPassList(ExcelMasterVO vo) throws Exception {
    	System.out.println("dao");
	return (List<ExcelMasterVO>) list("wjRpaManageDAO.selectPassList", vo);
    }

    
}
