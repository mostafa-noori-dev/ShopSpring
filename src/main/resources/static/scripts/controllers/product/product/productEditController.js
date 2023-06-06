app.controller("productEditCtrl", function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.productCategory = $rootScope.productCategory;
    $scope.allColorList = [];
    $scope.allSizeList = [];
    $scope.newFeature = {};
    $scope.data.features = [];
    $scope.featureList = [];
    $scope.selectedColors=[];
    $scope.selectedSizes=[];
    $scope.editData = () => {
        $scope.data.image = $rootScope.uploadedFile;
        if ($rootScope.uploadedFile != undefined && $rootScope.uploadedFile != null && $rootScope.uploadedFile != "")
            $scope.data.image = $rootScope.uploadedFile;
        $scope.data.categoryId = $scope.productCategory.id;
        if ($scope.data.title === undefined || $scope.data.title === null || $scope.data.title === "") {
            Swal.fire("please enter title");
            return;
        }
        if ($scope.data.price === undefined || $scope.data.price === null || $scope.data.price === "") {
            Swal.fire("please enter price");
            return;
        }
        if ($scope.data.exists === undefined || $scope.data.exists === null) {
            Swal.fire("please set exists");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable === null) {
            Swal.fire("please set enable");
            return;
        }
        if ($scope.data.image === undefined || $scope.data.image === null || $scope.data.image === "") {
            Swal.fire("please upload an image");
            return;
        }
        apiHandler.callPut("product/", $scope.data, (response) => {
            $scope.changeMenuWithProductCategory('product-list');
        }, (error) => {

        }, true);
    }
    $scope.changeMenuWithProductCategory = (template) => {
        $rootScope.productCategory = $scope.productCategory;
        $scope.changeMenu(template);
    }

    $scope.getAllColors = () => {
        apiHandler.callGet("color/", (response) => {
            $scope.allColorList = response.dataList;
        }, (error) => {

        }, true);
    }
    $scope.getAllSizes = () => {
        apiHandler.callGet("size/", (response) => {
            $scope.allSizeList = response.dataList;
        }, (error) => {

        }, true);
    }

    $scope.addFeature = () => {
        apiHandler.callPost("feature/", $scope.newFeature, (response) => {
            $scope.data.features.push(response.dataList[0].id);
            $scope.featureList.push(response.dataList[0]);
            $scope.newFeature = {};
        }, (error) => {

        }, true);
    }
    $scope.deleteFeature = (id) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                apiHandler.callDelete("feature/" + id, (response) => {
                    for (let i = 0; i < $scope.data.features.length; i++) {
                        if ($scope.data.features[i] == id) {
                            $scope.data.features.splice(i, 1);
                            break;
                        }
                    }
                    for (let i = 0; i < $scope.featureList.length; i++) {
                        if ($scope.featureList[i].id == id) {
                            $scope.featureList.splice(i, 1);
                            break;
                        }
                    }
                }, (error) => {

                }, true);

            }
        })

    }

    $scope.fillFeatures = () => {
        for (let i = 0; i < $scope.data.featuresDataList.length; i++) {
            $scope.featureList.push($scope.data.featuresDataList[i]);
        }
    }


    $scope.getData = () => {
        apiHandler.callGet("product/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
            for(let i=0;i<$scope.data.colors.length;i++)
                $scope.selectedColors.push($scope.data.colors[i]);
            for(let i=0;i<$scope.data.sizes.length;i++)
                $scope.selectedSizes.push($scope.data.sizes[i]);
            $scope.fillFeature();
        }, (error) => {

        }, true);
    }

    $scope.isSelected = (list,item) => {
        if(list==undefined) return false;
        return list.some(x=>x==item.id);
    }

    $scope.onColorChanged=(color)=>{
        if($scope.data.colors[color.id] && !$scope.selectedColors.some(x=>x==color.id)){
            $scope.selectedColors.push(color.id);
            return;
        }
        else if(!$scope.data.colors[color.id] && !$scope.selectedColors.some(x=>x==color.id)){
            for(let i=0;i<$scope.selectedColors.length;i++){
                if($scope.selectedColors[i]==color.id){
                    $scope.selectedColors.splice(i,1);
                    return;
                }
            }
        }
    }

    $scope.getAllSizes();
    $scope.getAllColors();
    $scope.getData();
});