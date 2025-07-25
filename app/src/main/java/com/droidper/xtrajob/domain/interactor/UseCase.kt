package com.droidper.xtrajob.domain.interactor

import com.droidper.xtrajob.core.common.Resource
import com.droidper.xtrajob.core.common.Failure

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Resource.Error] to the result) is the subclasses's responsibility.
 */
abstract class UseCase<in P, out R> where R : Any {

    abstract fun execute(parameters: P): Resource<Failure, R>
}