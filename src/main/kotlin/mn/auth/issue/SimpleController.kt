package mn.auth.issue

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.QueryValue
import io.reactivex.Single

@Controller("/simple")
class SimpleController {

    @Get("/")
    fun index(@QueryValue id: String? = null): Single<List<String>> {
        return Single.just(listOf(id ?: "no-id"))
    }
}