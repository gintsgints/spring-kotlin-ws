package com.balica.ws.springws3.greeting

import javax.validation.constraints.NotBlank

data class Greeting(
        @get: NotBlank
        val text: String,

        val id: Long?
)