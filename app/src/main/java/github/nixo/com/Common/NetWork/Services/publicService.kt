package github.nixo.com.Common.NetWork.Services

import github.nixo.com.MVP.Model.MainPublicEvent
import github.nixo.com.github.NetWork.retrofit
import retrofit2.Response
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import rx.Observable
import java.util.ArrayList

interface publicEventApi{


   @GET("events")
   fun getNewsList(
//            @Header("forceNetWork") forceNetWork: Boolean,
            @Query("page") page: Int
    ): Observable<GitHubPaging<MainPublicEvent>>
}
object EventServce : publicEventApi by retrofit.create(publicEventApi::class.java)