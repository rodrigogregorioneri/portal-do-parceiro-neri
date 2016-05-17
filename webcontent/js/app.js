var app = (function() {
    return angular.module('MyApp', [
    'ui.router',
    'ui.select',
    'ui-select-infinity',
    'ngResource',
    'ngSanitize',
    'custom.controllers', 
    'custom.services',
    'datasourcejs',
    'chart.js',
	  'ngMask',
    'ngJustGage',
    'pascalprecht.translate',
    'tmh.dynamicLocale',
    'ui-notification'
    ])
    
    .constant('LOCALES', {
      'locales': {
        'pt_BR': 'Portugues (Brasil)',
        'en_US': 'English'
      },
      'preferredLocale': 'pt_BR'
    })
    
    .config(function($stateProvider, $urlRouterProvider, NotificationProvider) {
        NotificationProvider.setOptions({
            delay: 5000,
            startTop: 20,
            startRight: 10,
            verticalSpacing: 20,
            horizontalSpacing: 20,
            positionX: 'right',
            positionY: 'top'
        });
        
        // Set up the states
        $stateProvider
          
          .state('login', {
            url: "",
            controller: 'LoginController',
            templateUrl: 'views/login.view.html'
          })
          
          .state('main', {
            url: "/",
            controller: 'LoginController',
            templateUrl: 'views/login.view.html'
          })
          
          .state('home', {
            url: "/home",
            controller: 'HomeController',
            templateUrl: 'views/logged/home.view.html'
          })

          .state('home.pages', {
            url: "/{name:.*}",
            controller: 'PageController',
            templateUrl: function(urlattr){
                return 'views/'+urlattr.name+'.view.html';
            }
          }) 
          
          .state('404', {
            url: "/error/404",
            controller: 'PageController',
            templateUrl: function(urlattr){
                return 'views/error/404.view.html';
            }
          })
          
          .state('403', {
            url: "/error/403",
            controller: 'PageController',
            templateUrl: function(urlattr){
                return 'views/error/403.view.html';
            }
          });
          
         // For any unmatched url, redirect to /state1
         $urlRouterProvider.otherwise("/error/404");
    })

    .config(function ($translateProvider, tmhDynamicLocaleProvider) {
      
      $translateProvider.useMissingTranslationHandlerLog();
      
      $translateProvider.useStaticFilesLoader({
        prefix: 'i18n/locale_',
        suffix: '.json'
      });
      
      $translateProvider.registerAvailableLanguageKeys(
        ['pt_BR', 'en_US'],
        {
          'en*': 'en_US',
          'pt*': 'pt_BR',
          '*'  : 'pt_BR'
        }
      );
      
      var locale = (window.navigator.userLanguage || window.navigator.language || 'pt_BR').replace('-', '_');
      
      $translateProvider.use(locale);
      $translateProvider.useSanitizeValueStrategy('escaped');

      tmhDynamicLocaleProvider.localeLocationPattern('plugins/angular-i18n/angular-locale_{{locale}}.js');
    })

    .directive('crnValue', ['$parse', function($parse) {
      return {
        restrict: 'A',
        require: '^ngModel',
        link: function(scope, element, attr, ngModel) {
            var evaluatedValue;
            if(attr.value) {
              evaluatedValue = attr.value;
            } else {
              evaluatedValue = $parse(attr.crnValue)(scope);
            }
            element.attr("data-evaluated", JSON.stringify(evaluatedValue));
            element.bind("click", function(event) {
              scope.$apply(function() {
                 ngModel.$setViewValue(evaluatedValue);  
              }.bind(element));
            });
        }
      };
    }])

    // General controller
    .controller('PageController',["$scope","$stateParams","$location","$http",function($scope, $stateParams, $location, $http){
      for(var x in app.userEvents)
        $scope[x]= app.userEvents[x].bind($scope);
      
      // save state params into scope
      $scope.params = $stateParams;
      $scope.$http = $http;
      
      // Query string params
      var queryStringParams = $location.search();
      for(var key in queryStringParams) {
        if(queryStringParams.hasOwnProperty(key)) {
          $scope.params[key] = queryStringParams[key];
        }
      }
    }])
    
    .run(function($rootScope,$state) {
      $rootScope.$on('$stateChangeError', function() {
        if(arguments.length >= 6) {
          var requestObj = arguments[5];
          if(requestObj.status === 404 || requestObj.status === 403) {
            $state.go(requestObj.status.toString()); 
          }
        } else {
          $state.go('404');
        }
      });
    });

}(window));

app.userEvents = {};