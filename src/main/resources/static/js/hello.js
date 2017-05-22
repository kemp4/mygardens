angular.module('hello', ['ngRoute'])
.config(['$locationProvider', function($locationProvider) {
	$locationProvider.hashPrefix('');
}])
.config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home',
		controllerAs: 'controller'
	}).when('/buildings', {
		templateUrl : 'buildings.html',
		controller : 'buildings',
		controllerAs: 'controller'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation',
		controllerAs: 'controller'
	}).when('/register', {
		templateUrl : 'register.html',
		controller : 'register',
		controllerAs: 'controller'
	}).otherwise('/');

	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

})
.controller('home', function($http) {
	var self = this;
	$http.get('/resource').then(function(response) {
		self.greeting = response.data;
	})
})
.controller('register', function($http) {
	var newuser;
	var self = this;
	self.register = function(){
		var param = {newuser:self.newuser};

		$http.post('/ws/register', param).then(function(response) {
			self.regstatus = response.data;
			//TODO success/failure info
		})
	};
})
.controller('buildings', function($http,$rootScope) {

	var self = this;
	self.buy = function(){
		var cost = {cost:10};
		$http.post('buy', cost).then(function(response) {
			self.buyinfo = response.data;
			$http.post('getplayergold').then(function(response) {
					$rootScope.goldamount = response.data.goldAmount;
			});
		})
	}

})
.controller('navigation',

		function($rootScope, $http, $location) {
	var self = this
	var authenticate = function(credentials, callback) {

		var headers = credentials ? {authorization : "Basic "
			+ btoa(credentials.username + ":" + credentials.password)
		} : {};

		$http.get('user', {headers : headers}).then(function(response) {
			if (response.data.name) {
				$rootScope.authenticated = true;
				//
				$rootScope.goldamount = response.data.principal.gold;
				//
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback();
		}, function() {
			$rootScope.authenticated = false;
			callback && callback();
		});
		self.logout = function() {
			$http.post('logout', {}).finally(function() {
				$rootScope.authenticated = false;
				$location.path("/");
			});
		}
	}

	authenticate();
	self.credentials = {};
	self.login = function() {
		authenticate(self.credentials, function() {
			if ($rootScope.authenticated) {
				$location.path("/");
				self.error = false;
			} else {
				$location.path("/login");
				self.error = true;
			}
		});
	};
});