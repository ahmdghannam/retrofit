package eng.ahmed.test

import retrofit2.Response
import retrofit2.http.GET

interface NamesApi {
    @GET("/random/random")
    suspend fun getNames(): Response<List<Person>>
}