package github.nixo.com.Ext

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

inline  fun FragmentManager.inT(func : FragmentTransaction.() -> Unit){
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}