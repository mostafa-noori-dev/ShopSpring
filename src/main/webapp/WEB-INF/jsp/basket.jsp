<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | Basket</title>
    <%--کل محتوای آدرس را در اینجا لود میکند همین!--%>
    <jsp:include page="partial/header.jsp"/>
    <%----%>
    <script src="/scripts/controllers/main/basketController.js"></script>
</head>
<body ng-app="onlineShopApp" ng-controller="basketCtrl">
<jsp:include page="partial/nav.jsp"/>
<div class="container-fluid">
    <div class="row min-height-500">
        <%--باعث میشود که ستون ما که شامل یک جدول است تقریبا وسط صفحه بیفتد و از چپ و راست یک ستون فاصله داشته باشد--%>
        <div class="col-1"></div>
        <%----%>
        <div class="col">
            <div class="card">
                <br/>
                <div class="card-header">
                    Shopping basket
                </div>
                <div class="card-body">
                    <%--کلاس table-striped باعث میشود که سطرها یکی در میون به رنگ های سفید و خاکستری دربیایند--%>
                    <table class="table table-striped">
                        <%----%>
                        <thead>
                        <tr>
                            <%--یه چیزی شبیه تیتر را شامل میشود که یک متنی را دارد--%>
                            <th scope="col">#</th>
                            <%----%>
                            <th scope="col">Item</th>
                            <th scope="col">Color</th>
                            <th scope="col">Size</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--
                        در این قسمت یک سطر اضافه کردیم که اگر سبد خرید خالی بود پیغام بدهیم
                        --%>
                        <tr ng-show="dataList.length==0">
                            <td colspan="7" class="text-center">
                                <H3>Your basket is empty</H3>
                            </td>
                        </tr>
                        <%----%>
                        <tr ng-repeat="data in dataList">
                            <%--
                            index$ در واقع یک directive ثابت در زبان Angular js است
                            که در واقع ما اینجا برای ردیف دادن استفاده کرده ایم و شماره هر ردیف به ترتیب
                            با اجرای حلقه هر بار یکی بالاتر میرود
                            --%>
                            <th scope="row">{{$index + 1}}</th>
                            <%----%>
                            <td>
                                <%--در این قسمت با کلیک روی عکس میتوانیم جزئیات سفارش خود را ببینیم--%>
                                <a title="Show Details" target="_blank" href="/product/{{data.productId}}">
                                    <img style="width: 60px" src="/api/utils/upload/files/{{data.product.image}}"/>
                                    <span>{{data.product.title}}</span>
                                </a>
                                <%----%>
                            </td>
                            <%--data یک شی است که مشخصات هر محصول درون آن است--%>
                            <td>
                                <div class="color-entity-box" style="background: {{data.color.value}}"></div>
                                <span class="color-title">{{data.color.name}}</span>
                            </td>
                            <td>{{data.size.title}}</td>
                            <%--
                            با توجه به کد زیر ما نیازی به نوشتن data.product.price نداشتیم و مستقیما خود property price را خواندیم
                            --%>
                            <td>{{data.price}}</td>
                            <%----%>
                            <td>{{data.count}}</td>
                            <td>
                                <a ng-click="removeItem(data)"><i class="fa fa-times color-red cursor-pointer"></i></a>
                            </td>
                        </tr>
                        </tbody>
                        <tfoot ng-show="dataList.length>0" class="table-footer">
                        <tr>
                            <td colspan="3">#</td>
                            <td>Total</td>
                            <td>
                                <b>{{totalPrice}}</b>
                            </td>
                            <td>
                                <b>{{totalCount}}</b>
                            </td>
                            <td colspan="7"></td>
                        </tr>
                        </tfoot>
                    </table>
                    <br/>
                    <a ng-show="dataList.length>0" href="/payment" class="btn btn-success"><i class="fa fa-credit-card"></i> Proceed to payment</a>
                    <a href="/products" class="btn btn-outline-info"><i class="fa fa-shopping-basket"></i> Continue Shopping</a>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<jsp:include page="partial/footer.jsp"/>
</body>
</html>