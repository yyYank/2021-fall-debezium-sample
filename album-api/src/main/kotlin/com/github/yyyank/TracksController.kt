package com.github.yyyank

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/albums/tracks")
class TracksController {
    private val log = LoggerFactory.getLogger(TracksController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTracks(): HttpStatus {
        log.info("トラックが作られました")
        return HttpStatus.CREATED
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateTracks(): HttpStatus {
        log.info("トラックが更新されました")
        return HttpStatus.OK
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
