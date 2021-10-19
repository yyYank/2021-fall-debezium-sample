package com.github.yyyank.album.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/albums")
class AlbumsController {
    private val log = LoggerFactory.getLogger(AlbumsController::class.java)

    @PostMapping
    fun createAlbums(): ResponseEntity<Map<String, String>> {
        log.info("アルバムが作られました")
        return ResponseEntity(mapOf("status" to HttpStatus.CREATED.name), HttpStatus.CREATED)
    }

    @PutMapping
    fun updateAlbums(): ResponseEntity<Map<String, String>> {
        log.info("アルバムが更新されました")
        return ResponseEntity(mapOf("status" to HttpStatus.OK.name), HttpStatus.OK)
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
