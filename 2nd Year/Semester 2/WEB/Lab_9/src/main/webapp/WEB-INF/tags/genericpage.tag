<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="resources/script.js"></script>
    <title>${pageTitle}</title>
</head>
<body>
<div id="body">
    <h1 class="text-center">${pageTitle}</h1>
    <div class="container">
        <div class="row">
        <jsp:doBody/>
        </div>
    </div>
</div>
<footer class="text-center">
    <c:if test="${(not empty status)}"><h6>Connection status: ${status}</h6></c:if>
</footer>
</body>
</html>