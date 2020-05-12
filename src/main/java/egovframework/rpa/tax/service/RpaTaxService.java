
package egovframework.rpa.tax.service;

import java.util.Map;

import egovframework.rpa.tax.vo.RpaTaxVO;

public interface RpaTaxService {
	public Map<String, Object> selectExtraTaxSum(RpaTaxVO searchVO) throws Exception;  

}

