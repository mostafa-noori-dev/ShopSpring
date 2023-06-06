app.controller("basketCtrl", function ($scope, mainApiHandler, $rootScope,$cookies) {
    /*
    * این بخش برای این است که وقتی ما درون این صفحه بودیم کدام آیتم از آیتم های منو به حالت فعال در بیاید
    * */
    $rootScope.page = "Basket";
    $scope.dataList=[];
    $scope.totalPrice=0;
    $scope.totalCount=0;
    /*
    * کاربرد این متد این است که محتویات کوکی basket را دریافت کند تا بتوانیم از آن برای پر کردن
    * و نمایش دادن سبد خرید کاربر استفاده کنیم
    * */
    $scope.fillDataList=()=>{
        if($cookies.get("basket")==undefined || $cookies.get("basket")==null )
        {
            $scope.dataList=[];
            return;
        }
        $scope.dataList=JSON.parse($cookies.get("basket"));
        for(let i=0;i<$scope.dataList.length;i++){
            $scope.totalCount+=$scope.dataList[i].count;
            $scope.totalPrice+=$scope.dataList[i].price;
        }
    }

    $scope.removeItem=(data)=>{
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you want to remove this item from your basket?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, remove it!'
        }).then((result) => {
            if (result.isConfirmed) {
                for(let i=0;i<$scope.dataList.length;i++){
                    if($scope.dataList[i].id ==data.id ){
                        $scope.dataList.splice(i,1);
                    }
                }
                $cookies.put("basket",JSON.stringify($scope.dataList),{path:'/'});
                location.href=location.href;
            }
        })
    }

    $scope.fillDataList();
});