package com.balica.ws.springws3.greeting

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/api/greeting")
class GreetingController(private val repository: GreetingService) {
    @GetMapping("/")
    fun greeting() =
            repository.findTopBy()

    @GetMapping("/{id}")
    fun getGreeting(@PathVariable id: Long): GreetingEntity {
        return repository.findById(id).orElse(null) ?:
            throw ResponseStatusException(HttpStatus.NOT_FOUND, """Greeting with ID:${id.toString()} not found""")
    }

    @PostMapping("/", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGreeting(@Valid @RequestBody greeting: GreetingEntity, result: BindingResult, model: Model): GreetingEntity {
        return repository.save(greeting)
    }
}
