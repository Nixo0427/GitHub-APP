package github.nixo.com.github.mvp.Impl

import android.app.Fragment
import android.content.res.Configuration
import android.os.Bundle

import github.nixo.com.github.mvp.IPresenter
import github.nixo.com.github.mvp.IView
import kotlin.coroutines.experimental.buildSequence
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure


/**
 * 这里使用的Fragment是app包下的Fragment 并不是Fragment-V4
 * 原因是Fragment-V4的onCrate生命周期与我写的ILifecycler的onCreate不符合返回值
 * Fragment -v4 -》 onCreate():V
 * ILifecycler -》 onCreate(): Unit
 */


abstract class BaseFragment<out P : BasePresenter<BaseFragment<P>>>:IView<P>, Fragment() {

    override val presenter: P

    init {
        presenter = createPresenter()
        presenter.view = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = this
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
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




    fun createPresenter(): P {
        buildSequence {
            var thisClass: KClass<*> = this@BaseFragment::class
            while (true) {
                yield(thisClass.supertypes) // 添加BseFragment子类的父类
//                然后替换掉这个类为
//                他的上一层父类，如果遍历到空，也就是Any的父类，那么就break出去，
//                这时候我们拿到了每一个父类的父类序列，每一个参数就是每一个父类和每一个父类的父类序列
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            //然后遍历第一个类的父类序列(也就是继承BaseFragment的父类的类型序列)
            it.flatMap { it.arguments }.asSequence()
        }.first {
            //            我们拿到上面遍历的第一个类型，去判断是否是Ipresenter的子类，是的话调用下层，不是返回false
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            //            最后我们返回这个Presenter的实现类
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }
}





