/**
 * Created by cliff.maury on 19/11/2014.
 */

'use strict';

angular.module('webappApp')
  .controller('NavbarController', function ($scope, $http, authService) {

    $scope.doLogout = function () {

      $http.post('/angularspring/api/logout').success(function () {
        console.log('log out');
        authService.loginCancelled();
      });
    };
  });
