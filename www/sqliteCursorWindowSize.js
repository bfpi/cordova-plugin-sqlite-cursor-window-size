var exec = require('cordova/exec');

exports.initialize = function (size, successCallback, errorCallback) {
  exec(successCallback, errorCallback, 'SQLite-CursorWindowSize', 'initialize', [size]);
};
