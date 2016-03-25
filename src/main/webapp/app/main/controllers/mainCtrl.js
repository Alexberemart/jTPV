angular.module('app.Controllers')
    .controller('main',
        function ($scope, $http, $location, $window, $q, urlConstantsFact, ngToast, $compile, $filter, $uibModal) {

            $scope.itemCode = "";
            $scope.transaction = {};
            $scope.paymentAmountByCash = null;

            var createTransaction = function () {
                $http.post(urlConstantsFact.CREATE_TRANSACTION(), {})
                    .success(showSuccess)
                    .error(showError)
            };

            //TODO: HACER COMO UNA DIRECTIVA??
            $scope.addNumber = function (number) {
                $scope.itemCode += number;
            };

            $scope.$watch('transaction', function (newValue, oldValue) {
                $scope.transaction.subTotal = parseFloat(($scope.transaction.price - $scope.transaction.discountAmount).toFixed(2));
                if (!!$scope.transaction.transactionItemList) {
                    $scope.transaction.transactionItemList.forEach(function (element, index, array) {
                        array[index].subTotal = parseFloat((array[index].price - array[index].discountAmount).toFixed(2));
                    })
                }
                $scope.transaction.euroTotalAmount = $filter('currency')($scope.transaction.totalAmount);
            });

            $scope.addTransactionItem = function () {

                var url = urlConstantsFact.TRANSACTION_ITEM();
                url += '?itemCode=' + $scope.itemCode;
                url += '&transactionId=' + $scope.transaction.id;
                $http.post(url, {})
                    .success(showSuccessWithError)
                    .error(showError)
                    .finally(function () {
                        $scope.itemCode = "";
                    })
            };

            $scope.deleteTransactionItem = function () {

                var url = urlConstantsFact.TRANSACTION_ITEM();
                url += '?itemCode=' + $scope.itemCode;
                url += '&transactionId=' + $scope.transaction.id;
                $http.delete(url)
                    .success(showSuccess)
                    .error(showError)
                    .finally(function () {
                        $scope.itemCode = "";
                    })
            };

            $scope.addQuantity = function (id) {
                $http.put(urlConstantsFact.ADD_QUANTITY() + '?transactionItemID=' + id, {})
                    .success(showSuccess)
                    .error(showError)
                    .finally(function () {
                        $scope.itemCode = "";
                    })
            };

            $scope.lessQuantity = function (id) {
                $http.put(urlConstantsFact.LESS_QUANTITY() + '?transactionItemID=' + id, {})
                    .success(showSuccessWithError)
                    .error(showError)
                    .finally(function () {
                        $scope.itemCode = "";
                    })
            };

            var showSuccessWithError = function (data) {
                if (data.status === -1) {
                    ngToast.create(
                        {
                            className: 'danger',
                            content: data.result,
                            dismissButton: true
                        })
                } else {
                    showSuccess(data.result);
                }
            };

            var showSuccess = function (data) {
                $scope.transaction = data;
                $scope.paymentAmountByCash = 0;
            };

            var showError = function (error) {
                ngToast.create(
                    {
                        className: 'danger',
                        content: error.error,
                        dismissButton: true
                    })
            };

            createTransaction();

            $scope.getTicket = function () {

                var modalInstance = $uibModal.open({
                    templateUrl: "app/ticket/ticket.html",
                    controller: 'ticketCtrl',
                    size: "sm",
                    resolve: {
                        transaction: function () {
                            return $scope.transaction;
                        }
                    }
                });
            };

            $scope.showPaymentByCash = function () {
                var modalInstance = $uibModal.open({
                    templateUrl: "app/payment-by-cash/views/paymentByCash.html",
                    controller: 'paymentByCashCtrl',
                    resolve: {
                        transaction: function () {
                            return $scope.transaction;
                        },
                        paymentMethod: function () {
                            return "METALICO"
                        }
                    }
                });
            };

            $scope.showPaymentByCreditCard = function () {
                var modalInstance = $uibModal.open({
                    templateUrl: "app/payment-by-cash/views/paymentByCash.html",
                    controller: 'paymentByCashCtrl',
                    resolve: {
                        transaction: function () {
                            return $scope.transaction;
                        },
                        paymentMethod: function () {
                            return "TARJETA"
                        }
                    }
                });
            };

            $scope.$on('paymentByCashEvent', function (event, data) {
                showSuccess(data.transaction);
            });

            $scope.paymentAvailable = function(){
                if (!$scope.transaction.transactionItemList){
                    return false;
                }
                return !(($scope.transaction.transactionItemList.length === 0) || ($scope.transaction.remainingAmount === 0));
            }
        }
    );