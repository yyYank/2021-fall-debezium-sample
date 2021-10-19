package com.github.yyyank.album.worker.http

import java.time.LocalDateTime

data class CreateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class UpdateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class DeleteAlbumsRequest(val id: Int)
data class CreateTracksRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class UpdateTracksRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class DeleteTracksRequest(val id: Int)
