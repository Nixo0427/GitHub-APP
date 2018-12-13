package github.nixo.com.Common.NetWork.follow

import github.nixo.com.MVP.Model.Following
import github.nixo.com.github.NetWork.retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

//https://api.github.com/users/Nixo0427/following


interface FollowApi{

    @GET("/users/{owner}/following")
    fun allFollowing(@Path("owner") userName:String):Observable<List<Following>>

}
object FollowService : FollowApi by retrofit.create(FollowApi::class.java)