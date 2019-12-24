package com.balica.ws.springws3.greeting

import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/greeting")
class GreetingController(private val repository: GreetingService) {
    @GetMapping("/")
    fun greeting() =
            repository.findTopBy()

    @PostMapping("/", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGreeting(@Valid @RequestBody greeting: GreetingEntity, result: BindingResult, model: Model): GreetingEntity {
        return repository.save(greeting)
    }
}
