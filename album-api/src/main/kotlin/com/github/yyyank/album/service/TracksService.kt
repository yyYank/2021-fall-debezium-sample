package com.github.yyyank.album.service

import com.github.yyyank.album.repository.Album
import com.github.yyyank.album.repository.AlbumsRepository
import com.github.yyyank.album.repository.Track
import com.github.yyyank.album.repository.TracksRepository
import org.springframework.stereotype.Service

@Service
class TracksService(val tracksRepository: TracksRepository) {

    fun createTrack(track: Track) {
        tracksRepository.createTrack(track)
    }

    fun updateTrack(track: Track) {
        tracksRepository.updateTrack(track)
    }

    fun deleteTrack(id: Int) {
        tracksRepository.deleteTrack(id)
    }
}
