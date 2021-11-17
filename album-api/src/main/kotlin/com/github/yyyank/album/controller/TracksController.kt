package com.github.yyyank.album.controller

import com.github.yyyank.album.service.TracksService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/albums/tracks")
class TracksController(
    val tracksService: TracksService
) {
    private val log = LoggerFactory.getLogger(TracksController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTracks(@RequestBody request: CreateTracksRequest): ResponseEntity<Map<String, String>> {
        log.info("トラックを作成しました")
        tracksService.createTrack(request)
        return ResponseEntity(mapOf("status" to HttpStatus.CREATED.name), HttpStatus.CREATED)
    }

    @PutMapping
    fun updateTracks(@RequestBody request: UpdateTracksRequest): ResponseEntity<Map<String, String>> {
        log.info("トラックを更新しました")
        tracksService.updateTrack(request)
        return ResponseEntity(mapOf("status" to HttpStatus.OK.name), HttpStatus.OK)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun fetchTracks(): HttpStatus {
        log.info("トラックを取得しました")
        return HttpStatus.OK
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteTracks(@RequestParam("id") id: Int): HttpStatus {
        log.info("トラックを削除しました")
        tracksService.deleteTrack(id)
        return HttpStatus.OK
    }
}

data class CreateTracksRequest(val id: Int, val name: String, val no: Int, val review: Int, val createdAt: LocalDateTime)
data class UpdateTracksRequest(val id: Int, val name: String, val no: Int, val review: Int, val createdAt: LocalDateTime)
