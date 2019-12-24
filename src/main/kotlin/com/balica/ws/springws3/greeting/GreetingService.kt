package com.balica.ws.springws3.greeting

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
public class GreetingService(private val greetingRepository: GreetingRepository) {
    fun findAll(pageRequest: PageRequest): Page<GreetingEntity> {
        return greetingRepository.findAll(pageRequest)
    }

    fun findById(id: Long): GreetingEntity {
        return greetingRepository.findById(id).orElse(null)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, """Greeting with ID:${id.toString()} not found""")
    }

    fun save(greetingEntity: GreetingEntity): GreetingEntity {
        return greetingRepository.save(greetingEntity)
    }
}
