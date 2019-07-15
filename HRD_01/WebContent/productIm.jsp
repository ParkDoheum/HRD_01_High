<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="hrd.*" %>
<%@ page import="java.util.*" %>    
<%
	List<ProductVo> list = (List<ProductVo>) request.getAttribute("list");
%>

<table>
	<tr>
		<th>입고 번호</th>
		<th>물품 이름</th>
		<th>입고 수량</th>
		<th>입고 일자</th>
	</tr>
	
	<% for(ProductVo vo : list) { %>
		<tr>
			<td><%=vo.getI_no() %></td>
			<td><%=vo.getP_name() %></td>
			<td><%=vo.getI_cnt() %></td>
			<td><%=vo.getI_date() %></td>
		</tr>
	<% } %>
</table>




