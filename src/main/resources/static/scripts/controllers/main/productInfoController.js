app.controller("productInfoCtrl", function ($scope, mainApiHandler, $rootScope,$cookies) {
    $rootScope.page = "Products";
    $scope.dataId = 0;
    $scope.data = {};
    $scope.orderItem = {
        sizeId:0,
        colorId:0
    };
    $scope.orderItems = [];
    $scope.getProductInfo = () => {
        let url = "product/info/" + $scope.dataId;
        mainApiHandler.callGet(url, (response) => {
            $scope.data = response.dataList[0];
            $scope.orderItem.colorId=$scope.data.colors[0];
            $scope.orderItem.sizeId=$scope.data.sizes[0];
        });
    }

    $scope.init = (id) => {
        $scope.dataId = id;
        $scope.getProductInfo();
    }
    $scope.addToBasket = () => {
        if($scope.orderItem.colorId==undefined ||
            $scope.orderItem.colorId==null ||
            $scope.orderItem.colorId=="" ||
            $scope.orderItem.colorId==0
        ){
            Swal.fire({
                title: "Warning",
                text: "Please select a color",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok'
            });
            return;
        }
        if($scope.orderItem.sizeId==undefined ||
            $scope.orderItem.sizeId==null ||
            $scope.orderItem.sizeId=="" ||
            $scope.orderItem.sizeId==0
        ){
            Swal.fire({
                title: "Warning",
                text: "Please select a size",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok'
            });
            return;
        }
        $scope.orderItem.productId = $scope.dataId;
        $scope.orderItem.count = 1;
        $scope.orderItem.price = $scope.data.price;
        let existed = false;
        let existedIndex = -1;
        for (let i = 0; i < $scope.orderItems.length; i++) {
            if ($scope.orderItems[i].productId == $scope.dataId &&
                $scope.orderItems[i].sizeId == $scope.orderItem.sizeId &&
                $scope.orderItems[i].colorId == $scope.orderItem.colorId) {
                existed = true;
                existedIndex = i;
                break;
            }
        }
        if (!existed) {
            $scope.orderItems.push({
                id:$scope.productId+"_"+$scope.orderItem.colorId+"_"+$scope.orderItem.sizeId,
                productId:$scope.orderItem.productId,
                count:$scope.orderItem.count,
                price:$scope.orderItem.price,
                sizeId:$scope.orderItem.sizeId,
                colorId:$scope.orderItem.colorId,
                product:{
                    image:$scope.data.image,
                    title:$scope.data.title,
                },
                /*
                *  در این بخش توانستیم اطلاعات و سایز انتخابی را جهت استفاده در سبد خرید
                * داشته باشیم
                * */
                color:$scope.data.colorsList.filter(x=>x.id==$scope.orderItem.colorId)[0],
                size:$scope.data.sizesList.filter(x=>x.id==$scope.orderItem.sizeId)[0]
            });
            /*
            *  در این خط کد ما اومدیم با کمک تابع filter از رنگ های محصول رنگ هایی رو که
            * */
            // $scope.data.colors.filter(x=>x.id==$scope.orderItem.colorId);
            Swal.fire({
                title: $scope.data.title,
                text: "Added to basket",
                icon: 'success',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok'
            });
        } else {
            $scope.orderItems[existedIndex].count++;
            Swal.fire({
                title: $scope.data.title,
                text: "Added to basket(" + $scope.orderItems[existedIndex].count + ")",
                icon: 'success',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok'
            });
        }
        /*محتویات سبد خرید را داخل کوکی مشخص شده میریزد*/
        /* تابع JSON.stringfy باعث میشود که شی که به آن میدهیم را به
         رشته تبدیل کند و درون کوکی به شکل رشته ای مانند شی ذخیره کند
         قسمت path کوکی هم مشخص میکند که این کوکی در چه مسیر قابل دسترس است که / یعنی این کوکی
         در تمام اپ قابل دسترسی است
         */

        $cookies.put("basket",JSON.stringify($scope.orderItems),{path:'/'});
    }

    /*
    *در این قسمت از کوکی به نام basket مقادیر آن را که شامل لیست داخل سبد خرید هست
    * رو میخونیم
    * در نهایت در یک آرایه ذخیره میکنیم
    * کاربرد تابع JSON.parse در این هست که شبه شی که به شکل رشته ذخیره شده است
    * را به شی برگرداند
    * */
    $scope.loadOrderItemList=()=>{
        if($cookies.get("basket")==undefined || $cookies.get("basket")==null )
        {
            $scope.orderItems=[];
            return;
        }
        $scope.orderItems=JSON.parse($cookies.get("basket"));
    }
    /**/

    $scope.loadOrderItemList();

});