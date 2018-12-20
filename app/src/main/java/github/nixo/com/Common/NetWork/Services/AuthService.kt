package github.nixo.com.github.NetWork.Services

import github.nixo.com.github.Common.Model.SerchUser
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.NetWork.retrofit
import github.nixo.com.github.Setting.Configs
import github.nixo.com.github.NetWork.entitles.AuthorizationReq
import github.nixo.com.github.NetWork.entitles.AuthorizationRsp
import retrofit2.Response
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.*
import rx.Observable

interface AuthApi{

    /**
     * 创建我们的Github Token -> Authorizations
     */
    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(@Body req: AuthorizationReq, @Path("fingerPrint")fingerPrint:String = Configs.Account.fingerPrint)
            :Observable<AuthorizationRsp>

    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path ("id") id : Int ):Observable<Response<Any>>

    //搜索用户信息
    @GET("/search/users")
    fun serchUser(@Query ("q") login : String ):Observable<SerchUser>

}

object AuthService :AuthApi by retrofit.create(AuthApi::class.java)