<%--
  Class Name : EgovFileList.jsp
  Description : 파일목록화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.12   이삼섭          최초 생성
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스 개발팀 이삼섭
    since    : 2009.03.12
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
    String today = formatter1.format(new Date());

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="X-UA-Compatible" content="IE=edage,chrome=1" >
<title>게시판 목록</title>
<link href="<c:url value='/'/>css/common.css?<%=today%>" rel="stylesheet" type="text/css" >

<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js" ></script>

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
/*datepicer 버튼 롤오버 시 손가락 모양 표시*/
.ui-datepicker-trigger{cursor: pointer;}
/*datepicer input 롤오버 시 손가락 모양 표시*/
.hasDatepicker{cursor: pointer;}
.ui-datepicker-calendar { display:none; }
</style>

<script type="text/javascript">
	 window.history.forward();
	 function noBack(){window.history.forward();}
</script>

<script type="text/javascript">
	
	//다건입력 여부
	var tmpChk = "0";
		
	
	function fn_egov_select_brdMstr2(){
		
			document.frm.action = "<c:url value='/cop/bbs/selectExtraSPList.do'/>";
			document.frm.submit();
			
	}
	
	
	function fn_egov_excel_download(num){
		
		document.frm.action = "<c:url value='/cop/bbs/selectExtraSPExcel.do'/>";
		
		document.frm.submit();
		
	}
	
	function fn_egov_firm(){
		
		var result = confirm('확인 진행을 하시겠습니까?'); 
		if(result) {
			document.frm.action = "<c:url value='/cop/bbs/confirmIncome.do'/>";
			
			document.frm.submit();
		} else { 
			return false;
		} 
		
		
	}
	
	
   $(function(){
	   $.datepicker.regional['ko'] = {
	        closeText: '닫기',
	        prevText: '이전달',
	        nextText: '다음달',
	        currentText: '오늘',
	        monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
	        '7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
	        monthNamesShort: ['1월','2월','3월','4월','5월','6월',
	        '7월','8월','9월','10월','11월','12월'],
	        dayNames: ['일','월','화','수','목','금','토'],
	        dayNamesShort: ['일','월','화','수','목','금','토'],
	        dayNamesMin: ['일','월','화','수','목','금','토'],
	        weekHeader: '주',
	        dateFormat: 'yy-mm-dd',
	        firstDay: 0,
	        isRTL: false,
	        showMonthAfterYear: true,
	        yearSuffix: '',
	        showOn: 'both',
	        buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif", //버튼 이미지 경로
	        buttonImageOnly: true, //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
	        buttonText: "선택", //버튼에 마우스 갖다 댔을 때 표시되는 텍스트
	        changeMonth: true,
	        changeYear: true,
	        showButtonPanel: true,
	        yearRange: 'c-99:c+99'
	    };
	    $.datepicker.setDefaults($.datepicker.regional['ko']);
	 
	    var datepicker_default = {
	        showOn: 'both',
	        buttonText: "달력",
	        currentText: "이번달",
	        changeMonth: true,
	        changeYear: true,
	        showButtonPanel: true,
	        yearRange: 'c-99:c+99',
	        showOtherMonths: true,
	        selectOtherMonths: true
	    }
	 
	    datepicker_default.closeText = "선택";
	    datepicker_default.dateFormat = "yymm";
	    datepicker_default.onClose = function (dateText, inst) {
	        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	        $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
	        $(this).datepicker('setDate', new Date(year, month, 1));
	    }
	 
	    datepicker_default.beforeShow = function () {
	        var selectDate = $(this).val().split("-");
	        var year = Number($(this).val().substring(0,4));
	        var month = Number($(this).val().substring(4,6)) - 1;
	        $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
	    }
	 
	    $("#searchMonth").datepicker(datepicker_default);
	    
	    
	
   });
	
   function genRowspan(className){

	    $("#" + className).each(function() {

	        var rows = $("#" + className + ":contains('" + $(this).text() + "')");

	        if (rows.length > 1) {

	            rows.eq(0).attr("rowspan", rows.length);

	            rows.not(":eq(0)").remove();

	        }

	    });

	}	
   
   /* 
    * 
    * 같은 값이 있는 열을 병합함
    * 
    * 사용법 : $('#테이블 ID').rowspan(0);
    * 
    */    
   $.fn.rowspan = function(colIdx, isStats) {       
       return this.each(function(){      
           var that;     
           $('tr', this).each(function(row) {      
               $('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
                   if ($(this).html() == $(that).html()&& 
                		   ((!isStats || isStats && $(this).prev().html() == $(that).prev().html()))) {   
                       rowspan = $(that).attr("rowspan") || 1;
                       rowspan = Number(rowspan)+1;
                       
                       $(that).attr("rowspan",rowspan);
                       // do your action for the colspan cell here  
                       $(this).hide();
                       //$(this).remove();
                       // do your action for the old cell here
                   } else {            
                       that = this;  
                   }     
                   
                   //$(this).colspan(row); // row 돌때 마다 colspan
                    
                   // set the that if not already set
                   that = (that == null) ? this : that;
                   
               });     
           });    
       });  
   }; 
   


   /* 
    * 
    * 같은 값이 있는 행을 병합함
    * 
    * 사용법 : $('#테이블 ID').colspan (0);
    * 
    */  
   $.fn.colspan = function(rowIdx) {
       return this.each(function(){
           var that;
           $('tr', this).filter(":eq("+rowIdx+")").each(function(row) {
               $(this).find('td').filter(':visible').each(function(col) {
                   if ($(this).html() == $(that).html()) {
                       colspan = $(that).attr("colSpan") || 1;
                       colspan = Number(colspan)+1;
                        
                       $(that).attr("colSpan",colspan);
                       $(this).hide(); // .remove();
                   } else {
                       that = this;
                   }
                   that = (that == null) ? this : that;
               });
           });
       });
   };
   
   $(window).load(function(){
	   var tbltr_cnt = $('#extralist tbody tr').length;
	   var tbltr_cnt1 = $('#extra1list tbody tr').length;
	   /* 짝수번째 tr 태그에 대하여 backgroundColor ,green */
	   //$('#extralist tbody tr:eq('+ (tbltr_cnt - 1) +')').addClass('selectedstyle');

	   $('#extralist').colspan (tbltr_cnt + 1);
	   $('#extra1list').colspan (tbltr_cnt1 + 1);
	   $('#extralist').rowspan(0);
	   $('#extra1list').rowspan(0);
	   $('#extralist').rowspan(1);
	   $('#extra1list').rowspan(1);

   }); 

    
    

	
</script>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
	
	A:link    { color: #000000; text-decoration:none; }
	A:visited { color: #000000; text-decoration:none; }
	A:active  { color: #000000; text-decoration:none; }
	A:hover   { color: #fa2e2e; text-decoration:none; }	
</style>

</head>



<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!-- 전체 레이어 시작 -->
<div id="wrap">

    <!-- header 시작 -->
    <div id="header"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncHeader" /></div>
    <div id="topnavi"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncTopnav" /></div>        
    <!-- //header 끝 -->


    <!-- container 시작 -->
    <div id="container">
    
        <!-- 좌측메뉴 시작 -->
        <div id="leftmenu"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncLeftmenu" /></div>
        <!-- //좌측메뉴 끝 -->

            <!-- 현재위치 네비게이션 시작 -->
            <div id="content">
                <div id="cur_loc">
                    <div id="cur_loc_align">
                        <ul>
                            <li>구매관리</li>
                            <li>&gt;</li>
                            <li>RPA</li>
                            <li>&gt;</li>
                            <li><strong>수입부대비용</strong></li>
                        </ul>
                    </div>
                </div>
                
                <!-- 검색 필드 박스 시작 -->
                <div id="search_field"> 
                    <div id="search_field_loc"><h2><strong>수입 부대비용 정보</strong></h2></div>
                    
					
					<form name="frm" method="post">
                        
                        <fieldset><legend>조건정보 영역</legend>    
                        <div class="sf_start">
                            
                            <ul id="search_first_ul">
                            	<li>
                                <label for="searchMonth" >BL일자</label>
                                <input size = "10" maxlength ="6" type="text" id="searchMonth" name="searchMonth"  value='<c:out value="${searchVO.searchMonth}"/>' >
                                </li>
                            
                                <li>
                                
                                    <div class="buttons" style="position:absolute;left:480px;top:180px;">
                                       <a href="#" onclick="fn_egov_select_brdMstr2('1'); return false;">
                                       		<img src="<c:url value='/images/img_search.gif' />" alt="search" />조회  </a>
                                    </div>                              
                                </li>
                                
                                <li>
                                 	<div class="buttons" style="position:absolute;left:1630px;top:180px;">
                                       <a href="#" onclick="fn_egov_firm('Y'); return false;">확인</a>
                                    </div>   
                                </li>  
                                <li>
                                 	<div class="buttons" style="position:absolute;left:1690px;top:180px;">
                                       <a href="#" onclick="fn_egov_excel_download('Y'); return false;">엑셀다운로드  </a>
                                    </div>   
                                </li>
                                
                            </ul>
                        </div>          
                        </fieldset>
                    </form>
                    
                    
                    
                    
                    
                </div>
                <!-- //검색 필드 박스 끝 -->
                <c:set var="price" value="${fn:substring(searchVO.searchMonth,4,6)}" />

				<fmt:formatNumber value="${price}" type="number" var="nowmon" />

                <!-- table add start -->
                <div class="default_tablestyle" style="color:black">
                    
                    <div id="search_field_loc"><h2><strong><c:out value="${nowmon}"/>월 수입 부대비용 마감 내역</strong></h2></div>
                    <table id = "extralist" summary="업체명,세부내역,관세사,공급가액,금액  목록입니다" cellpadding="0" cellspacing="0" style="width:1150px;">
                    <caption>사용자목록관리</caption>
                    
                    <colgroup>
                    <col width="30%">
                    <col width="15%">  
                    <col width="15%">
                    <col width="12%">
                    <col width="8%">
                    <col width="15%">
                    <col width="5%">
                    </colgroup>
                    
                    <thead>
                    <tr>
                        <th rowspan ="2" nowrap="nowrap">업체</th>
                        <th rowspan ="2" scope="row" nowrap="nowrap">세부내역</th>
                        <th rowspan ="2" scope="row" nowrap="nowrap">관세사</th>
                        
                        <th colspan ="3" scope="col" nowrap="nowrap" class="endth">금액(\)</th>
                        <th rowspan ="2" scope="row" nowrap="nowrap" class="endth">확인</th>
                    </tr>
                    <tr>    
                        <th scope="col" nowrap="nowrap" class='cellr'>공급가액</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>부가세</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>Total</th>
                    </tr>
                    </thead>
                    
                    
                    
                    <tbody>                 
					<c:forEach var="result" items="${extraList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr class='<c:out value="${result.getExtra_style()}" default="normaltr"/>'>
						<td class='cellc' nowrap="nowrap"><c:out value="${result.getExtra_company()}" default=""/></td>
					    <td class='cellc' nowrap="nowrap"><c:out value="${result.getExtra_detail()}"  default=""/></td>
			    		<td class='cellc endtd' nowrap="nowrap"><c:out value="${result.getExtra_custom()}"  default=""/></td>
			    		<td class='cellr <c:out value="${result.getIncome_gubun1()}"  default=""/>'><fmt:formatNumber value="${result.getExtra_price()}" pattern="#,###" /></td>
			    		<td class='cellr <c:out value="${result.getIncome_gubun2()}"  default=""/>'><fmt:formatNumber value="${result.getExtra_tax()}" pattern="#,###" /></td>
			    		<td class='cellr <c:out value="${result.getIncome_gubun3()}"  default=""/>'><fmt:formatNumber value="${result.getExtra_total()}" pattern="#,###" /></td>
			    		<td class='cellc endtd <c:out value="${result.getIncome_gubun3()}"  default=""/>'><c:out value="${result.getIncome_gubun()}"  default=""/></td>
			    	  </tr>
			    	  </c:forEach>		
			        </tbody>
	                </table>
	                <div id="search_field_loc"><h2><strong><c:out value="${nowmon}"/>월 수입 부대비용 개별 마감업체 내역</strong></h2></div>
                    <table id = "extra1list" summary="업체명,세부내역,관세사,공급가액,금액  목록입니다" cellpadding="0" cellspacing="0" style="width:1150px;">
                    <caption>사용자목록관리</caption>
                    
                    <colgroup>
                    <col width="30%">
                    <col width="15%">  
                    <col width="15%">
                    <col width="12%">
                    <col width="8%">
                    <col width="15%">
                    </colgroup>
                    
                    <thead>
                    <tr>
                        <th rowspan ="2" nowrap="nowrap">업체</th>
                        <th rowspan ="2" scope="row" nowrap="nowrap">세부내역</th>
                        <th rowspan ="2" scope="row" nowrap="nowrap">관세사</th>
                        
                        <th colspan ="3" scope="col" nowrap="nowrap" class="endth">금액(\)</th>
                        <th rowspan ="2" scope="row" nowrap="nowrap" class="endth">확인</th>
                    </tr>
                    <tr>    
                        <th scope="col" nowrap="nowrap">공급가액</th>
                        <th scope="col" nowrap="nowrap">부가세</th>
                        <th scope="col" nowrap="nowrap">Total</th>
                    </tr>
                    </thead>
                    
                    
                    
                    <tbody>                 

                    <!-- loop 시작 -->
                    <c:forEach var="result" items="${extra1List}" varStatus="status">                                
					  <tr class='<c:out value="${result.getExtra1_style()}" default="normaltr"/>'>
						<td class='cellc' nowrap="nowrap"><c:out value="${result.getExtra1_company()}" default=""/></td>
					    <td class='cellc' nowrap="nowrap"><c:out value="${result.getExtra1_detail()}"  default=""/></td>
			    		<td class='cellc' nowrap="nowrap"><c:out value="${result.getExtra1_custom()}"  default=""/></td>
			    		<td class='cellr <c:out value="${result.getIncome1_gubun1()}"  default=""/>'><fmt:formatNumber value="${result.getExtra1_price()}" pattern="#,###" /></td>
			    		<td class='cellr <c:out value="${result.getIncome1_gubun2()}"  default=""/>'><fmt:formatNumber value="${result.getExtra1_tax()}" pattern="#,###" /></td>
			    		<td class='cellr <c:out value="${result.getIncome1_gubun3()}"  default=""/>'><fmt:formatNumber value="${result.getExtra1_total()}" pattern="#,###" /></td>
			    		<td class='cellc endtd <c:out value="${result.getIncome1_gubun3()}"  default=""/>'><c:out value="${result.getIncome1_gubun()}"  default=""/></td>
			    		</tr>
			    	</c:forEach>	
			        </tbody>
                    <tfoot>                 

                    <!-- loop 시작 -->                                
					  <tr class="lasttotal">
						<td colspan = "3" class='cellc' nowrap="nowrap" >수입 부대비용 TOTAL</td>
					    <td colspan = "3" nowrap="nowrap" class='cellr'>
					    	<fmt:formatNumber value="${resultTotal}" pattern="#,###" />
					    </td>
					    <td class='cellc endtd'></td>
			        </tfoot>
	                </table>
                </div>
		        
                
                  
            <!-- //content 끝 -->    
        </div>  
        <!-- //container 끝 -->
       
    </div>
 </div>   
 </body>
</html>