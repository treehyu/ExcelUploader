<%@ page import="com.col.dao.ExcelDAO"%>
<%@ page import="com.col.vo.ExcelVO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");

	ExcelDAO dao=new ExcelDAO();
	List<ExcelVO> list=null;
	ExcelVO vo=null;
	
	if(request.getParameter("t_tablename")==null && request.getParameter("t_colname")==null && request.getParameter("s_tablename")==null && request.getParameter("s_colname")==null)
	{
		try{
			list=dao.selectAll();
			
		}catch(Exception e){ System.out.println("Exception!!!!: "+e.getMessage()); }
		
	}else
	{
		vo=new ExcelVO();
		vo.setT_tableName(request.getParameter("t_tablename"));
		vo.setT_colName(request.getParameter("t_colname"));
		vo.setS_tableName(request.getParameter("s_tablename"));
		vo.setS_colName(request.getParameter("s_colname"));
		
		try{
			list=dao.search(vo);
			
		}catch(Exception e){ System.out.println("Exception!!: "+e.getMessage()); }
		
	}
	
	request.setAttribute("list", list);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>컬럼 매핑 정의서</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/colmap.css?ver=0.212">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function()
	{
		opt=$("#sel option:selected").val();
		
		if(opt=="tobe" || opt=="")
			$(".asis").hide();
		
		if(opt=="asis")
			$(".tobe").hide();
		
		$("#alertlayer").hide();
		
		$("input").keydown(function(e)
		{
			if(e.keyCode==13)
				validate('${pageContext.request.contextPath }');
		});
		
	});
</script>
</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath }/colmap.js?ver=0.301"></script>
 
 <div id="alertlayer">
 	데이터를 불러옵니다....
 </div>

 <header>
  <h2>컬럼 매핑 정의서</h2>
 </header>
 
 <div id="wrapper">
 	<div id="search">
 	  <form>
 	  	검색: 
 	  	<select id="sel" name="sel">
 	  		<option value="tobe" 
 	  		<c:if test="${param.sel=='tobe' }">selected="selected"</c:if>
 	  		>TOBE</option>
 	  		<option value="asis" 
 	  		<c:if test="${param.sel=='asis' }">selected="selected"</c:if>
 	  		>ASIS</option>
 	  	</select>
 	  	<input type="text" name="t_tablename" id="t_tablename" class="tobe" placeholder="TOBE 테이블명" value="${param.t_tablename }"/>
 	  	<input type="text" name="t_colname" id="t_colname" class="tobe" placeholder="TOBE 컬럼명" value="${param.t_colname }"/>
 	  	<input type="text" name="s_tablename" id="s_tablename" class="asis" placeholder="ASIS 테이블명" value="${param.s_tablename }"/>
 	  	<input type="text" name="s_colname" id="s_colname" class="asis" placeholder="ASIS 컬럼명" value="${param.s_colname }"/>
 	  	
 	  	<input type="button" class="btn" onclick="validate('${pageContext.request.contextPath }');" value="검색">
 	  </form>
 	</div>
 
 	<div id="viewtable">
 	  <table>
 	  	<thead>
 	  	  <tr id="index">
 	  	  	<th colspan="8">Target(TOBE)</th>
 	  	  	<th colspan="7">Source(ASIS)</th>
 	  	  	<th colspan="2">Mapping Logic</th>
 	  	  </tr>
 	  	  <tr>
 	  	  	<th>DB명</th>	<th>주제영역명</th>	<th>테이블명</th>	<th>테이블한글명</th>	<th>속성명</th>	<th>컬럼명</th>	<th>데이터타입</th>	<th>비고</th>	
 	  	  	<th>DB명</th>	<th>테이블명</th>	<th>테이블한글명</th>	<th>속성명</th>	<th>컬럼명</th>	<th>데이터타입</th>	<th>비고</th>
 	  	  	<th>추출조건</th>	<th>비고</th>
 	  	  </tr>
 	  	</thead>
 	  	
 	  	<tbody>
 	  	  <c:forEach var="vo" items="${list }" >
 	  	  	<tr align="center">
 	  	  	 <td title="">${vo.t_dbName }</td>	<td>${vo.t_topic }</td>	<td>${vo.t_tableName }</td>	<td>${vo.t_tableNameKo }</td>	<td>${vo.t_attrName }</td>
 	  	  	 <td>${vo.t_colName }</td>	<td>${vo.t_dataType }</td>	<td>${vo.t_note }</td>	<td>${vo.s_dbName }</td>	<td>${vo.s_tableName }</td>
 	  	  	 <td>${vo.s_tableNameKo }</td>	<td>${vo.s_attrName }</td>	<td>${vo.s_colName }</td>	<td>${vo.s_dataType }</td>	<td>${vo.s_note }</td>
 	  	  	 <td>${vo.m_terms }</td>	<td>${vo.m_note }</td>
 	  	  	</tr>
 	  	  </c:forEach>
 	  	</tbody>
 	  </table>
 	</div>
 </div>

</body>
</html>