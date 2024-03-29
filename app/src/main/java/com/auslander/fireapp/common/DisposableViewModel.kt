package com.auslander.fireapp.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class DisposableViewModel:ViewModel() {
    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}