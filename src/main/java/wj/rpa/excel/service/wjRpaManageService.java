package wj.rpa.excel.service;

import java.util.List;
import java.util.Map;

/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.12
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  이삼섭          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성 
 *  
 *  </pre>
 */
public interface wjRpaManageService {

	
		public Map<String, Object> selectRpaMainList(ExcelMasterVO searchVO)
				throws Exception;
		
		public List<ExcelMasterVO> selectInvoiceList(ExcelMaster searchVO)
				throws Exception;
		
		public List<ExcelMasterVO> selectCargoList(ExcelMaster searchVO)
					throws Exception;
		
		public List<ExcelMasterVO> selectBlList(ExcelMaster searchVO)
				throws Exception;	
		
		public List<ExcelMasterVO> selectPassList(ExcelMaster searchVO)
				throws Exception;	

}