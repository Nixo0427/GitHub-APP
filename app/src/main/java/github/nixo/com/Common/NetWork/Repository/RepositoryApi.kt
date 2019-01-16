package github.nixo.com.Common.NetWork.Repository

import github.nixo.com.MVP.Model.ContentsRepository
import github.nixo.com.github.NetWork.retrofit
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface RepositoryApi{

    @GET("/users/{owner}/repos?type=all")
    fun listRepositoriesOfUser
            (@Path("owner") owner: String,
             @Query("page") page: Int = 1,
             @Query("per_page") per_page: Int = 20)
            : rx.Observable<GitHubPaging<Repository>>

    @GET("/search/repositories?order=desc&sort=updated")
    fun allRepositories
            (@Query("page") page: Int = 1,
             @Query("q") q: String,
             @Query("per_page") per_page: Int = 20)
            : rx.Observable<SearchRepositories>

//    https://api.github.com/repos/Nixo0427/NixoEditText/contents/?ref=master&uniqueLoginId=Nixo0427   代码预览
    @GET("/repos/{login}/{repo}/contents/{dir}")
    fun contentsRepositores
        (@Path("login") login :String,
         @Path("repo") repos: String,
         @Path("dir"  ,encoded = true) dir : String ,
         @Query("ref") branch :String,
         @Query("uniqueLoginId")uniqueLoginId:String)
        :Observable<MutableList<ContentsRepository>>

}
object RepositoryService : RepositoryApi by retrofit.create(RepositoryApi::class.java)