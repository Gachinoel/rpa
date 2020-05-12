package wj.rpa.excel.util;


import java.util.Map;
import egovframework.let.cop.bbs.service.BoardMasterVO;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
//import com.ibm.icu.impl.Row;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFCell;


//아래2개는 뭐냐
import com.ibm.icu.impl.Row;
//import jxl.Cell;

import wj.rpa.excel.util.Fruit;
//import antlr.collections.List;

public class ExcelService {
	
	
		
	/**
	**
    * 엑셀파일로 만들 리스트 생성
    * @param names
    * @param prices
    * @param quantities
    * @return 엑셀파일 리스트
    */
   public ArrayList<Fruit> makeFruitList(String[] names, long[] prices, int[] quantities){
       ArrayList<Fruit> list = new ArrayList<Fruit>();
       for(int i=0; i< names.length; i++) {
    	   Fruit Fruit = new Fruit(names[i], prices[i], quantities[i]);
           list.add(Fruit);
       }
       return list;
   }
   
   /**
    * 과일 리스트를 간단한 엑셀 워크북 객체로 생성
    * @param list
    * @return 생성된 워크북
    */
   public XSSFWorkbook makeSimpleFruitExcelWorkbook(List<Fruit> list) {
       
	   XSSFWorkbook workbook = new XSSFWorkbook();
       
       // 시트 생성
       XSSFSheet sheet = workbook.createSheet("과일표");
       
       //시트 열 너비 설정
       sheet.setColumnWidth(0, 1500);
       sheet.setColumnWidth(0, 3000);
       sheet.setColumnWidth(0, 3000);
       sheet.setColumnWidth(0, 1500);
       
       
       // 헤더 행 생
       //Row headerRow = sheet.createRow(0);
       XSSFRow headerRow = sheet.createRow(0);
       
       // 해당 행의 첫번째 열 셀 생성
       //Cell headerCell = headerRow.createCell(0);
       //headerCell.setCellValue("번호");
       //Cell headerCell = (Cell) headerRow.createCell(0);
       //((SXSSFCell) headerCell).setCellValue("번호");
       // 해당 행의 두번째 열 셀 생성
       //headerCell = headerRow.createCell(1);
       //headerCell.setCellValue("과일이름");
       
       //headerCell = (Cell) headerRow.createCell(1);
       //((SXSSFCell) headerCell).setCellValue("과일이름");
       // 해당 행의 세번째 열 셀 생성
       //headerCell = headerRow.createCell(2);
       //headerCell.setCellValue("가격");
       
       //headerCell = (Cell) headerRow.createCell(2);
       //((SXSSFCell) headerCell).setCellValue("가격");
       // 해당 행의 네번째 열 셀 생성
       //headerCell = headerRow.createCell(3);
       //headerCell.setCellValue("수량");
       //headerCell = (Cell) headerRow.createCell(3);
       //((SXSSFCell) headerCell).setCellValue("수량");
       
       // 과일표 내용 행 및 셀 생성
       Row bodyRow = null;
       //Cell bodyCell = null;
       
       //for(int i=0; i<list.size(); i++) {
       for(int i=0; i<((ArrayList<Fruit>) list).size(); i++) {
    	   //importVO ImportVO = list.get(i);
    	   Fruit ImportVO = ((ArrayList<Fruit>) list).get(i);
          /* 
           // 행 생성
           //bodyRow = sheet.createRow(i+1);
           bodyRow = sheet.createRow(i+1);
           // 데이터 번호 표시
           bodyCell = bodyRow.createCell(0);
           bodyCell.setCellValue(i + 1);
           // 데이터 이름 표시
           bodyCell = bodyRow.createCell(1);
           bodyCell.setCellValue(fruit.getName());
           // 데이터 가격 표시
           bodyCell = bodyRow.createCell(2);
           bodyCell.setCellValue(fruit.getPrice());
           // 데이터 수량 표시
           bodyCell = bodyRow.createCell(3);
           bodyCell.setCellValue(fruit.getQuantity());
           */
       }
       
       return workbook;
   }
   
   /**
    * 생성한 엑셀 워크북을 컨트롤레에서 받게해줄 메소
    * @param list
    * @return
    */
   public XSSFWorkbook excelFileDownloadProcess(List<Fruit> list) {
       return this.makeSimpleFruitExcelWorkbook(list);
   }


}
