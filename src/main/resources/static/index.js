angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:9090/market/api/v1';


    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                //page: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productList = response.data.content;
        });
    };

    $scope.deleteProduct = function (Id) {
        $http.delete(contextPath + '/products/' + Id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.loadProducts();
            });
    }
    //
    // $scope.sumTwoNumbers = function () {
    //     console.log($scope.calcAdd);
    //     $http({
    //         url: contextPath + '/calc/add',
    //         method: 'get',
    //         params: {
    //             a: $scope.calcAdd.a,
    //             b: $scope.calcAdd.b
    //         }
    //     }).then(function (response) {
    //         alert('Сумма равна ' + response.data.value);
    //         $scope.calcAdd.a = 10000;
    //     });
    // }

    $scope.loadProducts();
});