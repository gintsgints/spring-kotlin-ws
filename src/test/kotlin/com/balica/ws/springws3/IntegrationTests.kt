package com.balica.ws.springws3

import com.balica.ws.springws3.greeting.Greeting
import com.balica.ws.springws3.greeting.GreetingEntity
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["spring.datasource.initialization-mode=always"])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTests (@Autowired val restTemplate: TestRestTemplate) {
    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Insert greeting`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity<Greeting>(Greeting("gr1", null), headers)
        val result = restTemplate.postForEntity<Greeting>("/api/greeting/", request, Greeting::class.java)
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `GET greeting`() {
        val result = restTemplate.getForEntity<Greeting>("/api/greeting/1", Greeting::class.java)
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body?.text).isEqualTo("gr1")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}
