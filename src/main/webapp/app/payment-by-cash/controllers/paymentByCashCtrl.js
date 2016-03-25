angular.module('app.Controllers')
    .controller('paymentByCashCtrl',
        function ($scope, $uibModalInstance, transaction, urlConstantsFact, $http, $rootScope, paymentMethod) {
            $scope.transaction = transaction;
            $scope.paymentMethod = paymentMethod;
            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
            $scope.paymentByCashManager = function () {

                var amountToPay = 0;

                if ($scope.paymentAmountByCash <= $scope.transaction.remainingAmount) {
                    amountToPay = $scope.paymentAmountByCash;
                } else {
                    amountToPay = $scope.transaction.remainingAmount;
                }

                var url = urlConstantsFact.ADD_PAYMENT_PDF();
                url += '?paymentMethod=' + paymentMethod;
                url += '&transactionId=' + $scope.transaction.id;
                url += '&amount=' + amountToPay;
                $http.post(url, {})
                    .success(showSuccess);
            };
            var showSuccess = function (data) {
                $scope.transaction = data;
                $scope.paymentAmountByCash = 0;
                $rootScope.$broadcast('paymentByCashEvent', {
                    transaction: $scope.transaction
                });
                $scope.cancel();
            };

            $scope.$watch('paymentAmountByCash', function (newValue, oldValue) {
                if (!$scope.paymentAmountByCash) {
                    $scope.moneyToReturn = 0;
                } else {
                    if ($scope.paymentAmountByCash <= $scope.transaction.remainingAmount) {
                        $scope.moneyToReturn = 0;
                    } else {
                        $scope.moneyToReturn = ($scope.paymentAmountByCash - $scope.transaction.remainingAmount).toFixed(2);
                    }
                }
            });
        }
    );