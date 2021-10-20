package com.github.yyyank.album.service

import CreateTracksRequest
import UpdateTracksRequest
import com.github.yyyank.album.repository.Album
import com.github.yyyank.album.repository.AlbumsRepository
import com.github.yyyank.album.repository.Track
import com.github.yyyank.album.repository.TracksRepository
import org.springframework.stereotype.Service

@Service
class TracksService(val tracksRepository: TracksRepository) {

    fun createTrack(request: CreateTracksRequest) {
        tracksRepository.createTrack(Track(
            id = request.id,
            no = request.no,
            name = request.name,
            review = request.review,
            createdAt = request.createdAt
        ))
    }

    fun updateTrack(request: UpdateTracksRequest) {
        tracksRepository.updateTrack(Track(
            id = request.id,
            no = request.no,
            name = request.name,
            review = request.review,
            createdAt = request.createdAt
        ))
    }

    fun deleteTrack(id: Int) {
        tracksRepository.deleteTrack(id)
    }
}
