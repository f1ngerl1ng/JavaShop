<%--
  Created by IntelliJ IDEA.
  User: macnaer
  Date: 09.07.2020
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/header.jsp"%>
<%@include file="templates/internal-banner.jsp"%>
<!-- Start Contact Us  -->
<div class="container-wrapper">
    <div class="container">
        <div id="login-box">
            <div class="form-2">

            <h1><span class="log-in">Log in</span> or <span class="sign-up">sign up</span></h1>

            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <form name="loginForm" action="<c:url value="/j_spring_security_check" />" method="post">
                <c:if test="${not empty error}">
                    <div class="error" style="color: #ff0000;">${error}</div>
                </c:if>
                <p class="float">
                    <label for="username"><i class="icon-user"></i>Username</label>
                    <input type="text" name="username" placeholder="Username or email">
                </p>
                <p class="float">
                    <label for="password"><i class="icon-lock"></i>Password</label>
                    <input type="password" name="password" placeholder="Password" class="showpassword">
                </p>
                <p class="clearfix">
                    <a href="#" class="log-twitter">Log in with Twitter</a>
                    <input type="submit" name="submit" value="Log in">
                </p>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
            </div>

        </div>
    </div>
</div>
<!-- End Cart -->
<%@include file="templates/instagramFeed.jsp"%>
<%@include file="templates/footer.jsp"%>