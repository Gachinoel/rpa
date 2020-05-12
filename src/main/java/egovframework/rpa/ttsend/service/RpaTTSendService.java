
package egovframework.rpa.ttsend.service;

import java.util.Map;

import egovframework.rpa.ttsend.vo.RpaTTSendVO;

public interface RpaTTSendService {
	 public Map<String, Object> selectTTSendList(RpaTTSendVO searchVO)
				throws Exception;  

}

