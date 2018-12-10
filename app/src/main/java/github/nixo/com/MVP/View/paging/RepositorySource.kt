package github.nixo.com.MVP.View.paging

import android.arch.paging.PageKeyedDataSource
import github.nixo.com.Common.NetWork.Repository.Repository
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


//


//
class RepositorySource(val repository:List<Repository>) : PageKeyedDataSource<Int , Repository>(),AnkoLogger {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repository>) {
        info("loadIntial size: ${params.requestedLoadSize}")
        callback.onResult(repository,null,2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        info("loadAfter Size : ${params.requestedLoadSize}  page:${params.key}")
        repository.let {
            callback.onResult(repository,params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        info("loadAfter Size : ${params.requestedLoadSize}  page:${params.key}")
        repository.let {
            callback.onResult(repository,params.key - 1)
        }
    }
}