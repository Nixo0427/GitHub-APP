package github.nixo.com.github.NetWork

import github.nixo.com.github.Ext.AppContext
import github.nixo.com.github.Ext.ensureDir
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory2
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.github.com"

private val cacheFile by lazy{
    File(AppContext.cacheDir,"webServiceApi").apply { ensureDir() }
}

//用到的时候才回去创建，所以使用lazy懒加载模式
val retrofit by lazy {
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory2.createWithScheduler(rx.schedulers.Schedulers.io(),AndroidSchedulers.mainThread()))
            .client(OkHttpClient.Builder()
                    .connectTimeout(60,TimeUnit.SECONDS)
                    .readTimeout(60,TimeUnit.SECONDS)
                    .writeTimeout(60,TimeUnit.SECONDS)
                    .cache(Cache(cacheFile,1024*1024*1024)) // 设置请求可用缓存1G
                    .addInterceptor(AcceptInterceptor()) //Accept拦截器
                    .addInterceptor(AuthInterceptor())  //鉴权拦截器
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))// Log级别,Boby级别，貌似在info打印
                    .build()
            )
            .baseUrl(BASE_URL)
            .build()
}