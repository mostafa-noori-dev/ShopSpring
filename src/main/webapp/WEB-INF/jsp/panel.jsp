<%@ page import="test.shop.app.helper.uimodels.people.UserVM" %>
<%@ page import="test.shop.app.enums.UserRole" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | Panel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" href="/libs/bootstrap-5.1.3-dist/css/bootstrap.min.css"/>
    <script src="/libs/angular.min.js"></script>
    <script src="/libs/jquery-3.6.1.min.js"></script>
    <script src="/libs/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/libs/fontawesome-free-5.14.0-web/css/all.min.css">
    <script src="/libs/angular-cookies.js"></script>
    <script src="/libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <link rel="stylesheet" href="/libs/sweetalert2/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="/libs/textAngular-1.5.16/dist/textAngular.css">
    <script src="/libs/textAngular-1.5.16/dist/textAngular-rangy.min.js"></script>
    <script src="/libs/textAngular-1.5.16/dist/textAngular-sanitize.min.js"></script>
    <script src="/libs/textAngular-1.5.16/dist/textAngular.min.js"></script>
    <script src="/scripts/app.js"></script>
    <script src="/scripts/directives/fileModel.js"></script>
    <script src="/scripts/controllers/utils/uploadFileController.js"></script>
    <script src="/scripts/controllers/utils/getFileController.js"></script>
    <script src="/scripts/services/apiHandler.js"></script>
    <script src="/scripts/controllers/panelController.js"></script>
    <script src="/scripts/controllers/dashboardController.js"></script>
    <script src="/scripts/controllers/site/nav/navListController.js"></script>
    <script src="/scripts/controllers/site/nav/navAddController.js"></script>
    <script src="/scripts/controllers/site/nav/navEditController.js"></script>
    <script src="/scripts/controllers/site/content/contentListController.js"></script>
    <script src="/scripts/controllers/site/content/contentAddController.js"></script>
    <script src="/scripts/controllers/site/content/contentEditController.js"></script>
    <script src="/scripts/controllers/site/slider/sliderListController.js"></script>
    <script src="/scripts/controllers/site/slider/sliderAddController.js"></script>
    <script src="/scripts/controllers/site/slider/sliderEditController.js"></script>
    <script src="/scripts/controllers/site/blog/blogListController.js"></script>
    <script src="/scripts/controllers/site/blog/blogAddController.js"></script>
    <script src="/scripts/controllers/site/blog/blogEditController.js"></script>
    <script src="/scripts/controllers/people/user/userListController.js"></script>
    <script src="/scripts/controllers/people/user/userAddController.js"></script>
    <script src="/scripts/controllers/people/user/userEditController.js"></script>
    <script src="/scripts/controllers/products/category/categoryListController.js"></script>
    <script src="/scripts/controllers/products/category/categoryAddController.js"></script>
    <script src="/scripts/controllers/products/category/categoryEditController.js"></script>
    <script src="/scripts/controllers/products/color/colorListController.js"></script>
    <script src="/scripts/controllers/products/color/colorAddController.js"></script>
    <script src="/scripts/controllers/products/color/colorEditController.js"></script>
    <script src="/scripts/controllers/products/size/sizeListController.js"></script>
    <script src="/scripts/controllers/products/size/sizeAddController.js"></script>
    <script src="/scripts/controllers/products/size/sizeEditController.js"></script>
    <script src="/scripts/controllers/products/product/productListController.js"></script>
    <script src="/scripts/controllers/products/product/productAddController.js"></script>
    <script src="/scripts/controllers/products/product/productEditController.js"></script>
    <script src="/scripts/controllers/customer-dashboard/customerDashboardController.js"></script>
    <script src="/scripts/controllers/customer-dashboard/invoiceDetailsController.js"></script>
    <script src="/scripts/controllers/customer-dashboard/customerEditController.js"></script>
    <script src="/scripts/controllers/people/customer/customerListController.js"></script>
    <link rel="stylesheet" href="/styles/panel.css">
    <%
        UserVM user = (UserVM) request.getAttribute("user");
    %>
</head>
<body ng-app="onlineShopApp">
<% if (user != null) { %>
<div class="container-fluid" ng-controller="panelCtrl" ng-init="init(<%= user.getCustomerId() %>)">
    <div class="row">
        <div class="col p-0">
            <div class="panel-header">
                <div>
                    <a ng-click="logout()" class="btn btn-danger btn-sm">Logout</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-2 p-0">
            <div class="side-nav">
                <div class="text-center p-3">
                    <img src="/images/useravatar.webp" width="100"/>
                    <br/>
                    <span>{{user.fullName}}</span>
                </div>
                <ul>
                    <li>
                        <a href="/" >
                            <i class="fa fa-globe"></i>
                            <span>Website</span>
                        </a>
                    </li>
                    <% if (user.getRole() == UserRole.ADMIN) {%>
                    <li ng-class="{'side-nav-active':templateGroup==='dashboard'}">
                        <a href="#" ng-click="changeMenu('dashboard')">
                            <i class="fa fa-square"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='nav'}">
                        <a href="#" ng-click="changeMenu('nav-list')">
                            <i class="fa fa-link"></i>
                            <span>Navigations</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='content'}">
                        <a href="#" ng-click="changeMenu('content-list')">
                            <i class="fa fa-file"></i>
                            <span>Content</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='slider'}">
                        <a href="#" ng-click="changeMenu('slider-list')">
                            <i class="fa fa-photo-video"></i>
                            <span>Sliders</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='blog'}">
                        <a href="#" ng-click="changeMenu('blog-list')">
                            <i class="fa fa-newspaper"></i>
                            <span>Blogs</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='product'}">
                        <a href="#" ng-click="changeMenu('category-list')">
                            <i class="fa fa-cubes"></i>
                            <span>Products</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='user'}">
                        <a href="#" ng-click="changeMenu('user-list')">
                            <i class="fa fa-users"></i>
                            <span>Users</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='customer'}">
                        <a href="#" ng-click="changeMenu('customer-list')">
                            <i class="fa fa-shopping-bag"></i>
                            <span>Customers</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='uploader'}">
                        <a href="#" ng-click="changeMenu('uploader')">
                            <i class="fa fa-link"></i>
                            <span>File Manager</span>
                        </a>
                    </li>
                    <% } else {%>
                    <li ng-class="{'side-nav-active':templateGroup==='customer-dashboard'}">
                        <a href="#" ng-click="changeMenu('customer-dashboard')">
                            <i class="fa fa-link"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup==='customer-edit'}">
                        <a href="#" ng-click="changeMenu('customer-edit')">
                            <i class="fa fa-user"></i>
                            <span>Profile</span>
                        </a>
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <div class="col p-0">
            <div class="main-container" ng-include="template"></div>
        </div>
    </div>
</div>
<% } else { %>
<script>
    location.href = "/login";
</script>
<% } %>
</body>
</html>