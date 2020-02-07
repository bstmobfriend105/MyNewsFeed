package com.test.newsfeed.apis

object Model {
    data class Result(val articles: List<Article>)
    data class Article(val title: String,val urlToImage: String)
}