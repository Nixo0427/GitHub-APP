package github.nixo.com.github.Ext

import android.content.SharedPreferences
import github.nixo.com.Ext.Preference

object SharedSetting{
    var account : String by Preference(AppContext,"account","")
    var passWord : String by Preference(AppContext,"password","")

}