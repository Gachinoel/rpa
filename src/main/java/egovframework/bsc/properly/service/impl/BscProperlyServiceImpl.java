
package egovframework.bsc.properly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.bsc.properly.dao.BscProperlyDAO;
import egovframework.bsc.properly.service.BscProperlyService;
import egovframework.bsc.properly.vo.BscProperlyVO;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.rpa.ttsend.service.impl.RpaTTSendServiceImpl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("bscProperlyService")
public class BscProperlyServiceImpl extends EgovAbstractServiceImpl implements BscProperlyService{

	private static final Logger LOGGER = LoggerFactory.getLogger(RpaTTSendServiceImpl.class);
	
    @Resource(name = "bscProperlyDAO")
    private BscProperlyDAO bscProperlyDAO;
    
	@Override
	public Map<String, Object> selectBscProperlyList(BscProperlyVO searchVO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> mapParma = new HashMap<String, Object>();
			mapParma.put("searchMonth", searchVO.getSearchMonth());
			mapParma.put("searchGubun", searchVO.getSearchGubun());
			mapParma.put("searchItem", searchVO.getSearchItem());
        	
			List<BscProperlyVO> result = bscProperlyDAO.selectProperyList(mapParma);
		
			map.put("resultList", result);
		}
		catch(Exception e){
			LOGGER.error(e.toString());
			throw e;
	 	} 
		return map;
		
	}

}

