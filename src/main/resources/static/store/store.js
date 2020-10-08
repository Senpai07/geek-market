angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    var currentPage = 1;

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
            method: "GET",
            params: {
                title: $scope.filterProduct ? $scope.filterProduct.title : null,
                min_price: $scope.filterProduct ? $scope.filterProduct.min_price : null,
                max_price: $scope.filterProduct ? $scope.filterProduct.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            $scope.PaginationArray = $scope.generatePagesList(1, $scope.ProductsPage.totalPages);
        });
    };

    $scope.generatePagesList = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i)
        }
        return arr;
    }

    // $scope.fillTable = function () {
    //     console.log('fill');
    //     $http.get(contextPath + '/api/v1/products')
    //         .then(function (response) {
    //             $scope.Products = response.data;
    //         });
    // };

    $scope.clearFilter = function () {
        currentPage = 1;
        $scope.filterProduct = null;
        $scope.fillTable();
    };

    // $scope.applyFilter = function () {
    //     currentPage = 1;
    //     if ($scope.filterProduct != null) {
    //         $http({
    //             url: contextPath + '/api/v1/products',
    //             method: "GET",
    //             params: {
    //                 title: $scope.filterProduct.title,
    //                 min_price: $scope.filterProduct.min_price,
    //                 max_price: $scope.filterProduct.max_price
    //             }
    //         }).then(function (response) {
    //             $scope.Products = response.data;
    //         });
    //     } else {
    //         $scope.fillTable();
    //     }
    // }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                // $scope.Products.push(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    // $scope.previous = function () {
    //     if (currentPage > 1) {
    //         currentPage--;
    //     }
    //     if ($scope.filterProduct != null) {
    //         $http({
    //             url: contextPath + '/api/v1/products',
    //             method: "GET",
    //             params: {
    //                 p: currentPage,
    //                 title: $scope.filterProduct.title,
    //                 min_price: $scope.filterProduct.min_price,
    //                 max_price: $scope.filterProduct.max_price
    //             }
    //         }).then(function (response) {
    //             $scope.Products = response.data;
    //         });
    //     } else {
    //         $http({
    //             url: contextPath + '/api/v1/products',
    //             method: "GET",
    //             params: {
    //                 p: currentPage
    //             }
    //         }).then(function (response) {
    //             $scope.Products = response.data;
    //         });
    //     }
    // }
    // $scope.next = function () {
    //     currentPage++;
    //     if ($scope.filterProduct != null) {
    //         $http({
    //             url: contextPath + '/api/v1/products',
    //             method: "GET",
    //             params: {
    //                 p: currentPage,
    //                 title: $scope.filterProduct.title,
    //                 min_price: $scope.filterProduct.min_price,
    //                 max_price: $scope.filterProduct.max_price
    //             }
    //         }).then(function (response) {
    //             $scope.Products = response.data;
    //         });
    //     } else {
    //         $http({
    //             url: contextPath + '/api/v1/products',
    //             method: "GET",
    //             params: {
    //                 p: currentPage
    //             }
    //         }).then(function (response) {
    //             $scope.Products = response.data;
    //         });
    //     }
    // }

    $scope.fillTable();
});