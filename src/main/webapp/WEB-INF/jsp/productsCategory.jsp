<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | ProductsCategory</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="/scripts/controllers/main/productsCategoryController.js"></script>
    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>
</head>
<body ng-app="onlineShopApp" ng-controller="productsCategoryCtrl">
<jsp:include page="partial/nav.jsp"/>
<br/>
<br/>
<div class="container-fluid" ng-init="init(<%=dataId%>)">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">
            <H1 class="text-center">{{category.title}}
                <a href="/products" class="btn btn-sm btn-primary float-left">Return</a>
                <br/>
            </H1>
            <br/>
            <div class="container">
                <div class="row">
                    <div class="col-md-4 m-b-20" ng-repeat="product in dataList">
                        <div class="card">
                            <img width="200px" src="/api/utils/upload/files/{{product.image}}" class="card-img-top"
                                 alt="{{product.title}}">
                            <div class="card-body">
                                <H5 class="card-title">{{product.title}}</H5>
                                <ul>
                                    <li ng-repeat="feature in product.featuresDataList">
                                        {{feature.key}} {{feature.value}}
                                    </li>
                                </ul>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">{{product.addDateStr}}</small>
                                <a href="product/{{product.id}}" class="btn btn-sm btn-primary float-right">Add to
                                    basket</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
            <br/>
            <div class="container">
                <nav class="row">
                    <div class="col-3"></div>
                    <div class="col text-center m-auto">
                        <ul class="pagination pagination-sm">
                            <li ng-repeat="i in range(pageCount) track by $index" class="page-item" aria-current="page">
                                <span class="page-link" ng-click="changePage($index)">
                                    {{$index + 1}}
                                </span>
                            </li>
                        </ul>
                    </div>
                    <div class="col-3"></div>
                </nav>
            </div>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>