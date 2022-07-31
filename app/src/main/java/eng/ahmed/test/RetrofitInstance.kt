package eng.ahmed.test

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api:NamesApi by lazy {
          Retrofit
            .Builder()
            .baseUrl("http://names.drycodes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NamesApi::class.java)

    }

}
