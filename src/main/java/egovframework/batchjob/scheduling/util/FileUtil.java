package egovframework.batchjob.scheduling.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtil {
	
	public	static  void fileMove(String filepath,String copypath, String fileName) 
	{
		File folder1 = new File(filepath+ fileName);
		
		File folder2 = new File(copypath + fileName);
		
		copy(folder1, folder2);
		
		delete(folder1);
	}

	public static void copy(File sourceF, File targetF) 
	{
		if (sourceF.isFile())
		{
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(sourceF);
				fos = new FileOutputStream(targetF);
				
				byte[] b = new byte[10004096];
				int cnt = 0;
				while ((cnt = fis.read(b)) != -1) 
				{
					fos.write(b, 0, cnt);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public	static  void delete(File sourceF) 
	{
		try 
		{
			if (sourceF.delete()) {
				System.out.println("파일삭제 y" + sourceF.toString());
			} else {
				System.out.println("파일삭제 n" +  sourceF.toString());
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public static String nextAlphabet(String source) 
	{
		int length = source.length();
	    char lastChar = source.charAt(length - 1);
	    if (lastChar == 'Z') {
	    	if (length == 1) {
	    		return source = "AA";
	    	}
	    	source = nextAlphabet(source.substring(0, length - 1));
	    	source += "A";
	    	return source;
	    }
	    return source.substring(0, length - 1) + String.valueOf((char) (lastChar + 1));
	}


		

}
