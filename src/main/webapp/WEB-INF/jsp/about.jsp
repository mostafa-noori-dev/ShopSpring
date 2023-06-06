<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | About</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="/scripts/controllers/main/aboutController.js"></script>
    <script src="/scripts/controllers/main/contentController.js"></script>
</head>
<body ng-app="onlineShopApp" ng-controller="aboutCtrl">
<jsp:include page="partial/nav.jsp"/>
<div>
    <img src="/images/about.jpg" width="100%"/>
</div>
<br/>
<div ng-controller="contentCtrl" class="container-fluid">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">
            <div class="card text-center">
                <div class="card-header">
                    About Us
                </div>
                <div class="card-body">
                    <p class="card-text" ng-bind-html="getContent('about')"></p>
                    <a href="/products" class="btn btn-primary">Go Shopping</a>
                </div>
                <div class="card-footer text-muted" ng-bind-html="getContent('about_slogan')">
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>