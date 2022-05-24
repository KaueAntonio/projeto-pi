var nock        = require('nock'),
    should      = require('should'),
    CA;

var config = {
    'url': 'http://api.culinaryagents.com',
    'accessToken': 'Please-get-accessToken-from-culinaryagents.com'
}
var testValue = {
    'userId' : 1
}

beforeEach(function () {
    CA = require('../..');
});

afterEach(function () {
   nock.cleanAll();
});

describe('CA.api', function () {
    describe('/users', function () {
        describe('/user_id='+testValue.userId, function () {
            describe('/socials', function () {
                it('contains lastname "Yoon"', function (done) {
                    var get_uri = '/users/'+testValue.userId+'/socials';
                    nock(config.url)
                        //.log(console.log)
                        .get(get_uri+'?access_token='+config.accessToken)
                        .reply(200, '{"id":"1","lastname":"Yoon","firstname":"Jun"}', { 'access-control-allow-origin': '*',
                            'content-type': 'text/javascript; charset=UTF-8',
                            'content-length': '172' });

                    CA.api(config.url+get_uri, 'get',  {access_token:config.accessToken}, function (result) {
                        //console.log(result);
                        result.should.have.property('lastname', 'Yoon');
                        done();
                    });
                });//end if

                it('contains lastname "Kim"', function (done) {
                    var get_uri = '/users/'+testValue.userId+'/socials';
                    nock(config.url)
                        //.log(console.log)
                        .get(get_uri+'?access_token='+config.accessToken)
                        .reply(200, '{"id":"1","lastname":"Kim","firstname":"Hoonsung"}', { 'access-control-allow-origin': '*',
                            'content-type': 'text/javascript; charset=UTF-8',
                            'content-length': '172' });

                    CA.api(config.url+get_uri, 'get',  {access_token:config.accessToken}, function (result) {
                        //console.log(result);
                        result.should.have.property('lastname', 'Kim');
                        done();
                    });
                });//end if

                it('contains lastname "Chan"', function (done) {
                    var get_uri = '/users/'+testValue.userId+'/socials';
                    nock(config.url)
                        //.log(console.log)
                        .get(get_uri+'?access_token='+config.accessToken)
                        .reply(200, '{"id":"1","lastname":"Chan","firstname":"Raymond"}', { 'access-control-allow-origin': '*',
                            'content-type': 'text/javascript; charset=UTF-8',
                            'content-length': '172' });

                    CA.api(config.url+get_uri, 'get',  {access_token:config.accessToken}, function (result) {
                        //console.log(result);
                        result.should.have.property('lastname', 'Chan');
                        done();
                    });
                }); //end if
            }); //end describe /socials
        }); //end descrive /user_id=
    }); // end describe /users
});
