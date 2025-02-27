<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width" />
        <script type="text/javascript"></script>
        <c:set var="titleKey" value="title.account.createComplete" />
        <title><spring:message code="${titleKey}" text="secure-login" /></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="../layout/header.jsp" />
            <div id="wrapper">
                <h1>Welcome ${f:h(firstName)} ${f:h(lastName)}!</h1>
                <h3>Your initial password is <span id="password">${f:h(password)}</span>. Please login and change it.</h3>
                <a href="${f:h(pageContext.request.contextPath)}/login">Back to login page</a>
            </div>
            <hr />
            <p style="text-align: center; background: #e5ecf9">Copyright &copy; 20XX CompanyName</p>
        </div>
    </body>
</html>
