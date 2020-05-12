
package egovframework.rpa.tax.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rpa.tax.vo.RpaTaxVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("rpaTaxDAO")
public class RpaTaxDAO  extends EgovAbstractDAO {
	@SuppressWarnings("unchecked")
    public List<RpaTaxVO> selectExtraTaxSum(RpaTaxVO vo) throws Exception {
    	return (List<RpaTaxVO>) list("RpaTaxDAO.selectExtraTaxSum", vo);
    }

}

