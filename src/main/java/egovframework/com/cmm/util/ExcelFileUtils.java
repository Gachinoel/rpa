
package egovframework.com.cmm.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class ExcelFileUtils {
	
	public static List<Map<String, Object>> dirFileList(String dirpath) {
		
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		
		// 하위의 모든 파일
        for (File info : FileUtils.listFiles(new File(dirpath), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
        	Map<String, Object> fileMap = new HashMap<String, Object>();
            System.out.println(info.getName());
        }
        
        
		
		return resultMap;
		
	}

}

