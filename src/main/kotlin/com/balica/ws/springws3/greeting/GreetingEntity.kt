package com.balica.ws.springws3.greeting

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
class GreetingEntity(
        var text: String,

        @Id @GeneratedValue
        var id: Long = 0
)
