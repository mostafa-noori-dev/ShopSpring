<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | Blog</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="/scripts/controllers/main/blogController.js"></script>
</head>
<body ng-app="onlineShopApp" ng-controller="blogCtrl">
<jsp:include page="partial/nav.jsp"/>
<div>
    <img src="/images/blog.jpg" width="100%"/>
</div>
<br/>
<div class="container-fluid">
    <div class="card" ng-repeat="blog in blogList">
        <div class="row g-0">
            <div class="col-md-2">
                <img src="/api/utils/upload/files/{{blog.image}}" class="img-fluid rounded-start" alt="...">
            </div>
            <div class="col-md-10">
                <div class="card-body">
                    <h5 class="card-title">{{blog.title}}</h5>
                    <p class="card-text">{{blog.subtitle}}</p>
                    <p class="card-text"><small class="text-muted">{{blog.publishDateStr}}</small>
                    <a href="/blog/{{blog.id}}" class="btn btn-primary btn-sm float-right">Read more...</a>
                    </p>
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
<jsp:include page="partial/footer.jsp"/>
</body>
</html>