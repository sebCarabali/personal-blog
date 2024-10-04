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

    <div class="main-content">
        <c:forEach var="post" items="${posts}">
            <div class="blog-post">
                <h2><a href="${pageContext.request.contextPath}/show?id=${post.id}">${post.title}</a></h2>
                <p class="pub-date">Published on ${fn:formatDateTime(post.pubTime, "MMMM d, yyyy")}</p>
                <p>${fn:truncate(post.content, 300)}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>