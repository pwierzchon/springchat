var chatApp = angular.module("chatApp", []);

chatApp.controller("routeController",
        function ($scope) {
            $scope.inc = {
                "url": "login.html"
            };
        }
);

chatApp.controller("chatController",
        function ($scope, $http, TokenFactory) {
            $scope.getMessages = function () {
                var request = $http({
                    method: "GET",
                    url: "/findall",
                    headers: {
                        "LIB_AUTH_TOKEN": TokenFactory.getValue(),
                        "Accept": "application/json"
                    }
                });

                request.success(
                        function (response) {
                            $scope.messages = response;
                        });
            };

            $scope.getMessages();

            $scope.addMessage = function () {

                var request = $http({
                    method: "POST",
                    url: "/add",
                    data: {
                        "message": $scope.message.text,
                        "date": new Date,
                    },
                    headers: {
                        "LIB_AUTH_TOKEN": TokenFactory.getValue(),
                        "Accept": "application/json"
                    }
                });
                request.success(
                        function (response) {
                            angular.element(document.querySelector("#divstatus")).css("visibility", "visible");
                            if (response.status == "success") {
                                $scope.getMessages();
                                $scope.message = null;
                            }
                        });
            }

        });
chatApp.controller("loginController",
        function ($scope, $http, TokenFactory) {
            $scope.login = function () {
                var request = $http({
                    method: "GET",
                    url: "/role",
                    headers: {
                        "LIB_AUTH_TOKEN": $scope.txtlogin + ":" + $scope.txtpassword,
                        "Accept": "application/json"
                    }
                });
                request.success(
                        function (response) {
                            if (response["role"][0] == "ROLE_USER") {
                                $scope.inc.url = "chatwindow.html"; 
                            }
                            TokenFactory.setValue($scope.txtlogin + ":" + $scope.txtpassword);
                        });

                request.error(
                        function (response) {
                            angular.element(document.querySelector("#divstatus")).css("visibility", "visible");
                            angular.element(document.querySelector("#divstatus")).addClass("alert-danger");
                            $scope.statusmsg = "Error: " + response.message;
                        });

            }
        });

chatApp.constant('authToken', 'authToken');

chatApp.factory('TokenFactory', function () {
    var authToken = {
        value: ""
    };

    authToken.setValue = function (val) {
        this.value = val;
    };

    authToken.getValue = function () {
        return this.value;
    };

    return authToken;
});

chatApp.constant('userId', 'userId');

chatApp.factory('UserIdFactory', function () {
    var userId = {
        value: ""
    };

    authToken.setValue = function (val) {
        this.value = val;
    };

    authToken.getValue = function () {
        return this.value;
    };

    return userId;
});