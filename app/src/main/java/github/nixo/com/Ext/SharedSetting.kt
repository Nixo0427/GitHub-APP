package github.nixo.com.github.Ext

import github.nixo.com.Ext.Preference

object SharedSetting{
    var account : String by Preference(AppContext,"account","")
    var passWord : String by Preference(AppContext,"password","")
}