angular.module('app').controller('orderController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    var currentPage = 1;

    $scope.fillTable = function ($localStorage) {
        $http({
            url: contextPath + '/api/v1/orders',
            method: "GET",
            params: {
                userName: $localStorage.currentUser ? $localStorage.currentUser.username : null
            }
        }).then(function (response) {
            $scope.orderList = response.data;
        });
    };

    $scope.fillTable();
});