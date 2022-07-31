package eng.ahmed.test

import retrofit2.Response
import retrofit2.http.GET

interface NamesApi {
    @GET("/10?format=json")
    suspend fun getNames(): Response<List<String>>
}

