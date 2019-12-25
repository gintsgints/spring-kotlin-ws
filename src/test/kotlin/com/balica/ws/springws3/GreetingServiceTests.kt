package com.balica.ws.springws3

import com.balica.ws.springws3.greeting.GreetingEntity
import com.balica.ws.springws3.greeting.GreetingRepository
import com.balica.ws.springws3.greeting.GreetingService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.TestPropertySource

@DataJpaTest
class GreetingServiceTests @Autowired constructor(val entityManager: TestEntityManager, val greetingRepository: GreetingRepository) {
    @Test
    fun `List all greetings`() {
        val greeting = GreetingEntity("Hello tester")
        entityManager.persist(greeting)
        entityManager.flush()
        val pageReq = PageRequest.of(1, 1, Sort.Direction.fromString("ASC"), "text")
        val found = greetingRepository.findAll(pageReq)
        found.forEach { assertThat(it).isEqualTo(greeting) }
    }

    @Test
    fun `Find greeting by partial text`() {
        val greeting1 = GreetingEntity("Full text greeting")
        entityManager.persist(greeting1)
        val greeting2 = GreetingEntity("Short note greeting")
        entityManager.persist(greeting2)
        entityManager.flush()
        val found = greetingRepository.findGreetingByText("text")
        assertThat(found.count()).isEqualTo(1)
    }

    @Test
    fun `Insert new greeting`() {
        val greeting = GreetingEntity("Hello tester")
        val saved = greetingRepository.save(greeting)
        assertThat(greeting).isEqualTo(saved)
    }
}
