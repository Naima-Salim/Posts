package mima.anitab.myposts

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create()) //converts json to kotlin and vice versa
        .build()

    fun <T> buildApiClient(apiInterface: Class<T>): T{ //build api makes requests
        return retrofit.create(apiInterface)
    }
}
