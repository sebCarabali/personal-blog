<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://example.com/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Zebsoft Blog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>
    <jsp:include page="includes/header.jsp" />

    <div class="login-container">

        <div class="login-box">
            <h2>Login to Your Account</h2>

            <c:if test="${not empty param.error}">
                <div class="error-message" id="error-message">
                    ${param.error}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login.do" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <input class="btn" type="submit" value="Login">
                </div>
            </form>
        </div>
    </div>
</body>
</html>