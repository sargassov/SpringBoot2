angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:9090/market';
    $scope.countParam;

    $scope.loadProducts = function () {
        //$scope.backendParamRequest();
        $http({
            url: contextPath + '/products',
            method: 'GET'
        }).then(function (response) {
            $scope.productList = response.data;
        });

    };

    $scope.correctParam = function (int) {
        $http({
            url: contextPath + '/products/set_param',
            method: 'GET',
            params: {
                delta: int
            }
        }).then(function (responce) {
            $scope.countParam = responce.data;
            $scope.loadProducts();
        });
    };

    $scope.backendParamRequest = function () {
        $http(contextPath + '/products/param_request')
            .then(function (response) {
                $scope.countParam = response.data;
        });
    }

    $scope.deleteProduct = function (Id) {
        $http({
            url: contextPath + '/products/delete',
            method: 'GET',
            params: {
                id: Id
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.findAllWithSort = function () {
        $http({
            url: contextPath + '/products/sort',
            method: 'GET',
            params: {
                minCost: $scope.prod.minimalCost,
                maxCost: $scope.prod.maximalCost
            }
        }).then(function (response) {
            $scope.productList = response.data;
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

    $scope.addNewProduct = function () {
        $http({
            url: contextPath + '/products/new',
            method: 'GET',
            params: {
                title:$scope.newProd.title,
                cost: $scope.newProd.cost
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadProducts();
});