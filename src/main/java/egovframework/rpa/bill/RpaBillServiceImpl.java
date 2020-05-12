
package egovframework.rpa.bill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("rpaBillService")
public class RpaBillServiceImpl extends EgovAbstractServiceImpl implements RpaBillService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RpaBillServiceImpl.class);
	
    @Resource(name = "rpaBillDAO")
    private RpaBillDAO rpaBillDAO;

	public Map<String, Object> selectBillList(RpaBillVO searchVO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
	    	List<RpaBillVO> result = rpaBillDAO.selectBillList(searchVO);
			map.put("resultList", result);
		}
		catch(Exception e){
			LOGGER.error(e.toString());
			throw e;
	 	} 
		return map;
	}
}

