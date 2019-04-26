package mn.auth.issue

import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton

@Singleton
class AuthenticationProviderBasic(private val configuration: Configuration) : AuthenticationProvider {
    override fun authenticate(authenticationRequest: AuthenticationRequest<Any,Any>): Publisher<AuthenticationResponse> {
        println("username: ${configuration.security.endpoints.username}, password: ${configuration.security.endpoints.password}")
        println("identity: ${authenticationRequest.identity}, secret: ${authenticationRequest.secret}")
        val endpointSecurity = configuration.security.endpoints

        return if (authenticationRequest.identity == endpointSecurity.username && authenticationRequest.secret == endpointSecurity.password) {
            println("passed")
            Flowable.just(UserDetails(authenticationRequest.identity as String, emptyList()))
        } else {
            println("failed")
            Flowable.just(AuthenticationFailed())
        }
    }
}