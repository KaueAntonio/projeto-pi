(function() {
    var CA = (function() {
        var   request = require('request')
            , crypto  = require('crypto')
            , version = require(require('path').resolve(__dirname, 'package.json')).version
            , api
			, rest
            , oauthRequest
            , parseOAuthApiResponse
            , setAccessToken
            , getAccessToken
            , base64UrlDecode
            , log
            , has
            , options
            , METHODS = ['get', 'post', 'delete', 'put']
            , opts = {
                  'accessToken': null
                , 'timeout': null
                , 'scope':  null
                , 'proxy': null
            };
        /**
         *
         * @access public
         * @param path {String} the url path
         * @param method {String} the http method (default: `"GET"`)
         * @param params {Object} the parameters for the query
         * @param cb {Function} the callback function to handle the response
         */
        api = function() {
            rest.apply(this, arguments);
        };

        /**
         *
         * Make a api call to Graph server.
         *
         * Except the path, all arguments to this function are optiona. So any of
         * these are valid:
         *
         *  CA.api('/me') // throw away the response
         *  CA.api('/me', function(r) { console.log(r) })
         *  CA.api('/me', { fields: 'email' }); // throw away response
         *  CA.api('/me', { fields: 'email' }, function(r) { console.log(r) });
         *  CA.api('/123456789', 'delete', function(r) { console.log(r) } );
         *  CA.api(
         *      '/me/feed',
         *      'post',
         *      { body: 'hi there' },
         *      function(r) { console.log(r) }
         *  );
         *
         */
        rest = function() {
            var   args = Array.prototype.slice.call(arguments)
                , path = args.shift()
                , next = args.shift()
                , method
                , params
                , cb;

            while(next) {
                var type = typeof next;
                if(type === 'string' && !method) {
                    method = next.toLowerCase();
                } else if(type === 'function' && !cb) {
                    cb = next;
                } else if(type === 'object' && !params) {
                    params = next;
                } else {
                    log('Invalid argument passed to CA.api(): ' + next);
                    return;
                }
                next = args.shift();
            };

            method = method || 'get';
            params = params || {};

            // remove prefix slash if one is given, as it's already in the base url
            if(path[0] === '/') {
                path = path.substr(1);
            }

            if(METHODS.indexOf(method) < 0) {
                log('Invalid method passed to CA.api(): ' + method);
                return;
            }

            oauthRequest(path, method, params, cb);
        };

        /**
         * Add the oauth parameter, and fire of a request.
         *
         * @access private
         * @param path {String}     the request path
		 *                          if path doesn't include :// then use production api url
         * @param method {String}   the http method
         * @param params {Object}   the parameters for the query
         * @param cb {Function}     the callback function to handle the response
         */
        oauthRequest = function(path, method, params, cb) {
            var   uri
                , body
                , key
                , value
                , requestOptions
                , isOAuthRequest;

            cb = cb || function() {};
            if(!params.access_token && options('accessToken')) {
                params.access_token = options('accessToken');
            }

            if(path.indexOf(":") < 0) {
                uri = 'https://api.culinaryagents.com/' + path;
                //isOAuthRequest = /^oauth.*/.test('oauth/');  //temporary remove
				isOAuthRequest = true;
            }
            else {
                uri = path;
            }

            if(method === 'post') {
                body = '';
                if(params.access_token) {
                    uri += '?access_token=' + encodeURIComponent(params.access_token);
                    delete params['access_token'];
                }

                for(key in params) {
                    value = params[key];
                    if(typeof value !== 'string') {
                        value = JSON.stringify(value);
                    }
                    if(value !== undefined) {
                        body += encodeURIComponent(key) + '=' + encodeURIComponent(value) + '&';
                    }
                }

                if(body.length > 0) {
                    body = body.substring(0, body.length - 1);
                }
            } else {
                uri += '?';
                for(key in params) {
                    value = params[key];
                    if(typeof value !== 'string') {
                        value = JSON.stringify(value);
                    }
                    uri += encodeURIComponent(key) + '=' + encodeURIComponent(value) + '&';
                }
                uri = uri.substring(0, uri.length -1);
                body='';
            };

            requestOptions = {
                  method: method
                , uri: uri
                , body: body
            };
            if(options('proxy')) {
                requestOptions['proxy'] = options('proxy');
            }
            if(options('timeout')) {
                requestOptions['timeout'] = options('timeout');
            }
            request(requestOptions
            , function(error, response, body) {
                if(error !== null) {
                    if(error === Object(error) && has(error, 'error')) {
                        return cb(error);
                    }
                    return cb({error:error});
                }

                if(isOAuthRequest && response && response.statusCode === 200 &&
                    response.headers && /.*text\/plain.*/.test(response.headers['content-type'])) {
                    cb(parseOAuthApiResponse(body));
                } else {
                    var json;
                    try {
                        json = JSON.parse(body);
                    } catch (ex) {
                      // sometimes CA is has API errors that return HTML and a message
                      // of "Sorry, something went wrong". These are infrequent and unpredictable but
                      // let's not let them blow up our application.
                      json =  { error: {
                          code: 'JSONPARSE',
                          Error: ex,
                          body: body
                      }};
                    }
                    cb(json);
                }
            });
        };

        parseOAuthApiResponse = function (body) {
            var   result
                , key
                , value
                , split;

            result = {};
            body = body.split('&');
            for(key in body) {
                split = body[key].split('=');
                if(split.length === 2) {
                    value = split[1];
                    if(!isNaN(value)) {
                        result[split[0]] = parseInt(value);
                    } else {
                        result[split[0]] = value;
                    }
                }
            }

            return result;
        };

        log = function(d) {
            // todo
            console.log(d);
        };

        has = function (obj, key) {
            return Object.prototype.hasOwnProperty.call(obj, key);
        };

        getAccessToken = function () {
            return options('accessToken');
        };

        setAccessToken = function (accessToken) {
            options({'accessToken': accessToken});
        };

        base64UrlDecode = function (str) {
            var base64String = str.replace(/\-/g, '+').replace(/_/g, '/');
            var buffer = new Buffer(base64String, 'base64');
            return buffer.toString('utf8');
        }

        options = function (keyOrOptions) {
            var key;
            if(!keyOrOptions) {
                return opts;
            }
            if(Object.prototype.toString.call(keyOrOptions) == '[object String]') {
                return has(opts, keyOrOptions) ? opts[keyOrOptions] : null;
            }
            for(key in opts) {
                if(has(opts, key) && has(keyOrOptions, key)) {
                    opts[key] = keyOrOptions[key];
                }
            }
        };

        return {
              api: api
            , getAccessToken: getAccessToken
            , setAccessToken: setAccessToken 
            , options: options
            , version: version 
        };

    })();

    module.exports = CA;

})();