<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login view</title>
</head>
<body>
<h1>ログイン画面</h1>
<form action="Login" method="post">
ユーザーID: <input type="text" name="id"><br>
パスワード: <input type="password" name="pass"><br>
<input type="submit" value="ログイン">
<jsp:include page="/WEB-INF/component/footer.jsp"></jsp:include>
</form>
</body>
</html>