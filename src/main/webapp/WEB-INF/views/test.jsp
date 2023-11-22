<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>주문번호 : ${user.getCustomer().split("-")[0]}</h1>
<h1>가격 : ${user.getCustomer().split("-")[1]}</h1>
<h1>이름 : ${user.getCustomer().split("-")[2]}</h1>
<h1>아이디 : ${user.getCustomer().split("-")[3]}</h1>
</body>
</html>