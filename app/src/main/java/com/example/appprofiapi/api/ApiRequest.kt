package com.example.appprofiapi.api

import com.example.appprofiapi.models.Catalog
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
    @GET("api/catalog")
    fun getCatalog(): Call<List<Catalog>>
}