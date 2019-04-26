package mn.auth.issue

import io.micronaut.context.annotation.ConfigurationProperties
import javax.inject.Inject

@ConfigurationProperties("app")
class Configuration {
    @Inject
    internal var security = Security()

    @ConfigurationProperties("security")
    internal class Security {
        @Inject
        internal var endpoints = Endpoints()

        @ConfigurationProperties("endpoints")
        internal class Endpoints {
            lateinit var username: String
            lateinit var password: String
        }
    }
}