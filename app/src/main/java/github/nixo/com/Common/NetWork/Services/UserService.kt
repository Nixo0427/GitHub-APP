package github.nixo.com.github.NetWork.Services

import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.NetWork.retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable
import java.util.*

interface UserApi{

    @GET("/user")
    fun getAuthenticatedUser():Observable<User>
}

object UserService : UserApi by retrofit.create(UserApi::class.java)