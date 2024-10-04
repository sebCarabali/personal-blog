<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1>Zebsoft Personal Blog</h1>
    </header>
<nav>
<ul class="nav-left">
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
        </ul>
        
        <!-- Right-aligned Login/Logout buttons -->
        <ul class="nav-right">
            <c:choose>
                <c:when test="${empty sessionScope.username}">
                    <li><a href="${pageContext.request.contextPath}/login.do" class="nav-btn">Login</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/logout" class="nav-btn">Logout</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>