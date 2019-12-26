package com.balica.ws.springws3.greeting

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/greeting")
class GreetingController(private val service: GreetingService) {
    @GetMapping("/")
    fun greeting(
            @RequestParam("page", required = false, defaultValue = "0") page: Int,
            @RequestParam("size", required = false, defaultValue = "20") size: Int,
            @RequestParam("sortDir", required = false, defaultValue = "ASC") sortDir: String,
            @RequestParam("sort", required = false) sort: String?
    ): Page<Greeting> {
        val pageReq = if (sort === null) {
            PageRequest.of(page, size)
        } else {
            PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort)
        }
        return service.findAll(pageReq).map { it.toDto() }
    }

    @GetMapping("/{id}")
    fun getGreeting(@PathVariable id: Long): Greeting {
        return service.findById(id).toDto()
    }

    @PostMapping("/", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGreeting(@Valid @RequestBody greeting: Greeting, result: BindingResult, model: Model): Greeting {
        return service.save(greeting.toEntity()).toDto()
    }

    @PostMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateGreeting(@PathVariable id: Long, @Valid @RequestBody greeting: Greeting, result: BindingResult, model: Model): Greeting {
        return service.save(greeting.toEntity()).toDto()
    }

    fun GreetingEntity.toDto() = Greeting(text, id)
    fun Greeting.toEntity() = GreetingEntity(text, id ?: 0)
}
