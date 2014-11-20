/**
 * Created by cliff.maury on 18/11/2014.
 */

'use strict';

angular.module('webappApp').controller('LoginController', function ($scope, $http, authService, $rootScope) {

  $scope.doLogin = function () {

    $http.post(
      '/angularspring/api/login',
      'username=' + $scope.username + '&password=' + $scope.password,
      {
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
          "Accept": "application/json"
        }
      })
      .success(function (data, status, headers, config) {
        console.log(status);

        $http.get('/angularspring/api/name').success(function (data) {
          console.log(data);
          $rootScope.remoteUsername = data.name;
        });

        authService.loginConfirmed();

      })
      .error(function (data, status, headers, config) {
        console.log(status);
      });
  };
});
