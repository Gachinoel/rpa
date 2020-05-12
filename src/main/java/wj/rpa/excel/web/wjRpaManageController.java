package wj.rpa.excel.web;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.io.OutputStream;
import java.net.URLEncoder;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

//import egovframework.let.cop.bbs.service.BoardMaster;
//import egovframework.let.cop.bbs.service.BoardMasterVO;
import wj.rpa.excel.service.ExcelMaster;
import wj.rpa.excel.service.ExcelMasterVO;
import wj.rpa.excel.service.wjRpaManageService;

import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.let.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.stringtemplate.v4.compiler.CodeGenerator.list_return;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 게시판 속성관리를 위한 컨트롤러  클래스
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
 *  2009.06.26	한성곤		2단계 기능 추가 (댓글관리, 만족도조사)
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Controller
public class wjRpaManageController {

	private static final int Map = 0;

	/** wjRpaManageService */
    @Resource(name = "wjRpaManageService")
    private wjRpaManageService wjRpaService;

    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    /** DefaultBeanValidator */
    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 운영자 권한을 확인한다.(로그인 여부를 확인한다.)
     *
     * @param boardMaster
     * @throws EgovBizException
     */
    protected boolean checkAuthority(ModelMap model) throws Exception {
    	// 사용자권한 처리
    	if(!EgovUserDetailsHelper.isAuthenticated()) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return false;
    	}else{
    		return true;
    	}
    }

    /**
     *RPA 목록을 조회한다.
     */
    //@RequestMapping("/wj/rpa/excel/selectRpaMain.do")
    @RequestMapping("wj/rpa/excel/selectRpaMain.do")
    public String selectRpaMainList(@ModelAttribute("searchVO") ExcelMasterVO boardMasterVO, ModelMap model) throws Exception {
    	
    	System.out.println("오냐?");
    	
    	if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
    	
    	System.out.println("확정구분"+ boardMasterVO.getSearchCnd());
    	System.out.println("시작일"+boardMasterVO.getSearchBgnDe());
    	System.out.println("종료일"+boardMasterVO.getSearchEndDe());
    	System.out.println("bl번호"+boardMasterVO.getSearchWrd());
    	
    	
		Map<String, Object> map = wjRpaService.selectRpaMainList(boardMasterVO);

		
		model.addAttribute("resultList", map.get("resultList"));
		
		return "rpa/excel/wjRpaList";
		
    }

    
	//    @RequestMapping("/cop/bbs/SelectBBSMasterInfsPop.do") KKK
	//    public String selectBBSMasterInfsPop(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
	@RequestMapping("/rpa/excel/SelectWjRpaExcle.do")
	public String SelectWjRpaExcle(@ModelAttribute("searchVO") ExcelMasterVO boardMasterVO, ModelMap model) throws Exception {
		if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
	
		return "rpa/excel/wjRpaExcel";
		                
	}
 

                          
	@RequestMapping("/cop/bbs/SelectInvoiceList.do")
	public void selectInvoiceList(@ModelAttribute("searchVO") ExcelMasterVO boardMasterVO, ModelMap model, HttpServletResponse response) throws Exception {

	
		//if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
		
		List<ExcelMasterVO> list = wjRpaService.selectInvoiceList(boardMasterVO);
		
		//Excel Down 시작
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//시트생성
		HSSFSheet sheet = workbook.createSheet("Invoice");
		
		
		//해더부분셀에 스타일을 주기위한 인스턴스 생성   
	    HSSFCellStyle cellStyle = workbook.createCellStyle();            
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                     //스타일인스턴스의 속성 셑팅           
	    cellStyle.setFillForegroundColor(HSSFCellStyle.BORDER_DASH_DOT);        //셀에 색깔 채우기   
	    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
	    cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);              //테두리 설정   
	    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);   
	    cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);   
	    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);   
	    HSSFFont font = workbook.createFont();                                    //폰트 조정 인스턴스 생성   
	    font.setBoldweight((short)700);        
	    cellStyle.setFont(font);
	    
	  //얇은 테두리를 위한 스타일 인스턴스 생성   
	    HSSFCellStyle cellStyle1 = workbook.createCellStyle();           
	    cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);   
	
	
		
		//행, 열, 열번호
		HSSFRow row = null;
		HSSFCell cell = null;
	    
		
		int rowCount = 0;
	    int cellCount = 0;
		
		// 헤더 생성
	    row = sheet.createRow(rowCount++);
	    
	    cell = row.createCell(0);
	    cell.setCellValue("INVOICD_No.");
	    sheet.setColumnWidth(0, 4000);
	    
	    cell = row.createCell(1);
	    cell.setCellValue("운송구분");
	    sheet.setColumnWidth(1, 4000);
	    
	    cell = row.createCell(2);
	    cell.setCellValue("서류송부일");
	    sheet.setColumnWidth(2, 4000);
	    
	    
	    cell = row.createCell(3);
	    cell.setCellValue("PO No.");
	    sheet.setColumnWidth(3, 4000);
	    
	    cell = row.createCell(4);
	    cell.setCellValue("품목 No. (발주항번)");
	    sheet.setColumnWidth(4, 4000);
	    
	    cell = row.createCell(5);
	    cell.setCellValue("자재코드 (품목코드)");
	    sheet.setColumnWidth(5, 4000);
	    
	    cell = row.createCell(6);
	    cell.setCellValue("입고수량");
	    sheet.setColumnWidth(6, 4000);
	    
	    cell = row.createCell(7);
	    cell.setCellValue("단가");
	    sheet.setColumnWidth(7, 4000);
	    
	    cell = row.createCell(8);
	    cell.setCellValue("가격단위");
	    sheet.setColumnWidth(8, 4000);
	    
	    cell = row.createCell(9);
	    cell.setCellValue("입고금액");
	    sheet.setColumnWidth(9, 4000);
	    
	    cell = row.createCell(10);
	    cell.setCellValue("화폐단위");
	    sheet.setColumnWidth(10, 4000);
	    
	    cell = row.createCell(11);
	    cell.setCellValue("HS 코드");
	    sheet.setColumnWidth(11, 4000);
	    
	    cell = row.createCell(12);
	    cell.setCellValue("포워드사");
	    sheet.setColumnWidth(12, 4000);
	    
	    cell = row.createCell(13);
	    cell.setCellValue("HOUSE B/L");
	    sheet.setColumnWidth(13, 4000);
	    
	    cell = row.createCell(14);
	    cell.setCellValue("인도조건");
	    sheet.setColumnWidth(14, 4000);
	    
	    
	    for(ExcelMasterVO vo : list){
	    	row = sheet.createRow(rowCount++);
	    	System.out.println("row값" + row);
	    	
	    	cellCount = 0;
	        row.createCell(cellCount++).setCellValue(vo.gett1InNo());
	        row.createCell(cellCount++).setCellValue(vo.gett1Carry());
	        row.createCell(cellCount++).setCellValue(vo.gett3SSDt());
	        
	        row.createCell(cellCount++).setCellValue(vo.gett1PoNo());
	        row.createCell(cellCount++).setCellValue(vo.gett1PoLineNo());
	        row.createCell(cellCount++).setCellValue(vo.gett1ItemCd());
	        row.createCell(cellCount++).setCellValue(vo.gett1Qty());
	        
	        //cell.getNumericCellValue();
	        row.createCell(cellCount++).setCellValue(vo.gett4DanGa());
	        row.createCell(cellCount++).setCellValue(vo.gett4DanGa2());
	        row.createCell(cellCount++).setCellValue(vo.gett4GeumAeg());
	        row.createCell(cellCount++).setCellValue(vo.gett4JeCurDw());
	        row.createCell(cellCount++).setCellValue(vo.gett4SebeonCd());
	        
	        row.createCell(cellCount++).setCellValue(vo.gett3GgCoCd2());
	        row.createCell(cellCount++).setCellValue(vo.gett3HBlNo());
	        row.createCell(cellCount++).setCellValue(vo.gett4IndoJk());
	        
	    }
	 
	    String fileName ="인보이스";
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=test.xls");
	
	    
	   OutputStream os = null;
	   //HSSFWorkbook workbook = null;
	   
	   try {
	       //workbook = (HSSFWorkbook) model.get("workbook");
	       //os = response.getOutputStream();
	       
	       // 파일생성
	       workbook.write(response.getOutputStream());
	   }catch (Exception e) {
	       e.printStackTrace();
	   } finally {
	       if(workbook != null) {
	           try {
	//               workbook.close();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	       
	       if(os != null) {
	           try {
	               os.close();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	   }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/cop/bbs/SelectCargoList.do")
	public void selectCargoList(@ModelAttribute("searchVO") ExcelMasterVO boardMasterVO, ModelMap model, HttpServletResponse response) throws Exception {

	
		//if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
		
		List<ExcelMasterVO> list = wjRpaService.selectCargoList(boardMasterVO);
		
		//Excel Down 시작
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//시트생성
		HSSFSheet sheet = workbook.createSheet("Cargo");
		
		
		//해더부분셀에 스타일을 주기위한 인스턴스 생성   
	    HSSFCellStyle cellStyle = workbook.createCellStyle();            
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                     //스타일인스턴스의 속성 셑팅           
	    cellStyle.setFillForegroundColor(HSSFCellStyle.BORDER_DASH_DOT);        //셀에 색깔 채우기   
	    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
	    cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);              //테두리 설정   
	    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);   
	    cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);   
	    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);   
	    HSSFFont font = workbook.createFont();                                    //폰트 조정 인스턴스 생성   
	    font.setBoldweight((short)700);        
	    cellStyle.setFont(font);
	    
	  //얇은 테두리를 위한 스타일 인스턴스 생성   
	    HSSFCellStyle cellStyle1 = workbook.createCellStyle();           
	    cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);   
	
	
		
		//행, 열, 열번호
		HSSFRow row = null;
		HSSFCell cell = null;
	    
		
		int rowCount = 0;
	    int cellCount = 0;
		
		// 헤더 생성
	    row = sheet.createRow(rowCount++);
	    
	    cell = row.createCell(0);
	    cell.setCellValue("B/L일자");
	    sheet.setColumnWidth(0, 4000);
	    
	    cell = row.createCell(1);
	    cell.setCellValue("부보일자");
	    sheet.setColumnWidth(1, 4000);
	    
	    cell = row.createCell(2);
	    cell.setCellValue("보험관리번호");
	    sheet.setColumnWidth(2, 4000);
	    
	    
	    cell = row.createCell(3);
	    cell.setCellValue("부보요율");
	    sheet.setColumnWidth(3, 4000);
	    
	    cell = row.createCell(4);
	    cell.setCellValue("적용환율");
	    sheet.setColumnWidth(4, 4000);
	    
	    cell = row.createCell(5);
	    cell.setCellValue("통화단위");
	    sheet.setColumnWidth(5, 4000);
	    
	    cell = row.createCell(6);
	    cell.setCellValue("증권발급일자");
	    sheet.setColumnWidth(6, 4000);
	    
	    cell = row.createCell(7);
	    cell.setCellValue("보험료");
	    sheet.setColumnWidth(7, 4000);
	    
	    cell = row.createCell(8);
	    cell.setCellValue("INV");
	    sheet.setColumnWidth(8, 4000);
	    
	    cell = row.createCell(9);
	    cell.setCellValue("HOUSE B/L");
	    sheet.setColumnWidth(9, 4000);
	    
	    
	    
	    for(ExcelMasterVO vo : list){
	    	row = sheet.createRow(rowCount++);
	    	System.out.println("row값" + row);
	    	
	    	cellCount = 0;
	        row.createCell(cellCount++).setCellValue(vo.gett3BlDt());
	        row.createCell(cellCount++).setCellValue(vo.gett3SSDt());
	        row.createCell(cellCount++).setCellValue(vo.gett2StockNo());
	        
	        row.createCell(cellCount++).setCellValue(vo.gett2JyRt());
	        row.createCell(cellCount++).setCellValue(vo.gett4AACUERt());
	        row.createCell(cellCount++).setCellValue(vo.gett4AACDw());
	        row.createCell(cellCount++).setCellValue(vo.gett3SSDt());
	        
	        //cell.getNumericCellValue();
	        row.createCell(cellCount++).setCellValue(vo.gett2CKEIFee());
	        row.createCell(cellCount++).setCellValue(vo.gett3InNo());
	        row.createCell(cellCount++).setCellValue(vo.gett3HBlNo());   
	    }
	 
	    String fileName ="적하보험";
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=test.xls");
	
	    
	   OutputStream os = null;
	   //HSSFWorkbook workbook = null;
	   
	   try {
	       //workbook = (HSSFWorkbook) model.get("workbook");
	       //os = response.getOutputStream();
	       
	       // 파일생성
	       workbook.write(response.getOutputStream());
	   }catch (Exception e) {
	       e.printStackTrace();
	   } finally {
	       if(workbook != null) {
	           try {
	//               workbook.close();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	       
	       if(os != null) {
	           try {
	               os.close();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	   }
	}
	
	
	
	@RequestMapping("/cop/bbs/SelectBlList.do")
	public void selectBlList(@ModelAttribute("searchVO") ExcelMasterVO boardMasterVO, ModelMap model, HttpServletResponse response) throws Exception {

	
		//if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
		
		List<ExcelMasterVO> list = wjRpaService.selectPassList(boardMasterVO);
		
		//Excel Down 시작
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//시트생성
		HSSFSheet sheet = workbook.createSheet("Cargo");
		
		
		//해더부분셀에 스타일을 주기위한 인스턴스 생성   
	    HSSFCellStyle cellStyle = workbook.createCellStyle();            
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                     //스타일인스턴스의 속성 셑팅           
	    cellStyle.setFillForegroundColor(HSSFCellStyle.BORDER_DASH_DOT);        //셀에 색깔 채우기   
	    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
	    cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);              //테두리 설정   
	    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);   
	    cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);   
	    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);   
	    HSSFFont font = workbook.createFont();                                    //폰트 조정 인스턴스 생성   
	    font.setBoldweight((short)700);        
	    cellStyle.setFont(font);
	    
	  //얇은 테두리를 위한 스타일 인스턴스 생성   
	    HSSFCellStyle cellStyle1 = workbook.createCellStyle();           
	    cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);   
	
	
		
		//행, 열, 열번호
		HSSFRow row = null;
		HSSFCell cell = null;
	    
		
		int rowCount = 0;
	    int cellCount = 0;
		
		// 헤더 생성
	    row = sheet.createRow(rowCount++);
	    
	    cell = row.createCell(0);
	    cell.setCellValue("HOUSE B/L");
	    sheet.setColumnWidth(0, 4000);
	    
	    cell = row.createCell(1);
	    cell.setCellValue("B/L선적일자");
	    sheet.setColumnWidth(1, 4000);
	    
	    cell = row.createCell(2);
	    cell.setCellValue("ETD(출항예정일");
	    sheet.setColumnWidth(2, 4000);
	    
	    
	    cell = row.createCell(3);
	    cell.setCellValue("ETA (입항예정일)");
	    sheet.setColumnWidth(3, 4000);
	    
	    cell = row.createCell(4);
	    cell.setCellValue("선적국");
	    sheet.setColumnWidth(4, 4000);
	    
	    cell = row.createCell(5);
	    cell.setCellValue("도착국");
	    sheet.setColumnWidth(5, 4000);
	    
	    cell = row.createCell(6);
	    cell.setCellValue("선적항");
	    sheet.setColumnWidth(6, 4000);
	    
	    cell = row.createCell(7);
	    cell.setCellValue("도착항");
	    sheet.setColumnWidth(7, 4000);
	    
	    cell = row.createCell(8);
	    cell.setCellValue("FLT / VSSL (편명 및 선명)");
	    sheet.setColumnWidth(8, 4000);
	    
	    cell = row.createCell(9);
	    cell.setCellValue("20FT Qty");
	    sheet.setColumnWidth(9, 4000);
	    
	    cell = row.createCell(10);
	    cell.setCellValue("40FT Qty");
	    sheet.setColumnWidth(10, 4000);
	    
	    
	    
	    
	    
	    
	    cell = row.createCell(11);
	    cell.setCellValue("운송구분");
	    sheet.setColumnWidth(11, 4000);
	    
	    cell = row.createCell(12);
	    cell.setCellValue("총포장갯수");
	    sheet.setColumnWidth(12, 4000);
	    
	    cell = row.createCell(13);
	    cell.setCellValue("순중량/Net Weight");
	    sheet.setColumnWidth(13, 4000);
	    
	    cell = row.createCell(14);
	    cell.setCellValue("총중량/Gross Weight");
	    sheet.setColumnWidth(14, 4000);
	    
	    cell = row.createCell(15);
	    cell.setCellValue("서류송부일");
	    sheet.setColumnWidth(15, 4000);
	    
	    cell = row.createCell(16);
	    cell.setCellValue("선적서류 송부처");
	    sheet.setColumnWidth(16, 4000);
	    
	    cell = row.createCell(17);
	    cell.setCellValue("실입항일");
	    sheet.setColumnWidth(17, 4000);
	    
	    cell = row.createCell(18);
	    cell.setCellValue("입고예정일");
	    sheet.setColumnWidth(18, 4000);
	    
	    
	    
	    
	    for(ExcelMasterVO vo : list){
	    	row = sheet.createRow(rowCount++);
	    	System.out.println("row값" + row);
	    	
	    	cellCount = 0;
	        row.createCell(cellCount++).setCellValue(vo.gett3HblNo());
	        row.createCell(cellCount++).setCellValue(vo.gett3BSDt());
	        row.createCell(cellCount++).setCellValue(vo.gett3EtaDt());
	        
	        row.createCell(cellCount++).setCellValue(vo.gett3EtaDt());
	        row.createCell(cellCount++).setCellValue(vo.gett3SJGCd());
	        row.createCell(cellCount++).setCellValue(vo.gett3DJGCd());
	        row.createCell(cellCount++).setCellValue(vo.gett3SJHCd());
	        
	        //cell.getNumericCellValue();
	        row.createCell(cellCount++).setCellValue(vo.gett3DCHCd());
	        row.createCell(cellCount++).setCellValue(vo.gett3FVNm());
	        row.createCell(cellCount++).setCellValue(vo.gett3C20Qt());   
	    
	        row.createCell(cellCount++).setCellValue(vo.gett3C40Qt());
	        row.createCell(cellCount++).setCellValue(vo.gett3USgb());
	        row.createCell(cellCount++).setCellValue(vo.gett3CPJEa());   
	        
	        row.createCell(cellCount++).setCellValue(vo.gett3NWg());
	        row.createCell(cellCount++).setCellValue(vo.gett3GWg());
	        row.createCell(cellCount++).setCellValue(vo.gett3SLSBDt());   
	    
	        row.createCell(cellCount++).setCellValue(vo.gett3SJSLSBCCd());
	        row.createCell(cellCount++).setCellValue(vo.gett3SIHDt());
	        row.createCell(cellCount++).setCellValue(vo.gett3IGYJDt());   
	    
	    
	    
	    
	    
	    }
	 
	    String fileName ="BL";
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=test.xls");
	
	    
	   OutputStream os = null;
	   //HSSFWorkbook workbook = null;
	   
	   try {
	       //workbook = (HSSFWorkbook) model.get("workbook");
	       //os = response.getOutputStream();
	       
	       // 파일생성
	       workbook.write(response.getOutputStream());
	   }catch (Exception e) {
	       e.printStackTrace();
	   } finally {
	       if(workbook != null) {
	           try {
	//               workbook.close();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	       
	       if(os != null) {
	           try {
	               os.close();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	   }
	}
	
	
	
	
	@RequestMapping("/cop/bbs/SelectPassList.do")
	public void selectPassList(@ModelAttribute("searchVO") ExcelMasterVO boardMasterVO, ModelMap model, HttpServletResponse response) throws Exception {

	
		//if (!checkAuthority(model)) return "cmm/uat/uia/EgovLoginUsr";	// server-side 권한 확인
		
		List<ExcelMasterVO> list = wjRpaService.selectPassList(boardMasterVO);
		
		//Excel Down 시작
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//시트생성
		HSSFSheet sheet = workbook.createSheet("Cargo");
		
		
		//해더부분셀에 스타일을 주기위한 인스턴스 생성   
	    HSSFCellStyle cellStyle = workbook.createCellStyle();            
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                     //스타일인스턴스의 속성 셑팅           
	    cellStyle.setFillForegroundColor(HSSFCellStyle.BORDER_DASH_DOT);        //셀에 색깔 채우기   
	    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
	    cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);              //테두리 설정   
	    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);   
	    cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);   
	    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);   
	    HSSFFont font = workbook.createFont();                                    //폰트 조정 인스턴스 생성   
	    font.setBoldweight((short)700);        
	    cellStyle.setFont(font);
	    
	  //얇은 테두리를 위한 스타일 인스턴스 생성   
	    HSSFCellStyle cellStyle1 = workbook.createCellStyle();           
	    cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);   
	    cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);   
	
	
		
		//행, 열, 열번호
		HSSFRow row = null;
		HSSFCell cell = null;
	    
		
		int rowCount = 0;
	    int cellCount = 0;
		
		// 헤더 생성
	    row = sheet.createRow(rowCount++);
	    
	    cell = row.createCell(0);
	    cell.setCellValue("HOUSE B/L");
	    sheet.setColumnWidth(0, 4000);
	    
	    cell = row.createCell(1);
	    cell.setCellValue("통관요청일");
	    sheet.setColumnWidth(1, 4000);
	    
	    cell = row.createCell(2);
	    cell.setCellValue("신고자");
	    sheet.setColumnWidth(2, 4000);
	    
	    
	    cell = row.createCell(3);
	    cell.setCellValue("신고번호");
	    sheet.setColumnWidth(3, 4000);
	    
	    cell = row.createCell(4);
	    cell.setCellValue("세관.과-징수");
	    sheet.setColumnWidth(4, 4000);
	    
	    cell = row.createCell(5);
	    cell.setCellValue("신고수리일");
	    sheet.setColumnWidth(5, 4000);
	    
	    cell = row.createCell(6);
	    cell.setCellValue("BL 사항");
	    sheet.setColumnWidth(6, 4000);
	    
	    cell = row.createCell(7);
	    cell.setCellValue("총과세가격 $");
	    sheet.setColumnWidth(7, 4000);
	    
	    cell = row.createCell(8);
	    cell.setCellValue("총과세가격 원");
	    sheet.setColumnWidth(8, 4000);
	    
	    cell = row.createCell(9);
	    cell.setCellValue("부가가치세 과표");
	    sheet.setColumnWidth(9, 4000);
	    
	    
	    cell = row.createCell(10);
	    cell.setCellValue("운임)");
	    sheet.setColumnWidth(10, 4000);
	    
	    cell = row.createCell(11);
	    cell.setCellValue("환율)");
	    sheet.setColumnWidth(11, 4000);
	    
	    cell = row.createCell(12);
	    cell.setCellValue("가산금액)");
	    sheet.setColumnWidth(12, 4000);
	    
	    cell = row.createCell(13);
	    cell.setCellValue("총관세)");
	    sheet.setColumnWidth(13, 4000);
	    
	    cell = row.createCell(14);
	    cell.setCellValue("총부가세");
	    sheet.setColumnWidth(14, 4000);
	    
	    cell = row.createCell(15);
	    cell.setCellValue("수입신고필증");
	    sheet.setColumnWidth(15, 4000);
	    
	    cell = row.createCell(16);
	    cell.setCellValue("PO번호");
	    sheet.setColumnWidth(16, 4000);
	    
	    
	    
	    cell = row.createCell(17);
	    cell.setCellValue("품목코드");
	    sheet.setColumnWidth(17, 4000);
	    
	    
	    cell = row.createCell(18);
	    cell.setCellValue("란 정보");
	    sheet.setColumnWidth(18, 4000);
	    
	    
	    
	    cell = row.createCell(19);
	    cell.setCellValue("행 정보");
	    sheet.setColumnWidth(19, 4000);
	    
	    cell = row.createCell(20);
	    cell.setCellValue("관세율");
	    sheet.setColumnWidth(20, 4000);
	    
	    cell = row.createCell(21);
	    cell.setCellValue("관세");
	    sheet.setColumnWidth(21, 4000);
	    
	    
	    
	    
	    for(ExcelMasterVO vo : list){
	    	row = sheet.createRow(rowCount++);
	    	System.out.println("row값" + row);
	    	
	    	cellCount = 0;
	        row.createCell(cellCount++).setCellValue(vo.gett3HBlNo());
	        row.createCell(cellCount++).setCellValue("");//통관요청일
	        row.createCell(cellCount++).setCellValue("");//신고자
	        row.createCell(cellCount++).setCellValue(vo.gett4SingoNo());
	        row.createCell(cellCount++).setCellValue(vo.gett4SGSG());
	        row.createCell(cellCount++).setCellValue(vo.gett4SingoDt());
	        row.createCell(cellCount++).setCellValue("");//bl사항
	        row.createCell(cellCount++).setCellValue(vo.gett4CifUsd());
	        row.createCell(cellCount++).setCellValue(vo.gett4CifKrw());
	        row.createCell(cellCount++).setCellValue(vo.gett4BGSGP());
	        
	        
	        row.createCell(cellCount++).setCellValue("");//운임
	        row.createCell(cellCount++).setCellValue("");//환율
	        row.createCell(cellCount++).setCellValue("");//가산금액
	        row.createCell(cellCount++).setCellValue(vo.gett4GwanSe());
	        row.createCell(cellCount++).setCellValue(vo.gett4Bugase());
	        row.createCell(cellCount++).setCellValue("");//수입신고필증
	        row.createCell(cellCount++).setCellValue(vo.gett1PoNo());
	        row.createCell(cellCount++).setCellValue(vo.gett1PoLineNo());
	        row.createCell(cellCount++).setCellValue(vo.gett4LanNo2());   
	        row.createCell(cellCount++).setCellValue(vo.gett4HeangNo());
	        
	        row.createCell(cellCount++).setCellValue(vo.gett4GSY());
	        row.createCell(cellCount++).setCellValue(vo.gett4SJGSE());   
	    
	    
	    
	    
	    }
	 
	    String fileName ="BL";
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=test.xls");
	
	    
	   OutputStream os = null;
	   //HSSFWorkbook workbook = null;
	   
	   try {
	       //workbook = (HSSFWorkbook) model.get("workbook");
	       //os = response.getOutputStream();
	       
	       // 파일생성
	       workbook.write(response.getOutputStream());
	   }catch (Exception e) {
	       e.printStackTrace();
	   } finally {
	       if(workbook != null) {
	           try {
	//               workbook.close();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	       
	       if(os != null) {
	           try {
	               os.close();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	   }
	}
 
    
   

}
