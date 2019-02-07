package wallet.raccoon.raccoonwallet.rest

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

class HttpResponseInterceptor:Interceptor {
  companion object {
    private val sessionExpired:Int = 401
  }

  override fun intercept(chain: Chain): Response {
    if (chain == null) throw IOException("chain may not be null")

    val response = chain.proceed(chain.request())

    return response
  }

}