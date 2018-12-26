package github.nixo.com.MVP.View

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import com.zzhoujay.markdown.MarkDown
import github.nixo.com.MVP.Present.MDTextPresent
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.activity_mdtext.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MDTextActivity : BaseActivity<MDTextPresent>() {
    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mdtext)
        var open = assets.open("test.md")
        var inputStringReader  = InputStreamReader(open)
        var bufferReader = BufferedReader(inputStringReader)
        var string = bufferReader.readLine()
        var a = """
# 一级标题
## 二级标题
### 三级标题
#### 四级标题
##### 五级标题
###### 六级标题

```java
 System.out.println("Java代码");
```

```kotlin
print(""${'"'}kotlin""${'"'})
```
//"""
//        val drawable = resources.getDrawable(R.mipmap.ic_launcher)
//        drawable.setBounds(0, 0, 400, 400)
//        return drawable
        tv_mdtext!!.post {
            val spanned = MarkDown.fromMarkdown(a, {
                val drawable = resources.getDrawable(R.mipmap.logo2)
                drawable.setBounds(0, 0, 400, 400)
                drawable
            }, tv_mdtext)
            tv_mdtext!!.text = spanned
        }
    }
}

