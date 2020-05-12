
package egovframework.rpa.util.service.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.rpa.util.ExcelStyleBuilder;
import egovframework.rpa.util.service.RpaUtilService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("rpaUtilService")
public class RpaUtilServiceImpl extends EgovAbstractServiceImpl implements RpaUtilService{
	/**
	 * Excel File을 생성하여 Workbook을 반환.
	 *
	 * @param sheetTitle Sheet 제목
	 * @param excelCellInfos Cell의 생성 정보
	 * @param rows 실제 Data
	 * @return XSSFWorkbook
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchFieldException 
	 */
	@SuppressWarnings("unchecked")
	public SXSSFWorkbook buildExcelXSS(String sheetTitle, Map<String, Object> excelInfpMap
			, List<Object> result, boolean excelOption) 
					throws Exception {
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		
		ExcelStyleBuilder stylesXss = new ExcelStyleBuilder();

		Sheet sheet = workbook.createSheet(sheetTitle);

		sheet.setDisplayGridlines(false);
		
		Row row = null;
		Cell cell = null;
		
		//첫Row,마지막Row,첫cell,마지막cell, row높이, 스타일, 내용
		Map<String, Object> headerMap = (Map<String, Object>) excelInfpMap.get("headerMap");
		Map<String, Object> unitMap = (Map<String, Object>) excelInfpMap.get("unitMap");
		List<Map<String, Object>> unitList = (List<Map<String, Object>>) excelInfpMap.get("unitList");
		Map<String, Object> titleStyleMap = (Map<String, Object>) excelInfpMap.get("titleStyleMap");
		List<Map<String, Object>> mergeCellList= (List<Map<String, Object>>) excelInfpMap.get("mergeCellList");			
		List<Map<String, Object>> sumCellList= (List<Map<String, Object>>) excelInfpMap.get("sumCellList");			
		List<Map<String, Object>> titleCellList = (List<Map<String, Object>>) excelInfpMap.get("titleCellList");
		List<Map<String, Object>> fieldInfoList = (List<Map<String, Object>>) excelInfpMap.get("fieldInfoList");
				
		int nRow = 1;
		
		if (headerMap != null){
			for (int i = Integer.parseInt(headerMap.get("sRow").toString()); i <= Integer.parseInt(headerMap.get("eRow").toString()); i++) {
				row = sheet.createRow(i);
				for (int j = Integer.parseInt(headerMap.get("sCol").toString()); j <= Integer.parseInt(headerMap.get("eCol").toString()); j++) {
					cell = row.createCell(j);
					CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
							, headerMap.get("fontType").toString(), headerMap.get("fontColor").toString(), headerMap.get("styleColor").toString()
							, headerMap.get("textAlign").toString(), headerMap.get("textVAlign").toString()
							, headerMap.get("line").toString(),"");
					cell.setCellStyle(titleStyle);
				}
			
				int nRow1 = Integer.parseInt(headerMap.get("sRow").toString());
				int nRow2 = Integer.parseInt(headerMap.get("eRow").toString());
				int nCol1 = Integer.parseInt(headerMap.get("sCol").toString());
				int nCol2 = Integer.parseInt(headerMap.get("eCol").toString());
				
				row = sheet.getRow(nRow1);

				cell = row.getCell(nCol1);

				cell.setCellValue(headerMap.get("title").toString());
				
				if ((nRow1 != nRow2) || (nCol1 != nCol2)){
					sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
				}
				//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
				//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
				row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
				row.setHeight((short)600);
			}
			nRow = Integer.parseInt(headerMap.get("eRow").toString());
			
		}
		
		
		
		if (unitList != null){
			for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
				row = sheet.createRow(i);
				for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
					cell = row.createCell(j);
				}
			}
			
			for(int n = 0; n < unitList.size(); n++) {
				
				for (int i = Integer.parseInt(unitList.get(n).get("sRow").toString()); i <= Integer.parseInt(unitList.get(n).get("eRow").toString()); i++) {
					row = sheet.getRow(i);
					for (int j = Integer.parseInt(unitList.get(n).get("sCol").toString()); j <= Integer.parseInt(unitList.get(n).get("eCol").toString()); j++) {
						cell = row.getCell(j);
						CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
								, unitList.get(n).get("fontType").toString(), unitList.get(n).get("fontColor").toString(), unitList.get(n).get("styleColor").toString()
								, unitList.get(n).get("textAlign").toString(), unitList.get(n).get("textVAlign").toString()
								, unitList.get(n).get("line").toString(),"");
						cell.setCellStyle(titleStyle);
					}
				}
				
				int nRow1 = Integer.parseInt(unitList.get(n).get("sRow").toString());
				int nRow2 = Integer.parseInt(unitList.get(n).get("eRow").toString());
				int nCol1 = Integer.parseInt(unitList.get(n).get("sCol").toString());
				int nCol2 = Integer.parseInt(unitList.get(n).get("eCol").toString());
				
				row = sheet.getRow(nRow1);

				cell = row.getCell(nCol1);

				cell.setCellValue(unitList.get(n).get("title").toString());
				
				if ((nRow1 != nRow2) || (nCol1 != nCol2)){
					sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
				}
				//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
				//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
				row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
			}
			
			nRow = Integer.parseInt(unitMap.get("eRow").toString());
			
		}
		else if (unitMap != null){
			for (int i = Integer.parseInt(unitMap.get("sRow").toString()); i <= Integer.parseInt(unitMap.get("eRow").toString()); i++) {
				row = sheet.createRow(i);
				for (int j = Integer.parseInt(unitMap.get("sCol").toString()); j <= Integer.parseInt(unitMap.get("eCol").toString()); j++) {
					cell = row.createCell(j);
					CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
							, unitMap.get("fontType").toString(), unitMap.get("fontColor").toString(), unitMap.get("styleColor").toString()
							, unitMap.get("textAlign").toString(), unitMap.get("textVAlign").toString()
							, unitMap.get("line").toString(),"");
					cell.setCellStyle(titleStyle);
				}
			
				int nRow1 = Integer.parseInt(unitMap.get("sRow").toString());
				int nRow2 = Integer.parseInt(unitMap.get("eRow").toString());
				int nCol1 = Integer.parseInt(unitMap.get("sCol").toString());
				int nCol2 = Integer.parseInt(unitMap.get("eCol").toString());
				
				row = sheet.getRow(nRow1);

				cell = row.getCell(nCol1);

				cell.setCellValue(unitMap.get("title").toString());
				
				if ((nRow1 != nRow2) || (nCol1 != nCol2)){
					sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
				}
				//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
				//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
				row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
				row.setHeight((short)300);
			}
			nRow = Integer.parseInt(unitMap.get("eRow").toString());
			
		}
		
		
		if (titleStyleMap != null){
			for (int i = Integer.parseInt(titleStyleMap.get("sRow").toString()); i <= Integer.parseInt(titleStyleMap.get("eRow").toString()); i++) {
				row = sheet.createRow(i);
				for (int j = Integer.parseInt(titleStyleMap.get("sCol").toString()); j <= Integer.parseInt(titleStyleMap.get("eCol").toString()); j++) {
					cell = row.createCell(j);
					CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
							, titleStyleMap.get("fontType").toString(), titleStyleMap.get("fontColor").toString(), titleStyleMap.get("styleColor").toString()
							, titleStyleMap.get("textAlign").toString(), titleStyleMap.get("textVAlign").toString()
							, titleStyleMap.get("line").toString(),"");
					cell.setCellStyle(titleStyle);
				}
			}
			
			if (titleCellList != null){
				for(int n = 0; n < titleCellList.size(); n++) {
					int nRow1 = Integer.parseInt(titleCellList.get(n).get("sRow").toString());
					int nRow2 = Integer.parseInt(titleCellList.get(n).get("eRow").toString());
					int nCol1 = Integer.parseInt(titleCellList.get(n).get("sCol").toString());
					int nCol2 = Integer.parseInt(titleCellList.get(n).get("eCol").toString());
					
					row = sheet.getRow(nRow1);

					cell = row.getCell(nCol1);

					cell.setCellValue(titleCellList.get(n).get("cellTitle").toString());
					
					if ((nRow1 != nRow2) || (nCol1 != nCol2)){
						sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
					}
					//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
					//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
					row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
					sheet.setColumnWidth(n, Integer.parseInt(titleCellList.get(n).get("cellWidth").toString()) );
				}
			}
			else {
				if (fieldInfoList != null){
					for(int n = 0; n < fieldInfoList.size(); n++) {
						int nRow1 = Integer.parseInt(titleStyleMap.get("sRow").toString());
						int nRow2 = Integer.parseInt(titleStyleMap.get("eRow").toString());
						int nCol1 = n;
						int nCol2 = n;
						
						row = sheet.getRow(nRow1);
	
						cell = row.getCell(nCol1);
	
						cell.setCellValue(fieldInfoList.get(n).get("cellTitle").toString());
						
						if ((nRow1 != nRow2) || (nCol1 != nCol2)){
							sheet.addMergedRegion(new CellRangeAddress(nRow1, nRow2, nCol1, nCol2));
						}
						//sheet.autoSizeColumn(n);    //너비를 자동으로 다시 설정
						//sheet.setColumnWidth(n, (sheet.getColumnWidth(n))+512 );
						row.setHeightInPoints((sheet.getDefaultRowHeightInPoints()));
						sheet.setColumnWidth(n, Integer.parseInt(fieldInfoList.get(n).get("cellWidth").toString()) );
					}
				}
			}
			nRow = Integer.parseInt(titleStyleMap.get("eRow").toString());
			
		}
		
		
		if (titleStyleMap != null){
			
			nRow = nRow + 1;
			
			String[] tempField = new String[fieldInfoList.size()];
			for (int n = 0; n < fieldInfoList.size(); n++) {
				tempField[n] = fieldInfoList.get(n).get("field").toString();
			}	
			
			if (result != null){
				
				int[] arrStartCnt = new int[5];
				int[] arrEndCnt = new int[5];
				String[][] arrField = new String[5][result.size()];
				Arrays.fill(arrStartCnt, nRow);
				Arrays.fill(arrEndCnt, 0);
				for (String[] arrRow: arrField)
					Arrays.fill(arrRow, "");

				int nCnt = 0;
				
				for (Object item : result) {
					Map<String, Object> testMap = new HashMap<String, Object>();
					try {
						testMap = domainToMapWithExcept(item, tempField);
					} catch (Exception e) {
						
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					row = sheet.createRow(nRow++);
					int nStartCell = 100;
					String sCellColor = "FFFFFF";

					for (int i = 0; i < fieldInfoList.size(); i++) {
						
						cell = row.createCell(i);
						
						if (mergeCellList != null) {
							if (nCnt > 0) {

								for (int m = 0; m < mergeCellList.size(); m++) {
									Map<String, Object> mergeMap = mergeCellList.get(m);
									if (mergeMap.get("field").toString().equals(fieldInfoList.get(i).get("field").toString())){
										Boolean bFlag = false;
										for (int n = m, z=0; n >= 0; n--, z++) {
											int nMRow = Integer.parseInt(mergeCellList.get(m).get("fieldNum").toString());
											if(arrField[nMRow][nCnt-1].equals(testMap.get(mergeCellList.get(m).get("field").toString()).toString())) {
												bFlag = true;
												break;
											}
											else {
												bFlag = false;
												//break;
											}
										}
										if (bFlag) {
											arrEndCnt[Integer.parseInt(mergeCellList.get(m).get("fieldNum").toString())] = nRow - 1;
										}
										
									}
								}
							}
						}
						
						if ((testMap.get(fieldInfoList.get(i).get("field")) != null) && (!testMap.get(fieldInfoList.get(i).get("field")).equals(""))) {
							if (fieldInfoList.get(i).get("fileType").toString().equals("Int")) {
								double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								cell.setCellValue(d);
							} else if (fieldInfoList.get(i).get("fileType").toString().equals("Float")) {
								double d = Double.parseDouble(testMap.get(fieldInfoList.get(i).get("field")).toString());
								cell.setCellType(Cell.CELL_TYPE_NUMERIC);
								cell.setCellValue(d);
							} else {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								cell.setCellValue(testMap.get(fieldInfoList.get(i).get("field")).toString());
							}

						} else {
							cell.setCellValue(" ");
						}
						
						if (sumCellList != null) {
							for (int m = 0; m < sumCellList.size(); m++) {
								Map<String, Object> sumMap = sumCellList.get(m);
								if (sumMap.get("field").toString().equals(fieldInfoList.get(i).get("field").toString())){
									if (Integer.parseInt(sumMap.get("sCell").toString()) != Integer.parseInt(sumMap.get("eCell").toString())) {
										boolean subFlag = true;
										for (int z = Integer.parseInt(sumMap.get("sCell").toString()); z < Integer.parseInt(sumMap.get("eCell").toString()) + 1; z++) {
											if (sumMap.get("fieldNm").toString().equals(testMap.get(fieldInfoList.get(z).get("field")).toString())) {
												subFlag = true;
												continue;
											}
											else {
												subFlag = false;
												break;
											}
										}
										if (subFlag) {
											sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 
													Integer.parseInt(sumMap.get("sCell").toString()), Integer.parseInt(sumMap.get("eCell").toString())));
											nStartCell = Integer.parseInt(sumMap.get("sCell").toString());
											sCellColor = sumMap.get("cellColor").toString();
											break;
										}
										
									}
									else {
										if (sumMap.get("fieldNm").toString().equals(testMap.get(fieldInfoList.get(i).get("field")).toString())) {
											nStartCell = Integer.parseInt(sumMap.get("sCell").toString());
											sCellColor = sumMap.get("cellColor").toString();
											break;
										}
									}
									/*
					
									
									if ((testMap.get(fieldInfoList.get(i).get("field")).toString().equals("총계")) && (sumMap.get("fieldNm").toString().equals("총계"))) {
										sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, 
												Integer.parseInt(sumMap.get("sCell").toString()), Integer.parseInt(sumMap.get("eCell").toString())));
										nStartCell = Integer.parseInt(sumMap.get("sCell").toString());
										sCellColor = "c4c4c4";
										break;
									}
									else if ((testMap.get(fieldInfoList.get(i).get("field")).toString().equals("합계")) && (sumMap.get("fieldNm").toString().equals("합계"))) {
										sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, Integer.parseInt(sumMap.get("sCell").toString()), Integer.parseInt(sumMap.get("eCell").toString())));
										nStartCell = Integer.parseInt(sumMap.get("sCell").toString());
										sCellColor = "f4f4f4";
										break;
									}
									else if ((testMap.get(fieldInfoList.get(i).get("field")).toString().equals("소계")) && (sumMap.get("fieldNm").toString().equals("소계"))) {
										sheet.addMergedRegion(new CellRangeAddress(nRow-1, nRow-1, Integer.parseInt(sumMap.get("sCell").toString()), Integer.parseInt(sumMap.get("eCell").toString())));
										nStartCell = Integer.parseInt(sumMap.get("sCell").toString());
										sCellColor = "FFFFEE";
										break;
									}
									*/
								}
								else {
									continue;
								}
							}
						}
						
						if (nStartCell != 100) {
							if (i < nStartCell) {
								CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
										, fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
										, fieldInfoList.get(i).get("styleColor").toString()
										, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
										, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
								cell.setCellStyle(titleStyle);
							}
							else {
								CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
										, fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
										, sCellColor
										, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
										, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
								cell.setCellStyle(titleStyle);
							}
						}
						else {
							CellStyle titleStyle = stylesXss.createExcelCellStylesXSS(workbook
									, fieldInfoList.get(i).get("fontType").toString(), fieldInfoList.get(i).get("fontColor").toString()
									, fieldInfoList.get(i).get("styleColor").toString()
									, fieldInfoList.get(i).get("textAlign").toString(), fieldInfoList.get(i).get("textVAlign").toString()
									, fieldInfoList.get(i).get("line").toString(), fieldInfoList.get(i).get("fomule").toString());
							cell.setCellStyle(titleStyle);
						}
						
						sheet.setColumnWidth(i, Integer.parseInt(fieldInfoList.get(i).get("cellWidth").toString()) );
						
						if (mergeCellList != null) {
							if (nCnt > 0) {

								for (int m = 0; m < mergeCellList.size(); m++) {
									Map<String, Object> mergeMap = mergeCellList.get(m);
									if (mergeMap.containsValue(fieldInfoList.get(i).get("field").toString())){
										Boolean bFlag = false;
										for (int n = m, z=0; n >= 0; n--, z++) {
											int nMRow = Integer.parseInt(mergeCellList.get(m).get("fieldNum").toString());
											if(arrField[nMRow][nCnt-1].equals(testMap.get(mergeCellList.get(n).get("field").toString()).toString())) {
												bFlag = true;
												break;
											}
											else {
												bFlag = false;
												//break;
											}
										}
										int nMRow = Integer.parseInt(mergeCellList.get(m).get("fieldNum").toString());
										if (bFlag) {
											arrField[nMRow][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
										}
										else {
											if (arrStartCnt[nMRow] != 0 && arrEndCnt[nMRow] != 0 && arrEndCnt[nMRow] > arrStartCnt[nMRow]) {
												sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMRow], arrEndCnt[nMRow], nMRow, nMRow));
											}

											arrStartCnt[nMRow] = nRow - 1;
											arrField[nMRow][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
										}
										
									}
								}
							}
							else {
								for (int m = 0; m < mergeCellList.size(); m++) {
									int nMRow = Integer.parseInt(mergeCellList.get(m).get("fieldNum").toString());
									if (fieldInfoList.get(i).get("field").equals(mergeCellList.get(m).get("field").toString())) {
										arrField[nMRow][nCnt] = testMap.get(fieldInfoList.get(i).get("field")).toString();
									}
									
								}
								
							}
						}
						
						//sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
						//sheet.autoSizeColumn(i);    //너비를 자동으로 다시 설정
						
					}
					if (mergeCellList != null) {
						nCnt++;
					}
					
					
				}
				if (mergeCellList != null) {
					for(int nMer = 0; nMer < mergeCellList.size() - 1;nMer++) {
						arrEndCnt[nMer] = nRow - 1;
						if (arrStartCnt[nMer] != 0 && arrEndCnt[nMer] != 0 && arrEndCnt[nMer] > arrStartCnt[nMer]) {
							sheet.addMergedRegion(new CellRangeAddress(arrStartCnt[nMer], arrEndCnt[nMer], nMer, nMer));
						}
					}
				}
			}
			
			
		}
		
		if (excelOption) {
			sheet.createFreezePane(0, Integer.parseInt(titleStyleMap.get("sRow").toString())+1, Integer.parseInt(titleStyleMap.get("eCol").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString())+1);
			sheet.setAutoFilter(new CellRangeAddress(Integer.parseInt(titleStyleMap.get("sRow").toString()), Integer.parseInt(titleStyleMap.get("sRow").toString()), 0, Integer.parseInt(titleStyleMap.get("eCol").toString())));
		}
		return workbook;
	}
	
	/**
	 * 특정 변수를 제외해서 vo를 map형식으로 변환해서 반환.
	 * @param vo VO
	 * @param arrExceptList 제외할 property 명 리스트
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> domainToMapWithExcept(Object vo, String[] arrAccepttList) throws Exception {
	    Map<String, Object> result = new HashMap<String, Object>();
	    BeanInfo info = Introspector.getBeanInfo(vo.getClass());
	    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
	        Method reader = pd.getReadMethod();
	        if (reader != null) {
	            if(arrAccepttList != null && arrAccepttList.length > 0 && isContain(arrAccepttList, pd.getName()))
	            	result.put(pd.getName(), reader.invoke(vo));
	            else
	            	continue;
	            
	        }
	    }
	    return result;
	}
	public static Boolean isContain(String[] arrList, String name) {
	    for (String arr : arrList) {
	        if (StringUtils.contains(arr, name))
	            return true;
	    }
	    return false;
	}
}

