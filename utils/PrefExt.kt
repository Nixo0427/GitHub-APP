package github.nixo.com.github.utils


import github.nixo.com.github.Ext.SharedExt
import github.nixo.com.github.Ext.AppContext
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T) = SharedExt(AppContext,"", default, R::class
        .jvmName)