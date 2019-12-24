package com.balica.ws.springws3.greeting

import org.springframework.data.jpa.repository.JpaRepository

interface GreetingRepository : JpaRepository<GreetingEntity, Long> {
}