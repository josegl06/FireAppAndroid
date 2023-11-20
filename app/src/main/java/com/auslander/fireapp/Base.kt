package com.auslander.fireapp

import io.reactivex.disposables.CompositeDisposable

interface Base {
    val disposables:CompositeDisposable
}