<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width" />
        <script type="text/javascript"></script>
        <c:set var="titleKey" value="title.unlock.unlockForm" />
        <title><spring:message code="${titleKey}" text="secure-login" /></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="../layout/header.jsp" />
            <div id="wrapper">
                <h1>Unlock Account</h1>
                <t:messagesPanel />
                <form:form action="${f:h(pageContext.request.contextPath)}/unlock" method="POST" modelAttribute="unlockForm">
                    <table>
                        <tr>
                            <th><form:label path="username" cssErrorClass="error-label">Username</form:label></th>
                            <td><form:input path="username" cssErrorClass="error-input" /></td>
                            <td><form:errors path="username" cssClass="error-messages" /></td>
                        </tr>
                    </table>

                    <input id="submit" type="submit" value="Unlock" />
                </form:form>
                <a href="${f:h(pageContext.request.contextPath)}/">go to Top</a>
            </div>
            <hr />
            <p style="text-align: center; background: #e5ecf9">Copyright &copy; 20XX CompanyName</p>
        </div>
    </body>
</html>
