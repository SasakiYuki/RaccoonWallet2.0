package wallet.raccoon.raccoonwallet.rest

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wallet.raccoon.raccoonwallet.util.SharedPreferenceUtils
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiManager @Inject constructor(private val preferenceUtils: SharedPreferenceUtils){

  fun builder(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(preferenceUtils.activeNode)
        .client(builderHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(builderHttpClient())
        .build()
  }

  fun builderXembook(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(API_NEM_BOOK_URL)
        .client(builderHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
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
    return Retrofit.Builder()
        .baseUrl(API_ZAIF_URL)
        .client(builderHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(builderHttpClient())
        .build()
  }

  companion object {
    private const val API_NEM_BOOK_URL = "https://s3-ap-northeast-1.amazonaws.com/xembook.net/"
    private const val API_ZAIF_URL = "https://api.zaif.jp/"
  }
}