package com.github.yyyank

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/albums")
class AlbumsController {
    private val log = LoggerFactory.getLogger(AlbumsController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAlbums(): HttpStatus {
        log.info("アルバムが作られました")
        return HttpStatus.CREATED
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateAlbums(): HttpStatus {
        log.info("アルバムが更新されました")
        return HttpStatus.OK
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun fetchAlbums(): HttpStatus {
        log.info("アルバムが取得されました")
        return HttpStatus.OK
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteAlbums(): HttpStatus {
        log.info("アルバムが削除されました")
        return HttpStatus.OK
    }
}
