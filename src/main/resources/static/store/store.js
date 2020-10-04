angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    var currentPage = 1;

    $scope.fillTable = function () {
        console.log('fill');
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.Products = response.data;
            });
    };

    $scope.clearFilter = function () {
        currentPage = 1;
        $scope.filtrProduct = null;
        $scope.fillTable();
    };

    $scope.applyFilter = function () {
        currentPage = 1;
        if ($scope.filtrProduct != null) {
            $http({
                url: contextPath + '/api/v1/products',
                method: "GET",
                params: {
                    title: $scope.filtrProduct.title,
                    min_price: $scope.filtrProduct.min_price,
                    max_price: $scope.filtrProduct.max_price
                }
            }).then(function (response) {
                $scope.Products = response.data;
            });
        } else {
            $scope.fillTable();
        }
    }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                // $scope.Products.push(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.previous = function () {
        if (currentPage > 1) {
            currentPage--;
        }
        if ($scope.filtrProduct != null) {
            $http({
                url: contextPath + '/api/v1/products',
                method: "GET",
                params: {
                    p: currentPage,
                    title: $scope.filtrProduct.title,
                    min_price: $scope.filtrProduct.min_price,
                    max_price: $scope.filtrProduct.max_price
                }
            }).then(function (response) {
                $scope.Products = response.data;
            });
        } else {
            $http({
                url: contextPath + '/api/v1/products',
                method: "GET",
                params: {
                    p: currentPage
                }
            }).then(function (response) {
                $scope.Products = response.data;
            });
        }
    }
    $scope.next = function () {
        currentPage++;
        if ($scope.filtrProduct != null) {
            $http({
                url: contextPath + '/api/v1/products',
                method: "GET",
                params: {
                    p: currentPage,
                    title: $scope.filtrProduct.title,
                    min_price: $scope.filtrProduct.min_price,
                    max_price: $scope.filtrProduct.max_price
                }
            }).then(function (response) {
                $scope.Products = response.data;
            });
        } else {
            $http({
                url: contextPath + '/api/v1/products',
                method: "GET",
                params: {
                    p: currentPage
                }
            }).then(function (response) {
                $scope.Products = response.data;
            });
        }
    }

    $scope.fillTable();
});