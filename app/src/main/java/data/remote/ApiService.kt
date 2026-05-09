package com.example.moosic.data.remote

import com.example.moosic.data.model.Music
import retrofit2.http.GET

interface ApiService {

    @GET("music")
    suspend fun getMusic(): List<Music>

}