package github.nixo.com.github.NetWork

import android.util.Base64
import github.nixo.com.github.Common.Model.AccountManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //拿到我们的request
        val original = chain.request()
        /*然后返回一个Response，使用proceed我们拿到一个response，  而proceed的参数是一个request，我们可以在参数中修改这个请求
         *然后再次发送，这也就是拦截器的作用
         *所以我们使用.apply{} 当我们的请求体的路径包含authorizations，说明我们这是含有Basic 鉴权模式的接口(详见githubAPI)
         * 然后我们Base64塞进去请求头
         *
         * 如果已经登录了，我们直接塞Token,通过SharedPreference取就可以
         * 如果没登录，还不是含有鉴权模式的，我们去掉请求头即可，然后重新构建并发送
         */
        return chain.proceed(original.newBuilder()
                .apply {
                    when{
                        original.url().pathSegments().contains("authorizations") ->{
                            val userCredentials = AccountManager.username + ":" + AccountManager.passwd
                            //使用Baseic作为鉴权模式请求数据，转为Base64之后，需要使用trim，不然有可能转出来里面含有空串
                            val auth = "Basic " + String(Base64.encode(userCredentials.toByteArray(), Base64.DEFAULT)).trim()
                            //添加到请求头里
                            header("Authorization", auth)
                        }
                        AccountManager.isLoggedIn() ->{
                            val auth = "Token " + AccountManager.token
                            header("Authorization", auth)
                        }else -> removeHeader("Authorization")
                    }
                }
                .build())
    }
}