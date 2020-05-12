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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
 --%>
<% String cp = request.getContextPath(); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset ="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edage,chrome=1" >
<title>게시판 목록</title>
<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >

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

</style>


<script type="text/javascript">
	 window.history.forward();
	 function noBack(){window.history.forward();}
</script>

<script type="text/javascript">
	
	//다건입력 여부
	var tmpChk = "0";
		
	
	function fn_egov_select_brdMstr2(){
		
			document.frm.action = "<c:url value='/cop/bbs/SelectBBSSapInfs.do'/>";
			document.frm.submit();
			
	}
	
	
	function fn_egov_select_brdMstr(num){
		
		if(num != 1){
			return;
			
		}else{
			document.frm.action = "<c:url value='/cop/bbs/SelectBBSSapInfs.do'/>";
			document.frm.submit();
			
		}
	}
	
	function fn_egov_sapsub(num){
		
		$('#searchBlNo').val(num);
		
		
		document.frm.action = "<c:url value='/cop/bbs/SelectBBSSapSubInfs.do'/>";
		
		document.frm.submit();
		
	}
	
	function fn_egov_excel_download(){
		
		document.frm.action = "<c:url value='/cop/bbs/SelectSapList.do'/>";
	
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
	    
	    $("a[name='c1yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR00','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("a[name='c2yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR06','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("a[name='c3yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR02','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("a[name='c4yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR04','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("a[name='c5yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR01','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("a[name='c6yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR03','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("a[name='c7yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR05','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("a[name='c8yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR08','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });

		$("a[name='c9yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR07','searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("a[name='c10yn']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'FR09','searchCompany':'N'};
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
                    <div id="search_field_loc"><h2><strong>SAP 전표 정보</strong></h2></div>
                    
					
					<form name="frm" method="post">
						<!-- 
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
                         -->
                        <input type="hidden" id = "searchBlNo" name="searchBlNo"  />
                        <input type="hidden" id = "searchPreCnd"  name="searchPreCnd" />
                        <input type="hidden" id = "searchNowCnd"  name="searchNowCnd" />
                        
                        
                        <fieldset><legend>조건정보 영역</legend>    
                        <div class="sf_start">
                            
                            <ul id="search_first_ul">
                            	<li>
                                <label for="searchBgnDe" >BL일자</label>
                                <input size = "10" maxlength ="8" type="text" id="searchBgnDe" name="searchBgnDe"  value='<c:out value="${searchVO.searchBgnDe}"/>' > ~ 
                                <input size = "10" maxlength ="8" type="text" id="searchEndDe" name="searchEndDe"  value='<c:out value="${searchVO.searchEndDe}" />' >
                                </li>
                                
                                <li>
                                <label for="searchBl" >BL번호</label>
                                <input maxlength="20" type="text" id="searchBl" name="searchBl"   size="15"   value='<c:out value="${searchVO.searchBl}"/>'  />
                                </li>
                            	
                                <li>
                                    <label for="searchCnd" >확정유무</label>
					                <select id="searchCnd" name="searchCnd" title="검색유형선력" onchange="myFunction(this.value)">
					                   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if>> == 선택 == </option>
					                   <option value="7" <c:if test="${searchVO.searchCnd == '7'}">selected="selected"</c:if>>임시</option>
					                   <option value="6" <c:if test="${searchVO.searchCnd == '6'}">selected="selected"</c:if>>확정</option>
					                   <option value="3" <c:if test="${searchVO.searchCnd == '3'}">selected="selected"</c:if>>확인</option>
					                   <option value="4" <c:if test="${searchVO.searchCnd == '4'}">selected="selected"</c:if>>승인</option>
					                </select>
                                </li>
                                <li>
                                
                                    <div class="buttons" style="position:absolute;left:1750px;top:180px;">
                                       <a href="#" onclick="fn_egov_select_brdMstr2('1'); return false;">
                                       		<img src="<c:url value='/images/img_search.gif' />" alt="search" />조회  </a>
                                    </div>                              
                                </li>
                                
                            </ul>
                        </div>          
                        </fieldset>
                    </form>
                    
                    
                </div>
                
                <!-- table add start -->

                   <div class="fixed-table-container" id="fix-table" style="width:100%;height:500px;">
        <div class="fixed-table-header-bg"></div>
        <div class="fixed-table-wrapper">
					<table id="tbl_employee" class="fixed-table">
					<caption>사용자목록관리</caption>
                    
                    <colgroup>
                    <col width="10%">  
                    <col width="10%">
                    <col width="5%">
                    
                    <col width="7%">
                    <col width="7%">  
                    <col width="7%">
                    <col width="7%">
                    <col width="7%">
                    <col width="6%">
                    <col width="6%">
                    <col width="6%">
                    <col width="6%">  
                    <col width="6%">
                    <col width="6%">
                    
                    </colgroup>
                    
                    <thead>
                    <tr>
                        <th rowspan ="2" style="width:10%" ><div class="th-text2">BL번호</div></th>
                        <th rowspan ="2"  style="width:10%"><div class="th-text2">BL일자</div></th>
                        <th rowspan ="2"  style="width:5%"><div class="th-text2">구분</div></th>
                        <th colspan ="10"  style="width:75%"><div class="th-text3">전표</div></th>
                    </tr>
                    <tr>    
                        <th scope="col"  style="width:7%"><div class="th-text1">물대</div></th>
                        <th scope="col"  style="width:7%"><div class="th-text1">적하보험</div></th>
                        <th scope="col"  style="width:7%"><div class="th-text1">관세</div></th>
                        <th scope="col"  style="width:7%"><div class="th-text1">취급수수료</div></th>
                        <th scope="col"  style="width:7%"><div class="th-text1">운반비</div></th>
                        <th scope="col"  style="width:6%"><div class="th-text1">통관수수료</div></th>
                        <th scope="col"  style="width:6%"><div class="th-text1">보관료</div></th>
                        
                        <th scope="col"  style="width:6%"><div class="th-text1">내륙운송료</div></th>
                        <th scope="col"  style="width:6%"><div class="th-text1">이용료</div></th>
                        <th scope="col"  style="width:6%"><div class="th-text1">기타</div></th>
                        
                    </tr>
                    </thead>
                    
                    
                    
                    <tbody>                 

                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr>

						<td id="checkBtn"  class="checkBtn" class ="lt_text3"  nowrap="nowrap"> <a href="javascript:void(0);"  onclick="fn_egov_sapsub('<c:out value="${result.t3_blno}" default=""/>')"><c:out value="${result.t3_blno}" default=""/></a></td>
					    <td nowrap="nowrap"><c:out value="${result.t3_sjdt}"  default=""/></td>
					    <td nowrap="nowrap" bgcolor="#${result.t3_color}"><label id="t3_yn${status.count}"><c:out value="${result.t3_yn}" default=""/></label></td>
					    
					    <c:choose>
					    	<c:when test = "${result.c1yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c1yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c2yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c2yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c3yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c3yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c4yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c4yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c5yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c5yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c6yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c6yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c7yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c7yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c8yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c8yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c9yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c9yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    <c:choose>
					    	<c:when test = "${result.c10yn == '0'}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="c10yn"  content_id="${result.t3_blno}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
					    
					    
					    <!-- 
					    <td nowrap="nowrap"><c:out value="${result.t1_yn}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.t2_yn}" default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.t3_yn}" default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.t4_yn}" default=""/></td>
					     -->
					    
					    <!-- 
					    <td nowrap="nowrap">
					    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
					    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
					    </td>
					     -->  
					  </tr>
	                </c:forEach>	  
					
					<c:if test="${fn:length(resultList) == 0}">
					  <tr>
					    <td nowrap colspan="13"><spring:message code="common.nodata.msg" /></td>  
					  </tr>		 
					</c:if>
			        
			        </tbody>
	                </table>

                </div>
                </div>

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
	    	$('.fixed-table-container').css('height', $(window).height() - 300 );
	    	$(window).resize(function() {
	    		$('.fixed-table-container').css('height', $(window).height() - 300 );
	    	});
	    });	     


	    // always overwrite window.name, in case users try to set it manually
	    window.name = "result"
	  </script>

    
 </body>
</html>