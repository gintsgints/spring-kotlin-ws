package com.balica.ws.springws3.greeting

import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/api/greeting")
class GreetingController(private val service: GreetingService) {
    @GetMapping("/")
    fun greeting(
            @RequestParam("page") page: Int,
            @RequestParam("size") size: Int,
            @RequestParam("sortDir") sortDir: String,
            @RequestParam("sort") sort: String
    ): Page<GreetingEntity> {
        val pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort)
        return service.findAll(pageReq)
    }

    @GetMapping("/{id}")
    fun getGreeting(@PathVariable id: Long): GreetingEntity {
        return service.findById(id)
    }

    @PostMapping("/", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGreeting(@Valid @RequestBody greeting: GreetingEntity, result: BindingResult, model: Model): GreetingEntity {
        return service.save(greeting)
    }

    @PostMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateGreeting(@PathVariable id: Long, @Valid @RequestBody greeting: GreetingEntity, result: BindingResult, model: Model): GreetingEntity {
        return service.save(greeting)
    }
}
