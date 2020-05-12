
package egovframework.rpa.ttsend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rpa.ttsend.dao.RpaTTSendDAO;
import egovframework.rpa.ttsend.service.RpaTTSendService;
import egovframework.rpa.ttsend.vo.RpaTTSendVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("rpaTTSendService")
public class RpaTTSendServiceImpl extends EgovAbstractServiceImpl implements RpaTTSendService{

	private static final Logger LOGGER = LoggerFactory.getLogger(RpaTTSendServiceImpl.class);
	
    @Resource(name = "rpaTTSendDAO")
    private RpaTTSendDAO rpaTTSendDAO;
    
	public Map<String, Object> selectTTSendList(RpaTTSendVO searchVO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<RpaTTSendVO> result = rpaTTSendDAO.selectTTSendList(searchVO);
		
			map.put("resultList", result);
		}
		catch(Exception e){
			LOGGER.error(e.toString());
			throw e;
	 	} 
		return map;
		
	}

}

