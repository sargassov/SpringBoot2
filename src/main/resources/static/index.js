angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:9090/market/api/v1';

    $scope.loadProductsFromCart = function () {
        $http.get(contextPath + '/carts')
            .then(function (response) {
                $scope.cartList = response.data.content;
            });
    };

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

    $scope.addProductToCart = function (id) {
        $http.get(contextPath + '/carts/' + id)
            .then(function () {
                // $scope.loadProducts();
                $scope.loadProductsFromCart();
            });
    };

    $scope.deleteOneProductFromCart = function (Id) {
        $http.delete(contextPath + '/carts/' + Id)
            .then(function () {
                // $scope.loadProducts();
                $scope.loadProductsFromCart();
            });
    };

    $scope.deleteAllProductFromCart = function () {
        $http.delete(contextPath + '/carts')
            .then(function (response) {
                // $scope.loadProducts();
                $scope.loadProductsFromCart();
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

    $scope.loadProducts();
    $scope.loadProductsFromCart();
});