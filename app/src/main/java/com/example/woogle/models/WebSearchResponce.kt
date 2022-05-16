package com.example.woogle.models

data class WebSearchResponce(
    var _type: String? = null,
    var didUMean: String? = null,
    var totalCount: Number? = null,
    var relatedSearch: MutableList<String>? = null,
    var value: MutableList<SearchResult>? = null,
)

data class SearchResult(
    var id: Number? = null,
    var title: String? = null,
    var url: String? = null,
    var body: String? = null,
    var snippet: String? = null,
    var keywords: String? = null,
    var language: String? = null,
    var isSafe: Boolean? = null,
    var datePublished: String? = null,
    var provider: Provider? = null,
    var image: Image? = null,
)

data class Provider(
    var name: String? = null,
    var favIcon: String? = null,
    var favIconBase64Encoding: String? = null
)

data class Image(
    var url: String? = null,
    var height: Number? = null,
    var width: Number? = null,
    var thumbnail: String? = null,
    var thumbnailHeight: Number? = null,
    var thumbnailWidth: Number? = null,
    var base64Encoding: String? = null,
    var title: String? = null,
    var provider: Provider? = null,
    var imageWebSearchUrl: String? = null,
    var webpageUrl: String? = null,
)