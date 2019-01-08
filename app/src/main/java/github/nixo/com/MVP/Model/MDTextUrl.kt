package github.nixo.com.MVP.Model

import github.nixo.com.github.anno.PoKo
//
//
//{
//    "name": "README.md",
//    "path": "README.md",
//    "sha": "ff98b3049c10ea6b9fd178431f80783db56578b5",
//    "size": 1682,
//    "url": "https://api.github.com/repos/Nixo0427/NixoBottomButton/contents/README.md?ref=master",
//    "html_url": "https://github.com/Nixo0427/NixoBottomButton/blob/master/README.md",
//    "git_url": "https://api.github.com/repos/Nixo0427/NixoBottomButton/git/blobs/ff98b3049c10ea6b9fd178431f80783db56578b5",
//    "download_url": "https://raw.githubusercontent.com/Nixo0427/NixoBottomButton/master/README.md",
//    "type": "file",
//    "content": "IyBOaXhvQm90dG9tQnV0dG9uKOS7v+mXsumxvOW6lemDqOaRgeaJrSkKIyMg\n5LuT5bqT5Lit55qETml4b0JvdHRvbUJ1dHRvbuWwseaYr++8jOS9v+eUqOea\nhOaXtuWAmeivt+WwhmJhY2tncm91bmTorr7nva7kuLowMDAwMDAwMO+8iOWF\nqOmAj+aYju+8ieWQpuWImeS8muWHuueOsOWPmOS4uuefqeW9oueahOaDheWG\nte+8jOWImua3seWFpeWtpuS6huWHoOWkqeiHquWumuS5iVZpZXfvvIznqoHl\nj5HlpYfmg7PlgZrnmoQKIyMg5Yqg5Y+35peL6L2s54m55pWI55uu5YmN6L+Y\n5pyJYnVn77yM5omA5Lul5pqC5pe25rKh5pyJ54m55pWI5ZWm4pmqKF7iiIde\nKikgICDkvb/nlKjor7TmmI7op4HmnIDkuIvmlrnjgIIKCiMjIyDmkYHmia3m\nlYjmnpzlm74KIVvmkYHmia3mlYjmnpxdKGh0dHBzOi8vcHV1LnNoL0FOWGxk\nLzViZWM1YTRhNmUucG5nKQojIyMg5oC75L2T5pWI5p6c5Zu+CiFb5oC75L2T\n5pWI5p6c5Zu+XShodHRwczovL3B1dS5zaC9BTlhsZy9iYjNmMjg2YzBlLmpw\nZykKCgoKCgojIOS9v+eUqOivtOaYju+8jAogICAK5Zug5Li65pys5Lq65oqA\n5pyv5LiN5L2z77yM5omA5Lul5pyJ5Lqb5Zyw5pa56K6+6K6h55qE5LiN5piv\n6YKj5LmI6KeE6IyD77yM6L+Y6K+36KeB6LCF44CCICAKCiPlnKhKYXZh5paH\n5Lu25LitICAKTml4b0JvdHRvbUJ1dHRvbuWvueWkluiuvue9ruS6huS4pOS4\nquaWueazle+8jHNldC9nZXRJY29uKCnmlrnms5Ug5Y+v5Lul5re75Yqg6Ieq\n5bex55qE5pGB5omt5Lit5b+D5Zu+5qGIICAKKOaciWJ1Z++8jOWSseS4jeWP\nr+eUqCkgc2V0UGx1c0FuaW1hdG9yKCnmlrnms5XvvIzlj6/ku6Xoh6rlt7Fz\nZXTov5vljrvmg7PopoHnmoTkuK3lv4Plm77moYjliqjnlLsKCgojIOWcqFhN\nTOS4rQphbmRyb2lkOnRleHQ9IiIgKOiuvue9ruaRgeaJreW6lemDqOaWh+ac\nrCkgIApOaXhvOnRleHRTaXplPSIzMCIgKOiuvue9ruaWh+acrOWkp+Wwj++8\njOazqOaEj+ayoeacieWNleS9jSkgIApOaXhvOmNpcmNsZVBhZGRpbmdCb3R0\nb209IjIwIiAo5ZyG6Led56a75bqV6YOo55qE5YGP56e76YePIOayoeacieWN\nleS9jSkgIApOaXhvOmNpcmNsZUZyYW1lPSIyMCIo5ZyG55qE6L655qGG5aSn\n5bCPIOayoeacieWNleS9jSkgIApOaXhvOmNpcmNsZUNvbG9yPSIjNjQ3NWZk\nIiAo5ZyG55qE6aKc6ImyKSAgCk5peG86Y2lyY2xlRnJhbWVDb2xvcj0iI2Zm\nZiIgKOWchui+ueahhuminOiJsikgIAoKCgojIOaVtOS9k+WunuS+iyAgCjxj\nb20uZXhhbXBsZS5uaXhvLm5peG92aWV3Lk5peG9Cb3R0b21CdXR0b24KICAK\nICAKICBhbmRyb2lkOmxheW91dF9hbGlnblBhcmVudEJvdHRvbT0idHJ1ZSIK\nICAKICAKICBhbmRyb2lkOmxheW91dF9jZW50ZXJIb3Jpem9udGFsPSJ0cnVl\nIgogIAogIAogIGFuZHJvaWQ6aWQ9IkAraWQvQm90dG9tQnV0dG9uIgogIAog\nIAogIGFuZHJvaWQ6bGF5b3V0X3dpZHRoPSIxMDBkcCIKICAKICAKICBhbmRy\nb2lkOmxheW91dF9oZWlnaHQ9IjEyMGRwIgogIAogIAogIGFuZHJvaWQ6YmFj\na2dyb3VuZD0iIzAwMDAwMDAwIgogIAogIAogIE5peG86Y2lyY2xlQ29sb3I9\nIiM2NDc1ZmQiCiAgCiAgCiAgTml4bzp0ZXh0U2l6ZT0iMzAiCiAgCiAgCiAg\nTml4bzpjaXJjbGVGcmFtZUNvbG9yPSIjZmZmIgogIAogIAogIE5peG86Y2ly\nY2xlUGFkZGluZ0JvdHRvbT0iNyIKICAKICAKICBOaXhvOmNpcmNsZUZyYW1l\nPSIxNCIKICAKICAKICAvPgo=\n",
//    "encoding": "base64",
//    "_links": {
//    "self": "https://api.github.com/repos/Nixo0427/NixoBottomButton/contents/README.md?ref=master",
//    "git": "https://api.github.com/repos/Nixo0427/NixoBottomButton/git/blobs/ff98b3049c10ea6b9fd178431f80783db56578b5",
//    "html": "https://github.com/Nixo0427/NixoBottomButton/blob/master/README.md"
//}
//}


@PoKo
data class MDBaseInfo(
        var name : String,
        var path : String,
        var sha  : String,
        var size : Number,
        var url : String,
        var html_url: String,
        var git_url : String,
        var download_url:String,
        var type:String,
        var content:String,
        var encoding:String,
        var _links : _links


)

@PoKo
data class _links(
        var self :String,
        var git : String,
        var html:String
)