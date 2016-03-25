angular.module('app.Factory')
    .factory('urlConstantsFact', function () {

        //var prefix = 'http://192.168.1.254:8080/HT/Services';
        //var prefix = 'http://localhost:8080/htultimate/rest';
        //var prefix2 = 'http://someone:mypass@localhost:8080/htultimate/rest';
        var prefix = '/TPV/rest';
        //var prefix = 'http://alexberemart.synology.me:8080/HT/Services';

        return{
            /**
             * @return {string}
             */
            TRANSACTION_ITEM: function () {
                return prefix + '/transactionItem';
            },
            /**
             * @return {string}
             */
            CREATE_TRANSACTION: function () {
                return prefix + '/transaction';
            },
            /**
             * @return {string}
             */
            ADD_QUANTITY: function () {
                return prefix + '/transactionItem/addQuantity';
            },
            /**
             * @return {string}
             */
            LESS_QUANTITY: function () {
                return prefix + '/transactionItem/lessQuantity';
            },
            /**
             * @return {string}
             */
            GET_PDF: function () {
                return prefix + '/transactions/';
            },
            /**
             * @return {string}
             */
            ADD_PAYMENT_PDF: function () {
                return prefix + '/transactionPayment';
            }
        };
    });
