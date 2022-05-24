package com.example

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TennisGameTest : FunSpec({
    fun newGame() = TennisGame("Boris Becker", "Bjørn Borg")

    fun TennisGame.createScore(playerOneBalls: Int, playerTwoBalls: Int) {
        for (i in 0 until playerOneBalls) {
            playerOneScores()
        }
        for (i in 0 until playerTwoBalls) {
            playerTwoScores()
        }
    }

    test("testNewGameShouldReturnLoveAll") {
        val game = newGame()
        val score = game.score
        "Love all" shouldBe score
    }

    test("testPlayerOneWinsFirstBall") {
        val game = newGame()
        game.playerOneScores()
        val score = game.score
        "Fifteen,Love" shouldBe score
    }

    test("testFifteenAll") {
        val game = newGame()
        game.playerOneScores()
        game.playerTwoScores()
        val score = game.score
        "Fifteen all" shouldBe score
    }

    test("testPlayerTwoWinsFirstTwoBalls") {
        val game = newGame()
        game.createScore(0, 2)
        val score = game.score
        "Love,Thirty" shouldBe score
    }

    test("testPlayerOneWinsFirstThreeBalls") {
        val game = newGame()
        game.createScore(3, 0)
        val score = game.score
        "Forty,Love" shouldBe score
    }

    test("testPlayersAreDeuce") {
        val game = newGame()
        game.createScore(3, 3)
        val score = game.score
        "Deuce" shouldBe score
    }

    test("testPlayerOneWinsGame") {
        val game = newGame()
        game.createScore(4, 0)
        val score = game.score
        "Boris Becker wins" shouldBe score
    }

    test("testPlayerTwoWinsGame") {
        val game = newGame()
        game.createScore(1, 4)
        val score = game.score
        "Bjørn Borg wins" shouldBe score
    }

    test("testPlayersAreDuce4") {
        val game = newGame()
        game.createScore(4, 4)
        val score = game.score
        "Deuce" shouldBe score
    }

    test("testPlayerTwoAdvantage") {
        val game = newGame()
        game.createScore(4, 5)
        val score = game.score
        "Advantage Bjørn Borg" shouldBe score
    }

    test("testPlayerOneAdvantage") {
        val game = newGame()
        game.createScore(5, 4)
        val score = game.score
        "Advantage Boris Becker" shouldBe score
    }

    test("testPlayerTwoWins") {
        val game = newGame()
        game.createScore(2, 4)
        val score = game.score
        "Bjørn Borg wins" shouldBe score
    }

    test("testPlayerTwoWinsAfterAdvantage") {
        val game = newGame()
        game.createScore(6, 8)
        val score = game.score
        "Bjørn Borg wins" shouldBe score
    }

    test("testPlayerOneWinsAfterAdvantage") {
        val game = newGame()
        game.createScore(8, 6)
        val score = game.score
        "Boris Becker wins" shouldBe score
    }
})
