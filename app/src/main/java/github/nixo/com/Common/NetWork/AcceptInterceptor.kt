package github.nixo.com.github.NetWork

import android.util.Log
import github.nixo.com.github.utils.pref
import okhttp3.Interceptor
import okhttp3.Response
class AcceptInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return chain.proceed(original.newBuilder()
                .apply {
                    header("accept","application/vnd.github.v3.full+json,${original.header("accept") ?: ""}")
                    var accept by pref("application/vnd.github.v3.full+json,${original.header("accept") ?: ""}")
                    Log.e("Nixo--获取User的Header","application/vnd.github.v3.full+json,${original.header("accept") ?: ""}")
                }.build())
    }
}