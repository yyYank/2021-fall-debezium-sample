package com.github.yyyank.album.worker.http

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

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

    fun deleteAlbums(id: Int): DeleteAlbumsResponse {
        val headers = HttpHeaders().apply {
        }
        try {
            restTemplate.exchange(
                "/albums?id={id}",
                HttpMethod.DELETE,
                HttpEntity<Void>(headers),
                Void::class.java,
                id
            ).body
        } catch (ex: RestClientException) {
            throw ex
        }
        return DeleteAlbumsResponse("deleted")
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

    fun deleteTracks(id: Int): DeleteTracksResponse {
        val headers = HttpHeaders().apply {
        }
        try {
            restTemplate.exchange(
                "/albums/tracks?id={id}",
                HttpMethod.DELETE,
                HttpEntity<Void>(headers),
                Void::class.java,
                id
            ).body
        } catch (ex: RestClientException) {
            throw ex
        }
        return DeleteTracksResponse("deleted")
    }

}


