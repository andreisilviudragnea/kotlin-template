package com.example

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class SumTest : StringSpec({
    "sum of two numbers" {
        forAll(
            row(1, 5, 6),
            row(1, 0, 1),
            row(0, 0, 0)
        ) { a, b, expectedSum ->
            sum(a, b) shouldBe expectedSum
        }
    }

    "service test" {
        val repository = mockk<Repository>()

        val user = User("name")

        every { repository.getUser() } returns user

        val service = DefaultService(repository)

        service.getUser() shouldBe user

        verify { repository.getUser() }

        confirmVerified(repository)
    }
})
