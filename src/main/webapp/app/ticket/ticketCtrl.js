angular.module('app.Controllers')
    .controller('ticketCtrl',
        function ($scope, $uibModalInstance, transaction, urlConstantsFact, $http, $rootScope) {
            $scope.transaction = transaction;
            $scope.currentDate = new Date();
            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
            $scope.printToCart = function () {
                html2canvas(document.getElementById("ticketPanel"), {
                    onrendered: function (canvas) {
                        var popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
                        popupWinindow.document.open();
                        popupWinindow.document.write('<html><head></head><body onload="window.print()"><img src="' + canvas.toDataURL() + '"></html>');
                        popupWinindow.document.close();
                    }
                });
                $uibModalInstance.dismiss('cancel');
                $rootScope.$broadcast('ticketPrintedEvent');
            };
            $scope.compareNotEquals = function (actual, expected) {
                return actual !== expected;
            };
        }
    );