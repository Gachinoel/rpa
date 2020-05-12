
package egovframework.rpa.cmm.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rpa.cmm.vo.RpaCmmVO;

public interface RpaCmmService {
	public List<RpaCmmVO> selectBLNoInfs(RpaCmmVO searchVO)
			throws Exception;
	
	public List<RpaCmmVO> selectCompanyInfs(RpaCmmVO searchVO)
			throws Exception;
	
	public int insertFileSapInf(MultipartHttpServletRequest multiRequest,
            HttpServletRequest request) throws Exception;	
    

    public Map<String, Object> selectFileSap(RpaCmmVO searchVO)
			throws Exception;
    
    public RpaCmmVO selectFileSapDetail(RpaCmmVO searchVO)
			throws Exception;
    
    public Map<String, Object> deleteFileSap(RpaCmmVO searchVO)
			throws Exception;

}

