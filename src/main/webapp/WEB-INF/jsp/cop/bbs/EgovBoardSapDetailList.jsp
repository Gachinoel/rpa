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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
 --%>
<% 
	String cp = request.getContextPath();
	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
	String today = formatter1.format(new Date());
%>

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

<script  src="/js/colresizable/colResizable-1.6.js"></script>

<style>
/*datepicer 버튼 롤오버 시 손가락 모양 표시*/
.ui-datepicker-trigger{cursor: pointer;}
/*datepicer input 롤오버 시 손가락 모양 표시*/
.hasDatepicker{cursor: pointer;}

</style>


<script type="text/javascript">
	 window.history.forward();
	 function noBack(){window.history.forward();}
</script>

<script type="text/javascript">
	
	//다건입력 여부
	var tmpChk = "0";
		
	
	function fn_egov_select_brdMstr2(){
		
			document.frm.action = "<c:url value='/cop/bbs/SelectBBSSapDetailInfs.do'/>";
			document.frm.submit();
			
	}
	
	


	function fn_egov_excel_download(){
		
		document.frm.action = "<c:url value='/cop/bbs/selectSapListExcel.do'/>";
	
		document.frm.submit();
		
	}
	
	
	//날짜입력 체크
	$(document).ready(function() {
		
		$("#searchBgnDe").keyup(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );
		$("#searchEndDe").keyup(function(){$(this).val( $(this).val().replace(/[^0-9]/g,"") );} );

		$("#searchWrd").keyup(function(){$(this).val( $(this).val().replace(/[^\!-z]/g,"") );} );
		

	});

	
   $(function(){
	   $("#searchBgnDe").datepicker({
           dateFormat: 'yymmdd' //Input Display Format 변경
           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
           ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
           ,changeYear: true //콤보박스에서 년 선택 가능
           ,changeMonth: true //콤보박스에서 월 선택 가능                
           ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
           ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
           ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
           ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
           ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
           ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
          // ,minDate: "-1Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
           ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
       });  
	   $("#searchEndDe").datepicker({
           dateFormat: 'yymmdd' //Input Display Format 변경
           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
           ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
           ,changeYear: true //콤보박스에서 년 선택 가능
           ,changeMonth: true //콤보박스에서 월 선택 가능                
           ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
           ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
           ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
           ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
           ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
           ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
          // ,minDate: "-1Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
           ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
       }); 
	   
	   	$("#searchBl")
	   	 .on("keydown", function( event ) {
	         if(event.keyCode === $.ui.keyCode.TAB && $(this).autocomplete("instance").menu.active) {
	             event.preventDefault();
	         }
	     })
	     .autocomplete({

	         source: function( request, response ) {

	             $.getJSON( "/cop/bbs/selectBBSSAPBktxtInfs.do", 
	            		{
	            	 		searchKeyword: extractLast(request.term),
	            	 		searchBgnDe: $("#searchBgnDe").val(),
	            	 		searchEndDe: $("#searchEndDe").val()
	            	 	}
	             , response);

	         },
	         search: function() {
	             // 최소 입력 길이를 마지막 항목으로 처리합니다.
	             var term = extractLast(this.value);
	             if(term.length < 2) {
	                 return false;
	             }
	         },
	         focus: function() {
	             return false;
	         },
	         select: function(event, ui) {
	             var terms = split(this.value);
	             // 현재 입력값 제거합니다.
	             terms.pop();
	             // 선택된 아이템을 추가합니다.
	             terms.push(ui.item.value);
	             // 끝에 콤마와 공백을 추가합니다.
	             //terms.push("");
	             this.value = terms;//.join(", ");
	             return false;
	         }


    	});
	   	$("#searchCom")
	   	 .on("keydown", function( event ) {
	         if(event.keyCode === $.ui.keyCode.TAB && $(this).autocomplete("instance").menu.active) {
	             event.preventDefault();
	         }
	     })
	     .autocomplete({ 

	         source: function( request, response ) {

	             $.getJSON( "/cop/bbs/selectBBSSAPLifnrInfs.do", 
	            		{
	            	 		searchKeyword: extractLast(request.term),
	            	 		searchBgnDe: $("#searchBgnDe").val(),
	            	 		searchEndDe: $("#searchEndDe").val()
	            	 	}
	             , response);

	         },
	         search: function() {
	             // 최소 입력 길이를 마지막 항목으로 처리합니다.
	             var term = extractLast(this.value);
	             if(term.length < 2) {
	                 return false;
	             }
	         },
	         focus: function() {
	             return false;
	         },
	         select: function(event, ui) {
	        	 var terms = split(this.value);
	             // 현재 입력값 제거합니다.
	             terms.pop();
	             // 선택된 아이템을 추가합니다.
	             terms.push(ui.item.value);
	             // 끝에 콤마와 공백을 추가합니다.
	             //terms.push("");
	             this.value = terms;//.join(", ");
	             return false;
	         }


	   	});
		   	
	   	function split( val ) {
	        return val.split( /,\s*/ );
	    }
	
	    function extractLast( term ) {
	        return split( term ).pop();
	    }
	    
	    $("a[name='filepopup']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':$(this).attr("ext_id"),'searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
	});
	function setPostPopupWin(url,params,winnm,winl,wint,nWidth,nHeight,strScroll) {           

		var nScnWidth = screen.width/2;
		var nScnHeight = screen.height/2;
		
		if (winl == 0)
			winl = nScnWidth - nWidth/2;
			
		if (wint == 0)
			wint = nScnHeight - nHeight/2;
			
		if (strScroll == "auto") strScroll = "yes";
			
		var settings  ='height='+nHeight+'px,';
		settings +='width='+nWidth+'px,';
		settings +='top='+wint+'px,';
		settings +='left='+winl+'px,';
		settings +='scrollbars='+strScroll+',';
		settings +='toolbar=no,location=no,directories=no,status=no,resizable=yes,menubar=no,copyhistory=no';

		var win = window.open("",winnm,settings);

		var $form = $('<form></form>');
		$form.attr('action', url);
		$form.attr('method', 'post');
		$form.attr('target', winnm);
		$form.appendTo('body');
		for(var key in params) { 
			var hiddenField = $('<input name="'+key+'" type="hidden" value="'+params[key]+'">'); 
			$form.append(hiddenField);
		}
		$form.submit();	

		if (!win)
			alert('차단된 팝업창을 허용해 주세요.');
		else{
			win.window.resizeTo(nWidth,nHeight);

			if(parseInt(navigator.appVersion) >= 4){win.window.focus();}
		}
	}
	
	
	
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
                            <li><strong>SAP 전표</strong></li>
                        </ul>
                    </div>
                </div>
                
                <!-- 검색 필드 박스 시작 -->
                <div id="search_field"> 
                    <div id="search_field_loc"><h2><strong>SAP 전표 조회</strong></h2></div>
                    
					
					<form name="frm" method="post">
						<!-- 
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
                         -->
                        <input type="hidden" id = "searchBlNo" name="searchBlNo"  />
                        <input type="hidden" id = "confirmYn"  name="confirmYn" />
                        
                        
                        <fieldset><legend>조건정보 영역</legend>    
                        <div class="sf_start">
                            
                            <ul id="search_first_ul">
                                <li>
                                    <label for="searchGubun" >구분</label>
					                <select id="searchGubun" name="searchGubun" title="날짜구분선택">
					                   <option value="BL" <c:if test="${searchVO.searchGubun == 'BL'}">selected="selected"</c:if>>B/L</option>
					                   <option value="BT" <c:if test="${searchVO.searchGubun == 'BT'}">selected="selected"</c:if>>증빙일</option>
					                </select>
                                </li>
                            	<li>
                                <input size = "10" maxlength ="8" type="text" id="searchBgnDe" name="searchBgnDe"  value='<c:out value="${searchVO.searchBgnDe}"/>' > ~ 
                                <input size = "10" maxlength ="8" type="text" id="searchEndDe" name="searchEndDe"  value='<c:out value="${searchVO.searchEndDe}" />' >
                                </li>
                                <li>
                                    <label for="searchType" >조건유형</label>
					                <select id="searchType" name="searchType" title="조건유형선택">
					                   <option value="" <c:if test="${searchVO.searchType == ''}">selected="selected"</c:if>> == 선택 == </option>	
					                   <option value="FR00" <c:if test="${searchVO.searchType == 'FR00'}">selected="selected"</c:if>>물대</option>
					                   <option value="FR02" <c:if test="${searchVO.searchType == 'FR02'}">selected="selected"</c:if>>관세</option>
					                   <option value="FR06" <c:if test="${searchVO.searchType == 'FR06'}">selected="selected"</c:if>>적하보험료</option>
					                   <option value="FR01" <c:if test="${searchVO.searchType == 'FR01'}">selected="selected"</c:if>>운반비</option>
					                   <option value="FR04" <c:if test="${searchVO.searchType == 'FR04'}">selected="selected"</c:if>>취급수수료</option>
					                   <option value="FR07" <c:if test="${searchVO.searchType == 'FR07'}">selected="selected"</c:if>>이용료</option>
					                   <option value="FR09" <c:if test="${searchVO.searchType == 'FR09'}">selected="selected"</c:if>>기타</option>
					                   <option value="FR03" <c:if test="${searchVO.searchType == 'FR03'}">selected="selected"</c:if>>통관수수료</option>
					                   <option value="FR08" <c:if test="${searchVO.searchType == 'FR08'}">selected="selected"</c:if>>내륙운송료</option>
					                   <option value="FR05" <c:if test="${searchVO.searchType == 'FR05'}">selected="selected"</c:if>>보관료</option>
					                </select>
                                </li>
                                <li>
                                <label for="searchBl" >BL번호</label>
                                <input maxlength="20" type="text" id="searchBl" name="searchBl"   size="15"   value='<c:out value="${searchVO.searchBl}"/>'  />
                                </li>
                            	
                                <li>
                                    <label for="searchCnd" >업체명</label>
									<input maxlength="50" type="text" id="searchCom" name="searchCom"   size="20"  value='<c:out value="${searchVO.searchCom}"/>'  />                                
                                </li>
                                <li>
                                
                                    <div class="buttons" style="position:absolute;left:1610px;top:180px;">
                                       <a href="#" onclick="fn_egov_select_brdMstr2('1'); return false;">
                                       		<img src="<c:url value='/images/img_search.gif' />" alt="search" />조회  </a>
                                    </div>                              
                                </li>
                                
                                <li>
                                 	<div class="buttons" style="position:absolute;left:1690px;top:180px;">
                                       <a href="#" onclick="fn_egov_excel_download(); return false;">엑셀다운로드  </a>
                                    </div>   
                                </li>
                                
                            </ul>
                        </div>          
                        </fieldset>
                    </form>
                    
                    
                </div>
                
                <!-- table add start -->
                <div class="fixed-table-container1" id="fix-table" style="width:100%;height:500px;">
		        <div class="fixed-table-header-bg1"></div>
		        <div class="fixed-table-wrapper">
                    
                    <table id="tbl_employee" class="fixed-table">
                    <caption>사용자목록관리</caption>
                    
                    <colgroup>
                    <col width="5%">  
                    <col width="5%">
                    
                    <col width="7%">
                    <col width="*">
                    <col width="15%">  
                    <col width="4%">
                    <col width="7%">
                    <col width="8%">
                    <col width="5%">
                    <col width="5%">
                    <col width="5%">
                    <col width="6%">
                    <col width="6%">
                    <col width="5%">
                    
                    </colgroup>
                    
                    <thead>
                    <tr>
                        <th scope="col" style="width:5%"><div class="th-text">증빙일</div></th>
                        <th scope="col" style="width:5%"><div class="th-text">전기일</div></th>
                        <th scope="col" style="width:7%"><div class="th-text">BL번호</div></th>
                        <th scope="col" style="width:15%"><div class="th-text">조건유형</div></th>
                        <th scope="col" style="width:15%"><div class="th-text">공급업체</div></th>
                        <th scope="col" style="width:4%"><div class="th-text">통화</div></th>
                        <th scope="col" style="width:7%"><div class="th-text" class='cellr'>현지금액</div></th>
                        <th scope="col" style="width:8%"><div class="th-text" class='cellr'>원화금액</div></th>
                        <th scope="col" style="width:5%"><div class="th-text" class='cellr'>환율</div></th>
                        <th scope="col" style="width:5%"><div class="th-text" class='cellr'>세액</div></th>
                        <th scope="col" style="width:5%"><div class="th-text">세액코드</div></th>
                        <th scope="col" style="width:6%"><div class="th-text">전표번호</div></th>
                        <th scope="col" style="width:6%"><div class="th-text">사업자번호</div></th>
                        <th scope="col" style="width:5%"><div class="th-text">관련파일</div></th>
                    </tr>
                    </thead>
                    
                    
                    
                    <tbody>                 

                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr class="normaltr">
						<td nowrap="nowrap"><c:out value="${result.bldat}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.budat}"  default=""/></td>
						<td id="checkBtn"  class="checkBtn" class ="lt_text3"  nowrap="nowrap"><c:out value="${result.bktxt}" default=""/></td>
					    <td nowrap="nowrap" class='celll'><c:out value="${result.vtext}"  default=""/></td>
					    <td nowrap="nowrap" class='celll'><c:out value="${result.name1}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.waers}"  default=""/></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.wrbtr}" pattern="#,###.00" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.dmbtr}" pattern="#,###.00" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.kursf}" pattern="#,###.00" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber minIntegerDigits="1"  value="${result.wmwst}" pattern="#,###.00" /></td>
					    <td nowrap="nowrap"><c:out value="${result.mwskz}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.belnr}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.stcd2}"  default=""/></td>
					    <c:choose>
					    	<c:when test = "${result.filecnt == 0}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="filepopup"  content_id="${result.bktxt}" ext_id="${result.filetype}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					  </tr>
	                </c:forEach>
	                <c:forEach var="result" items="${totalList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr class="normaltr">
						<td nowrap="nowrap" colspan="3"><c:out value="${result.key}"  default=""/> 합계</td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"><c:out value="${result.key}"  default=""/></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.value.get('wrbtr')}" pattern="#,###" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.value.get('dmbtr')}" pattern="#,###" /></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.value.get('wmwst')}" pattern="#,###.##" /></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"></td>
					  </tr>
	                </c:forEach>	 	  
					
					<c:if test="${fn:length(resultList) == 0}">
					  <tr>
					    <td nowrap colspan="14"><spring:message code="common.nodata.msg" /></td>  
					  </tr>		 
					</c:if>
			        
			        </tbody>
                </div>
                </div>
                
		        
		        <!-- 페이지 네비게이션 시작 
		        <!--
		        <div id="paging_div">
                    <ul class="paging_align">
                       <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_brdMstr"  />
                    </ul>
		        </div>
		        -->                          
                <!-- //페이지 네비게이션 끝 -->
                
                  
            <!-- //content 끝 -->    
        </div>  
        <!-- //container 끝 -->
	  <!-- 
	  <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>  
       -->
       
    </div>
    <!-- //전체 레이어 끝 -->
    <script>
	    // tell the embed parent frame the height of the content
	    if (window.parent && window.parent.parent){
	      window.parent.parent.postMessage(["resultsFrame", {
	        height: document.body.getBoundingClientRect().height,
	        slug: "4jy85xwf"
	      }], "*")
	    }
	
	    $(document).ready(function(){
	    	$('.fixed-table-container1').css('height', $(window).height() - 300 );
	    	$(window).resize(function() {
	    		$('.fixed-table-container1').css('height', $(window).height() - 300 );
	    	});
	    	
	    	$("#tbl_employee").colResizable({
	    		liveDrag:true, 
				gripInnerHtml:"<div class='grip'></div>", 
				draggingClass:"dragging", 
	            resizeMode:'fit'
		      });
	    });	
	    
	    // always overwrite window.name, in case users try to set it manually
	    window.name = "result"
	  </script>

    
 </body>
</html>