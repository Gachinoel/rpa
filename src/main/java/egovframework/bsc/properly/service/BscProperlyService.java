
package egovframework.bsc.properly.service;

import java.util.Map;

import egovframework.bsc.properly.vo.BscProperlyVO;


public interface BscProperlyService {
	public Map<String, Object> selectBscProperlyList(BscProperlyVO searchVO)
			throws Exception;  

}

