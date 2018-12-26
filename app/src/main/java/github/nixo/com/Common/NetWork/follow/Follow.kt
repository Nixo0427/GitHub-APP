package github.nixo.com.Common.NetWork.follow

import github.nixo.com.MVP.Model.Following
import github.nixo.com.github.NetWork.retrofit
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

//https://api.github.com/users/Nixo0427/following


interface FollowApi{

    @GET("/users/{owner}/following")
    fun allFollowing(@Path("owner") userName:String):Observable<GitHubPaging<Following>>

    @DELETE("/user/following/{username}")
    fun unFollowing(@Path("username")username : String ):Observable<Any>

    @GET("users/{username}/followers")
    fun allFollower(@Path("username")username : String):Observable<GitHubPaging<Following>>


}
object FollowService : FollowApi by retrofit.create(FollowApi::class.java)