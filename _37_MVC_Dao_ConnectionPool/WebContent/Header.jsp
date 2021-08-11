<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<!-- jsp액션태그 session에서 spms.vo.Member타입이면서 
        member라는 이름으로 저장된 객체를 사용하겠다 -->
<jsp:useBean id="member"
			scope="session"
			class="spms.vo.Member"/>

<%
/* 아래 java코드를 위의 jsp 액션태그로 대신한다.
*/
	// HttpSession에 저장된 로그인 정보를 꺼낸다
	//Member member = (Member)session.getAttribute("member");
%>

<div style="background-color:#00008b; color:white; height:20px; padding:5px;">
	SPMS(Simple Project Management System)
	
	<% if(member.getEmail() != null) { %>
	<span style="float:right;">
		<%=member.getName() %>
		<a style="color:white;"		
			href="<%=request.getContextPath() %>/auth/logout">
			로그아웃
		</a>
	</span>
	<% } %>
</div>