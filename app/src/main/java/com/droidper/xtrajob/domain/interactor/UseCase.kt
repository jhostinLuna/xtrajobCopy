package com.droidper.xtrajob.domain.interactor

import androidx.compose.runtime.snapshots.SnapshotApplyResult
import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.domain.model.CoreFailure

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Resource.Error] to the result) is the subclasses's responsibility.
 */
abstract class UseCase<in P, out R> where R : Any {
    suspend operator fun invoke(parameters: P, onResult: (Resource<CoreFailure, R>) -> Unit = {}) {
        onResult(execute(parameters))

    }
    abstract suspend fun execute(parameters: P): Resource<CoreFailure, R>
}