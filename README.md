# Steps

1.) Run the Spring app, and query the endpoint `GET /test`.
* BlockHound will throw an error.

2.) Run the test, which queries the same endpoint.
* BlockHound will **not** throw an error.

3.) Uncomment line 38 in the handler function (`.delayElement(Duration.ofMillis(1))`) and run the test again.
* Blockhound will throw an error.
