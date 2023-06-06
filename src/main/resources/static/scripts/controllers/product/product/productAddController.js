app.controller("productAddCtrl", function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.productCategory=$rootScope.productCategory;
    $scope.colorList=[];
    $scope.sizeList=[];
    $scope.newFeature={};
    $scope.data.features=[];
    $scope.featureList=[];
    $scope.addData = () => {
        $scope.data.image=$rootScope.uploadedFile;
        $scope.data.categoryId=$scope.productCategory.id;
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
        if($scope.data.image===undefined || $scope.data.image===null || $scope.data.image===""){
            Swal.fire("please upload an image");
            return;
        }
        apiHandler.callPost("product/",$scope.data,(response)=>{
            $scope.changeMenuWithProductCategory('product-list');
        },(error)=>{

        },true);
    }

    $scope.changeMenuWithProductCategory=(template)=>{
        $rootScope.productCategory=$scope.productCategory;
        $scope.changeMenu(template);
    }

    $scope.getAllColors=()=>{
        apiHandler.callGet("color/",(response)=>{
            
            $scope.colorList=response.dataList;
        },(error)=>{

        },true);
    }
    $scope.getAllSizes=()=>{
        apiHandler.callGet("size/",(response)=>{
            $scope.sizeList=response.dataList;
        },(error)=>{

        },true);
    }

    $scope.addFeature=()=>{
        apiHandler.callPost("feature/",$scope.newFeature,(response)=>{
            $scope.data.features.push(response.dataList[0].id);
            $scope.featureList.push(response.dataList[0]);
            $scope.newFeature={};
        },(error)=>{

        },true);
    }
    $scope.deleteFeature=(id)=>{
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
                apiHandler.callDelete("feature/"+id,(response)=>{
                    for(let i=0;i<$scope.data.features.length;i++){
                        if($scope.data.features[i]==id){
                            $scope.data.features.splice(i,1);
                            break;
                        }
                    }
                    for(let i=0;i<$scope.featureList.length;i++){
                        if($scope.featureList[i].id==id){
                            $scope.featureList.splice(i,1);
                            break;
                        }
                    }
                },(error)=>{

                },true);

            }
        })

    }
    $scope.getAllSizes();
    $scope.getAllColors();
});