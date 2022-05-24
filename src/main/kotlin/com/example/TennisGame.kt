package com.example

class TennisGame(private val playerOneName: String, private val playerTwoName: String) {
    private var playerOneScore = 0
    private var playerTwoScore = 0

    val score: String
        get() {
            if (hasWinner()) {
                return playerWithHighestScore() + " wins"
            }
            if (hasAdvantage()) {
                return "Advantage " + playerWithHighestScore()
            }
            if (isDeuce) return "Deuce"
            return if (playerOneScore == playerTwoScore) {
                translateScore(playerOneScore) + " all"
            } else translateScore(playerOneScore) + "," + translateScore(playerTwoScore)
        }

    private val isDeuce: Boolean
        get() = playerOneScore >= 3 && playerTwoScore == playerOneScore

    private fun playerWithHighestScore(): String {
        return if (playerOneScore > playerTwoScore) {
            playerOneName
        } else {
            playerTwoName
        }
    }

    private fun hasWinner(): Boolean {
        if (playerTwoScore >= 4 && playerTwoScore >= playerOneScore + 2) return true
        return playerOneScore >= 4 && playerOneScore >= playerTwoScore + 2
    }

    private fun hasAdvantage(): Boolean {
        if (playerTwoScore >= 4 && playerTwoScore == playerOneScore + 1) return true
        return playerOneScore >= 4 && playerOneScore == playerTwoScore + 1
    }

    fun playerOneScores() {
        playerOneScore++
    }

    fun playerTwoScores() {
        playerTwoScore++
    }

    private fun translateScore(score: Int): String {
        return when (score) {
            3 -> "Forty"
            2 -> "Thirty"
            1 -> "Fifteen"
            0 -> "Love"
            else -> throw IllegalArgumentException("Illegal score: $score")
        }
    }
}
