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
        <article class="blog-post">
            <h2>${post.title}</h2>
            <p class="pub-date">Published on ${fn:formatDateTime(post.pubTime, "dd/MM/yyyy HH:mm")}</span></p>
            <p>${post.content}</p>
        </article>
    </div>
</body>
</html>