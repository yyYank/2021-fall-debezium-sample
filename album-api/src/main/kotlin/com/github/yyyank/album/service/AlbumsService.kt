package com.github.yyyank.album.service

import com.github.yyyank.album.repository.Album
import com.github.yyyank.album.repository.AlbumsRepository
import org.springframework.stereotype.Service

@Service
class AlbumsService(val albumsRepository: AlbumsRepository) {

    fun createAlbum(album: Album) {
        albumsRepository.createAlbum(album)
    }

    fun updateAlbum(album: Album) {
        albumsRepository.updateAlbum(album)
    }

    fun deleteAlbum(id: Int) {
        albumsRepository.deleteAlbum(id)
    }
}
