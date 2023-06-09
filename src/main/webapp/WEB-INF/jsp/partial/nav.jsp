<nav class="navbar navbar-expand-lg bg-light" ng-controller="navCtrl">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <img src="/images/logo.png" width="60px" />
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li ng-repeat="nav in navList" class="nav-item" >
                    <a class="nav-link" ng-class="{'active':page==nav.title}"  aria-current="page" href="{{nav.link}}">{{nav.title}}</a>
                </li>
            </ul>
            <div class="d-flex" role="search">
                <a href="/panel" class="btn btn-outline-success m-r-10"><i class="fa fa-user"></i></a>
                <a href="/basket" class="btn btn-outline-primary m-r-10"><i class="fa fa-shopping-basket"></i></a>
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" ng-model="searchKey">
                <button class="btn btn-outline-success" type="submit" ng-click="search()">Search</button>
            </div>
        </div>
    </div>
</nav>