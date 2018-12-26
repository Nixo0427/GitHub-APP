package github.nixo.com.Common.NetWork.Star

import github.nixo.com.Common.NetWork.Repository.Repository
import github.nixo.com.Common.NetWork.Repository.RepositoryApi
import github.nixo.com.github.NetWork.retrofit
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface StarInterface{

    @GET("/users/{username}/starred")
    fun listRepositoryBeginStar(@Path("username")username :String, @Query("page") page: Int = 1) : Observable<GitHubPaging<Repository>>


}
object StarService : StarInterface by retrofit.create(StarInterface::class.java)