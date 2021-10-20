package com.github.yyyank.album.worker.http

import java.time.LocalDateTime

data class CreateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class UpdateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class CreateTracksRequest(val id: Int, val name: String, val no: Int, val review: Int, val createdAt: LocalDateTime)
data class UpdateTracksRequest(val id: Int, val name: String, val no: Int, val review: Int, val createdAt: LocalDateTime)
