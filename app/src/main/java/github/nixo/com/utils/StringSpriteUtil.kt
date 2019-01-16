package github.nixo.com.utils

class StringSpriteUtil(){

    fun UrlBackSprite(url : String) : String{
        var split = url.split("\\/")
        var index2 = 0
        var result = ""
        while (true){
            if(index2 == split.size-1){
                break
            }
            result = result+split[index2]
        }
        return ""
    }

}