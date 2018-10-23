package github.nixo.com.utils

import android.util.Log
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.adapter.rxjava.HttpException
import java.net.ConnectException

class ExcaptionUtil  {

    /**
     * 约定异常
     */
    internal object ERROR {
        /**
         * 未知错误
         */
        val UNKNOWN = 1000
        /**
         * 解析错误
         */
        val PARSE_ERROR = 1001
        /**
         * 网络错误
         */
        val NETWORD_ERROR = 1002
        /**
         * 协议出错
         */
        val HTTP_ERROR = 1003

        /**
         * 证书出错
         */
        val SSL_ERROR = 1005
    }

    companion object {
        private val UNAUTHORIZED = 401
        private val FORBIDDEN = 403
        private val NOT_FOUND = 404
        private val REQUEST_TIMEOUT = 408
        private val INTERNAL_SERVER_ERROR = 500
        private val BAD_GATEWAY = 502
        private val SERVICE_UNAVAILABLE = 503
        private val GATEWAY_TIMEOUT = 504

        fun handleException(e: Throwable): ResponeThrowable {
            val ex: ResponeThrowable
            Log.i("tag", "e.toString = " + e.toString())
            if (e is HttpException) {
                val httpException = e as HttpException
                ex = ResponeThrowable(e, ERROR.HTTP_ERROR)
                when (httpException.code()) {
                    UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE ->
                        //ex.code = httpException.code();
                        ex.message = "网络错误"
                    else -> ex.message = "网络错误"
                }
                return ex
            } else if (e is ServerException) {
                val resultException = e as ServerException
                ex = ResponeThrowable(resultException, resultException.code)
                ex.message = resultException.message
                return ex
            } else if (e is JsonParseException || e is JSONException) {
                ex = ResponeThrowable(e, ERROR.PARSE_ERROR)
                ex.message = "解析错误"
                return ex
            } else if (e is ConnectException) {
                ex = ResponeThrowable(e, ERROR.NETWORD_ERROR)
                ex.message = "连接失败"
                return ex
            } else if (e is javax.net.ssl.SSLHandshakeException) {
                ex = ResponeThrowable(e, ERROR.SSL_ERROR)
                ex.message = "证书验证失败"
                return ex
            } else {
                ex = ResponeThrowable(e, ERROR.UNKNOWN)
                ex.message = "未知错误"
                return ex
            }/*|| e instanceof ParseException*/
        }
    }

    class ResponeThrowable(throwable: Throwable, var code: Int) : Exception(throwable) {
        override var message: String? = null
    }

    /**
     * ServerException发生后，将自动转换为ResponeThrowable返回
     */
    internal inner class ServerException : RuntimeException() {
        var code: Int = 0
        override var message: String? = null
    }
}