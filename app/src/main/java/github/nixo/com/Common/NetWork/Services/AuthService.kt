package github.nixo.com.github.NetWork.Services

import github.nixo.com.github.NetWork.retrofit
import github.nixo.com.github.Setting.Configs
import github.nixo.com.github.NetWork.entitles.AuthorizationReq
import github.nixo.com.github.NetWork.entitles.AuthorizationRsp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path
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
}

object AuthService :AuthApi by retrofit.create(AuthApi::class.java)