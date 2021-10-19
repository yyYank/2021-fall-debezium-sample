package com.github.yyyank.album.worker.http

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime

@Component
class AlbumApiHttpClient(
    private val restTemplate: RestTemplate,
) {

    fun createAlbums(request: CreateAlbumsRequest): CreateAlbumsResponse {
        val headers = HttpHeaders().apply {
        }
        val response = try {
            restTemplate.exchange(
                "/albums",
                HttpMethod.POST,
                HttpEntity(request, headers),
                CreateAlbumsResponse::class.java,
            ).body
        } catch (ex: RestClientException) {
            throw ex
        }
        return response ?: error("invalid state")
    }

    fun updateAlbums(request: UpdateAlbumsRequest): UpdateAlbumsResponse {
        val headers = HttpHeaders().apply {
        }
        val response = try {
            restTemplate.exchange(
                "/albums",
                HttpMethod.PUT,
                HttpEntity(request, headers),
                UpdateAlbumsResponse::class.java,
            ).body
        } catch (ex: RestClientException) {
            throw ex
        }
        return response ?: error("invalid state")
    }

    fun deleteAlbums(request: DeleteAlbumsRequest): DeleteAlbumsResponse {
        val headers = HttpHeaders().apply {
        }
        try {
            restTemplate.exchange(
                "/albums",
                HttpMethod.DELETE,
                HttpEntity<Void>(headers),
                Void::class.java,
                request.id
            ).body
        } catch (ex: RestClientException) {
            throw ex
        }
        return DeleteAlbumsResponse("delted")
    }

    fun createTracks(request: CreateTracksRequest): CreateTracksResponse {
        val headers = HttpHeaders().apply {
        }
        val response = try {
            restTemplate.exchange(
                "/albums/tracks",
                HttpMethod.POST,
                HttpEntity(request, headers),
                CreateTracksResponse::class.java,
            ).body
        } catch (ex: RestClientException) {
            throw ex
        }
        return response ?: error("invalid state")
    }

    fun updateTracks(request: UpdateTracksRequest): UpdateTracksResponse {
        val headers = HttpHeaders().apply {
        }
        val response = try {
            restTemplate.exchange(
                "/albums/tracks",
                HttpMethod.PUT,
                HttpEntity(request, headers),
                UpdateTracksResponse::class.java,
            ).body
        } catch (ex: RestClientException) {
            throw ex
        }
        return response ?: error("invalid state")
    }

    fun deleteTracks(request: DeleteTracksRequest): DeleteTracksResponse {
        val headers = HttpHeaders().apply {
        }
        try {
            restTemplate.exchange(
                "/albums/tracks",
                HttpMethod.DELETE,
                HttpEntity<Void>(headers),
                Void::class.java,
                request.id
            ).body
        } catch (ex: RestClientException) {
            throw ex
        }
        return DeleteTracksResponse("deleted")
    }

}

data class CreateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class CreateAlbumsResponse(val status: String)
data class UpdateAlbumsRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class UpdateAlbumsResponse(val status: String)
data class DeleteAlbumsRequest(val id: Int)
data class DeleteAlbumsResponse(val status: String)
data class CreateTracksRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class CreateTracksResponse(val status: String)
data class UpdateTracksRequest(val id: Int, val name: String, val createdAt: LocalDateTime)
data class UpdateTracksResponse(val status: String)
data class DeleteTracksRequest(val id: Int)
data class DeleteTracksResponse(val status: String)
