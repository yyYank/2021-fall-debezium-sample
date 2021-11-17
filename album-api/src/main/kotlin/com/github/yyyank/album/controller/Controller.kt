package com.github.yyyank.album.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    private val log = LoggerFactory.getLogger(Controller::class.java)

    @GetMapping("/hello")
    fun hello(): String {
        return "hello album-api"
    }
}
