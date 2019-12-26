package com.balica.ws.springws3

import com.balica.ws.springws3.greeting.Greeting
import com.balica.ws.springws3.greeting.GreetingEntity
import com.balica.ws.springws3.greeting.GreetingRepository
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.persistence.EntityManager

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTests (@Autowired val restTemplate: TestRestTemplate, @Autowired val greetingRepository: GreetingRepository) {
    lateinit var gr1: GreetingEntity;
    lateinit var gr2: GreetingEntity;
    lateinit var greeting: GreetingEntity;
    lateinit var greetingv2: GreetingEntity;

    @BeforeAll
    fun setup() {
        greetingRepository.deleteAll()
        gr1 = GreetingEntity("gr1")
        greetingRepository.save(gr1)
        gr2 = GreetingEntity("gr2")
        greetingRepository.save(gr2)
        greeting = GreetingEntity("gr2")
        greetingRepository.save(greeting)
        greetingv2 = GreetingEntity("gr2")
        greetingRepository.save(greetingv2)
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
        val result = restTemplate.getForEntity<Greeting>("/api/greeting/${gr1.id}", Greeting::class.java)
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body?.text).isEqualTo("gr1")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}
