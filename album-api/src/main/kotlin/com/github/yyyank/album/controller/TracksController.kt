package com.github.yyyank.album.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/albums/tracks")
class TracksController {
    private val log = LoggerFactory.getLogger(TracksController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTracks(): ResponseEntity<Map<String, String>> {
        log.info("トラックが作られました")
        return ResponseEntity(mapOf("status" to HttpStatus.CREATED.name), HttpStatus.CREATED)
    }

    @PutMapping
    fun updateTracks(): ResponseEntity<Map<String, String>> {
        log.info("トラックが更新されました")
        return ResponseEntity(mapOf("status" to HttpStatus.OK.name), HttpStatus.OK)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun fetchTracks(): HttpStatus {
        log.info("トラックが取得されました")
        return HttpStatus.OK
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteTracks(): HttpStatus {
        log.info("トラックが削除されました")
        return HttpStatus.OK
    }
}
