angular.module('app').controller('userController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    var currentPage = 1;

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/api/v1/user',
            method: 'GET'
        }).then(function (response) {
            $scope.profile = response.data;
        });
    };

    $scope.fillTable();
});