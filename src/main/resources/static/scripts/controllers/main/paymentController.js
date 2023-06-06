app.controller("paymentCtrl", function ($scope, $http, mainApiHandler, $rootScope, $cookies) {
    /*
    * این بخش برای این است که وقتی ما درون این صفحه بودیم کدام آیتم از آیتم های منو به حالت فعال در بیاید
    * */
    $rootScope.page = "Payment";
    $scope.dataList = [];
    $scope.totalPrice = 0;
    $scope.totalCount = 0;
    $scope.data = {};
    $scope.paymentType = 'ZarinPal';
    $scope.userLoggedIn=false;
    /*
    * کاربرد این متد این است که محتویات کوکی payment را دریافت کند تا بتوانیم از آن برای پر کردن
    * و نمایش دادن سبد خرید کاربر استفاده کنیم
    * */
    $scope.fillDataList = () => {
        if ($cookies.get("basket") == undefined || $cookies.get("basket") == null) {
            $scope.dataList = [];
            return;
        }
        $scope.dataList = JSON.parse($cookies.get("basket"));
        for (let i = 0; i < $scope.dataList.length; i++) {
            $scope.totalCount += $scope.dataList[i].count;
            $scope.totalPrice += $scope.dataList[i].price;
        }
    }

    $scope.goToPayment = () => {
        let orderItems = [];
        for (let i = 0; i < $scope.dataList.length; i++) {
            let item = $scope.dataList[i];
            orderItems.push({
                productId: item.productId,
                colorId: item.colorId,
                sizeId: item.sizeId,
                count: item.count
            });
        }
        if (orderItems.length == 0) {
            Swal.fire("your basket is empty");
            return;
        }
        let paymentVM = {
            customer: $scope.data,
            orderItems: orderItems,
            paymentType: $scope.paymentType,
            customerId:$scope.data.customer.id
        }
        mainApiHandler.callPost("payment/", paymentVM, (response) => {
            let href = response.dataList[0].location;
            location.href = href;
        }, (err) => {

        });
    }

    $scope.init = () => {
        var token = $cookies.get("userToken");
        if (token != undefined && token != null)
            $scope.getUserInfo(token);
    };

    $scope.getUserInfo = (token) => {
        let request = {
            url: "/api/user/getUserInfo",
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        };
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                $scope.userLoggedIn=true;
                $scope.data = response.data.dataList[0];
                $scope.data.mobile=$scope.data.customer.mobile;
                $scope.data.address=$scope.data.customer.address;
                $scope.data.tel=$scope.data.customer.tel;
                $scope.data.postalCode=$scope.data.customer.postalCode;
            }
        });
    }

    $scope.init();

    $scope.fillDataList();
});