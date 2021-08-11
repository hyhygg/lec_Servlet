<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 페이지 지시자 추가. java의 import와 동일 -->
<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id="members"
			scope="request"
			class="java.util.ArrayList"
			type="java.util.ArrayList<spms.vo.Member>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>회원 목록</h1>
	<p><a href='add'>신규 회원</a></p>
	<%
		System.out.println("MemberList.jsp 실행");
	/* 아래 members는 jsp 액션태그로 변경한다
		ArrayList<Member> members = 
			(ArrayList<Member>)request.getAttribute("members");
	*/
		for(Member member : members){
	%>
		<%=member.getNo() %>,
		<a href='update?no=<%=member.getNo() %>'>
			<%=member.getName() %>
		</a>,
		<%=member.getEmail() %>,
		<%=member.getCreatedDate() %>
		<a href='delete?no=<%=member.getNo() %>'>
			[삭제]
		</a>
		<br/>
	<%
		}
	%>
	<jsp:include page="/Tail.jsp"/>
</body>
</html>







