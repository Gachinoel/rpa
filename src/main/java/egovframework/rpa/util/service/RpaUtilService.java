
package egovframework.rpa.util.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public interface RpaUtilService {
	public SXSSFWorkbook buildExcelXSS(String sheetTitle, Map<String, Object> excelInfpMap, List<Object> rows, boolean excelOption)
			throws Exception ;

}

