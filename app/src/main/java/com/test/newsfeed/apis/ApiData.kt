package com.test.newsfeed.apis

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class ApiData {
    companion object {
        val api by lazy { Connect.callApi() }
        var disposable: Disposable? = null
        fun apiData(callback:Response){
            disposable = api.getApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                        result ->
                    callback.data(result,true)
                }, { error ->
                    error.printStackTrace()
                })

        }

    }
    interface Response {
        fun data(data:Model.Result,status:Boolean)
    }
}