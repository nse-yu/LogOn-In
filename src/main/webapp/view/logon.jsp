<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Coreタグライブラリの指定 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logon view</title>
</head>
<body>
<h1>新規登録</h1>
<form action="Logon" method="post" style="text-align:center;margin-left:auto;margin-right:auto;">
	<div style="text-align:right;padding-right:400px">
		<div>
		ユーザID:<input type="text" name="id" placeholder="ID"><br>
		</div>
		<div>
		パスワード:<input type="password" name="pass"><br>
		</div>
		<div>
		パスワード（確認）:<input type="password" name="re_pass"><br>
		</div>
		<div>
		メールアドレス:<input type="email" name="mail" placeholder="example@gmail.com"><br>
		</div>
		<div>
		ユーザ名:<input type="text" name="name"><br>
		</div>
		<div>
		年齢:
			<select name="age">
				<c:forEach var="i" begin="1" end="100" step="1">
					<option value="${ i }">${ i }</option>
				</c:forEach>
			</select>
		</div>
		<div>
		<input type="submit" value="登録">
		</div>
	</div>
</form>
<jsp:include page="/WEB-INF/component/footer.jsp"></jsp:include>
</body>
</html>