package mn.auth.issue

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.reactivex.Single

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/simple")
class SimpleController {

    @Get("/")
    fun index(@QueryValue id: String? = null): Single<List<String>> {
        return Single.just(listOf(id ?: "no-id"))
    }
}