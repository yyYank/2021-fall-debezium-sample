package com.github.yyyank.album.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AlbumsRepository(val jdbcTemplate: JdbcTemplate) {
    fun createAlbum(album: Album) {
        jdbcTemplate.update("""
           INSERT INTO `albums` (`id`, `name`, `created_at`)
           VALUES (?, ?, ?)
        """.trimIndent(), album.id, album.name, album.createdAt)
    }

    fun updateAlbum(album: Album) {
        jdbcTemplate.update("""
           UPDATE `albums` SET name = ?, created_at = ?
           WHERE id = ?
        """.trimIndent(), album.name, album.createdAt, album.id)
    }

    fun deleteAlbum(id: Int) {
        jdbcTemplate.update("""
           DELETE FROM `albums`
           WHERE id = ?
        """.trimIndent(), id)
    }
}

data class Album(
    val id: Int,
    val name: String,
    val createdAt: LocalDateTime
)
