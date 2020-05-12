
package egovframework.rpa.bill;

import java.util.Map;


public interface RpaBillService {
	public Map<String, Object> selectBillList(RpaBillVO searchVO) throws Exception;  
}

