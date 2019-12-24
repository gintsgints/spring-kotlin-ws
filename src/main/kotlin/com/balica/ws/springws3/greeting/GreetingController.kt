package com.balica.ws.springws3.greeting

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController(private val repository: GreetingService) {
    @GetMapping("/")
    fun greeting() =
            repository.findTopBy()
}