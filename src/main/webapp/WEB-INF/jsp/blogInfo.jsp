<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | Blog</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="/scripts/controllers/main/blogInfoController.js"></script>
    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>
</head>
<body ng-app="onlineShopApp" ng-controller="blogInfoCtrl">
<jsp:include page="partial/nav.jsp"/>
<br/>
<br/>
<div class="container-fluid" ng-init="init(<%=dataId%>)">
    <div class="row">
        <div class="col-1"></div>
        <div class="col">
            <div class="card text-center">
                <div class="card-header">
                    <H1>{{data.title}}
                        <a href="/blog" class="btn btn-sm btn-primary float-right">Return</a>
                    </H1>
                </div>
                <div class="card-body">
                    <H3>
                        {{data.subtitle}}
                    </H3>
                    <img style="width: 50%" src="/api/utils/upload/files/{{data.image}}"/>
                    <br/>
                    <hr/>
                    <br/>
                    <p class="card-text" ng-bind-html="data.description"></p>
                </div>
                <div class="card-footer text-muted">
                    <i class="fa fa-calendar"></i>
                    <span>{{data.publishDateStr}}</span>
                    &nbsp
                    <i class="fa fa-eye"></i>
                    <span>{{data.visitCount}}</span>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>