package github.nixo.com.github.Setting

import github.nixo.com.github.Ext.AppContext
import github.nixo.com.github.Ext.logger
import github.nixo.com.github.utils.deviceId

object Configs {

    object Account{
        val SCOPES = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "0b72f34b028b5af3edac"
        const val clientSecret = "a11df0e210845bd472bc9567e9f29cb40e7770d8"
        const val note = "NixoApplication"
        const val noteUrl = "https://github.com/Nixo0427/GitHub-APP.git"

        val fingerPrint by lazy {
            (AppContext.deviceId + clientId).also { logger.info("fingerPrint: "+it) }
        }
    }

}