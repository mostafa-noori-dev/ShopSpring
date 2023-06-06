<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | Home</title>
    <jsp:include page="partial/header.jsp"/>
    <link rel="stylesheet" href="/libs/carousel-lazy-loading-square/square1.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/libs/carousel-lazy-loading-square/square2.css" rel="stylesheet">
    <script src="/libs/carousel-lazy-loading-square/square1.min.js"></script>
    <script src="/scripts/controllers/main/homeController.js"></script>
    <script src="/scripts/controllers/main/sliderController.js"></script>
    <script src="/scripts/controllers/main/productController.js"></script>
</head>
<body ng-app="onlineShopApp" ng-controller="homeCtrl">
<jsp:include page="partial/nav.jsp"/>
<div ng-controller="sliderCtrl">
    <div class="slideshow top-slider">
        <div ng-repeat="slider in sliderList">
            <a href="{{slider.link}}">
                <img src="/api/utils/upload/files/{{slider.image}}" alt="{{slider.title}}">
                <div class="text_content">
                    <H3>{{slider.title}}</H3>
                    <H5>{{slider.description}}</H5>
                </div>
            </a>
        </div>
    </div>
</div>
<div ng-controller="productCtrl">
    <H1 class="text-center">New Product</H1>
    <br/>
    <div class="container">
        <div class="row">
            <div class="col-md-4 m-b-20" ng-repeat="product in newProductList">
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
                        <a href="product/{{product.id}}" class="btn btn-sm btn-primary float-right">Add to basket</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="parallax-cover">
    </div>
    <H1 class="text-center m-t-30">Popular Products</H1>
    <br/>
    <div class="container">
        <div class="row">
            <div class="col-4 m-b-20" ng-repeat="product in popularProductList">
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
                        <a href="product/{{product.id}}" class="btn btn-sm btn-primary float-right">Add to basket</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>