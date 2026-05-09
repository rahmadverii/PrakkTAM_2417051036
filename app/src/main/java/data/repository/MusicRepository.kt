package com.example.moosic.data.repository

import com.example.moosic.data.remote.RetrofitInstance

class MusicRepository {

    suspend fun getMusic() = RetrofitInstance.api.getMusic()

}