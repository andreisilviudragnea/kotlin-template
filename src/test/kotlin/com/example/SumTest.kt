package com.example

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import org.assertj.core.api.Assertions.assertThat

class SumTest : StringSpec({
    "maximum of two numbers" {
        forAll(
            row(1, 5, 6),
            row(1, 0, 1),
            row(0, 0, 0)
        ) { a, b, expectedSum ->
            assertThat(sum(a, b)).isEqualTo(expectedSum)
        }
    }
})
