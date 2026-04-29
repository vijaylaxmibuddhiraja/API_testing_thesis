## Source 1 — Guru99 REST Assured Tutorial
**URL:** https://www.guru99.com/rest-assured.html


## What I learned from this source:
- How to structure a basic RestAssured test with given/when/then
- How to use statusCode() to verify HTTP responses
- How to set up baseURI before tests run

## Conclusion:
The pattern is the same but the implementation,

## JSONPlaceholder behaviour note:
Source: https://jsonplaceholder.typicode.com
POST, PUT, DELETE requests are faked —
responses are realistic but data is not saved.
Confirmed by official documentation.
This is acceptable for framework comparison.

## ReqRes.in API Key Note
Source: https://reqres.in
API key used for authentication tests.
This is a free personal test key from reqres.in.
In a real project this would be stored in
environment variables, not hardcoded in test code.
