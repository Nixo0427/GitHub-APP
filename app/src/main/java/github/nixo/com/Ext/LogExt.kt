package github.nixo.com.github.Ext

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val loggerMap = HashMap<Class<*>, Logger>()


/**
 * 泛型具体化工具
 * reified T 这里reified翻译过来是具体化的意思，也就是被reified修饰过的泛型T 在编译时会变为具体类型
 */

inline val <reified T> T.logger: Logger
    get() {
        return loggerMap[T::class.java]?: LoggerFactory.getLogger(T::class.java).apply { loggerMap[T::class.java] = this }
    }