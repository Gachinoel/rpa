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
<%@ page import="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
 --%>
<% String cp = request.getContextPath(); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String referer = (String)request.getHeader("REFERER");
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
    String today = formatter1.format(new Date());
	LoginVO user = (LoginVO)request.getSession().getAttribute("LoginVO");
	String userId = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset ="utf-8">
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
		
		document.frm.action = "<c:url value='/cop/bbs/selectExtraSubList.do'/>";
		
		document.frm.submit();
			
	}
	
		
	function fn_excel_down(){
		
		var searchBgnDe = $("#searchBgnDe").val();
		searchBgnDe = searchBgnDe.replace("-", "");
		var searchMonth = searchBgnDe.substring( 0, 6 )

		$("#searchMonth").val(searchMonth);
		document.frm.action = "<c:url value='/cop/bbs/selectExtraTax.do'/>";
		
		document.frm.submit();
		
	}
	
	function fn_egov_confirm(obj){
		if (obj == "Y"){
			var result = confirm('확인진행을 하시겠습니까?'); 
			if(result) {
				var checkCnt = $("input:checkbox[name='checkOne']:checked").length;
				var items=[]; 
				$('input[name="checkOne"]:checkbox:checked').each(function(){
					items.push($(this).val());
				}); 
				var tmp = items.join(','); 
				
				if (checkCnt == 0){
					alert("확인진행을 할 항목을 선택해주세요.");
					return false;
				}
				$('#searchType').val(tmp);
				$('#t3_confirm').val("Y");
				
				document.frm.action = "<c:url value='/cop/bbs/confirmStatement.do'/>";
				
				document.frm.submit();
			} else { 
				return fase;
			}
		}
		else if (obj == "D"){
			var result = confirm('삭제진행을 하시겠습니까?'); 
			if(result) {
				var checkCnt = $("input:checkbox[name='checkOne']:checked").length;
				var items=[]; 
				$('input[name="checkOne"]:checkbox:checked').each(function(){
					items.push($(this).val());
				}); 
				var tmp = items.join(','); 
				
				if (checkCnt == 0){
					alert("삭제진행을 할 항목을 선택해주세요.");
					return false;
				}
				$('#searchType').val(tmp);
				$('#t3_confirm').val("D");
				
				document.frm.action = "<c:url value='/cop/bbs/confirmStatement.do'/>";
				
				document.frm.submit();
			} else { 
				return fase;
			}
		}
		
	}
	
	
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
           //,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
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
           //,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
           ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
       }); 
		    
		$("a[name='file']").click(function(){
			var ext_id = $(this).attr("ext_id").toLowerCase();
			var content_id = $(this).attr("content_id");
			if (ext_id == "pdf"){
				var win = window.open("/cop/bbs/pdfView.do?atchFileId="+content_id, "fileView", "toolbar=no,scrollbars=yes,resizable=yes,top=500,left=500,width=1000,height=600");
			}
			else if ((ext_id == "png") ||(ext_id == "gif") || (ext_id == "jpg") || (ext_id == "jpeg")){
				var win = window.open("/cop/bbs/imgView.do?atchFileId="+content_id, "fileView", "toolbar=no,scrollbars=yes,resizable=yes,top=500,left=500,width=1000,height=600");
			}
			else{
				location.href = "/display.do?searchAtchFileId="+content_id;
			}

	    });
		
		$("[name=checkAll]").click(function(){
			allCheckFunc( this );
		});
		$("[name=checkOne]").each(function(){
			$(this).click(function(){
				oneCheckFunc( $(this) );
			});
		});
		
		$("a[name='fileupload']").click(function(){
			var url = "/cop/bbs/popupExtraExcel.do" ;
			var searchBgnDe = $("#searchBgnDe").val();
			searchBgnDe = searchBgnDe.replace("-", "");
			var searchMonth = searchBgnDe.substring( 0, 6 )

			$("#searchMonth").val(searchMonth);
			var param = {'searchMonth':$("#searchMonth").val(),'searchBgnDe':$("#searchBgnDe").val(),'searchEndDe':$("#searchEndDe").val()};
			var popName = "fileUpload";
			setPostPopupWin(url,param,popName,0,0,550,500,'auto');
        });
		
		$("a[name='fileview']").click(function(){
			var url = "/cop/bbs/selectFileView.do" ;
			var confirmValue = $(this).attr("result_id");
			var searchCompany = "Y";
			if (confirmValue == "L") searchCompany = "N";
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':'','searchCompany':searchCompany,'searchBgnDe':$("#searchBgnDe").val(),'searchEndDe':$("#searchEndDe").val(),'searchGubun':$("#searchGubun").val(),'searchConfirm':$("#searchConfirm").val(),'searchCom':$("#searchCom").val()};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,0,0,870,800,'auto');
        });
 
		$("a[name='filereg']").click(function(){
			var url = "/cop/bbs/SelectBBSFileUploadPop.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchBgnDe':$("#searchBgnDe").val(),'searchEndDe':$("#searchEndDe").val(),'searchGubun':$("#searchGubun").val(),'searchConfirm':$("#searchConfirm").val(),'searchCom':$("#searchCom").val()};
			var popName = "fileReg";
			setPostPopupWin(url,param,popName,0,0,550,500,'auto');

        });

		
	});
	function allCheckFunc( obj ) {
		//$("[name=checkOne]").prop("checked", $(obj).prop("checked") );
		if( $(obj).prop("checked") )
		{
			var chkValue = new Array;
			
			$('input:checkbox[name="checkOne"]').each(function() {
				var splitDATA = $(this).val().split("-");
				chkValue.push(splitDATA[1])
			});
			
			var chkValueFnr = new Array;
			$('input:checkbox[name="checkOne"]').each(function() {
				var splitSubDATA = $(this).val().split("-");
				if ($.inArray(splitSubDATA[1], chkValue) > -1){
					if ($.inArray(splitSubDATA[0], chkValueFnr) == -1){
						$(this).prop("checked", true);
						chkValueFnr.push(splitSubDATA[0]);
					}
					else{
						$(this).prop("checked", false);
					}
				}
				else{
					$(this).prop("checked", true);
					chkValueFnr.push(splitSubDATA[0]);
				}
	
			});
		}
		else{
			$("[name=checkOne]").prop("checked", $(obj).prop("checked") );
		}
		
		checkBoxLength = $("[name=checkOne]").length;
		checkedLength = $("[name=checkOne]:checked").length;

		if( checkBoxLength == checkedLength ) {
			$("[name=checkAll]").prop("checked", true);
		} else {
			$("[name=checkAll]").prop("checked", false);
		}
		
	}
	
	/* 체크박스 체크시 전체선택 체크 여부 */
	function oneCheckFunc( obj )
	{
		var allObj = $("[name=checkAll]");
		var objName = $(obj).attr("name");
		
		var chkValue = new Array;
		
		$('input:checkbox[name="checkOne"]').each(function() {

			if ($(this).prop("checked") && $(this).attr("id") != $(obj).attr("id")){
				var splitDATA = $(this).val().split("-");
				chkValue.push(splitDATA[0])
			}
		});
		
	
		if( $(obj).prop("checked") )
		{
			var splitSubDATA = $(obj).val().split("-");
			if ($.inArray(splitSubDATA[0], chkValue) > -1){
				alert("동일한 조건유형은 하나만 선택가능합니다.");
				$(obj).prop("checked", false);
				return;
			}
			
			checkBoxLength = $("[name="+ objName +"]").length;
			checkedLength = $("[name="+ objName +"]:checked").length;
	
			if( checkBoxLength == checkedLength ) {
				allObj.prop("checked", true);
			} else {
				allObj.prop("checked", false);
			}
		}
		else
		{
			allObj.prop("checked", false);
		}
	}


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
                            <li><strong>수입부대비용마감 상세정보</strong></li>
                        </ul>
                    </div>
                </div>
                
                <!-- 검색 필드 박스 시작 -->
                <div id="search_field"> 
                    <div id="search_field_loc"><h2><strong>수입부대비용마감 상세정보</strong></h2></div>
                    
					
					<form name="frm" method="post">
                        <input type="hidden" id = "searchType" name="searchType"/> 
                        <input type="hidden" id = "t3_confirm" name="t3_confirm" /> 
                        <input type="hidden" id = "searchMonth" name="searchMonth" />
                        
                        <fieldset><legend>조건정보 영역</legend>    
                        <div class="sf_start">
                            <ul id="search_first_ul">
                            	<li>
	                                <label for="searchBgnDe" >전기일자</label>
	                                <input size = "10" maxlength ="8" type="text" id="searchBgnDe" name="searchBgnDe"  value='<c:out value="${searchVO.searchBgnDe}"/>' > ~ 
	                                <input size = "10" maxlength ="8" type="text" id="searchEndDe" name="searchEndDe"  value='<c:out value="${searchVO.searchEndDe}" />' >
                                </li>
                                
                                <li>
                                	<label for="searchGubun" >부가세유형</label>
                                	<select id="searchGubun" name="searchGubun" title="부가세유형선택" onchange="myFunction(this.value)">
					                   <option value="" <c:if test="${searchVO.searchGubun == ''}">selected="selected"</c:if>> == 선택 == </option>
					                   <option value="MON" <c:if test="${searchVO.searchGubun == 'MON'}">selected="selected"</c:if>>일반부가세</option>
					                   <option value="BL" <c:if test="${searchVO.searchGubun == 'BL'}">selected="selected"</c:if>>관,부가세</option>
					                </select>
                                </li>
                            	
                                <li>
                                    <label for="searchConfirm" >확정유무</label>
					                <select id="searchConfirm" name="searchConfirm" title="확정유무선택" onchange="myFunction(this.value)">
					                   <option value="" <c:if test="${searchVO.searchConfirm == ''}">selected="selected"</c:if>> == 선택 == </option>
					                   <option value="Y" <c:if test="${searchVO.searchConfirm == 'Y'}">selected="selected"</c:if>>확정</option>
					                   <option value="N" <c:if test="${searchVO.searchConfirm == 'N'}">selected="selected"</c:if>>미확정</option>
					                </select>
                                </li>
                                <li>
                                <label for="searchCom" >업체명</label>
                                <input maxlength="11" type="text" id="searchCom" name="searchCom"   size="10" value='<c:out value="${searchVO.searchCom}"/>' >
                                </li>
                            
                                <li>
                                
                                    <div class="buttons" style="position:absolute;left:1230px;top:180px;">
                                       <a href="#" onclick="fn_egov_select_brdMstr2('1'); return false;">
                                       		<img src="<c:url value='/images/img_search.gif' />" alt="search" />조회  </a>
                                    </div>                              
                                </li>
                            	<li>
                            		<div  style="position:absolute;left:1320px;top:190px;">
	                                <label style="background-color:#FFFFFF;width:60px;display: inline-block;text-align:center;font-size:12px;">미확인</label>
	                                <label style="background-color:#E4F7BA;width:60px;display: inline-block;text-align:center;font-size:12px;">확인</label>
	                                </div>  
                                </li>
								 <% if (userId.equals("admin") || userId.equals("99000303") || userId.equals("20170034")) {%>
                                <li>
                                 	<div class="buttons" style="position:absolute;left:1450px;top:180px;">
                                       <a href="#" onclick="fn_excel_down(); return false;">월부가세액다운</a>
                                    </div>   
                                </li> 
                                <li>
                                 	<div class="buttons" style="position:absolute;left:1605px;top:180px;">
                                       <a name="fileupload" style="cursor: pointer">관부가세업로드</a>
                                    </div>   
                                </li> 
                                <%} %>            
					    		<li>
                                 	<div class="buttons" style="position:absolute;left:1750px;top:180px;">
                                       <a href="#" onclick="fn_egov_confirm('Y'); return false;">확인</a>
                                    </div>   
                                </li>  
							    
					    		<li>
                                 	<div class="buttons" style="position:absolute;left:1810px;top:180px;">
                                       <a href="#" onclick="fn_egov_confirm('D'); return false;">삭제</a>
                                    </div>   
                                </li>  
                                
                            </ul>
                        </div>          
                        </fieldset>
                    </form>
                    
                    
                </div>
                
                <!-- table add start -->
                <div class="default_tablestyle">
                    
                    <table id="disabled" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <caption>사용자목록관리</caption>
                    
                    <colgroup>
                    <col width="2%">
                    <col width="5%">  
                    <col width="5%">
                    
                    <col width="7%">
                    <col width="6%">  
                    <col width="10%">  
                    <col width="*">
                    <col width="4%">
                    <col width="6%">
                    <col width="6%">
                    <col width="6%">
                    <col width="5%">
                    <col width="6%">
                    <col width="8%">
                    <col width="5%">
                    <col width="7%">
                    <col width="5%">
                    <col width="5%">
                    </colgroup>
                    
                    <thead>
                    <tr>
                    	<th scope="col" nowrap="nowrap" ><input name="checkAll" class="checkBtn" id="checkAll" type="checkbox"></th>
                        <th scope="col" nowrap="nowrap">증빙일</th>
                        <th scope="col" nowrap="nowrap">전기일</th>
                        <th scope="col" nowrap="nowrap">전표번호</th>
                        <th scope="col" nowrap="nowrap">조건유형</th>
                        <th scope="col" nowrap="nowrap">공급업체</th>
                        <th scope="col" nowrap="nowrap">신고자상호</th>
                        <th scope="col" nowrap="nowrap">통화</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>현지금액</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>부가세</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>합계금액</th>
                        <th scope="col" nowrap="nowrap">세액코드</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>과세표준</th>
                        <th scope="col" nowrap="nowrap">수입신고번호</th>
                        <th scope="col" nowrap="nowrap">B/L번호</th>
                        <th scope="col" nowrap="nowrap">사업자번호</th>
                        <th scope="col" nowrap="nowrap">관련파일</th>
                        <th scope="col" nowrap="nowrap">파일등록</th>
                    </tr>
                    </thead>
                    
                    
                    
                    <tbody>                 

                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr class="normaltr"  bgcolor="#${result.sapcolor}">
						<td nowrap="nowrap" >
						<c:choose>
					    	<c:when test = "${result.confirm_yn == 'Y'}">
					    		<input type="checkbox"  id="checkOne${status.count}" name="checkOne" class="checkBtn" checked value="${result.statement_key}">
					    	</c:when>
					    	<c:otherwise>
					    		<input type="checkbox"  id="checkOne${status.count}" name="checkOne" class="checkBtn" value="${result.statement_key}">
					    	</c:otherwise>
					    </c:choose>
					    </td>
					    <td nowrap="nowrap"><c:out value="${result.bldat}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.bldat}"  default=""/></td>
						<td nowrap="nowrap"><c:out value="${result.zfacdo}" default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.gubun_nm}"  default=""/></td>
					    <td nowrap="nowrap" class='celll'><c:out value="${result.name1}"  default=""/></td>
					    <td nowrap="nowrap" class='celll'><c:out value="${result.zfapnm}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.waers1}"  default=""/></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.twrbtr}" pattern="#,###" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.wrbtr_tax}" pattern="#,###" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.extra_total}" pattern="#,###" /></td>
					    <td nowrap="nowrap"><c:out value="${result.mwskz}"  default=""/></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.swrbtr}" pattern="#,###" /></td>
					    <td nowrap="nowrap"><c:out value="${result.zfidrno}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.zfblno}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.stcd2}"  default=""/></td>
					    <% if (userId.equals("admin") || userId.equals("99000303")) {%>
					    <c:choose>
					    	<c:when test = "${result.filecnt == 0}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="fileview"  content_id="${result.statement_key}" result_id="Y"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
			    		<td><a name="filereg"  content_id="${result.statement_key}" result_id="Y"><img src="/images/filereg.png" height="25" border="0" style="cursor: pointer" /></a></td>
			    		<%} else{ %>
					    <c:choose>
					    	<c:when test = "${result.filecnt == 0}">
					    		<td></td>
					    	</c:when>
					    	<c:otherwise>
					    		<td><a name="fileview"  content_id="${result.statement_key}" result_id="N"><img src="/images/file.png" height="25" border="0" style="cursor: pointer" /></a></td>
					    	</c:otherwise>
					    </c:choose>
			    		<td></td>
			    		<%} %>
					  </tr>
	                </c:forEach>	  
					<c:forEach var="result" items="${totalList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr class="normaltr">
						<td nowrap="nowrap" colspan="3"><c:out value="${result.key}"  default=""/> 합계</td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap" ></td>
					    <td nowrap="nowrap" ></td>
					    <td nowrap="nowrap"><c:out value="${result.key}"  default=""/></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.value.get('twrbtr')}" pattern="#,###" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.value.get('wrbtr_tax')}" pattern="#,###" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.value.get('extra_total')}" pattern="#,###" /></td>
					    <td nowrap="nowrap" ></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.value.get('swrbtr')}" pattern="#,###" /></td>
					    <td nowrap="nowrap" ></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"></td>
					    <td nowrap="nowrap"></td>
					  </tr>
	                </c:forEach>	 	  
					<c:if test="${fn:length(resultList) == 0}">
					  <tr>
					    <td nowrap colspan="18"><spring:message code="common.nodata.msg" /></td>  
					  </tr>		 
					</c:if>
			        
			        </tbody>
	                </table>
	                
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
	
	    $("#disabled").colResizable({
	          liveDrag:true, 
	          gripInnerHtml:"<div class='grip'></div>", 
	          draggingClass:"dragging", 
	          resizeMode:'overflow',
	          disabledColumns: [14]
	      });

	    // always overwrite window.name, in case users try to set it manually
	    window.name = "resultIncome"
	  </script>

    
 </body>
</html>