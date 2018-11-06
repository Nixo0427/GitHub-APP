package github.nixo.com.Common.NetWork.Repository

import github.nixo.com.github.NetWork.retrofit
import io.reactivex.Observable
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryApi{

    @GET("/user/{owner}/repos?type=all")
    fun listRepositoriesOfUser(@Path("owner") owner: String ,@Query("page")page : Int = 1 , @Query("per_page")perpage:Int = 20): Observable<GitHubPaging<Repository>>

}
object RepositoryService : RepositoryApi by retrofit.create(RepositoryApi::class.java)