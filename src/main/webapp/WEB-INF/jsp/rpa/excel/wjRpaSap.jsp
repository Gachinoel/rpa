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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js" ></script>
   <script type="text/javascript" src="&quot;https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
  
  
<meta http-equiv="Content-Language" content="ko" >
<title>게시판 목록</title>
<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >

<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />"></script>



<!-- jQuery  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    
    <!-- bootstrap JS -->
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    


<script type="text/javascript">	
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_brdMstr('1');
		}
	}
	
	
	
	function fn_validation(){
		var stDt = searchBgnDe.value; 
		alert(stDt);
		
		if(searchBgnDe.value ==""){
			alert("시작일을 입력하세요!")
			return false;
		};
		
		if(searchEndDe.value ==""){
			alert("종료일을 입력하세요!")
			return false;
		};
	}
	
	function fn_wj_select()
	{
		alert("1");
		/*
		fn_validation();
		
		if (chkInput == false){
			alert("ok");
			return;
			
		}
		*/
		
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/wj/rpa/excel/selectRpaMain.do'/>";
		document.frm.submit();	
	}
	
	
	
	
	
	function fn_wj_excel_download(num){
		
		if($('#searchBlno').val() ==""){
			alert("확정대상이 없습니다!");
			return;
		}
		//document.frm.bbsId.value = bbsId;
		document.frm.action = "<c:url value='/wj/rpa/excel/SelectBBSMasterInfsPop.do'/>";
		document.frm.submit();
		
	}
	
   $(function(){
	   //alert("뭐지")
	   $('.lt_text3').click(function(){
		  //alert("체크");
		  if($("input:checkbox[id='checkBtn']").is(":checked") == true){

			  var row_index = $(this).parent().index('tr');
			  var col_index = $(this).index('tr:eq('+row_index+') td');
		      //alert('Row # '+(row_index)+' Column # '+(col_index));
			  var data1 =$('tr:eq('+ row_index+') >td:eq(1)').html();
			  //alert("bl번호" + data1);
		      var data3 =$('tr:eq('+ row_index+') >td:eq(3)').html();
			  var data4 =$('tr:eq('+ row_index+') >td:eq(4)').html();
			  var data5 =$('tr:eq('+ row_index+') >td:eq(5)').html();
			  var data6 =$('tr:eq('+ row_index+') >td:eq(6)').html();
			  if(data3 == "확정" && data4 == "확정" && data5 == "확정" && data6 == "확정"   ){
			  	 //alert("확정대상");
			  	 $('#searchBlno').val(data1);	  
			  	
			  }else{
				  //alert("미확정대상");
				  $('#searchBlno').val("");	  
				  
				  $("input:checkbox[id='checkBtn']").prop("checked", false);
				  /*
				  
				  //원본
				  //$("input:checkbox[id='checkBtn']").prop("checked", false); 
				  $("input:checkbox[id='checkBtn["+ row_index+"]']").prop("checked", false); 
				  $('tr:eq('+ row_index+') >td:eq(3)').css("background-color","blue");
				  $('tr:eq('+ row_index+') >td:eq(3)').text("확정");
				  $('tr:eq('+ row_index+') >td:eq(4)').css("background-color","blue");
				  $('tr:eq('+ row_index+') >td:eq(4)').text("확정");
				  $('tr:eq('+ row_index+') >td:eq(5)').css("background-color","blue");
				  $('tr:eq('+ row_index+') >td:eq(5)').text("확정");
				  $('tr:eq('+ row_index+') >td:eq(6)').css("background-color","blue");
				  $('tr:eq('+ row_index+') >td:eq(6)').text("확정");
				  
				  */  
			  }
		  }else{
			  fn_egov_select_brdMstr('1');
		  }
		}); 
	
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
                            <li><strong>SAP전표</strong></li>
                        </ul>
                    </div>
                </div>
                
                <!-- 검색 필드 박스 시작 -->
                <div id="search_field"> 
                    <div id="search_field_loc"><h2><strong>SAP전표 정보</strong></h2></div>
                    
					
					<form name="frm" action="<c:url value='/wj/rpa/excel/selectRpaMain.do'/>" method="post">
					
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
                        <input id = "searchBlno" name="searchBlno" type="text" />
                        
                        
                        <fieldset><legend>조건정보 영역</legend>    
                        <div class="sf_start">
                            
                            <ul id="search_first_ul">
                            	<li>
                                <label for="searchBgnDe" >BL일자</label>
                                <input type="date" id="searchBgnDe" name="searchBgnDe"  value='<c:out value="${searchVO.searchBgnDe}"/>' >
                                <input type="date" id="searchEndDe" name="searchEndDe"  value='<c:out value="${searchVO.searchEndDe}" />' >
                                </li>
                                
                                <li>
                                <label for="searchWrd" >BL번호</label>
                                <input maxlength="11" type="text" id="searchWrd" name="searchWrd"  value='<c:out value="${searchVO.searchWrd}" />' size="11">
                                </li>
                            	
                                <li>
                                    <label for="searchCnd" >확정유무</label>
					                <select id="searchCnd" name="searchCnd" title="검색유형선력" onchange="myFunction(this.value)">
					                   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if>>Yes</option>
					                   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if>>No</option>
					                </select>
                                </li>
                            
                                <li>
                                
                                    <div class="buttons" style="position:absolute;left:1130px;top:175px;">
                                       <a href="#" onclick="fn_wj_select(); return false;">
                                       		<img src="<c:url value='/images/img_search.gif' />" alt="search" />조회</a>
                                    </div>                              
                                </li>
                                
                                <li>
                                 	<div class="buttons" style="position:absolute;left:1200px;top:175px;">
                                       <a href="#" onclick="fn_wj_excel_download(this); return false;">확정</a>
                                    </div>   
                                </li>
                                
                                <li>
                                 	<div class="buttons" style="position:absolute;left:1250px;top:175px;">
                                       <a href="#" onclick="fn_wj_excel_download('1'); return false;">확정취소</a>
                                    </div>   
                                </li>
                                
                            </ul>
                        </div>          
                        </fieldset>
                    </form>
                    
                    
                    
                    
                    
                </div>
                <!-- //검색 필드 박스 끝 -->

                <!-- div id="page_info"><div id="page_info_align">총 <strong>321</strong>건 (<strong>1</strong> / 12 page)</div></div-->                    
                
                
                <!-- table add start -->
                <div class="default_tablestyle">
                    
                    <table id = "find_table" summary="선택,BL번호,BL일자,INVOICE,적하보험,BL,수입신고필증,물대,적하보험,관세,취급수수료,운반비,통관수수료,보관료,내륙운송료,관세,이용료,기타  목록입니다" cellpadding="0" cellspacing="0">
                    <caption>사용자목록관리</caption>
                    
                    <colgroup>
                    <col width="5%">
                    <col width="7%">  
                    <col width="7%">
                    <col width="5%">
                    <col width="5%">
                    <col width="5%">
                    <col width="5%">
                    
                    <col width="5%">
                    <col width="5%">  
                    <col width="5%">
                    <col width="5%">
                    <col width="5%">
                    <col width="5%">
                    <col width="5%">
                    <col width="5%">
                    <col width="5%">  
                    <col width="5%">
                    <col width="5%">
                    
                    </colgroup>
                    
                    <thead>
                    <tr>
                        <th rowspan ="2" nowrap="nowrap">선택</th>
                        <th rowspan ="2" scope="row" nowrap="nowrap">BL번호</th>
                        <th rowspan ="2" scope="row" nowrap="nowrap">BL일자</th>
                        
                        <th colspan ="4" scope="col" nowrap="nowrap">통관정보</th>
                        <th colspan ="11" scope="col" nowrap="nowrap">전표</th>
                    </tr>
                    <tr>    
                        <th scope="col" nowrap="nowrap">INVOICE</th>
                        <th scope="col" nowrap="nowrap">적하보험</th>
                        <th scope="col" nowrap="nowrap">BL</th>
                        <th scope="col" nowrap="nowrap">수입신고필증</th>
                    
                        <th scope="col" nowrap="nowrap">물대</th>
                        <th scope="col" nowrap="nowrap">적하보험</th>
                        <th scope="col" nowrap="nowrap">관세</th>
                        <th scope="col" nowrap="nowrap">취급수수료</th>
                        <th scope="col" nowrap="nowrap">운반비</th>
                        <th scope="col" nowrap="nowrap">통관수수료</th>
                        <th scope="col" nowrap="nowrap">보관료</th>
                        
                        <th scope="col" nowrap="nowrap">내륙운송료</th>
                        <th scope="col" nowrap="nowrap">관세</th>
                        <th scope="col" nowrap="nowrap">이용료</th>
                        <th scope="col" nowrap="nowrap">기타</th>
                        
                    </tr>
                    </thead>
                    
                    
                    
                    <tbody>                 

                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr>

					    <td class="lt_text3" nowrap="nowrap" >
					    	<strong><input type="checkbox"  id="checkBtn"  class="checkBtn" onclick="fn_Check();"></strong>
					    </td>
					    
					    
						<td nowrap="nowrap"><c:out value="${result.t3_blno}" default="foo"/></td>
					    <td nowrap="nowrap"><c:out value="${result.t3_sjdt}"  default="foo"/></td>
					    
					    <c:set var="td1" value="${result.t1_yn}" ></c:set>
					    <c:choose>
					    	<c:when test = "${td1 == '확정'}">
					    		<td id ="aa" bgcolor ="blue"">확정</td>
					    	</c:when>
					    	
					    	<c:when test = "${td1 == '등록'}">
					    		<td id ="aa" bgcolor ="yellow"">등록</td>
					    	</c:when>
					    	
					    	<c:when test = "${td1 == '미등록' }">
					    		<td id ="aa" bgcolor ="gray"">미등록</td>
					    	</c:when>
					    	<c:otherwise>
					    		<td id ="aa" bgcolor ="red"">에러</td>
					    	</c:otherwise>
					    </c:choose>
					    
					    
					    
					    <c:set var="td2" value="${result.t2_yn}" ></c:set>
					    <c:choose>
					    	<c:when test = "${td2 == '확정'}">
					    		<td id ="bb" bgcolor ="blue"">확정</td>
					    	</c:when>
					    	
					    	<c:when test = "${td2 == '등록' }">
					    		<td id ="bb" bgcolor ="yellow"">등록</td>
					    	</c:when>
					    	<c:when test = "${td2 == '미등록' }">
					    		<td id ="aa" bgcolor ="gray"">미등록</td>
					    	</c:when>
					    	<c:otherwise>
					    		<td id ="bb" bgcolor ="red"">에러</td>
					    	</c:otherwise>
					    </c:choose>
					    
					    <c:set var="td3" value="${result.t3_yn}" ></c:set>
					    <c:choose>
					    	<c:when test = "${td3 == '확정'}">
					    		<td id ="cc" bgcolor ="blue"">확정</td>
					    	</c:when>
					    	
					    	<c:when test = "${td3 == '등록' }">
					    		<td id ="cc" bgcolor ="yellow"">등록</td>
					    	</c:when>
					    	<c:when test = "${td3 == '미등록' }">
					    		<td id ="cc" bgcolor ="gray"">미등록</td>
					    	</c:when>
					    
					    	<c:otherwise>
					    		<td id ="cc" bgcolor ="red"">에러</td>
					    	</c:otherwise>
					    </c:choose>
					    
					    <c:set var="td4" value="${result.t4_yn}" ></c:set>
					    <c:choose>
					    	<c:when test = "${td4 == '확정'}">
					    		<td id ="dd" bgcolor ="blue"">확정</td>
					    	</c:when>
					    	
					    	<c:when test = "${td4 == '등록' }">
					    		<td id ="dd" bgcolor ="yellow"">등록</td>
					    	</c:when>
					    	<c:when test = "${td4 == '미등록' }">
					    		<td id ="dd" bgcolor ="gray"">미등록</td>
					    	</c:when>
					    	<c:otherwise>
					    		<td dd ="dd" bgcolor ="red"">에러</td>
					    	</c:otherwise>
					    </c:choose>
					    
					    
					    
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    <td bgcolor ="gray"">미등록</td>
					    
					    
					    <!-- 
					    <td nowrap="nowrap"><c:out value="${result.t1_yn}"  default="foo"/></td>
					    <td nowrap="nowrap"><c:out value="${result.t2_yn}" default="foo"/></td>
					    <td nowrap="nowrap"><c:out value="${result.t3_yn}" default="foo"/></td>
					    <td nowrap="nowrap"><c:out value="${result.t4_yn}" default="foo"/></td>
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
					    <td nowrap colspan="6"><spring:message code="common.nodata.msg" /></td>  
					  </tr>		 
					</c:if>
			        
			        </tbody>
	                </table>
                </div>
		        
		        <!-- 페이지 네비게이션 시작 -->
		        <div id="paging_div">
                    <ul class="paging_align">
                       <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_brdMstr"  />
                    </ul>
		        </div>                          
                <!-- //페이지 네비게이션 끝 -->
                
                  
            <!-- //content 끝 -->    
        </div>  
        <!-- //container 끝 -->
	    <!-- footer 시작 -->
        <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
	    <!-- //footer 끝 -->
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
	
	    // always overwrite window.name, in case users try to set it manually
	    window.name = "result"
	  </script>

    
 </body>
</html>