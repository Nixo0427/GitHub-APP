package github.nixo.com.MVP.Model

import github.nixo.com.github.anno.PoKo
import java.util.*

//
//{
//    "id": "8801156567",
//    "type": "PushEvent",
//    "actor": {
//    "id": 45460391,
//    "login": "somasundaram1996",
//    "display_login": "somasundaram1996",
//    "gravatar_id": "",
//    "url": "https://api.github.com/users/somasundaram1996",
//    "avatar_url": "https://avatars.githubusercontent.com/u/45460391?"
//},
//    "repo": {
//    "id": 159677794,
//    "name": "somasundaram1996/sornambigaiJewellers",
//    "url": "https://api.github.com/repos/somasundaram1996/sornambigaiJewellers"
//},
//    "payload": {
//    "push_id": 3162962815,
//    "size": 3,
//    "distinct_size": 1,
//    "ref": "refs/heads/develop",
//    "head": "4c373dcab65dcebc0a22c4192a6c7a64eacf7232",
//    "before": "bbc847ce40c19d1f3c44b6e96037161a1a0c0777",
//    "commits": [
//    {
//        "sha": "160105e35080d2dfd72d23d25d449a26f9ceb7ba",
//        "author": {
//        "email": "somavk18@gmail.com",
//        "name": "somasundaram1996"
//    },
//        "message": "first-commit",
//        "distinct": false,
//        "url": "https://api.github.com/repos/somasundaram1996/sornambigaiJewellers/commits/160105e35080d2dfd72d23d25d449a26f9ceb7ba"
//    },
//    {
//        "sha": "d752b855084dab1acbc5a9de912098d29676d08b",
//        "author": {
//        "email": "somavk18@gmail.com",
//        "name": "somasundaram1996"
//    },
//        "message": "refs #bakcend setup commit",
//        "distinct": false,
//        "url": "https://api.github.com/repos/somasundaram1996/sornambigaiJewellers/commits/d752b855084dab1acbc5a9de912098d29676d08b"
//    },
//    {
//        "sha": "4c373dcab65dcebc0a22c4192a6c7a64eacf7232",
//        "author": {
//        "email": "45460391+somasundaram1996@users.noreply.github.com",
//        "name": "Somasundaram Muthusamy"
//    },
//        "message": "Merge pull request #2 from somasundaram1996/checkbranch\n\nSetup",
//        "distinct": true,
//        "url": "https://api.github.com/repos/somasundaram1996/sornambigaiJewellers/commits/4c373dcab65dcebc0a22c4192a6c7a64eacf7232"
//    }
//    ]
//},
//    "public": true,
//    "created_at": "2018-12-25T08:31:32Z"
//},

@PoKo
data class Event(
        var id : String,
        var type : String,
        var actor : actor,
        var repo : repo,
        var payload : payload,
        var public : Boolean,
        var created_at  : String
)


//    "actor": {
//    "id": 45460391,
//    "login": "somasundaram1996",
//    "display_login": "somasundaram1996",
//    "gravatar_id": "",
//    "url": "https://api.github.com/users/somasundaram1996",
//    "avatar_url": "https://avatars.githubusercontent.com/u/45460391?"
//},
@PoKo
data class actor(
        var id : Number,
        var login : String,
        var display_login :String ,
        var gravatar_id : String ,
        var url : String ,
        var avatar_url : String
)

//    "repo": {
//    "id": 159677794,
//    "name": "somasundaram1996/sornambigaiJewellers",
//    "url": "https://api.github.com/repos/somasundaram1996/sornambigaiJewellers"
//},

@PoKo
data class repo(
        var id : Number,
        var name : String,
        var url : String
)

//    "payload": {
//    "push_id": 3162962815,
//    "size": 3,
//    "distinct_size": 1,
//    "ref": "refs/heads/develop",
//    "head": "4c373dcab65dcebc0a22c4192a6c7a64eacf7232",
//    "before": "bbc847ce40c19d1f3c44b6e96037161a1a0c0777",
//    "commits": [
//    {
//        "sha": "160105e35080d2dfd72d23d25d449a26f9ceb7ba",
//        "author": {
//        "email": "somavk18@gmail.com",
//        "name": "somasundaram1996"
//    },
//        "message": "first-commit",
//        "distinct": false,
//        "url": "https://api.github.com/repos/somasundaram1996/sornambigaiJewellers/commits/160105e35080d2dfd72d23d25d449a26f9ceb7ba"
//    },
//    {
//        "sha": "d752b855084dab1acbc5a9de912098d29676d08b",
//        "author": {
//        "email": "somavk18@gmail.com",
//        "name": "somasundaram1996"
//    },
//        "message": "refs #bakcend setup commit",
//        "distinct": false,
//        "url": "https://api.github.com/repos/somasundaram1996/sornambigaiJewellers/commits/d752b855084dab1acbc5a9de912098d29676d08b"
//    },
//    {
//        "sha": "4c373dcab65dcebc0a22c4192a6c7a64eacf7232",
//        "author": {
//        "email": "45460391+somasundaram1996@users.noreply.github.com",
//        "name": "Somasundaram Muthusamy"
//    },
//        "message": "Merge pull request #2 from somasundaram1996/checkbranch\n\nSetup",
//        "distinct": true,
//        "url": "https://api.github.com/repos/somasundaram1996/sornambigaiJewellers/commits/4c373dcab65dcebc0a22c4192a6c7a64eacf7232"
//    }

data class payload(
        var push_id : Number,
        var size : Int,
        var distince_size : Int,
        var ref : String ,
        var head : String ,
        var before : String ,
        var commits : commits
)


//    "commits": [
//    {
//        "sha": "160105e35080d2dfd72d23d25d449a26f9ceb7ba",
//        "author": {
//        "email": "somavk18@gmail.com",
//        "name": "somasundaram1996"
//    },
data class commits(
    var sha : String ,
    var author : author,
    var message :String ,
    var distinct : Boolean,
    var url : String
)
data class author(
        var email : String ,
        var name : String
)