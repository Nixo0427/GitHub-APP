package github.nixo.com.MVP.Model

import github.nixo.com.github.anno.PoKo

/*
 {
        "name": "Demo",
        "path": "Demo",
        "sha": "1d85c24c25db91e0aba40ea77c6c802abb8c6368",
        "size": 0,
        "url": "https://api.github.com/repos/Nixo0427/NixoEditText/contents/Demo?ref=master",
        "html_url": "https://github.com/Nixo0427/NixoEditText/tree/master/Demo",
        "git_url": "https://api.github.com/repos/Nixo0427/NixoEditText/git/trees/1d85c24c25db91e0aba40ea77c6c802abb8c6368",
        "download_url": null,
        "type": "dir",
        "_links": {
            "self": "https://api.github.com/repos/Nixo0427/NixoEditText/contents/Demo?ref=master",
            "git": "https://api.github.com/repos/Nixo0427/NixoEditText/git/trees/1d85c24c25db91e0aba40ea77c6c802abb8c6368",
            "html": "https://github.com/Nixo0427/NixoEditText/tree/master/Demo"
        }
    }
 */

@PoKo
data class ContentsRepository(
    var name : String ,
    var path : String ,
    var sha : String ,
    var size : Int ,
    var url :String ,
    var html_url : String ,
    var git_url : String ,
    var download_url:String,
    var type : String ,
    var _links: _links
)
