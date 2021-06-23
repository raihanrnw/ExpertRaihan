package com.example.raihanclubapps.core.data.remote.network

import com.example.raihanclubapps.core.data.remote.response.ListClubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("lookup_all_teams.php")
    suspend fun getClubList(@Query("id") listId : String
    ) : ListClubResponse
}