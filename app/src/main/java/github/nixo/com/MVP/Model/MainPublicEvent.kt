package github.nixo.com.MVP.Model

import com.google.gson.annotations.SerializedName

class MainPublicEvent {

    /**
     * id : 8803100631
     * type : WatchEvent
     * actor : {"id":15187791,"login":"wudaoluo","display_login":"wudaoluo","gravatar_id":"","url":"https://api.github.com/users/wudaoluo","avatar_url":"https://avatars.githubusercontent.com/u/15187791?"}
     * repo : {"id":20904437,"name":"gin-gonic/gin","url":"https://api.github.com/repos/gin-gonic/gin"}
     * payload : {"action":"started"}
     * public : true
     * created_at : 2018-12-26T06:02:42Z
     * org : {"id":7894478,"login":"gin-gonic","gravatar_id":"","url":"https://api.github.com/orgs/gin-gonic","avatar_url":"https://avatars.githubusercontent.com/u/7894478?"}
     */

    var id: String? = null
    var type: String? = null
    var actor: ActorBean? = null
    var repo: RepoBean? = null
    var payload: PayloadBean? = null
    @SerializedName("public")
    var isPublicX: Boolean = false
    var created_at: String? = null
    var org: OrgBean? = null
    var body : String? = null

    class ActorBean {
        /**
         * id : 15187791
         * login : wudaoluo
         * display_login : wudaoluo
         * gravatar_id :
         * url : https://api.github.com/users/wudaoluo
         * avatar_url : https://avatars.githubusercontent.com/u/15187791?
         */

        var id: Int = 0
        var login: String? = null
        var display_login: String? = null
        var gravatar_id: String? = null
        var url: String? = null
        var avatar_url: String? = null
    }

    class RepoBean {
        /**
         * id : 20904437
         * name : gin-gonic/gin
         * url : https://api.github.com/repos/gin-gonic/gin
         */

        var id: Int = 0
        var name: String? = null
        var url: String? = null
    }


        class PayloadBean{

            /**
             * push_id : 3164108950
             * size : 1
             * distinct_size : 1
             * ref : refs/heads/residential-ipauth
             * head : df5c340cac26d92b01db0f7637f10ebdfb52f2a0
             * before : 14c7cdd23d073f1f17b92e707e6feffd7199b191
             * commits : [{"sha":"df5c340cac26d92b01db0f7637f10ebdfb52f2a0","author":{"email":"42248628+navarrojandg@users.noreply.github.com","name":"navarrojandg"},"message":"Update squid.conf","distinct":true,"url":"https://api.github.com/repos/navarrojandg/squid_proxy/commits/df5c340cac26d92b01db0f7637f10ebdfb52f2a0"}]
             */

            var push_id: Long = 0
            var size: Int = 0
            var distinct_size: Int = 0
            var ref: String? = null
            var head: String? = null
            var before: String? = null
            var commits: List<CommitsBean>? = null
            val body: String? = null

            class CommitsBean {
                /**
                 * sha : df5c340cac26d92b01db0f7637f10ebdfb52f2a0
                 * author : {"email":"42248628+navarrojandg@users.noreply.github.com","name":"navarrojandg"}
                 * message : Update squid.conf
                 * distinct : true
                 * url : https://api.github.com/repos/navarrojandg/squid_proxy/commits/df5c340cac26d92b01db0f7637f10ebdfb52f2a0
                 */

                var sha: String? = null
                var author: AuthorBean? = null
                var message: String? = null
                var isDistinct: Boolean = false
                var url: String? = null

                class AuthorBean {
                    /**
                     * email : 42248628+navarrojandg@users.noreply.github.com
                     * name : navarrojandg
                     */

                    var email: String? = null
                    var name: String? = null
                }
            }
        }


    class OrgBean {
        /**
         * id : 7894478
         * login : gin-gonic
         * gravatar_id :
         * url : https://api.github.com/orgs/gin-gonic
         * avatar_url : https://avatars.githubusercontent.com/u/7894478?
         */

        var id: Int = 0
        var login: String? = null
        var gravatar_id: String? = null
        var url: String? = null
        var avatar_url: String? = null
    }
}
