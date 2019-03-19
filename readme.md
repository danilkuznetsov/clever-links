# Clever Links [![Build Status](https://travis-ci.org/danilkuznetsov/clever-links.svg?branch=master)](https://travis-ci.org/danilkuznetsov/clever-links)
--------------------------------------------------------------------------------------------------

### Main Features:
   - create a new auto-gen short URL by long URL
   - create a new user-defined short URL  
   - add a new user-defined short URL for existen long URL
   - update a long URL for short URL
   - delete short URL
   - validation long URL, support only http and https protocols 
   - normalize long URL

### Planned Features:
* Allow Rest API
   - create API for all action in main features 
   - bulk create auto-gen short URLs in one API request
   - bulk delete short URLs in one API request

* Ignores weird trailing characters (!, ", #, $, %, &, ', (, ), *, +, ,, -, ., /, @, :, ;, <, =, >, [, \, ], ^, _, {, |, }, ~) in short slug
* Allow add secret postfix-key to short URLs
* Collect statistic for click short URLs
* Choices algorithm for generating short URLs
 - Base 16 encoding
 - Base 32 encoding
 - Base 62 encoding
 - [Douglas Crockford's base 32](http://www.crockford.com/wrmg/base32.html)
 - Hex encoding
 - Binary encoding
 - etc.
* Choices 301 or 302 HTTP status for redirect
   - 301 - permanent
   - 302 - temporary for sites when shorten links may change
* Create docker image with service and compose config for simple deployment  
* Redirect to custom 404 error page
* Block reserved URLs or word. 
* Allow forward slashes / in shorten URLs
* Allow define prefix for short URLs in 
* Admin pages require auth
* Admin pages require pagination
* Admin pages view top short URLs
* Allow aliases for main domain
* Add support for custom protocol
* Don't track admin or bot clicks
* Logger for edition history
* Google Analytics Link Tagging
* Black list IP and domains
* Share link to social and etc. 
