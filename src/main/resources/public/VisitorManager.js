angular.module('myApp', []).controller('userCtrl', function($scope, $http) {
$scope.visitorDetails = {};
$scope.loadVisitorDetails = function(visitorTrackingCode) {
  var url = 'http://localhost:8080/getVisitorDetailsByMeetingVisitorTrackerCode?meetingVisitorTrackerId='+visitorTrackingCode;
  $http.get(url).success(function(data) {
            $scope.visitorDetails = data;
        });
};
});