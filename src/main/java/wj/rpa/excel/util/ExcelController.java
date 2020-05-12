package wj.rpa.excel.util;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.cop.bbs.service.BoardMasterVO;




import egovframework.let.cop.bbs.service.BoardMaster;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService;


import java.util.ArrayList;
import javax.enterprise.inject.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import antlr.collections.List;
import java.util.Locale;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

@Controller
public class ExcelController {
	
	/** EgovBBSAttributeManageService */
    @Resource(name = "EgovBBSAttributeManageService")
    private EgovBBSAttributeManageService bbsAttrbService;

	
    

	
	
	//@RequestMapping(value = "/excelPoi.do")
    public void excelPoi(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, HttpServletResponse response, ModelMap model) throws Exception {
/*
    	System.out.println("cont exe");
        
    	// 게시판 목록조회
    	Map<String, Object> map = bbsAttrbService.SelectInvoiceList(boardMasterVO);

        // 워크북 생성

    	HSSFWorkbook objWorkBook = new HSSFWorkbook();
        HSSFSheet objSheet = null;
        HSSFRow objRow = null;
        HSSFCell objCell = null;       //셀 생성


        //제목 폰트
        HSSFFont font = objWorkBook.createFont();
        font.setFontHeightInPoints((short)9);
        font.setBoldweight((short)font.BOLDWEIGHT_BOLD);
        font.setFontName("맑은고딕");

        //제목 스타일에 폰트 적용, 정렬
        HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
        styleHd.setFont(font);
        styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleHd.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);

        objSheet = objWorkBook.createSheet("첫번째 시트");     //워크시트 생성

        
        Map map2 = (Map)map.get("map");
		String pwd = (String)map2.get("PWD");
        System.out.println(pwd);
        
        
        // 1행
        objRow = objSheet.createRow(0);
        objRow.setHeight ((short) 0x150);

        objCell = objRow.createCell(0);
        objCell.setCellValue("번호");
        objCell.setCellStyle(styleHd);

        objCell = objRow.createCell(1);
        objCell.setCellValue("이름");
        objCell.setCellStyle(styleHd);

        // 2행
        objRow = objSheet.createRow(1);
        objRow.setHeight ((short) 0x150);

        objCell = objRow.createCell(0);
        objCell.setCellValue("1");
        objCell.setCellStyle(styleHd);

        objCell = objRow.createCell(1);
        objCell.setCellValue("홍길동");
        objCell.setCellStyle(styleHd);


     
        response.setContentType("Application/Msexcel");
        response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("테스트","UTF-8")+".xls");

        OutputStream fileOut  = response.getOutputStream();
        objWorkBook.write(fileOut);
        fileOut.close();

        response.getOutputStream().flush();
        response.getOutputStream().close();
        System.out.println("돼?");
        */
      }


}
