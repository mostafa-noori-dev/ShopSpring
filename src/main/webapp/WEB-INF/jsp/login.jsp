<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" href="/libs/bootstrap-5.1.3-dist/css/bootstrap.min.css" />
    <script src="/libs/angular.min.js"></script>
    <script src="/libs/jquery-3.6.1.min.js"></script>
    <script src="/libs/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
    <script src="/libs/angular-cookies.js"></script>
    <script src="/libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <link rel="stylesheet" href="/libs/sweetalert2/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="/libs/textAngular-1.5.16/dist/textAngular.css">
    <script src="/libs/textAngular-1.5.16/dist/textAngular-rangy.min.js"></script>
    <script src="/libs/textAngular-1.5.16/dist/textAngular-sanitize.min.js"></script>
    <script src="/libs/textAngular-1.5.16/dist/textAngular.min.js"></script>
    <script src="/scripts/app.js"></script>
    <script src="/scripts/services/apiHandler.js"></script>
    <script src="/scripts/controllers/loginController.js"></script>
    <link rel="stylesheet" href="/styles/login.css">
</head>
<body ng-app="onlineShopApp">

<div class="container-fluid" ng-controller="loginCtrl">
        <div class="row">
            <div class="col-3"></div>
            <div class="col login-box-holder">
                <h3>Login to panel</h3>
                <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" ng-model="user.username">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" ng-model="user.password">
                </div>
                <button type="submit" class="btn btn-primary" ng-click="doLogin()">Login</button>
            </div>
            <div class="col-3"></div>
        </div>
</div>

</body>
</html>