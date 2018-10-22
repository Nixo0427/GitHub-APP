package github.nixo.com.github.mvp

import android.content.res.Configuration
import android.os.Bundle

interface ILifecycler{

    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState : Bundle?)

    fun onViewStateResotre(saveInstanceState : Bundle?)

    fun onConfigurationChanged(newConfig: Configuration)

    fun onResume()

    fun onStop()

    fun onDestory()

    fun onStart()

    fun onPause()



}