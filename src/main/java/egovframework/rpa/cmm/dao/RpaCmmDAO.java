
package egovframework.rpa.cmm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rpa.cmm.vo.RpaCmmVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("rpaCmmDAO")
public class RpaCmmDAO  extends EgovAbstractDAO {
	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param vo
	 * @throws Exception
	 */
	public int insertFileSapInf(Map<String, Object> map) throws Exception {
		int process = -1;
		process += (int)update("RpaCmmDAO.insertFileSapMaster", map);
		process += (int)update("RpaCmmDAO.insertFileSapDetailMulti", map);
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
		return (String)select("RpaCmmDAO.selectFileSapMax", map);
    }
	
	/**
     * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
	public int selectFileSapCnt(RpaCmmVO vo) throws Exception {
		return (int)select("RpaCmmDAO.selectFileSapCnt", vo);
    }

	
	/**
     * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
     *
     * @param ExcelMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<RpaCmmVO> selectFileSap(RpaCmmVO boardMasterVO) throws Exception {
    	return (List<RpaCmmVO>) list("RpaCmmDAO.selectFileSap", boardMasterVO);
    }
    
    /**
     * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
     *
     * @param ExcelMasterVO
     */
    public RpaCmmVO selectFileSapDetail(RpaCmmVO boardMaster) throws Exception {
    	return (RpaCmmVO) select("RpaCmmDAO.selectFileSapDetail", boardMaster);
    }
    
    /**
     * 게시판 속성정보를 수정한다.
     *
     * @param ExcelMaster
     */
    public int deleteFileSap(RpaCmmVO vo) throws Exception {
    	return (Integer)delete("RpaCmmDAO.deleteFileSap", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<RpaCmmVO> selectBLNoInfs(RpaCmmVO vo) throws Exception {
    	System.out.println("dao");
    	return (List<RpaCmmVO>) list("RpaCmmDAO.selectBLNoInfs", vo);
    }

    @SuppressWarnings("unchecked")
    public List<RpaCmmVO> selectCompanyInfs(RpaCmmVO vo) throws Exception {
    	System.out.println("dao");
    	return (List<RpaCmmVO>) list("RpaCmmDAO.selectCompanyInfs", vo);
    }
	

}

