
package egovframework.rpa.yetarr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rpa.yetarr.vo.RpaYetarrVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("rpaYetarrDAO")
public class RpaYetarrDAO  extends EgovAbstractDAO {
	@SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectYetArrivedList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) list("RpaYetarrDAO.selectYetArrivedList", map);
    }
	
	public int insertExtraTax(RpaYetarrVO vo) throws Exception {

    	return (Integer)update("rpaYetarrDAO.insertExtraTax", vo);
    }

}

