package mn.auth.issue

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import java.net.URISyntaxException
import javax.inject.Singleton

@Produces
@Singleton
class UriSyntaxExceptionHandler : ExceptionHandler<URISyntaxException, HttpResponse<Any>> {
    override fun handle(request: HttpRequest<Any>, exception: URISyntaxException): HttpResponse<Any> {
        val unexpectedHeaderMessage = "The request was malformed: ${exception.message}"
        println(unexpectedHeaderMessage)
        return HttpResponse.badRequest(unexpectedHeaderMessage)
    }
}