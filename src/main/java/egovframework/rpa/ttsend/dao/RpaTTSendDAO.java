
package egovframework.rpa.ttsend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rpa.ttsend.vo.RpaTTSendVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("rpaTTSendDAO")
public class RpaTTSendDAO  extends EgovAbstractDAO {
	@SuppressWarnings("unchecked")
    public List<RpaTTSendVO> selectTTSendList(RpaTTSendVO vo) throws Exception {
    	return (List<RpaTTSendVO>) list("RpaTTSendDAO.selectTTSendList", vo);
    }

}

