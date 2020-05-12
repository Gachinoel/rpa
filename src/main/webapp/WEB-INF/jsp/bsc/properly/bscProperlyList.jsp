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
<title>적정재고정합성</title>
<link href="/css/common.css?<%=today%>" rel="stylesheet" type="text/css" >

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
		document.frm.action = "<c:url value='/bsc/properly/selectBscProperly.do'/>";
		document.frm.submit();
			
	}
	
	function fn_egov_list(gubun,item){
		$('#searchGubun').val(gubun);
		$('#searchItem').val(item);
		document.frm.action = "<c:url value='/bsc/properly/selectBscProperly.do'/>";
		document.frm.submit();
			
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

    <!-- container 시작 -->
    <div id="container">
    
            <!-- 현재위치 네비게이션 시작 -->
            <div id="content_bsc">
                
                <!-- 검색 필드 박스 시작 -->
                <div id="search_field_bsc"> 
                    <div id="search_field_bsc_loc" style="width:100%;text-align:center;font-size:40px;font-weight:bold;height:40px;margin-top:10px;">적정재고정합성</div>
                    
					
					<form name="frm" method="post">
                        <input type="hidden" id = "searchGubun" name="searchGubun" value='<c:out value="${searchVO.searchGubun}"/>' />
                        <input type="hidden" id = "searchItem"  name="searchItem" value='<c:out value="${searchVO.searchItem}"/>' />
                        <input type="hidden" id = "searchMonth"  name="searchMonth" value='<c:out value="${searchVO.searchMonth}"/>' />
                    </form>
                    
                    
                </div>
                
                <!-- table add start -->
                <div class="bactable" style="color:black">
                    
                    <table id = "yetlist" class="bsctable" summary="업체명,세부내역,관세사,공급가액,금액  목록입니다" cellpadding="0" cellspacing="0" style="width:100%;">
                    <caption>사용자목록관리</caption>
                    
                    <colgroup>
                    <col width="10%">
                    <c:choose>
				    	<c:when test = "${btnGubun == 'SPART' || btnGubun == 'PSPART'}">
				    		<col width="15%">
				    	</c:when>
				    	<c:when test = "${btnGubun == 'MTART' || btnGubun == 'PMTART'}">
				    		<col width="15%">
				    	</c:when>
				    </c:choose>  
                    <col width="13%">
                    <col width="12%">
                    <col width="12%">
                    <col width="12%">
                    <col width="10%">
                    </colgroup>
                    
                    <thead>
                    <tr>    
                        <th scope="col" nowrap="nowrap" rowspan="2">항목</th>
                        <c:choose>
					    	<c:when test = "${btnGubun == 'SPART' || btnGubun == 'PSPART'}">
					    		<th scope="col" nowrap="nowrap" rowspan="2">자재유형</th>
					    	</c:when>
					    	<c:when test = "${btnGubun == 'MTART' || btnGubun == 'PMTART'}">
					    		<th scope="col" nowrap="nowrap" rowspan="2">아이템유형</th>
					    	</c:when>
					    </c:choose>                        
                        <th scope="col" nowrap="nowrap" rowspan="2" class='cellr'>목표금액</th>
                        <th scope="col" nowrap="nowrap" colspan="3">재고금액</th>
                        <th scope="col" nowrap="nowrap" rowspan="2"  class="endth">달성율</th>
                    </tr>
                    <tr>
                        <th scope="col" nowrap="nowrap" class='cellr'>안전재고</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>가용재고</th>
                        <th scope="col" nowrap="nowrap" class='cellr'>재고금액</th>
                    </tr>
                    </thead>
                    
                    <tbody>                 

                    <c:forEach var="result" items="${resultList}" varStatus="status">
                    <!-- loop 시작 -->                                
					  <tr class="normaltr"  bgcolor="#${result.bgcolor}">
						<td nowrap="nowrap"><c:out value="${result.item_nm}"  default=""/></td>
					  	<c:choose>
					    	<c:when test = "${btnGubun == 'SPART' || btnGubun == 'PSPART'}">
					    		<td nowrap="nowrap"><c:out value="${result.spart_nm}"  default=""/></td>
					    	</c:when>
					    	<c:when test = "${btnGubun == 'MTART' || btnGubun == 'PMTART'}">
					    		<td nowrap="nowrap"><c:out value="${result.mtart_nm}" default=""/></td>
					    	</c:when>
					    </c:choose>
					    
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.goal_amt/100000000}" pattern="#,##0.0" />억&nbsp;</td>
					    <td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.safe_amt/100000000}" pattern="#,##0.0" />억&nbsp;</td>
					    <c:choose>
					    	<c:when test = "${btnGubun == 'ALL' && result.item != 'TOT'}">
					    		<td nowrap="nowrap" class='cellr'><a href="#" onclick="fn_egov_list('SPART','${result.item}'); return false;"><fmt:formatNumber value="${result.use_amt/100000000}" pattern="#,##0.0" />억</a>&nbsp;</td>
					    	</c:when>
					    	<c:otherwise>
					    		<td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.use_amt/100000000}" pattern="#,##0.0" />억&nbsp;</td>
					    	</c:otherwise>
					    </c:choose>
					    
					    <c:choose>
					    	<c:when test = "${btnGubun == 'ALL' && result.item != 'TOT'}">
					    		<td nowrap="nowrap" class='cellr'><a href="#" onclick="fn_egov_list('MTART','${result.item}'); return false;"><fmt:formatNumber value="${result.inven_amt/100000000}" pattern="#,##0.0" />억</a>&nbsp;</td>
					    	</c:when>
					    	<c:otherwise>
					    		<td nowrap="nowrap" class='cellr'><fmt:formatNumber value="${result.inven_amt/100000000}" pattern="#,##0.0" />억&nbsp;</td>
					    	</c:otherwise>
					    </c:choose>
					    
					    <c:set var="achieve_rate" value="${result.achieve_rate}" />
						<fmt:parseNumber var="rate" type="number" value="${achieve_rate}" />
					    <c:choose>
					    	<c:when test = "${rate == 0}">
					    		<td nowrap="nowrap" class='cellr endtd'> </td>
					    	</c:when>
					    	<c:otherwise>
					    		<td nowrap="nowrap" class='cellr endtd'><fmt:formatNumber value="${result.achieve_rate}" pattern="#,###" />%</td>
					    	</c:otherwise>
					    </c:choose>
					  </tr>
	                </c:forEach>
					
					<c:if test="${fn:length(resultList) == 0}">
					  <tr>
					  	<c:choose>
					    	<c:when test = "${btnGubun == 'ALL'}">
					    		<td nowrap colspan="6"><spring:message code="common.nodata.msg" /></td>
					    	</c:when>
					    	<c:when test = "${btnGubun != 'ALL'}">
					    		<td nowrap colspan="7"><spring:message code="common.nodata.msg" /></td>
					    	</c:when>
					    </c:choose>
					  </tr>		 
					</c:if>
			        
			        </tbody>
			        </table>
			         <c:if test="${btnGubun != 'ALL' && btnGubun != 'PSPART' && btnGubun != 'PMTART'}">
				         <div style="width:100%;text-align:center;">
					        <br />
					        <div class="buttons" style="display: inline-block;">
								<a href="#" onclick="fn_egov_list('ALL',''); return false;">목록</a>
							</div>
						</div>
					</c:if>
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