package com.balica.ws.springws3.greeting

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GreetingRepository : JpaRepository<GreetingEntity, Long> {
    @Query("select g from GreetingEntity g where g.text like %:text% ")
    fun findGreetingByText(@Param("text") text: String): Iterable<GreetingEntity>
}
