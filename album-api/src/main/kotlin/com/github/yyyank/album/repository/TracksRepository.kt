package com.github.yyyank.album.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class TracksRepository(val jdbcTemplate: JdbcTemplate) {
    fun createTrack(track: Track) {
        jdbcTemplate.update("""
           INSERT INTO `tracks` (`id`, `no`, `name`, `review`, `created_at`)
           VALUES (?, ?, ?, ?, ?)
        """.trimIndent(), track.id, track.no, track.name, track.review, track.createdAt)
    }

    fun updateTrack(track: Track) {
        jdbcTemplate.update("""
           UPDATE `tracks` SET no = ?, name = ?, review = ?, created_at = ?
           WHERE id = ?
        """.trimIndent(), track.no, track.review, track.review, track.createdAt, track.id)
    }

    fun deleteTrack(id: Int) {
        jdbcTemplate.update("""
           DELETE FROM `tracks`
           WHERE id = ?
        """.trimIndent(), id)
    }
}

data class Track(
    val id: Int,
    val no: Int,
    val name: String,
    val review: Int,
    val createdAt: LocalDateTime
)
