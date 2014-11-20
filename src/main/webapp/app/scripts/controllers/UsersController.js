/**
 * Created by cliff.maury on 18/11/2014.
 */

'use strict';

angular.module('webappApp').controller('UsersController', function ($scope, $resource) {

  var Users = $resource('/angularspring/api/users/');


  $scope.users = Users.query();

  $scope.addUser = function () {

    Users.save({name: $scope.name, lastName: $scope.lastName});
    $scope.users = Users.query();
    $scope.name = $scope.lastName = '';
  };

});
