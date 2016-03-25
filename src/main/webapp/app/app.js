angular.module('myApp', [ 'ui.router', 'app.Controllers', 'app.Factory', 'app.Directives', 'ngToast', 'ui.bootstrap', "ui.bootstrap.tpls"])

    .config(['$stateProvider', function ($stateProvider) {

        $stateProvider
            .state('dashboard', {
                url: '/dashboard',
                abstract: true,   //marca este estado como no navegable.
                views: {
                    content: {
                        template: '<div ui-view="dashboardContent"></div>'
                    }
                }
            })
            .state('dashboard.main', {
                url: '/main',
                views: {
                    dashboardContent: {
                        templateUrl: 'app/main/views/main.html',
                        controller: 'main'
                    }
                }
            })
    }])

    .config(['$urlRouterProvider', '$httpProvider', function ($urlRouterProvider) {
        $urlRouterProvider.otherwise('/dashboard/main');
    }]);