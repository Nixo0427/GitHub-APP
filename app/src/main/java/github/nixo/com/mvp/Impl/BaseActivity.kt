package github.nixo.com.github.mvp.Impl

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import github.nixo.com.github.mvp.IPresenter
import github.nixo.com.github.mvp.IView
import kotlin.coroutines.experimental.buildSequence
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseActivity<out P : BasePresenter<BaseActivity<P>>> :IView<P> , AppCompatActivity() {
    override val presenter : P

    init {
        presenter = createPresenter()
        presenter.view = this
    }

    fun createPresenter(): P {
        buildSequence {
            var thisClass : KClass<*> = this@BaseActivity::class
            while (true){
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure?:break
            }
        }.flatMap{
            it.flatMap{
                it.arguments
            }.asSequence()
        }.first{
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class)?:false
        }.let{
            return it.type!!.jvmErasure?.primaryConstructor!!.call() as P
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        presenter.view = this
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        presenter.view = this
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        presenter.view = this
    }

  

    override fun onResume() {
        super.onResume()
        presenter.view = this
    }

    override fun onStop() {
        super.onStop()
        presenter.view = this
    }

    override fun onStart() {
        super.onStart()
        presenter.view = this
    }

    override fun onPause() {
        super.onPause()
        presenter.view = this
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = this
    }


}


