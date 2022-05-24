package com.example.interview

import java.net.URI
import java.security.SecureRandom
import java.util.concurrent.atomic.AtomicInteger

fun interface SeoGenerationPolicy {
    fun generateSeo(): String
}

class RandomSeoGenerationPolicy(private val len: Int) : SeoGenerationPolicy {
    override fun generateSeo(): String {
        val random = SecureRandom()
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()
        return (1..len).map { chars[random.nextInt(chars.size)] }.joinToString("")
    }
}

class IncrementalSeoPolicy(private var currentVal: AtomicInteger = AtomicInteger(1)) : SeoGenerationPolicy {
    override fun generateSeo(): String {
        val currentVal = currentVal.getAndIncrement()
        return currentVal.toString()
    }
}

class UrlShortenerService(private val shortenerUrl: URI, private val seoGenerationPolicy: SeoGenerationPolicy) {
    private val store = mutableMapOf<String, URI>()

    fun shortenUrl(url: URI, seo: String): URI {
        if (seo.length > 20) {
            throw IllegalArgumentException("seo is longer than 20 characters")
        }

        return shortenUrlInternal(url, seo)
    }

    fun shortenUrl(url: URI): URI {
        return shortenUrlInternal(url, seoGenerationPolicy.generateSeo())
    }

    private fun shortenUrlInternal(url: URI, seo: String): URI {
        store[seo] = url
        return URI("$shortenerUrl/$seo")
    }

    fun expandUrl(shortUrl: URI): URI? {
        return store[shortUrl.path]
    }
}
