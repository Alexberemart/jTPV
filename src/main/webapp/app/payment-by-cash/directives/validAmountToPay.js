angular.module('app.Directives')
    .directive('validAmountToPay', function () {
        return {
            restrict: 'A',
            require: 'ngModel',
            scope: {
                validAmountToPayRemainingAmount: '=validAmountToPayRemainingAmount',
                validAmountToPayPaymentMethod: '=validAmountToPayPaymentMethod'
            },
            link: function (scope, element, attributes, ngModel) {

                var validAmountToPayRemainingAmount = scope.validAmountToPayRemainingAmount;
                var validAmountToPayPaymentMethod = scope.validAmountToPayPaymentMethod;

                ngModel.$parsers.push(function (value) {
                    debugger;
                    console.log(validAmountToPayRemainingAmount);
                    var valid = true;

                    if (validAmountToPayPaymentMethod === "TARJETA") {
                        if (value > validAmountToPayRemainingAmount) {
                            valid = false;
                        }
                    }
                    if (value <= 0) {
                        valid = false;
                    }
                    ngModel.$setValidity('validAmountToPay', valid);
                    return valid ? value : undefined;
                });
            }
        }
    });
