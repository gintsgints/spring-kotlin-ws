package com.balica.ws.springws3.greeting

import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/greeting")
class GreetingController(private val repository: GreetingService) {
    @GetMapping("/")
    fun greeting() =
            repository.findTopBy()

    @PostMapping("/")
    fun createGreeting(@Valid greeting: GreetingEntity, result: BindingResult, model: Model): GreetingEntity {
        return repository.save(greeting)
    }
}
