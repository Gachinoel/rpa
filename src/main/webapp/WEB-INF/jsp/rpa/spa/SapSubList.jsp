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
		
		document.frm.action = "<c:url value='/cop/bbs/SelectBBSSapInfs.do'/>";
		
		document.frm.submit();
			
	}
	
	

	function fn_egov_excel_download(num){
		
		
		document.frm.action = "<c:url value='/cop/bbs/selectSapSubListExcel.do'/>";
		
		document.frm.submit();
		
	}
	
	function fn_egov_firm(num){
		
		if(num == 'L'){
			var result = confirm('승인 진행을 하시겠습니까?'); 
			if(result) {
				$('#t3_confirm').val("L");
			} else { 
				return fase;
			} 
		}
		else if(num == 'Y'){
			var result = confirm('확인 진행을 하시겠습니까?'); 
			if(result) {
				if ($('#t3_confirm').val() == 'T'){
					$('#t3_preconfirm').val($('#t3_confirm').val());
				}
				$('#t3_confirm').val("F");
			} else { 
				return fase;
			} 
		}
		else if(num == 'T'){
			var result = confirm('임시저장을 하시겠습니까?'); 
			if(result) {
				var checkCnt = $("input:checkbox[name='checkOne']:checked").length;
				var items=[]; $('input[name="checkOne"]:checkbox:checked').each(function(){
					items.push($(this).val());
				}); 
				var tmp = items.join(','); 
				
				if (checkCnt == 0){
					alert("임시저장할 항목을 선택해주세요.");
					return false;
				}
				$('#t3_preconfirm').val($('#t3_confirm').val());
				$('#checkContType').val(tmp);
				$('#t3_confirm').val("T");
			} else { 
				return fase;
			} 
		}
		else if(num == 'N'){
			var result = confirm('승인 취소를 하시겠습니까?'); 
			if(result) { 
				$('#t3_confirm').val($('#t3_preconfirm').val());
				$('#t3_preconfirm').val("");
			} else { 
				return fase; 
			} 
		}
		
		document.frm.action = "<c:url value='/cop/bbs/FinishBBSMasterInf.do'/>";
		
		document.frm.submit();
		
	}
	
	
	$(function(){
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
		
		$("a[name='filepopup']").click(function(){
			var url = "/cop/bbs/selectFileSap.do" ;
			var param = {'searchBlNo':$(this).attr("content_id"),'searchFileCn':$(this).attr("ext_id"),'searchCompany':'N'};
			var popName = "fileView";
			setPostPopupWin(url,param,popName,10,0,870,800,'auto');
        });
		
		$("[name=checkAll]").click(function(){
			allCheckFunc( this );
		});
		$("[name=checkOne]").each(function(){
			$(this).click(function(){
				oneCheckFunc( $(this) );
			});
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
                            <li><strong>SAP 전표</strong></li>
                        </ul>
                    </div>
                </div>
                
                <!-- 검색 필드 박스 시작 -->
                <div id="search_field"> 
                    <div id="search_field_loc"><h2><strong>SAP 전표 상세 정보</strong></h2></div>
                    
					
					<form name="frm" method="post">
						<!-- 
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
                         -->
                        <input type="hidden" id = "searchBlNo" name="searchBlNo"  value="<c:out value='${searchVO.searchBlNo}'/>"/>
                        <input type="hidden" id = "searchBgnDe" name="searchBgnDe"  value="<c:out value='${searchVO.searchBgnDe}'/>"/>
                        <input type="hidden" id = "searchEndDe" name="searchEndDe"  value="<c:out value='${searchVO.searchEndDe}'/>"/>
                        <input type="hidden" id = "searchBl" name="searchBl"  value="<c:out value='${searchVO.searchBl}'/>"/>
                        <input type="hidden" id = "searchCnd" name="searchCnd"  value="<c:out value='${searchVO.searchCnd}'/>"/>                        
                        <input type="hidden" id = "t3_confirm" name="t3_confirm" value="<c:out value='${t3_confirm}'/>" />
                        <input type="hidden" id = "t3_preconfirm" name="t3_preconfirm" value="<c:out value='${t3_preconfirm}'/>" />
                        <input type="hidden" id = "checkContType" name="checkContType"/>                        
                        
                        
                        <fieldset><legend>조건정보 영역</legend>    
                        <div class="sf_start">
                            
                            <ul id="search_first_ul">
                            	<li>
	                                <label style="background-color:#FFFFFF;width:130px;display: inline-block;text-align:center;font-size:12px;">미확인</label>
	                                <label style="background-color:#FFFF96;width:160px;display: inline-block;text-align:center;font-size:12px;">임시저장</label>
	                                <label style="background-color:#FFC0CB;width:180px;display: inline-block;text-align:center;font-size:12px;">역분개완료 전표</label>
	                                <label style="background-color:#E4F7BA;width:180px;display: inline-block;text-align:center;font-size:12px;">정상</label>
                                </li>
								 <% if (userId.equals("admin") || userId.equals("20120066")) {%>
								 <c:choose>
							    	<c:when test = "${t3_confirm == 'F'}">
							    		<li>
		                                 	<div class="buttons" style="position:absolute;left:1610px;top:180px;">
		                                       <a href="#" onclick="fn_egov_firm('L'); return false;">승인</a>
		                                    </div>   
		                                </li>  
							    	</c:when>
							    	<c:when test = "${t3_confirm == 'L'}">
							    		<li>
		                                 	<div class="buttons" style="position:absolute;left:1580px;top:180px;">
		                                       <a href="#" onclick="fn_egov_firm('N'); return false;">승인취소</a>
		                                    </div>   
		                                </li>  
							    	</c:when>
							    	<c:otherwise>
							    		<li>
		                                 	<div class="buttons" style="position:absolute;left:1515px;top:180px;">
		                                       <a href="#" onclick="fn_egov_firm('T'); return false;">임시저장</a>
		                                    </div>   
		                                </li>  
							    		<li>
		                                 	<div class="buttons" style="position:absolute;left:1610px;top:180px;">
		                                       <a href="#" onclick="fn_egov_firm('Y'); return false;">확인</a>
		                                    </div>   
		                                </li>  
							    	</c:otherwise>
						    	</c:choose>   
								<% }
								 else{
								 %>
								 <c:choose>
							    	<c:when test = "${t3_confirm != 'F' && t3_confirm != 'L'}">
							    		<li>
		                                 	<div class="buttons" style="position:absolute;left:1515px;top:180px;">
		                                       <a href="#" onclick="fn_egov_firm('T'); return false;">임시저장</a>
		                                    </div>   
		                                </li>  
							    		<li>
		                                 	<div class="buttons" style="position:absolute;left:1610px;top:180px;">
		                                       <a href="#" onclick="fn_egov_firm('Y'); return false;">확인</a>
		                                    </div>   
		                                </li>  
							    	</c:when>
						    	</c:choose>
								 <%} %>                 
						    	<li>
                                
                                    <div class="buttons" style="position:absolute;left:1670px;top:180px;">
                                       <a href="#" onclick="fn_egov_select_brdMstr2('1'); return false;">
                                       		<img src="<c:url value='/images/img_search.gif' />" alt="search" />목록 </a>
                                    </div>                              
                                </li>
                                <li>
                                 	<div class="buttons" style="position:absolute;left:1750px;top:180px;">
                                       <a href="#" onclick="fn_egov_excel_download('Y'); return false;">엑셀다운로드  </a>
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
                    	<th scope="col" nowrap="nowrap" ><input name="checkAll" class="checkBtn" id="checkAll" type="checkbox"></th>
                        <th scope="col" nowrap="nowrap">증빙일</th>
                        <th scope="col" nowrap="nowrap">전기일</th>
                        <th scope="col" nowrap="nowrap">BL번호</th>
                        <th scope="col" nowrap="nowrap">조건유형</th>
                        <th scope="col" nowrap="nowrap">공급업체</th>
                        <th scope="col" nowrap="nowrap">통화</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>현지금액</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>원화금액</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>환율</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>세액</th>
                        <th scope="col" nowrap="nowrap">세액코드</th>
                        <th scope="col" nowrap="nowrap">전표번호</th>
                        <th scope="col" nowrap="nowrap">사업자번호</th>
                        <th scope="col" nowrap="nowrap">관련파일</th>
                    </tr>
                    </thead>
                    
                    
                    
                    <tbody>                 

                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr class="normaltr"  bgcolor="#${result.sapcolor}">
						<td nowrap="nowrap" >
						<c:choose>
					    	<c:when test = "${result.sapcheck == '1'}">
					    		<input type="checkbox"  id="checkOne${status.count}" name="checkOne" class="checkBtn" checked value="${result.bktxt}${result.cond_type1}-${result.belnr}">
					    	</c:when>
					    	<c:otherwise>
					    		<input type="checkbox"  id="checkOne${status.count}" name="checkOne" class="checkBtn" value="${result.bktxt}${result.cond_type1}-${result.belnr}">
					    	</c:otherwise>
					    </c:choose>
					    	
					    </td>
					    <td nowrap="nowrap"><c:out value="${result.bldat}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.budat}"  default=""/></td>
						<td id="checkBtn"  class="checkBtn" class ="lt_text3"  nowrap="nowrap"><c:out value="${result.bktxt}" default=""/></td>
					    <td nowrap="nowrap" class='celll'><c:out value="${result.vtext}"  default=""/></td>
					    <td nowrap="nowrap" class='celll'><c:out value="${result.name1}"  default=""/></td>
					    <td nowrap="nowrap"><c:out value="${result.waers}"  default=""/></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.wrbtr}" pattern="#,###.00" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.dmbtr}" pattern="#,###.00" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.kursf}" pattern="#,###.00" /></td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber minIntegerDigits="1" value="${result.wmwst}" pattern="#,###.00" /></td>
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
					
					<c:if test="${fn:length(resultList) == 0}">
					  <tr>
					    <td nowrap colspan="15"><spring:message code="common.nodata.msg" /></td>  
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
	    window.name = "result"
	  </script>

    
 </body>
</html>