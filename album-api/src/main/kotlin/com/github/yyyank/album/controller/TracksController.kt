package com.github.yyyank.album.controller

import com.github.yyyank.album.repository.Track
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
        log.info("トラックが作られました")
        tracksService.createTrack(Track(
            id = request.id,
            no = request.no,
            name = request.name,
            review = request.review,
            createdAt = request.createdAt
        ))
        return ResponseEntity(mapOf("status" to HttpStatus.CREATED.name), HttpStatus.CREATED)
    }

    @PutMapping
    fun updateTracks(@RequestBody request: UpdateTracksRequest): ResponseEntity<Map<String, String>> {
        log.info("トラックが更新されました")
        tracksService.updateTrack(Track(
            id = request.id,
            no = request.no,
            name = request.name,
            review = request.review,
            createdAt = request.createdAt
        ))
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
    fun deleteTracks(@RequestParam("id") id: Int): HttpStatus {
        log.info("トラックが削除されました")
        tracksService.deleteTrack(id)
        return HttpStatus.OK
    }
}

data class CreateTracksRequest(val id: Int, val name: String, val no: Int, val review: Int, val createdAt: LocalDateTime)
data class UpdateTracksRequest(val id: Int, val name: String, val no: Int, val review: Int, val createdAt: LocalDateTime)
