package com.github.yyyank.album.controller

import com.github.yyyank.album.service.AlbumsService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/albums")
class AlbumsController(val albumsService: AlbumsService) {
    private val log = LoggerFactory.getLogger(AlbumsController::class.java)

    @PostMapping
    fun createAlbums(@RequestBody request: CreateAlbumsRequest): ResponseEntity<Map<String, String>> {
        albumsService.createAlbum(request)
        log.info("アルバムを作成しました")
        return ResponseEntity(mapOf("status" to HttpStatus.CREATED.name), HttpStatus.CREATED)
    }

    @PutMapping
    fun updateAlbums(@RequestBody request: UpdateAlbumsRequest): ResponseEntity<Map<String, String>> {
        albumsService.updateAlbum(request)
        log.info("アルバムが更新しました")
        return ResponseEntity(mapOf("status" to HttpStatus.OK.name), HttpStatus.OK)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun fetchAlbums(): HttpStatus {
        log.info("アルバムを取得しました")
        return HttpStatus.OK
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    fun deleteAlbums(@RequestParam("id") id: Int): HttpStatus {
        albumsService.deleteAlbum(id)
        log.info("アルバムを削除しました")
        return HttpStatus.OK
    }
}
data class CreateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class UpdateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
