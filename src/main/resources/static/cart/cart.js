angular.module('app').controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: "GET"
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/dec/'+productId,
            method: "GET"
        }).then(function (response) {
            $scope.cartContentRequest();
        });
    };

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/'+productId,
            method: "GET"
        }).then(function (response) {
            $scope.cartContentRequest();
        });
    };

    $scope.removeItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/remove/'+productId,
            method: "GET"
        }).then(function (response) {
            $scope.cartContentRequest();
        });
    };

    $scope.createOrder = function ($localStorage) {
        $http({
            url: contextPath + '/api/v1/orders/confirm',
            method: "POST",
            params: {
                userName: $localStorage.currentUser ? $localStorage.currentUser.username : null,
                receiverName: $scope.order ? $scope.order.receiverName : null,
                phone: $scope.order ? $scope.order.phone : null,
                address: $scope.order ? $scope.order.address : null
            }
        }).then(function (response) {
            $scope.order = response.data;
        });
    };


    $scope.cartContentRequest();
});