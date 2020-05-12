
package egovframework.rpa.bill;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("rpaBillDAO")
public class RpaBillDAO extends EgovAbstractDAO {
	@SuppressWarnings("unchecked")
    public List<RpaBillVO> selectBillList(RpaBillVO vo) throws Exception {
    	return (List<RpaBillVO>) list("RpaBillDAO.selectBillList", vo);
    }
}

