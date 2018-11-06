package retrofit2.adapter.rxjava

import github.nixo.com.github.Ext.logger
import okhttp3.HttpUrl

class GitHubPaging<T> : ArrayList<T>(){
    companion object {
        const val URL_PATTERN = """(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"""
    }

    private val relMap = HashMap<String , String ?>().withDefault { null }

    private val first by relMap
    private val last by relMap
    private val next by relMap
    private val prev by relMap


    val isLast
        get() = last == null
    val hasNext
        get() = next != null
    val isFirst
        get() = first == null
    val hasPrev
        get() = prev != null

    var since: Int = 0

    /**
     * 重载这个当前类的get方法，我们返回不是当前类，返回当前的Map集合key的value
     */
    operator fun get(key: String): String?{
        return relMap[key]
    }

    /**
     * 解析头中Link字段的Url里面关键信息
     * @param Link中的url
     */
    fun setupLinks(link : String){
        logger.warn("setupLinks: $link")
        Regex("""<($URL_PATTERN)>; rel="(\w+)"""").findAll(link).asIterable()
                .map {
                    //使用正则过滤url信息
                    matchResult ->
                    //url
                    val url = matchResult.groupValues[1]
                    //锚点
                    relMap[matchResult.groupValues[3]] = url
                    if(url.contains("since")){
                        //如果url中有since字段，那么我们把url这个字符串变为HttpUrl ， 然后通过let函数截取到字段的值转为int
                        HttpUrl.parse(url)?.queryParameter("since")?.let {
                            since = it.toInt()
                        }
                    }
                    logger.warn("${matchResult.groupValues[3]} => ${matchResult.groupValues[1]}")
                }
    }

    //拿到新一页的数据后，合并为一个Map
    fun mergeData(paging: GitHubPaging<T>): GitHubPaging<T> {
        addAll(paging)
        relMap.clear()
        relMap.putAll(paging.relMap)
        since = paging.since
        return this
    }



}