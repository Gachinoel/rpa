package egovframework.batchjob.scheduling;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import egovframework.batchjob.scheduling.util.ExcelFileType;
import egovframework.batchjob.scheduling.util.ExcelRead;
import egovframework.batchjob.scheduling.util.ExcelReadOption;
import egovframework.batchjob.scheduling.util.FileUtil;

@Service("batchService")
public class BatchServiceImpl implements BatchService {
	@Resource(name = "batchDAO")
    private BatchDAO batchDAO;
	
	public Map<String, Object> processImport() {
		int returnValue = 0;
		int filecnt = 0;
		Map<String, Object> resultMap = new HashMap<String,Object>();
		try {

        	String path = "//192.9.200.112\\wqms_백업\\A0. 수입통관\\01. 수입신고\\";
        	String copypath = "//192.9.200.112\\wqms_백업\\A0. 수입통관\\backUp\\수입신고\\";
    		String tempFileName = "";

    		try {
    			File dirFile = new File(path);
    			File[] fileList = dirFile.listFiles();
    			
    			List<Map<String, Object>> resultExcel = new ArrayList<Map<String, Object>>();

    			if(fileList.length > 0) 
    			{
    				for (File tempFile : fileList) {
    					
    					tempFileName = tempFile.getName();
    					
    					resultExcel = new ArrayList<Map<String, Object>>();
    					
    					int pos = tempFileName.lastIndexOf( "." );
    					String ext = tempFileName.substring( pos + 1 );
    					
    					if(ext.equals("xls"))
    					{
    						returnValue = 0;
    						
    						List<String> strKeyColumn = new ArrayList<String>();
    						Map<String, Object> alphabetMap = new HashMap<String, Object>();
    						//Service 단에서 가져온 코드 
    				        ExcelReadOption excelReadOption = new ExcelReadOption();
    				        excelReadOption.setFilePath(path+tempFileName);
    				        excelReadOption.setOutputColumns(11,"A",alphabetMap);
    				        excelReadOption.setStartRow(2);
    				        
    				        strKeyColumn.add("ITEM_CD");
    				        strKeyColumn.add("LOT");
    				        strKeyColumn.add("QTY");
    				        strKeyColumn.add("BL_DATE");
    				        strKeyColumn.add("OUTER_NO");
    				        strKeyColumn.add("INNER_NO");
    				        strKeyColumn.add("PO_NO");
    				        strKeyColumn.add("PO_LINE_NO");
    				        strKeyColumn.add("IV_NO");
    				        strKeyColumn.add("ETD");
    				        strKeyColumn.add("CARRY");
    				        excelReadOption.setOutputObjColumns(strKeyColumn);    
    				        
    						resultExcel = ExcelRead.read(excelReadOption);
    	
    						HashMap<String, Object> arrParam = new HashMap<String, Object>();

    			            if (resultExcel != null) {
    			            	if (resultExcel.size() > 0) {
    			            		arrParam.put("REMARK", tempFileName);
		    	                	arrParam.put("List", resultExcel);
		    	                	returnValue += batchDAO.updateImportFile(arrParam);
		    	                	returnValue += batchDAO.updateImport(arrParam);
    			    	            returnValue += batchDAO.insertImport(arrParam);
    			    	            resultMap.put("result", returnValue); //변수값
    			    	            resultMap.put("resultMessage", "엑셀파일이 정상적으로 처리되었습니다."); //변수값
    			                } else {
    			    	            resultMap.put("result", -1); //변수값
    			    	            resultMap.put("resultMessage", "엑셀데이타가 존재하지 않습니다."); //변수값
    			                }
    			            }
    			            
    			            if (returnValue > 0) {
            					FileUtil.fileMove(path,copypath, tempFileName);
            				}
            				else {
            					resultMap.put("result", -1); //변수값
                	            resultMap.put("resultMessage", "DB저장 중 오류가 발생했습니다."); //변수값
            				}
    			            filecnt++;
    					}
    					
    				}
    				if (filecnt == 0) {
    					resultMap.put("result", -1); //변수값
        	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
    				}
    				
    			}else {
    				resultMap.put("result", -1); //변수값
    	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			resultMap.put("result", -1); //변수값
	            resultMap.put("resultMessage", "Exception 오류가 발생했습니다."); //변수값
    			
    		}

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", -1); //변수값
            resultMap.put("resultMessage", "Exception 오류가 발생했습니다."); //변수값
        }
        return resultMap;
	}
	
	
	public Map<String, Object> processCargo() {
		int returnValue = 0;
		int filecnt = 0;
		Map<String, Object> resultMap = new HashMap<String,Object>();
		try {

        	String path = "//192.9.200.112\\wqms_백업\\A0. 수입통관\\02. 적하보험\\";
        	String copypath = "//192.9.200.112\\wqms_백업\\A0. 수입통관\\backUp\\적하보험\\";
    		String tempFileName = "";

    		try {
    			File dirFile = new File(path);
    			File[] fileList = dirFile.listFiles();
    			
    			List<Map<String, Object>> resultExcel = new ArrayList<Map<String, Object>>();

    			if(fileList.length > 0) 
    			{
    				for (File tempFile : fileList) {
    					
    					tempFileName = tempFile.getName();
    					
    					resultExcel = new ArrayList<Map<String, Object>>();
    					
    					int pos = tempFileName.lastIndexOf( "." );
    					String ext = tempFileName.substring( pos + 1 );
    					
    					if(ext.equals("xls"))
    					{
    						returnValue = 0;
    						
    						List<String> strKeyColumn = new ArrayList<String>();
    						Map<String, Object> alphabetMap = new HashMap<String, Object>();
    						//Service 단에서 가져온 코드 
    				        ExcelReadOption excelReadOption = new ExcelReadOption();
    				        excelReadOption.setFilePath(path+tempFileName);
    				        excelReadOption.setOutputColumns(16,"A",alphabetMap);
    				        excelReadOption.setStartRow(2);
    				        
    				        strKeyColumn.add("TRANS_GB");
    				        strKeyColumn.add("STOCK_NO");
    				        strKeyColumn.add("CHAMJO_MUNSEO_NO1");
    				        strKeyColumn.add("CHAMJO_MUNSEO_NO2");
    				        strKeyColumn.add("GAAEG_HWAPYE_CD");
    				        strKeyColumn.add("NUJEOG_AMT");
    				        strKeyColumn.add("GAIB_AMT_HWAPYE_CD");
    				        strKeyColumn.add("GAIB_AMT");
    				        strKeyColumn.add("KRW_EXCH_GAIB_AMT");
    				        strKeyColumn.add("JEOGYONG_RT");
    				        strKeyColumn.add("CO_KRW_EXCH_INSR_FEE");
    				        strKeyColumn.add("ACNT_DT");
    				        strKeyColumn.add("UNSONG_YONG_GU");
    				        strKeyColumn.add("UNSONG_YONG_GU_NM");
    				        strKeyColumn.add("CHULBALJI_NM");
    				        strKeyColumn.add("DOCHAGJI_CD");
    				        strKeyColumn.add("DOCHAGJI_NM");
    				        excelReadOption.setOutputObjColumns(strKeyColumn);    
    				        
    						resultExcel = ExcelRead.read(excelReadOption);
    	
    						HashMap<String, Object> arrParam = new HashMap<String, Object>();

    			            if (resultExcel != null) {
    			            	if (resultExcel.size() > 0) {
    			            		List<Map<String, Object>> resultExcel1 = new ArrayList<Map<String, Object>>();
    			                	int totalSize = resultExcel.size();

    			                	for(int i=0; i < totalSize; i++) {
    			                    	Map<String, Object> paramMap = resultExcel.get(i);
    			                    	for(int j = 0; j < 10; j++) {
    			                    		paramMap.put("IV_NO_" + String.valueOf(j+1),"");
    			                    	}
    			                    	List<String> temp = new ArrayList<String>();
    			                        
    			                    	String munseo_no1 = resultExcel.get(i).get("CHAMJO_MUNSEO_NO1").toString();
    			                    	String munseo_no2 = resultExcel.get(i).get("CHAMJO_MUNSEO_NO2").toString();
    			                    	if (munseo_no1.length() > 0) {
    			                    		String[] mu_no1 = munseo_no1.split("#");
    			                    		if (mu_no1.length > 0) {
    			                    			String[] mu_no2 = mu_no1[1].split("-");
    			                    			if (mu_no2.length > 0) {
    			                    				String firstStr = mu_no2[0];
        			                    			String[] mu_no3 = mu_no2[1].split(",");
        			                    			if (mu_no3.length > 0) {
            			                    			for(int n = 0; n < mu_no3.length; n++) {
            			                    				temp.add(firstStr + "-"+ mu_no3[n]);
            			                    			}
            			                    		}
        			                    			else {
        			                    				temp.add(firstStr + "-"+ mu_no2[1]);
        			                    			}
        			                    		}
    			                    		}
    			                    	}
    			                    	if (munseo_no2.length() > 0) {
    			                    		String[] mu_no1 = munseo_no2.split("#");
    			                    		if (mu_no1.length > 0) {
    			                    			String[] mu_no2 = mu_no1[1].split("-");
    			                    			if (mu_no2.length > 0) {
    			                    				String firstStr = mu_no2[0];
        			                    			String[] mu_no3 = mu_no2[1].split(",");
        			                    			if (mu_no3.length > 0) {
            			                    			for(int n = 0; n < mu_no3.length; n++) {
            			                    				temp.add(firstStr + "-"+ mu_no3[n]);
            			                    			}
            			                    		}
        			                    			else {
        			                    				temp.add(firstStr + "-"+ mu_no2[1]);
        			                    			}
        			                    		}
    			                    		}
    			                    	}
    			                    	if (temp.size() > 0) {
    			                    		for (int m = 0; m < temp.size(); m++) {
    			                    			paramMap.put("IV_NO_" + String.valueOf(m+1), temp.get(m));
    			                    		}
    			                    	}
    			                    	
    			                    	resultExcel1.add(paramMap);
    			                    }
    			            		arrParam.put("REMARK", tempFileName);
		    	                	arrParam.put("List", resultExcel1);
		    	                	returnValue += batchDAO.updateCargoFile(arrParam);
		    	                	returnValue += batchDAO.updateCargo(arrParam);
    			    	            returnValue += batchDAO.insertCargo(arrParam);
    			    	            resultMap.put("result", returnValue); //변수값
    			    	            resultMap.put("resultMessage", "엑셀파일이 정상적으로 처리되었습니다."); //변수값
    			                } else {
    			    	            resultMap.put("result", -1); //변수값
    			    	            resultMap.put("resultMessage", "엑셀데이타가 존재하지 않습니다."); //변수값
    			                }
    			            	if (returnValue > 0) {
                					FileUtil.fileMove(path,copypath, tempFileName);
                				}
                				else {
                					resultMap.put("result", -1); //변수값
                    	            resultMap.put("resultMessage", "DB저장 중 오류가 발생했습니다."); //변수값
                				}
        			            filecnt++;
        					}
        					
        				}
        				if (filecnt == 0) {
        					resultMap.put("result", -1); //변수값
            	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
        				}
    				}
    				if (returnValue > 0) {
    					FileUtil.fileMove(path,copypath, tempFileName);
    				}
    				else {
    					resultMap.put("result", -1); //변수값
        	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
    				}
    			}else {
    				resultMap.put("result", -1); //변수값
    	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			resultMap.put("result", -1); //변수값
	            resultMap.put("resultMessage", "Exception 오류가 발생했습니다."); //변수값
    			
    		}

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", -1); //변수값
            resultMap.put("resultMessage", "Exception 오류가 발생했습니다."); //변수값
        }
        return resultMap;
	}
	
	public Map<String, Object> processForward() {
		int returnValue = 0;
		int filecnt = 0;
		Map<String, Object> resultMap = new HashMap<String,Object>();
		try {

        	String path = "//192.9.200.112\\wqms_백업\\A0. 수입통관\\03. 포워더\\";
        	String copypath = "//192.9.200.112\\wqms_백업\\A0. 수입통관\\backUp\\포워더\\";
    		String tempFileName = "";

    		try {
    			File dirFile = new File(path);
    			File[] fileList = dirFile.listFiles();
    			
    			List<Map<String, Object>> resultExcel = new ArrayList<Map<String, Object>>();

    			if(fileList.length > 0) 
    			{
    				for (File tempFile : fileList) {
    					
    					tempFileName = tempFile.getName();
    					
    					resultExcel = new ArrayList<Map<String, Object>>();
    					
    					int pos = tempFileName.lastIndexOf( "." );
    					String ext = tempFileName.substring( pos + 1 );
    					
    					if(ext.equals("xls"))
    					{
    						returnValue = 0;
    						
    						List<String> strKeyColumn = new ArrayList<String>();
    						Map<String, Object> alphabetMap = new HashMap<String, Object>();
    						alphabetMap.put("1", "21");
    						alphabetMap.put("2", "32");
    						//Service 단에서 가져온 코드 
    				        ExcelReadOption excelReadOption = new ExcelReadOption();
    				        excelReadOption.setFilePath(path+tempFileName);
    				        excelReadOption.setOutputColumns(42,"B",alphabetMap);
    				        excelReadOption.setStartRow(2);
    				        
    				        strKeyColumn.add("IN_NO");
    				        strKeyColumn.add("H_BL_NO");
    				        strKeyColumn.add("BL_SEONJEOG_DT");
    				        strKeyColumn.add("ETD_DT");
    				        strKeyColumn.add("ETA_DT");
    				        strKeyColumn.add("SEONJEOG_GUG_CD");
    				        strKeyColumn.add("DOCHAG_GUG_CD");
    				        strKeyColumn.add("SEONJEOG_HANG_CD");
    				        strKeyColumn.add("DOCHAG_HANG_CD");
    				        strKeyColumn.add("FLT_VSSL_NM");
    				        strKeyColumn.add("C_20FT_QT");
    				        strKeyColumn.add("C_40FT_QT");
    				        strKeyColumn.add("UNSONG_GB");
    				        strKeyColumn.add("CHONG_POJANG_EA");
    				        strKeyColumn.add("NET_WG");
    				        strKeyColumn.add("GROSS_WG");
    				        strKeyColumn.add("SEOLYU_SONGBU_DT");
    				        strKeyColumn.add("SEONJEOG_SEOLYU_SONGBUCHEO_CD");
    				        strKeyColumn.add("SIL_IBHANG_DT");
    				        strKeyColumn.add("IBGO_YEJEONG_DT");
    				        strKeyColumn.add("H_BL_NO_1");
    				        strKeyColumn.add("BIYONG_GEULUB_1");
    				        strKeyColumn.add("JEUNGBING_DT_1");
    				        strKeyColumn.add("JEONGI_DT_1");
    				        strKeyColumn.add("BL_DT_1");
    				        strKeyColumn.add("SAEOBJANG_CD_1");
    				        strKeyColumn.add("JEONPYO_TOT_AMT_1");
    				        strKeyColumn.add("TOT_SE_AMT_1");
    				        strKeyColumn.add("BIYONG_CD_1");
    				        strKeyColumn.add("GONGGEUB_CO_CD_1");
    				        strKeyColumn.add("JEONPYO_JEMOG_1");
    				        strKeyColumn.add("BIYONG_GEULUB_2");
    				        strKeyColumn.add("JEUNGBING_DT_2");
    				        strKeyColumn.add("JEONGI_DT_2");
    				        strKeyColumn.add("BL_DT_2");
    				        strKeyColumn.add("SAEOBJANG_CD_2");
    				        strKeyColumn.add("JEONPYO_TOT_AMT_2");
    				        strKeyColumn.add("TOT_SE_AMT_2");
    				        strKeyColumn.add("BIYONG_CD_2");
    				        strKeyColumn.add("GONGGEUB_CO_CD_2");
    				        strKeyColumn.add("JEONPYO_JEMOG_2");
    				        excelReadOption.setOutputObjColumns(strKeyColumn);    
    				        
    						resultExcel = ExcelRead.read(excelReadOption);
    	
    						HashMap<String, Object> arrParam = new HashMap<String, Object>();

    			            if (resultExcel != null) {
    			            	if (resultExcel.size() > 0) {
    			            		arrParam.put("REMARK", tempFileName);
		    	                	arrParam.put("List", resultExcel);
		    	                	returnValue += batchDAO.updateForwardFile(arrParam);
		    	                	returnValue += batchDAO.updateForward(arrParam);
    			    	            returnValue += batchDAO.insertForward(arrParam);
    			    	            resultMap.put("result", returnValue); //변수값
    			    	            resultMap.put("resultMessage", "엑셀파일이 정상적으로 처리되었습니다."); //변수값
    			                } else {
    			    	            resultMap.put("result", -1); //변수값
    			    	            resultMap.put("resultMessage", "엑셀데이타가 존재하지 않습니다."); //변수값
    			                }
    			            }
    			            if (returnValue > 0) {
            					FileUtil.fileMove(path,copypath, tempFileName);
            				}
            				else {
            					resultMap.put("result", -1); //변수값
                	            resultMap.put("resultMessage", "DB저장 중 오류가 발생했습니다."); //변수값
            				}
    			            filecnt++;
    					}
    					
    				}
    				if (filecnt == 0) {
    					resultMap.put("result", -1); //변수값
        	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
    				}
    			}else {
    				resultMap.put("result", -1); //변수값
    	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			resultMap.put("result", -1); //변수값
	            resultMap.put("resultMessage", "Exception 오류가 발생했습니다."); //변수값
    			
    		}

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", -1); //변수값
            resultMap.put("resultMessage", "Exception 오류가 발생했습니다."); //변수값
        }
        return resultMap;
	}
	
	
	public Map<String, Object> processCustom() {
		int returnValue = 0;
		int filecnt = 0;
		Map<String, Object> resultMap = new HashMap<String,Object>();
		try {

        	String path = "//192.9.200.112\\wqms_백업\\A0. 수입통관\\04. 관세사\\";
        	String copypath = "//192.9.200.112\\wqms_백업\\A0. 수입통관\\backUp\\관세사\\";
    		String tempFileName = "";

    		try {
    			File dirFile = new File(path);
    			File[] fileList = dirFile.listFiles();
    			
    			List<Map<String, Object>> resultExcel = new ArrayList<Map<String, Object>>();

    			if(fileList.length > 0) 
    			{
    				for (File tempFile : fileList) {
    					
    					tempFileName = tempFile.getName();
    					
    					resultExcel = new ArrayList<Map<String, Object>>();
    					
    					int pos = tempFileName.lastIndexOf( "." );
    					String ext = tempFileName.substring( pos + 1 );
    					
    					if(ext.equals("xlsx"))
    					{
    						returnValue = 0;
    						
    						List<String> strKeyColumn = new ArrayList<String>();
    						Map<String, Object> alphabetMap = new HashMap<String, Object>();
    						//Service 단에서 가져온 코드 
    				        ExcelReadOption excelReadOption = new ExcelReadOption();
    				        excelReadOption.setFilePath(path+tempFileName);
    				        
    				        Workbook wb = ExcelFileType.getWorkbook(path+tempFileName);
    				        /**
    				         * 엑셀 파일에서 첫번째 시트를 가지고 온다.
    				         */
    				        Sheet sheet = wb.getSheetAt(0);
    				        Row row = sheet.getRow(0);
    				        int colNum = row.getLastCellNum();
    				        
    				        excelReadOption.setOutputColumns(colNum -1,"A",alphabetMap);
    				        excelReadOption.setStartRow(2);
    				        
    				        if(colNum < 280) {
    							
    				        	for (int i=0;i<153;i++)      
    							{
    								strKeyColumn.add("d"+String.valueOf(i));
    					        }
    				        	strKeyColumn.add("PASSCELL");
    				        	for (int i=157;i<164;i++)      
    							{
    				        		strKeyColumn.add("d"+String.valueOf(i));    				        		
    					        }
    				        	strKeyColumn.add("PASSCELL");
    				        	strKeyColumn.add("PASSCELL");
    				        	strKeyColumn.add("PASSCELL");
    							for (int i=165;i<260;i++)      
    							{
    								strKeyColumn.add("d"+String.valueOf(i));
    					        }
    							strKeyColumn.add("PASSCELL");
    							for (int i=267;i<283;i++)      
    							{
    								strKeyColumn.add("d"+String.valueOf(i));
    					        }
    							strKeyColumn.add("PASSCELL");
    							strKeyColumn.add("PASSCELL");
    							strKeyColumn.add("PASSCELL");
    							strKeyColumn.add("PASSCELL");
    							strKeyColumn.add("PASSCELL");
    							strKeyColumn.add("PASSCELL");
    							strKeyColumn.add("PASSCELL");
    						
    						}else {
    							
    							for (int i=0;i<colNum;i++)      
    							{
    								strKeyColumn.add("d"+String.valueOf(i));
    					        }
    							
    						}
    				        excelReadOption.setOutputObjColumns(strKeyColumn);    
    				        
    						resultExcel = ExcelRead.read(excelReadOption);
    	
    						HashMap<String, Object> arrParam = new HashMap<String, Object>();

    			            if (resultExcel != null) {
    			            	if (resultExcel.size() > 0) {
    			            		arrParam.put("REMARK", tempFileName);
		    	                	arrParam.put("List", resultExcel);
		    	                	returnValue += batchDAO.updateCustomFile(arrParam);
		    	                	returnValue += batchDAO.updateCustom(arrParam);
    			    	            returnValue += batchDAO.insertCustom(arrParam);
    			    	            resultMap.put("result", returnValue); //변수값
    			    	            resultMap.put("resultMessage", "엑셀파일이 정상적으로 처리되었습니다."); //변수값
    			                } else {
    			    	            resultMap.put("result", -1); //변수값
    			    	            resultMap.put("resultMessage", "엑셀데이타가 존재하지 않습니다."); //변수값
    			                }
    			            }
    			            if (returnValue > 0) {
            					FileUtil.fileMove(path,copypath, tempFileName);
            				}
            				else {
            					resultMap.put("result", -1); //변수값
                	            resultMap.put("resultMessage", "DB저장 중 오류가 발생했습니다."); //변수값
            				}
    			            filecnt++;
    					}
    					
    				}
    				if (filecnt == 0) {
    					resultMap.put("result", -1); //변수값
        	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
    				}
    			}else {
    				resultMap.put("result", -1); //변수값
    	            resultMap.put("resultMessage", "파일이 존재하지 않습니다."); //변수값
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			resultMap.put("result", -1); //변수값
	            resultMap.put("resultMessage", "Exception 오류가 발생했습니다."); //변수값
    			
    		}

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", -1); //변수값
            resultMap.put("resultMessage", "Exception 오류가 발생했습니다."); //변수값
        }
        return resultMap;
	}
}
