package com.test.newsfeed.apis

import retrofit2.http.GET
import io.reactivex.Observable

interface CallApi {

    @GET(Url.URL)
//query needed if there is any query
    fun getApi(): Observable<Model.Result>
}