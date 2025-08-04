package com.droidper.xtrajob.domain.model

sealed class DomainFailure: CoreFailure(){
    //data object NetworkConnection : DomainFailure()
    data object AbortInsertDataBaseError: DomainFailure()

}