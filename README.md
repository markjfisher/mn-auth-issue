This repo illustrates an issue with auth handling combined with exception handling
in micronaut.

# Building

    ./gradlew clean build run

# Problem Summary

With security disabled, this correctly returns a 400 due to UriSyntaxExceptionHandler picking it up:

    curl -v http://localhost:8080/simple?id=^

With security enabled, this incorrectly returns a 401 as the ExceptionHandler is not invoked.

    curl -v -u 'user:pass' http://localhost:8080/simple?id=^

It appears the error is causing the auth handling to kick in rather than it passing auth and
going onto the UriSyntaxExceptionHandler.

# Detailed description

Run following without security enabled (the default for the repo) :

    curl -v http://localhost:8080/simple?id=1

This works fine.

Now send a bad request to invoke the UriSyntaxExceptionHandler:

    curl -v http://localhost:8080/simple?id=^

This correctly invokes the handler and gives back a 400, with message:

    The request was malformed: Illegal character in query at index 11: /simple?id=^

# Enabling security to show the issue

Now change `src/main/resources/application.yml` to enable security:

      security:
        enabled: true

Restart the application, and try following:

    # correctly returns 401 as password is incorrect:
    curl -v -u 'user:passx' http://localhost:8080/simple?id=1
    
    # correct case:
    curl -v -u 'user:pass' http://localhost:8080/simple?id=1

Now the issue:

    curl -v -u 'user:pass' http://localhost:8080/simple?id=^

This returns a 401, unauthorized, but should return a 400 with the UriSyntaxExceptionHandler
message as in the case without security enabled.
