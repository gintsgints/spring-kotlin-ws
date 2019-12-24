package com.balica.ws.springws3.greeting

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class GreetingEntity (
  val text: String,
  @Id @GeneratedValue var id: Long? = null
)
