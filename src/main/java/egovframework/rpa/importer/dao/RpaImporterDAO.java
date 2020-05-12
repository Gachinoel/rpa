
package egovframework.rpa.importer.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rpa.importer.vo.RpaImporterVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("rpaImporterDAO")
public class RpaImporterDAO  extends EgovAbstractDAO {
	@SuppressWarnings("unchecked")
    public List<RpaImporterVO> selectStatement(RpaImporterVO vo) throws Exception {
	return (List<RpaImporterVO>) list("RpaImporterDAO.selectStatement", vo);
    }
    
    public int updateStatementConfirm(RpaImporterVO vo) throws Exception {
    	return (Integer)update("RpaImporterDAO.updateStatementConfirm", vo);
    }
    
    public int updateMultiStatementConfirm(RpaImporterVO vo) throws Exception {
    	return (Integer)update("RpaImporterDAO.updateMultiStatementConfirm", vo);
    }
    
    public int deleteStatement(RpaImporterVO vo) throws Exception {
    	return (Integer)delete("RpaImporterDAO.deleteStatement", vo);
    }
    
    /**
   	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
   	 *
   	 * @param vo
   	 * @throws Exception
   	 */
   	public int insertForward(RpaImporterVO boardMasterVO) throws Exception {
   		int process = (int)update("RpaImporterDAO.insertForward", boardMasterVO);
   		return process;
   	}
   	
   	/**
        * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
        *
        * @param vo
        * @return
        * @throws Exception
        */
   	public int selectForwardCnt(RpaImporterVO vo) throws Exception {
   		return (int)select("RpaImporterDAO.selectForwardCnt", vo);
	}
   	
   	/**
        * 게시판 속성정보를 수정한다.
        *
        * @param ExcelMaster
        */
   	public int deleteForward(RpaImporterVO vo) throws Exception {
   		return (Integer)delete("RpaImporterDAO.deleteForward", vo);
   	}
       
   /**
	* 게시판 속성정보를 수정한다.
	*
	* @param ExcelMaster
	*/
	public int insertRpaIncome(RpaImporterVO vo) throws Exception {
		return (Integer)update("RpaImporterDAO.insertRpaIncome", vo);
	}
	   
	   /**
	* 게시판 속성정보를 수정한다.
	*
	* @param ExcelMaster
	*/
	public int deleteRpaIncome(RpaImporterVO vo) throws Exception {
		return (Integer)delete("RpaImporterDAO.deleteRpaIncome", vo);
	}
   
	public int insertMultiStatement(HashMap<String, Object> vo) {

		return (Integer)update("RpaImporterDAO.insertMultiStatement", vo);
	}

       

}

