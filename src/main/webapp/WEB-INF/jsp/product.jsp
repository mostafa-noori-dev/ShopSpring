<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | ProductInfo</title>
    <jsp:include page="partial/header.jsp"/>
    <script src="/scripts/controllers/main/productInfoController.js"></script>
    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>
</head>
<body ng-app="onlineShopApp" ng-controller="productInfoCtrl" ng-init="init(<%=dataId%>)">
<jsp:include page="partial/nav.jsp"/>
<br/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-6">
            <img style="width: 100%" src="/api/utils/upload/files/{{data.image}}"/>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    {{data.title}}
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <label for="colors" class="form-label">Color</label>
                        <div id="colors" ng-repeat="color in data.colorsList">
                            <div class="color-entity-box" style="background: {{color.value}}"></div>
                            <div class="color-title">
                                <input type="radio" name="color" id="color_{{color.id}}" ng-value="{{color.id}}"
                                       ng-model="orderItem.colorId"/>
                                <label for="color_{{color.id}}">{{color.name}}</label>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="sizes" class="form-label">Size</label>
                        <select class="form-control" id="sizes" ng-model="orderItem.sizeId">
                            <option ng-repeat="size in data.sizesList" value="{{size.id}}">{{size.title}}</option>
                        </select>
                    </div>
                    <br/>
                    <a class="btn btn-primary" ng-click="addToBasket()"> + Add to basket </a>
                    <br/>
                    <hr/>
                    <p class="card-text" ng-bind-html="data.description"></p>
                    <ul>
                        <li ng-repeat="feature in data.featuresDataList">
                            {{feature.key}} {{feature.value}}
                        </li>
                    </ul>

                </div>
                <div class="card-footer text-muted">
                    <i class="fa fa-eye"></i>
                    <span>{{data.visitCount}}</span>
                    &nbsp;
                    <i class="fa fa-calendar"></i>
                    <span>{{data.addDateStr}}</span>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>