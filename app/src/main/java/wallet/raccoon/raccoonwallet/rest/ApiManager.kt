package wallet.raccoon.raccoonwallet.rest

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {
  const val API_NEM_BOOK_URL = "https://s3-ap-northeast-1.amazonaws.com/xembook.net/"
  const val API_ZAIF_URL = "https://api.zaif.jp/"

  fun builder(): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .client(builderHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(builderHttpClient())
        .build()
    return retrofit
  }

  fun builderXembook(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(API_NEM_BOOK_URL)
        .client(builderHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(builderHttpClient())
        .build()
  }

  private fun builderHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
    client.connectTimeout(30, TimeUnit.SECONDS)
    client.readTimeout(30, TimeUnit.SECONDS)
    client.writeTimeout(30, TimeUnit.SECONDS)
    client.addInterceptor(logging)
    client.addInterceptor { chain ->
      val original = chain.request()

      val request = original.newBuilder()
          .header("Accept", "application/json")
          .method(original.method(), original.body())
          .build()

      val response = chain.proceed(request)

      response
    }

    return client.build()
  }

  fun builderZaif(): Retrofit {
    val url: String = API_ZAIF_URL
    return Retrofit.Builder()
        .baseUrl(url)
        .client(builderHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(builderHttpClient())
        .build()
  }

    //todo nodeを反映させる
  fun getBaseUrl() = "http://62.75.251.134:7890"
}