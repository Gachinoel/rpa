
package egovframework.rpa.yetarr.service;

import java.util.Map;

import egovframework.rpa.yetarr.vo.RpaYetarrVO;


public interface RpaYetarrService {
	public Map<String, Object> selectYetArrivedList(RpaYetarrVO searchVO)
			throws Exception;
}

