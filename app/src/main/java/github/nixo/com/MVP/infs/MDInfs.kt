package github.nixo.com.MVP.infs

import github.nixo.com.MVP.Model.MDBaseInfo
import github.nixo.com.github.NetWork.retrofit
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import rx.Observable


interface DownLoadApi{

    @GET()
    fun allFollowing(@Url url:String): Observable<ResponseBody>

//   https://api.github.com/repos/Nixo0427/NixoBottomButton/readme?uniqueLoginId=Nixo0427

    @GET("/repos/{login}/{repoName}/readme?uniqueLoginId={login}")
    fun getMdTextUrl(@Path("login")login:String,@Path("repoName")repoName:String):Observable<MDBaseInfo>




}
object Downloadervice : DownLoadApi by retrofit.create(DownLoadApi::class.java)