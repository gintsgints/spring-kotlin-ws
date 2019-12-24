package com.balica.ws.springws3

import com.balica.ws.springws3.greeting.GreetingEntity
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTests (@Autowired val restTemplate: TestRestTemplate) {
    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}
