package com.gabrielleeg1.looqbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class LooqboxApplication

@RestController
class HelloResource {
    @GetMapping("/hello")
    suspend fun hello() = "Hello, World!"
}

fun main(args: Array<String>) {
    runApplication<LooqboxApplication>(*args)
}
