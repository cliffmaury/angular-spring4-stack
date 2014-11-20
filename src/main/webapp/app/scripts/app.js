'use strict';

/**
 * @ngdoc overview
 * @name webappApp
 * @description
 * # webappApp
 *
 * Main module of the application.
 */
angular
  .module('webappApp', [
    'ngResource',
    'ngRoute',
    'http-auth-interceptor'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginController'
      })
      .when('/users', {
        templateUrl: 'views/users.html',
        controller: 'UsersController'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .run(function ($rootScope, $location) {

    // Call when the the client is confirmed
    $rootScope.$on('event:auth-loginConfirmed', function (data) {
      console.log('loginConfirmed');
      $rootScope.authenticated = true;
      if ($location.path() === '/login') {
        var search = $location.search();
        if (search.redirect !== undefined) {
          $location.path(search.redirect).search('redirect', null).replace();
        } else {
          $location.path('/').replace();
        }
      }
    });

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function (rejection) {
      console.log('loginRequired');
      $rootScope.authenticated = false;
      if ($location.path() !== '/' && $location.path() !== '' && $location.path() !== '/register' &&
        $location.path() !== '/activate' && $location.path() !== '/login') {
        var redirect = $location.path();
        $location.path('/login').search('redirect', redirect).replace();
      }
    });

    // Call when the 403 response is returned by the server
    $rootScope.$on('event:auth-notAuthorized', function (rejection) {
      $rootScope.errorMessage = 'errors.403';
      $location.path('/error').replace();
    });

    // Call when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function () {
      $rootScope.authenticated = false;
      $location.path('');
    });

  })
;
