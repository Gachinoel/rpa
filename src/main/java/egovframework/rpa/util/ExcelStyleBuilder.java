
package egovframework.rpa.util;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class ExcelStyleBuilder {
	

	public Map<String, CellStyle> createExcelStylesXSS(SXSSFWorkbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        DataFormat fmt = wb.createDataFormat();

        Font fontTitle = wb.createFont();
        fontTitle.setFontName("맑은 고딕");
        fontTitle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        fontTitle.setFontHeightInPoints((short)15);
        fontTitle.setUnderline(XSSFFont.U_DOUBLE);

        Font fontNomal = wb.createFont();
        fontNomal.setFontName("맑은 고딕");
        fontNomal.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        fontNomal.setFontHeightInPoints((short)10);
        fontNomal.setUnderline(XSSFFont.U_NONE);

        Font fontNomalBlue = wb.createFont();
        fontNomalBlue.setFontName("맑은 고딕");
        fontNomalBlue.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        fontNomalBlue.setFontHeightInPoints((short)10);
        fontNomalBlue.setUnderline(XSSFFont.U_NONE);
        fontNomalBlue.setColor(IndexedColors.BLUE.getIndex());

        Font fontNomalRed = wb.createFont();
        fontNomalRed.setFontName("맑은 고딕");
        fontNomalRed.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        fontNomalRed.setFontHeightInPoints((short)10);
        fontNomalRed.setUnderline(XSSFFont.U_NONE);
        fontNomalRed.setColor(IndexedColors.RED.getIndex());

        Font fontBold = wb.createFont();
        fontBold.setFontName("맑은 고딕");
        fontBold.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        fontBold.setFontHeightInPoints((short)10);
        fontBold.setUnderline(XSSFFont.U_NONE);

        Font fontNomalLine = wb.createFont();
        fontNomalLine.setFontName("맑은 고딕");
        fontNomalLine.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        fontNomalLine.setFontHeightInPoints((short)10);
        fontNomalLine.setUnderline(XSSFFont.U_SINGLE);

        //공란
        CellStyle blankCell = wb.createCellStyle();
        blankCell.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        blankCell.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        blankCell.setDataFormat(fmt.getFormat("@"));
        blankCell.setBorderTop(CellStyle.BORDER_NONE);
        blankCell.setBorderBottom(CellStyle.BORDER_NONE);
        blankCell.setBorderLeft(CellStyle.BORDER_NONE);
        blankCell.setBorderRight(CellStyle.BORDER_NONE);
        blankCell.setFont(fontNomal);
        styles.put("blankcell", blankCell);

        //제목
        CellStyle styleNoneLine = wb.createCellStyle();
        styleNoneLine.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        styleNoneLine.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        styleNoneLine.setDataFormat(fmt.getFormat("@"));
        styleNoneLine.setWrapText(true);
        styleNoneLine.setBorderTop(CellStyle.BORDER_NONE);
        styleNoneLine.setBorderBottom(CellStyle.BORDER_NONE);
        styleNoneLine.setBorderLeft(CellStyle.BORDER_NONE);
        styleNoneLine.setBorderRight(CellStyle.BORDER_NONE);
        styleNoneLine.setFont(fontTitle);
        styles.put("title", styleNoneLine);

        //부제목 왼쪽정렬
        CellStyle subTitleL = wb.createCellStyle();
        subTitleL.setAlignment(CellStyle.ALIGN_LEFT); //가로 정렬
        subTitleL.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        subTitleL.setDataFormat(fmt.getFormat("@"));
        subTitleL.setWrapText(true);
        
        subTitleL.setBorderTop(CellStyle.BORDER_NONE);
        subTitleL.setBorderBottom(CellStyle.BORDER_NONE);
        subTitleL.setBorderLeft(CellStyle.BORDER_NONE);
        subTitleL.setBorderRight(CellStyle.BORDER_NONE);
        subTitleL.setFont(fontNomal);
        styles.put("l_subtitle", subTitleL);

        //부제목 가운데정렬
        CellStyle subTitleC = wb.createCellStyle();
        subTitleC.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        subTitleC.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        subTitleC.setDataFormat(fmt.getFormat("@"));
        subTitleC.setWrapText(true);
        
        subTitleC.setBorderTop(CellStyle.BORDER_NONE);
        subTitleC.setBorderBottom(CellStyle.BORDER_NONE);
        subTitleC.setBorderLeft(CellStyle.BORDER_NONE);
        subTitleC.setBorderRight(CellStyle.BORDER_NONE);
        subTitleC.setFont(fontNomal);
        styles.put("c_subtitle", subTitleC);

        //부제목 오른쪽 정렬
        CellStyle subTitleR = wb.createCellStyle();
        subTitleR.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        subTitleR.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        subTitleR.setDataFormat(fmt.getFormat("@"));
        subTitleR.setWrapText(true);
        subTitleR.setBorderTop(CellStyle.BORDER_NONE);
        subTitleR.setBorderBottom(CellStyle.BORDER_NONE);
        subTitleR.setBorderLeft(CellStyle.BORDER_NONE);
        subTitleR.setBorderRight(CellStyle.BORDER_NONE);
        subTitleR.setFont(fontNomal);
        styles.put("r_subtitle", subTitleR);

        //셀타이틀 가운데정렬
        CellStyle cellTitle = wb.createCellStyle();
        cellTitle.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellTitle.setDataFormat(fmt.getFormat("@"));
        cellTitle.setWrapText(true);
        cellTitle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellTitle.setBorderTop(CellStyle.BORDER_THIN);
        cellTitle.setBorderBottom(CellStyle.BORDER_THIN);
        cellTitle.setBorderLeft(CellStyle.BORDER_THIN);
        cellTitle.setBorderRight(CellStyle.BORDER_THIN);
        cellTitle.setFont(fontNomal);
        styles.put("celltitle", cellTitle);

        //본문 첫셀 가운데 정렬
        CellStyle cellContSL = wb.createCellStyle();
        cellContSL.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContSL.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSL.setDataFormat(fmt.getFormat("@"));
        cellContSL.setWrapText(true);
        cellContSL.setBorderTop(CellStyle.BORDER_NONE);
        cellContSL.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContSL.setBorderLeft(CellStyle.BORDER_THIN);
        cellContSL.setBorderRight(CellStyle.BORDER_NONE);
        cellContSL.setFont(fontNomal);
        styles.put("sl_cellcont", cellContSL);

        //본문 마지막셀 텍스트 가운데정렬
        CellStyle cellContERTXT = wb.createCellStyle();
        cellContERTXT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContERTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContERTXT.setDataFormat(fmt.getFormat("@"));
        cellContERTXT.setWrapText(true);
        cellContERTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContERTXT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContERTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContERTXT.setBorderRight(CellStyle.BORDER_THIN);
        cellContERTXT.setFont(fontNomal);
        styles.put("ertxt_cellcont", cellContERTXT);

        //본문 마지막셀 텍스트 왼쪽정렬
        CellStyle cellContELTXT = wb.createCellStyle();
        cellContELTXT.setAlignment(CellStyle.ALIGN_LEFT); //가로 정렬
        cellContELTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContELTXT.setDataFormat(fmt.getFormat("@"));
        cellContELTXT.setWrapText(true);
        cellContELTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContELTXT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContELTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContELTXT.setBorderRight(CellStyle.BORDER_THIN);
        cellContELTXT.setFont(fontNomal);
        styles.put("eltxt_cellcont", cellContELTXT);

        //본문 마지막셀 정수 가운데정렬
        CellStyle cellContERINT = wb.createCellStyle();
        cellContERINT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContERINT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContERINT.setDataFormat(fmt.getFormat("* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-"));
        cellContERINT.setBorderTop(CellStyle.BORDER_NONE);
        cellContERINT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContERINT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContERINT.setBorderRight(CellStyle.BORDER_THIN);
        cellContERINT.setFont(fontNomal);
        styles.put("erint_cellcont", cellContERINT);

        //본문 마지막셀 실수 가운데정렬
        CellStyle cellContERFLO = wb.createCellStyle();
        cellContERFLO.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContERFLO.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContERFLO.setDataFormat(fmt.getFormat("* #,##0.00_-;-* #,##0.00_-;_-* \"-\"_-;_-@_-"));
        cellContERFLO.setBorderTop(CellStyle.BORDER_NONE);
        cellContERFLO.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContERFLO.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContERFLO.setBorderRight(CellStyle.BORDER_THIN);
        cellContERFLO.setFont(fontNomal);
        styles.put("erfloat_cellcont", cellContERFLO);

        //본문 마지막셀 텍스트 오른쪽정렬
        CellStyle cellContELRTXT = wb.createCellStyle();
        cellContELRTXT.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContELRTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContELRTXT.setDataFormat(fmt.getFormat("@"));
        cellContELRTXT.setWrapText(true);
        cellContELRTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContELRTXT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContELRTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContELRTXT.setBorderRight(CellStyle.BORDER_DOTTED);
        cellContELRTXT.setFont(fontNomal);
        styles.put("elrtxt_cellcont", cellContELRTXT);

        //본문 마지막셀 정수 가운데정렬
        CellStyle cellContERRINT = wb.createCellStyle();
        cellContERRINT.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContERRINT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContERRINT.setDataFormat(fmt.getFormat("* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-"));
        cellContERRINT.setBorderTop(CellStyle.BORDER_NONE);
        cellContERRINT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContERRINT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContERRINT.setBorderRight(CellStyle.BORDER_THIN);
        cellContERRINT.setFont(fontNomal);
        styles.put("errint_cellcont", cellContERRINT);

        //본문 마지막셀 실수 가운데정렬
        CellStyle cellContERRFLO = wb.createCellStyle();
        cellContERRFLO.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContERRFLO.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContERRFLO.setDataFormat(fmt.getFormat("* #,##0.00_-;-* #,##0.00_-;_-* \"-\"_-;_-@_-"));
        cellContERRFLO.setBorderTop(CellStyle.BORDER_NONE);
        cellContERRFLO.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContERRFLO.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContERRFLO.setBorderRight(CellStyle.BORDER_THIN);
        cellContERRFLO.setFont(fontNomal);
        styles.put("errfloat_cellcont", cellContERRFLO);

        //본문 셀 문자 왼쪽정렬
        CellStyle cellContCLTXT = wb.createCellStyle();
        cellContCLTXT.setAlignment(CellStyle.ALIGN_LEFT); //가로 정렬
        cellContCLTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCLTXT.setDataFormat(fmt.getFormat("@"));
        cellContCLTXT.setWrapText(true);
        cellContCLTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContCLTXT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCLTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCLTXT.setBorderRight(CellStyle.BORDER_NONE);
        cellContCLTXT.setFont(fontNomal);
        styles.put("cltxt_cellcont", cellContCLTXT);

        //본문 셀 문자 가운데정렬
        CellStyle cellContCCTXT = wb.createCellStyle();
        cellContCCTXT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContCCTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCCTXT.setDataFormat(fmt.getFormat("@"));
        cellContCCTXT.setWrapText(true);
        cellContCCTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContCCTXT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCCTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCCTXT.setBorderRight(CellStyle.BORDER_NONE);
        cellContCCTXT.setFont(fontNomal);
        styles.put("cctxt_cellcont", cellContCCTXT);

        //본문 셀 문자 가운데정렬
        CellStyle cellContCTTXT = wb.createCellStyle();
        cellContCTTXT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContCTTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCTTXT.setDataFormat(fmt.getFormat("@"));
        cellContCTTXT.setWrapText(true);
        cellContCTTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContCTTXT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCTTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCTTXT.setBorderRight(CellStyle.BORDER_NONE);
        cellContCTTXT.setFont(fontNomal);
        styles.put("cttxt_cellcont", cellContCTTXT);

        //본문 셀 문자 오른쪽정렬
        CellStyle cellContCRTXT = wb.createCellStyle();
        cellContCRTXT.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContCRTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCRTXT.setDataFormat(fmt.getFormat("@"));
        cellContCRTXT.setWrapText(true);
        cellContCRTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContCRTXT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCRTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCRTXT.setBorderRight(CellStyle.BORDER_NONE);
        cellContCRTXT.setFont(fontNomal);
        styles.put("crtxt_cellcont", cellContCRTXT);

        //본문 셀 정수 가운데정렬
        CellStyle cellContCCINT = wb.createCellStyle();
        cellContCCINT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContCCINT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCCINT.setDataFormat(fmt.getFormat("* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-"));
        cellContCCINT.setBorderTop(CellStyle.BORDER_NONE);
        cellContCCINT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCCINT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCCINT.setBorderRight(CellStyle.BORDER_NONE);
        cellContCCINT.setFont(fontNomal);
        styles.put("ccint_cellcont", cellContCCINT);

        //본문 셀 정수 오른쪽정렬
        CellStyle cellContCRINT = wb.createCellStyle();
        cellContCRINT.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContCRINT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCRINT.setDataFormat(fmt.getFormat("* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-"));
        cellContCRINT.setBorderTop(CellStyle.BORDER_NONE);
        cellContCRINT.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCRINT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCRINT.setBorderRight(CellStyle.BORDER_NONE);
        cellContCRINT.setFont(fontNomal);
        styles.put("crint_cellcont", cellContCRINT);

        //본문 셀 실수 가운데정렬
        CellStyle cellContCCFLO = wb.createCellStyle();
        cellContCCFLO.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContCCFLO.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCCFLO.setDataFormat(fmt.getFormat("* #,##0.00_-;-* #,##0.00_-;_-* \"-\"_-;_-@_-"));
        cellContCCFLO.setBorderTop(CellStyle.BORDER_NONE);
        cellContCCFLO.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCCFLO.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCCFLO.setBorderRight(CellStyle.BORDER_NONE);
        cellContCCFLO.setFont(fontNomal);
        styles.put("ccfloat_cellcont", cellContCCFLO);

        //본문 셀 실수 오른쪽정렬
        CellStyle cellContCRFLO = wb.createCellStyle();
        cellContCRFLO.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContCRFLO.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCRFLO.setDataFormat(fmt.getFormat("* #,##0.00_-;-* #,##0.00_-;_-* \"-\"_-;_-@_-"));
        cellContCRFLO.setBorderTop(CellStyle.BORDER_NONE);
        cellContCRFLO.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCRFLO.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCRFLO.setBorderRight(CellStyle.BORDER_NONE);
        cellContCRFLO.setFont(fontNomal);
        styles.put("crfloat_cellcont", cellContCRFLO);

        //본문 셀 문자 오른쪽정렬 균등
        CellStyle cellContCRTXTSame = wb.createCellStyle();
        cellContCRTXTSame.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContCRTXTSame.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCRTXTSame.setDataFormat(fmt.getFormat("[Red]▲#,##0;[Blue]▼#,##0;\"-\";_-@_-"));
        cellContCRTXTSame.setBorderTop(CellStyle.BORDER_NONE);
        cellContCRTXTSame.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCRTXTSame.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCRTXTSame.setBorderRight(CellStyle.BORDER_NONE);
        cellContCRTXTSame.setFont(fontNomal);
        styles.put("crtxt_cellcont_same", cellContCRTXTSame);

        //본문 셀 문자 오른쪽정렬 블루
        CellStyle cellContCRTXTBlue = wb.createCellStyle();
        cellContCRTXTBlue.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContCRTXTBlue.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCRTXTBlue.setDataFormat(fmt.getFormat("@"));
        cellContCRTXTBlue.setWrapText(true);
        cellContCRTXTBlue.setBorderTop(CellStyle.BORDER_NONE);
        cellContCRTXTBlue.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCRTXTBlue.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCRTXTBlue.setBorderRight(CellStyle.BORDER_NONE);
        cellContCRTXTBlue.setFont(fontNomalBlue);
        styles.put("crtxt_cellcont_blue", cellContCRTXTBlue);

        //본문 셀 문자 오른쪽정렬 레드
        CellStyle cellContCRTXTRed = wb.createCellStyle();
        cellContCRTXTRed.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContCRTXTRed.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContCRTXTRed.setDataFormat(fmt.getFormat("@"));
        cellContCRTXTRed.setWrapText(true);
        cellContCRTXTRed.setBorderTop(CellStyle.BORDER_NONE);
        cellContCRTXTRed.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContCRTXTRed.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContCRTXTRed.setBorderRight(CellStyle.BORDER_NONE);
        cellContCRTXTRed.setFont(fontNomalRed);
        styles.put("crtxt_cellcont_red", cellContCRTXTRed);

        //본문 마지막셀 텍스트 오른쪽정렬 블루
        CellStyle cellContELRTXTBlue = wb.createCellStyle();
        cellContELRTXTBlue.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContELRTXTBlue.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContELRTXTBlue.setDataFormat(fmt.getFormat("@"));
        cellContELRTXTBlue.setWrapText(true);
        cellContELRTXTBlue.setBorderTop(CellStyle.BORDER_NONE);
        cellContELRTXTBlue.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContELRTXTBlue.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContELRTXTBlue.setBorderRight(CellStyle.BORDER_DOTTED);
        cellContELRTXTBlue.setFont(fontNomalBlue);
        styles.put("elrtxt_cellcont_blue", cellContELRTXTBlue);

        //본문 마지막셀 텍스트 오른쪽정렬 레드
        CellStyle cellContELRTXTRed = wb.createCellStyle();
        cellContELRTXTRed.setAlignment(CellStyle.ALIGN_RIGHT); //가로 정렬
        cellContELRTXTRed.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContELRTXTRed.setDataFormat(fmt.getFormat("@"));
        cellContELRTXTRed.setWrapText(true);
        cellContELRTXTRed.setBorderTop(CellStyle.BORDER_NONE);
        cellContELRTXTRed.setBorderBottom(CellStyle.BORDER_DOTTED);
        cellContELRTXTRed.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContELRTXTRed.setBorderRight(CellStyle.BORDER_DOTTED);
        cellContELRTXTRed.setFont(fontNomalRed);
        styles.put("elrtxt_cellcont_red", cellContELRTXTRed);

        //본문 부분합 첫셀 가운데 정렬
        CellStyle cellContPSSL = wb.createCellStyle();
        cellContPSSL.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContPSSL.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSSL.setDataFormat(fmt.getFormat("@"));
        cellContPSSL.setWrapText(true);
        cellContPSSL.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSSL.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSSL.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSSL.setBorderLeft(CellStyle.BORDER_THIN);
        cellContPSSL.setBorderRight(CellStyle.BORDER_NONE);
        cellContPSSL.setFont(fontNomal);
        styles.put("subsum_sl_cellcont", cellContPSSL);

        //본문 부분합 마지막셀 가운데 정렬
        CellStyle cellContPSERTXT = wb.createCellStyle();
        cellContPSERTXT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContPSERTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSERTXT.setDataFormat(fmt.getFormat("@"));
        cellContPSERTXT.setWrapText(true);
        cellContPSERTXT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSERTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSERTXT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSERTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContPSERTXT.setBorderRight(CellStyle.BORDER_THIN);
        cellContPSERTXT.setFont(fontNomal);
        styles.put("subsum_ertxt_cellcont", cellContPSERTXT);

        //본문 부분합 마지막셀 왼쪽 정렬
        CellStyle cellContPSELTXT = wb.createCellStyle();
        cellContPSELTXT.setAlignment(CellStyle.ALIGN_LEFT); //가로 정렬
        cellContPSELTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSELTXT.setDataFormat(fmt.getFormat("@"));
        cellContPSELTXT.setWrapText(true);
        cellContPSELTXT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSELTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSELTXT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSELTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContPSELTXT.setBorderRight(CellStyle.BORDER_THIN);
        cellContPSELTXT.setFont(fontNomal);
        styles.put("subsum_eㅣtxt_cellcont", cellContPSELTXT);

        //본문 부분합 마지막셀 정수 가운데 정렬
        CellStyle cellContPSERINT = wb.createCellStyle();
        cellContPSERINT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContPSERINT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSERINT.setDataFormat(fmt.getFormat("* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-"));
        cellContPSERINT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSERINT.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSERINT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSERINT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContPSERINT.setBorderRight(CellStyle.BORDER_THIN);
        cellContPSERINT.setFont(fontNomal);
        styles.put("subsum_erint_cellcont", cellContPSERINT);

        //본문 부분합 마지막셀 실수 가운데 정렬
        CellStyle cellContPSERFLO = wb.createCellStyle();
        cellContPSERFLO.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContPSERFLO.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSERFLO.setDataFormat(fmt.getFormat("* #,##0.00_-;-* #,##0.00_-;_-* \"-\"_-;_-@_-"));
        cellContPSERFLO.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSERFLO.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSERFLO.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSERFLO.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContPSERFLO.setBorderRight(CellStyle.BORDER_THIN);
        cellContPSERFLO.setFont(fontNomal);
        styles.put("subsum_erfloat_cellcont", cellContPSERFLO);

        //본문 부분합 셀 왼쪽 정렬
        CellStyle cellContPSCLTXT = wb.createCellStyle();
        cellContPSCLTXT.setAlignment(CellStyle.ALIGN_LEFT); //가로 정렬
        cellContPSCLTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSCLTXT.setDataFormat(fmt.getFormat("@"));
        cellContPSCLTXT.setWrapText(true);
        cellContPSCLTXT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSCLTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSCLTXT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSCLTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContPSCLTXT.setBorderRight(CellStyle.BORDER_NONE);
        cellContPSCLTXT.setFont(fontNomal);
        styles.put("subsum_cltxt_cellcont", cellContPSCLTXT);

        //본문 부분합 셀 가운데 정렬
        CellStyle cellContPSCCTXT = wb.createCellStyle();
        cellContPSCCTXT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContPSCCTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSCCTXT.setDataFormat(fmt.getFormat("@"));
        cellContPSCCTXT.setWrapText(true);
        cellContPSCCTXT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSCCTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSCCTXT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSCCTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContPSCCTXT.setBorderRight(CellStyle.BORDER_NONE);
        cellContPSCCTXT.setFont(fontNomal);
        styles.put("subsum_cctxt_cellcont", cellContPSCCTXT);

        //본문 부분합 셀 정수 가운데 정렬
        CellStyle cellContPSCCINT = wb.createCellStyle();
        cellContPSCCINT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContPSCCINT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSCCINT.setDataFormat(fmt.getFormat("* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-"));
        cellContPSCCINT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSCCINT.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSCCINT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSCCINT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContPSCCINT.setBorderRight(CellStyle.BORDER_NONE);
        cellContPSCCINT.setFont(fontNomal);
        styles.put("subsum_ccint_cellcont", cellContPSCCINT);

        //본문 부분합 셀 실수 가운데 정렬
        CellStyle cellContPSCCFLO = wb.createCellStyle();
        cellContPSCCFLO.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContPSCCFLO.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContPSCCFLO.setDataFormat(fmt.getFormat("* #,##0.00_-;-* #,##0.00_-;_-* \"-\"_-;_-@_-"));
        cellContPSCCFLO.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContPSCCFLO.setBorderTop(CellStyle.BORDER_NONE);
        cellContPSCCFLO.setBorderBottom(CellStyle.BORDER_THIN);
        cellContPSCCFLO.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContPSCCFLO.setBorderRight(CellStyle.BORDER_NONE);
        cellContPSCCFLO.setFont(fontNomal);
        styles.put("subsum_ccfloat_cellcont", cellContPSCCFLO);


        //본문 종합 첫셀 가운데 정렬
        CellStyle cellContSSL = wb.createCellStyle();
        cellContSSL.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContSSL.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSSL.setDataFormat(fmt.getFormat("@"));
        cellContSSL.setWrapText(true);
        cellContSSL.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSSL.setBorderTop(CellStyle.BORDER_NONE);
        cellContSSL.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSSL.setBorderLeft(CellStyle.BORDER_THIN);
        cellContSSL.setBorderRight(CellStyle.BORDER_NONE);
        cellContSSL.setFont(fontNomal);
        styles.put("sum_sl_cellcont", cellContSSL);

        //본문 종합 마지막셀 가운데 정렬
        CellStyle cellContSERTXT = wb.createCellStyle();
        cellContSERTXT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContSERTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSERTXT.setDataFormat(fmt.getFormat("@"));
        cellContSERTXT.setWrapText(true);
        cellContSERTXT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSERTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContSERTXT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSERTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContSERTXT.setBorderRight(CellStyle.BORDER_THIN);
        cellContSERTXT.setFont(fontNomal);
        styles.put("sum_ertxt_cellcont", cellContSERTXT);


        //본문 종합 마지막셀 왼쪽 정렬
        CellStyle cellContSELTXT = wb.createCellStyle();
        cellContSELTXT.setAlignment(CellStyle.ALIGN_LEFT); //가로 정렬
        cellContSELTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSELTXT.setDataFormat(fmt.getFormat("@"));
        cellContSELTXT.setWrapText(true);
        cellContSELTXT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSELTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContSELTXT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSELTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContSELTXT.setBorderRight(CellStyle.BORDER_THIN);
        cellContSELTXT.setFont(fontNomal);
        styles.put("sum_eltxt_cellcont", cellContSELTXT);

        //본문 종합 마지막셀 정수 가운데 정렬
        CellStyle cellContSERINT = wb.createCellStyle();
        cellContSERINT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContSERINT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSERINT.setDataFormat(fmt.getFormat("* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-"));
        cellContSERINT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSERINT.setBorderTop(CellStyle.BORDER_NONE);
        cellContSERINT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSERINT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContSERINT.setBorderRight(CellStyle.BORDER_THIN);
        cellContSERINT.setFont(fontNomal);
        styles.put("sum_erint_cellcont", cellContSERINT);

        //본문 종합 마지막셀 실수 가운데 정렬
        CellStyle cellContSERFLO = wb.createCellStyle();
        cellContSERFLO.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContSERFLO.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSERFLO.setDataFormat(fmt.getFormat("* #,##0.00_-;-* #,##0.00_-;_-* \"-\"_-;_-@_-"));
        cellContSERFLO.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSERFLO.setBorderTop(CellStyle.BORDER_NONE);
        cellContSERFLO.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSERFLO.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContSERFLO.setBorderRight(CellStyle.BORDER_THIN);
        cellContSERFLO.setFont(fontNomal);
        styles.put("sum_erfloat_cellcont", cellContSERFLO);

        //본문 종합 셀 왼쪽정렬
        CellStyle cellContSCLTXT = wb.createCellStyle();
        cellContSCLTXT.setAlignment(CellStyle.ALIGN_LEFT); //가로 정렬
        cellContSCLTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSCLTXT.setDataFormat(fmt.getFormat("@"));
        cellContSCLTXT.setWrapText(true);
        cellContSCLTXT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSCLTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContSCLTXT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSCLTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContSCLTXT.setBorderRight(CellStyle.BORDER_NONE);
        cellContSCLTXT.setFont(fontNomal);
        styles.put("sum_cltxt_cellcont", cellContSCLTXT);

        //본문 종합 셀 가운데 정렬
        CellStyle cellContSCCTXT = wb.createCellStyle();
        cellContSCCTXT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContSCCTXT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSCCTXT.setDataFormat(fmt.getFormat("@"));
        cellContSCCTXT.setWrapText(true);
        cellContSCCTXT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSCCTXT.setBorderTop(CellStyle.BORDER_NONE);
        cellContSCCTXT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSCCTXT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContSCCTXT.setBorderRight(CellStyle.BORDER_NONE);
        cellContSCCTXT.setFont(fontNomal);
        styles.put("sum_cctxt_cellcont", cellContSCCTXT);

        //본문 종합 셀 정수 가운데 정렬
        CellStyle cellContSCCINT = wb.createCellStyle();
        cellContSCCINT.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContSCCINT.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSCCINT.setDataFormat(fmt.getFormat("* #,##0_-;-* #,##0_-;_-* \"-\"_-;_-@_-"));
        cellContSCCINT.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSCCINT.setBorderTop(CellStyle.BORDER_NONE);
        cellContSCCINT.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSCCINT.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContSCCINT.setBorderRight(CellStyle.BORDER_NONE);
        cellContSCCINT.setFont(fontNomal);
        styles.put("sum_ccint_cellcont", cellContSCCINT);

        //본문 종합 셀 실수 가운데 정렬
        CellStyle cellContSCCFLO = wb.createCellStyle();
        cellContSCCFLO.setAlignment(CellStyle.ALIGN_CENTER); //가로 정렬
        cellContSCCFLO.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //세로정렬
        cellContSCCFLO.setDataFormat(fmt.getFormat("* #,##0.00_-;-* #,##0.00_-;_-* \"-\"_-;_-@_-"));
        cellContSCCFLO.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellContSCCFLO.setBorderTop(CellStyle.BORDER_NONE);
        cellContSCCFLO.setBorderBottom(CellStyle.BORDER_THIN);
        cellContSCCFLO.setBorderLeft(CellStyle.BORDER_DOTTED);
        cellContSCCFLO.setBorderRight(CellStyle.BORDER_NONE);
        cellContSCCFLO.setFont(fontNomal);
        styles.put("sum_ccfloat_cellcont", cellContSCCFLO);

        return styles;
    }
	
	
	public  CellStyle createExcelCellStylesXSS(SXSSFWorkbook wb, String fontType, String fontColor, String styleColor, String textAlign, String textVAlign
				, String Line, String strFormat){
		
		XSSFDataFormat fmt = (XSSFDataFormat) wb.createDataFormat();

		int nRFontColor = 0;
		int nGFontColor = 0;
		int nBFontColor = 0;
		if (fontColor.length() == 6) {
			nRFontColor = Integer.valueOf( fontColor.substring( 0, 2 ), 16 );
			nGFontColor = Integer.valueOf( fontColor.substring( 2, 4 ), 16 );
			nBFontColor = Integer.valueOf( fontColor.substring( 4, 6 ), 16 );
		}
		
		int nRCellColor = 255;
		int nGCellColor = 255;
		int nBCellColor = 255;
		if (styleColor.length() == 6) {
			nRCellColor = Integer.valueOf( styleColor.substring( 0, 2 ), 16 );
			nGCellColor = Integer.valueOf( styleColor.substring( 2, 4 ), 16 );
			nBCellColor = Integer.valueOf( styleColor.substring( 4, 6 ), 16 );
		}
		
		XSSFFont fontStyle = (XSSFFont) wb.createFont();
		
		fontStyle.setFontName("맑은 고딕");
		fontStyle.setFamily(FontFamily.MODERN);
		if (fontType.equals("title")) {
			fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			fontStyle.setFontHeightInPoints((short)15);
			fontStyle.setUnderline(XSSFFont.U_NONE);
		}
		else if (fontType.equals("titleLine")) {
			fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			fontStyle.setFontHeightInPoints((short)15);
			fontStyle.setUnderline(XSSFFont.U_DOUBLE);
		}
		else if (fontType.equals("subtitle")) {
			fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			fontStyle.setFontHeightInPoints((short)10);
			fontStyle.setUnderline(XSSFFont.U_NONE);
		}
		else if (fontType.equals("subtitleLine")) {
			fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			fontStyle.setFontHeightInPoints((short)10);
			fontStyle.setUnderline(XSSFFont.U_SINGLE);
		}
		else if (fontType.equals("unit")) {
			fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
			fontStyle.setFontHeightInPoints((short)10);
			fontStyle.setUnderline(XSSFFont.U_NONE);
		}
		else if (fontType.equals("unitLine")) {
			fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
			fontStyle.setFontHeightInPoints((short)10);
			fontStyle.setUnderline(XSSFFont.U_SINGLE);
		}
		else {
			fontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
			fontStyle.setFontHeightInPoints((short)10);
			fontStyle.setUnderline(XSSFFont.U_NONE);
		}
		fontStyle.setColor(new XSSFColor(new java.awt.Color(nRFontColor, nGFontColor, nBFontColor)));
		
		XSSFCellStyle cellStyle = (XSSFCellStyle) wb.createCellStyle();
		if (textAlign.equals("left")) {
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT); //왼쪽
		}
		else if (textAlign.equals("right")) {
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT); //오른쪽 정렬
		}
		else {
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); //중앙 정렬
		}
		if (textVAlign.equals("top")) {
			cellStyle.setVerticalAlignment(VerticalAlignment.TOP); //왼쪽
		}
		else if (textVAlign.equals("bottom")) {
			cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM); //오른쪽 정렬
		}
		else {
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //중앙 정렬
		}
		if (strFormat.length() > 0) {
			cellStyle.setDataFormat(fmt.getFormat(strFormat));
		}
		cellStyle.setWrapText(true);
		cellStyle.setShrinkToFit(true);
		cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(nRCellColor, nGCellColor, nBCellColor)));
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		
		if (Line.equals("none")){
			cellStyle.setBorderLeft(XSSFCellStyle.BORDER_NONE);
			cellStyle.setBorderRight(XSSFCellStyle.BORDER_NONE);
			cellStyle.setBorderTop(XSSFCellStyle.BORDER_NONE);
			cellStyle.setBorderBottom(XSSFCellStyle.BORDER_NONE);
		}
		else if (Line.equals("dot")){
			cellStyle.setBorderLeft(XSSFCellStyle.BORDER_DOTTED);
			cellStyle.setBorderRight(XSSFCellStyle.BORDER_DOTTED);
			cellStyle.setBorderTop(XSSFCellStyle.BORDER_DOTTED);
			cellStyle.setBorderBottom(XSSFCellStyle.BORDER_DOTTED);
		}
		else {
			cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		}
		
		cellStyle.setFont(fontStyle);
		
		return cellStyle;
		
	}
	
	
}

