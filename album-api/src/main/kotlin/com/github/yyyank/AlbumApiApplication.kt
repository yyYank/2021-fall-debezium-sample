package com.github.yyyank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlbumApiApplication

fun main(args: Array<String>) {
    runApplication<AlbumApiApplication>(*args)
}
