package com.droidper.xtrajob.core.interactor

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.core.exception.Failure
import kotlinx.coroutines.*

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Resource.Error] to the result) is the subclasses's responsibility.
 */
abstract class UseCase<in P, out R> where R : Any {
    operator fun invoke(coroutineScope: CoroutineScope ,params: P, onResult: (Resource<Failure, R>) -> Unit = {}) {
        coroutineScope.launch(Dispatchers.Main) {
            val job = async(Dispatchers.IO) { execute(params) }
            onResult(job.await())
        }
    }

    abstract fun execute(parameters: P): Resource<Failure, R>
}