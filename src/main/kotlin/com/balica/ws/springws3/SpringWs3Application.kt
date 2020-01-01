package com.balica.ws.springws3

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(info = Info(title = "Balcia demo API", version = "1.0.0"))
@SecuritySchemes(
        SecurityScheme(name = "openIdConnectUrl",
                type = SecuritySchemeType.OPENIDCONNECT,
                openIdConnectUrl = "http://localhost:8280/auth/realms/quarkus/.well-known/openid-configuration"),
        SecurityScheme(name = "barrier",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer")
)
@SpringBootApplication
class SpringWs3Application

fun main(args: Array<String>) {
    runApplication<SpringWs3Application>(*args)
}
