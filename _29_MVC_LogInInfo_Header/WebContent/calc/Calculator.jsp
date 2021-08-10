<!-- 페이지 지시자
     jsp -> java로 변환된다 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 자바코드 사용(스크립트릿) 
	_jspService()요청시 처리를 담당하는 메서드
	_jspService()내부에서 html보다 먼저 처리됨
	
	스크립트릿 : _jspService()에서 응답을 처리하기 위한 java 코드
-->
<%
	String v1="";
	String v2="";
	String result="";
	String[] selected = {"","","",""};
	
	// v1 변수가 존재할 때
	if(request.getParameter("v1")!=null){
		v1 = request.getParameter("v1");
		v2 = request.getParameter("v2");
		String op = request.getParameter("op");
		result = calculate(Integer.parseInt(v1),
						   Integer.parseInt(v2),
						   op);
		switch(op){
		case "+": selected[0] = "selected"; break;
		case "-": selected[1] = "selected"; break;
		case "*": selected[2] = "selected"; break;
		case "/": selected[3] = "selected"; break;
		}
	}
%>    

<!-- Calculator_jsp.java 서블릿내에서 브라우저에 전송할 문자열로 변환 
	     _jspService() 내부에서 마지막에 브라우저에 전송됨.
	     
	표현식 : 선언문/스크립트릿 저장된 변수값을 브라우저상에 전달하기 위한 문법
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기</title>
</head>
<body>
	<h2>jsp 계산기</h2>
	<form action="Calculator.jsp" method="get">
		<input type="text" name="v1" size="4" value="<%=v1%>">
		<select name="op">
			<option value="+" <%=selected[0] %>>+</option>
			<option value="-" <%=selected[1] %>>-</option>
			<option value="*" <%=selected[2] %>>*</option>
			<option value="/" <%=selected[3] %>>/</option>
		</select>
		<input type="text" name="v2" size="4" value="<%=v2%>">
		<input type="submit" value="=">
		<input type="text" size="8" value="<%=result%>">
	</form>
</body>
</html>

<!-- 선언문 
	class Calculator_jsp의 메서드/필드
	_jspService() 밖에 메서드/필드를 선언하는 곳(가장 먼저 처리)
	 필드나 메서드 등록
-->
<%!
	private String calculate(int v1, int v2, String op){
		int result = 0;
		switch(op){
		case "+": result=v1+v2; break;
		case "-": result=v1-v2; break;
		case "*": result=v1*v2; break;
		case "/": result=v1/v2; break;
		}
		return Integer.toString(result);
	}
%>







