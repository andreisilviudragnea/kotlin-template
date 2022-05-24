package com.example.interview

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.net.URI

class UrlShortenerServiceTest : StringSpec({
    "shortenUrl 1" {
        UrlShortenerService(URI("http://short.com")) { "abcd" }.shortenUrl(
            URI("http://looooong.com/somepath"),
            "MY-NEW-WS"
        ) shouldBe URI("http://short.com/MY-NEW-WS")
    }

    "shortenUrl 2" {
        UrlShortenerService(URI("http://short.com")) { "abcd" }.shortenUrl(
            URI("http://looooong.com/somepath"),
            "POTATO"
        ) shouldBe URI("http://short.com/POTATO")
    }

    "shortenUrl random seo" {
        UrlShortenerService(
            URI("http://short.com")
        ) { "abcd" }.shortenUrl(URI("http://looooong.com/somepath")) shouldBe URI("http://short.com/abcd")
    }

    "shortenUrl seo too long" {
        assertThatThrownBy {
            UrlShortenerService(URI("http://short.com")) { "abcd" }.shortenUrl(
                URI("http://looooong.com/somepath"),
                ".".repeat(21)
            )
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("seo is longer than 20 characters")
    }

    "shortenUrl incremental seo" {
        val seoGenerationPolicy = IncrementalSeoPolicy()
        UrlShortenerService(
            URI("http://short.com"), seoGenerationPolicy
        ).shortenUrl(URI("http://looooong.com/somepath")) shouldBe URI("http://short.com/1")
        UrlShortenerService(
            URI("http://short.com"), seoGenerationPolicy
        ).shortenUrl(URI("http://looooong.com/somepath")) shouldBe URI("http://short.com/2")
    }
})
