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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/modal.css">
</head>
<body>
    <jsp:include page="../includes/header.jsp" />
    <input type="hidden" id="context-path" value="${pageContext.request.contextPath}">

    <main class="dashboard">
        <c:if test="${not empty param.error}">
                <div class="error-message" id="error-message">
                    ${param.error}
                </div>
        </c:if>
        <c:if test="${not empty param.success}">
                <div class="success-message" id="success-message">
                    ${param.success}
                </div>
        </c:if>
        <div class="header">
            <h2>Your Posts</h2>
            <button id="create-post-btn" class="create-post-btn">Create Post</button>
        </div>

        <div class="card-container">
            <c:if test="${empty posts}">
                No post to show
            </c:if>
            <c:forEach var="post" items="${posts}">
                <div class="card">
                    <h3 class="card-title"><a href="${pageContext.request.contextPath}/show?id=${post.id}">${post.title}</a></h3>
                    <p class="card-content">${fn:truncate(post.content, 100)}...</p>
                    <div class="card-footer">
                        <span>${fn:formatDateTime(post.pubTime, "MMMM d, yyyy")}</span>
                        <div class="card-options">
                            <a href="javascript:void(0)" class="edit-btn" data-id="${post.id}">Edit</a>
                            <a href="${pageContext.request.contextPath}/delete-post?id=${post.id}" class="delete-btn" data-id="${post.id}">Delete</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>

    <div id="postModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2 id="modal-title">Create a New Post</h2>

            <form id="postForm" action="${pageContext.request.contextPath}/create-post" method="post">
                
                <input type="hidden" id="postId" name="id">

                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" required>
                </div>

                <div class="form-group">
                    <label for="content">Content</label>
                    <textarea id="content" name="content" rows="5" required></textarea>
                </div>

                <div class="form-group">
                    <input class="btn" type="submit" id="submitBtn" value="Create Post">
                </div>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/assets/js/dashboard.js"></script>
</body>
</html>