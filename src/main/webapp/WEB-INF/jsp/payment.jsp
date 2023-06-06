<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | Basket</title>
    <%--کل محتوای آدرس را در اینجا لود میکند همین!--%>
    <jsp:include page="partial/header.jsp"/>
    <%----%>
    <script src="/scripts/controllers/main/paymentController.js"></script>
</head>
<body ng-app="onlineShopApp" ng-controller="paymentCtrl">
<jsp:include page="partial/nav.jsp"/>
<div class="container-fluid">
    <div class="row min-height-500">
        <%--باعث میشود که ستون ما که شامل یک جدول است تقریبا وسط صفحه بیفتد و از چپ و راست یک ستون فاصله داشته باشد--%>
        <div class="col-1"></div>
        <%----%>
        <%--کلاس col-md-5 در اینجا یعنی در صفحه نمایش های متوسط مثل گوشی 5 ستون از 12 ستون را بگیرد برامون --%>
        <div class="col-md-5">
            <br/>
            <div class="card-header">
                Payment Information
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-5">
                        <table class="table table-striped">
                            <%----%>
                            <thead>
                            <tr>
                                <th scope="col">Item</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--
                            در این قسمت یک سطر اضافه کردیم که اگر سبد خرید خالی بود پیغام بدهیم
                            --%>
                            <tr ng-show="dataList.length==0">
                                <td colspan="6" class="text-center">
                                    <H3>Your basket is empty</H3>
                                </td>
                            </tr>
                            <%----%>
                            <tr ng-repeat="data in dataList">
                                <td>
                                    <%--در این قسمت با کلیک روی عکس میتوانیم جزئیات سفارش خود را ببینیم--%>
                                    <a title="Show Details" target="_blank" href="/product/{{data.productId}}">
                                        <img style="width: 60px" src="/api/utils/upload/files/{{data.product.image}}"/>
                                        <span>{{data.product.title}}</span>
                                    </a>
                                    <%----%>
                                </td>
                                <td>{{data.price}}</td>
                                <%----%>
                                <td>{{data.count}}</td>
                            </tr>
                            </tbody>
                            <tfoot ng-show="dataList.length>0" class="table-footer">
                            <tr>
                                <td>Total</td>
                                <td>
                                    <b>{{totalPrice}}</b>
                                </td>
                                <td>
                                    <b>{{totalCount}}</b>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                        <br/>
                        <div>
                            <label for="PaymentType">Payment Type</label>
                            <div id="PaymentType" class="mb-3">
                                <select ng-model="paymentType" class="form-control">
                                    <option value="ZarinPal">ZarinPal</option>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <a ng-click="goToPayment()" class="btn btn-success"><i class="fa fa-credit-card"></i> Proceed to payment</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <br/>
                <div class="card-header">
                    User Information
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <div class="row">
                                <div class="col">
                                    <div class="mb-3">
                                        <input type="text" placeholder="First Name" class="form-control" id="firstName"
                                               ng-model="data.firstName">
                                    </div>
                                    <div class="mb-3">
                                        <input type="text" placeholder="Last Name" class="form-control" id="lastName" ng-model="data.lastName">
                                    </div>
                                    <div class="mb-3">
                                        <input type="text" placeholder="Username" class="form-control" id="username" ng-model="data.username">
                                    </div>
                                    <div class="mb-3" ng-show="!userLoggedIn">
                                        <input type="password" placeholder="Password" class="form-control" id="password"
                                               ng-model="data.password">
                                    </div>
                                    <div class="mb-3">
                                        <input type="email" placeholder="Email" class="form-control" id="email" ng-model="data.email">
                                    </div>
                                    <div class="mb-3">
                                        <input type="text" placeholder="Mobile" class="form-control" id="mobile" ng-model="data.mobile">
                                    </div>
                                    <div class="mb-3">
                                        <input type="tel" placeholder="Tel" class="form-control" id="tel" ng-model="data.tel">
                                    </div>
                                    <div class="mb-3">
                                        <textarea placeholder="Address" ng-model="data.address" class="form-control" id="address"></textarea>
                                    </div>
                                    <div class="mb-3">
                                        <input type="text" placeholder="PostalCode" class="form-control" id="postal"
                                               ng-model="data.postalCode">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>