angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:9090/market';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.productList = response.data;
            });
    };

    $scope.deleteProduct = function (Id) {
        $http.get(contextPath + '/products/delete/' + Id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeCost = function (id, delta) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'GET',
            params: {
                id:id,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadProducts();
});