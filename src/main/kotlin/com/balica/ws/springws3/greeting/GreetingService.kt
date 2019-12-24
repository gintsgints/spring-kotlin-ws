package com.balica.ws.springws3.greeting

import org.springframework.data.repository.CrudRepository

interface GreetingService : CrudRepository<GreetingEntity, Long> {
    fun findTopBy(): Iterable<GreetingEntity>
}
