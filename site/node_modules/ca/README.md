# NodeJS Library for Culinary Agents API

You can use Culinary Agents API with this SDK for node.js

**Author:** [Jun S. Yoon](https://github.com/jsyoon94)

**License:** Apache v2

# Installing culinaryagents-api-node-sdk

```
npm install ca-api
```

```javascript
var CA = require('ca');
```

## Culinary Agents Api

### Get

```js
var CA = require('ca');

CA.api('/users', 'get',  {access_token:'oAuth-access-token'}, function (result) {
    console.log(result);
});
```


### POST

```js
var CA = require('ca');

CA.api('/users', 'post',  {access_token:'oAuth-access-token', 'firstname':'Jun', 'lastname':'Yoon'}, function (result) {
    console.log(result);
});
```


### Test Node module with mocha
```ca
node ./node_modules/mocha/bin/mocha --recursive --reporter spec
```
```result
  CA.api
    /users
      /user_id=1
        /socials
          v contains lastname "Yoon"
          v contains lastname "Kim"
          v contains lastname "Chan"


  3 passing (127ms)
```
