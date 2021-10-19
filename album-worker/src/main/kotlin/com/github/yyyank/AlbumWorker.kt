package com.github.yyyank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlbumWorker

fun main(args: Array<String>) {
    runApplication<AlbumWorker>(*args)
}
