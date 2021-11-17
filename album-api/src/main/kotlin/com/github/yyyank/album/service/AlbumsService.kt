package com.github.yyyank.album.service

import com.github.yyyank.album.controller.CreateAlbumsRequest
import com.github.yyyank.album.controller.UpdateAlbumsRequest
import com.github.yyyank.album.repository.Album
import com.github.yyyank.album.repository.AlbumsRepository
import org.springframework.stereotype.Service

@Service
class AlbumsService(val albumsRepository: AlbumsRepository) {

    fun createAlbum(request: CreateAlbumsRequest) {
        albumsRepository.createAlbum(Album(id = request.id, name = request.name, createdAt = request.createdAt))
    }

    fun updateAlbum(request: UpdateAlbumsRequest) {
        albumsRepository.updateAlbum(Album(id = request.id, name = request.name, createdAt = request.createdAt))
    }

    fun deleteAlbum(id: Int) {
        albumsRepository.deleteAlbum(id)
    }
}
