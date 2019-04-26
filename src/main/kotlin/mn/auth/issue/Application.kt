package mn.auth.issue

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .mainClass(Application.javaClass)
                .start()
    }
}