package com.github.yyyank.album.controller

import com.github.yyyank.album.repository.Album
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
        log.info("アルバムが作られました")
        albumsService.createAlbum(Album(id = request.id, name = request.name, createdAt = request.createdAt))
        return ResponseEntity(mapOf("status" to HttpStatus.CREATED.name), HttpStatus.CREATED)
    }

    @PutMapping
    fun updateAlbums(@RequestBody request: UpdateAlbumsRequest): ResponseEntity<Map<String, String>> {
        log.info("アルバムが更新されました")
        albumsService.updateAlbum(Album(id = request.id, name = request.name, createdAt = request.createdAt))
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
    fun deleteAlbums(@RequestParam("id") id: Int): HttpStatus {
        log.info("アルバムが削除されました")
        albumsService.deleteAlbum(id)
        return HttpStatus.OK
    }
}
data class CreateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class UpdateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
