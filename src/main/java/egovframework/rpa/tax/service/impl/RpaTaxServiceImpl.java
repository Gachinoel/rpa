
package egovframework.rpa.tax.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rpa.tax.dao.RpaTaxDAO;
import egovframework.rpa.tax.service.RpaTaxService;
import egovframework.rpa.tax.vo.RpaTaxVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("rpaTaxService")
public class RpaTaxServiceImpl extends EgovAbstractServiceImpl implements RpaTaxService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RpaTaxServiceImpl.class);
	
    @Resource(name = "rpaTaxDAO")
    private RpaTaxDAO rpaTaxDAO;

	public Map<String, Object> selectExtraTaxSum(RpaTaxVO searchVO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
	    	List<RpaTaxVO> result = rpaTaxDAO.selectExtraTaxSum(searchVO);
			map.put("resultList", result);
		}
		catch(Exception e){
			LOGGER.error(e.toString());
			throw e;
	 	} 
		return map;
	}

}

