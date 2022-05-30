package com.example.interview2

import java.util.concurrent.atomic.AtomicInteger

data class Account(var balance: AtomicInteger)

class InvalidAmountException(fromAmount: Int, actualAmount: Int) : RuntimeException() {
    override val message = "From amount is $fromAmount and actual amount is $actualAmount"
}

// Optimistic vs pessimistic locking
// Transaction isolation levels
// DDD - Domain driven design
// How a database index is implemented
class BalanceTransferService {
    fun transfer(from: Account, to: Account, amount: Int) {
        if (from == to) {
            throw IllegalArgumentException("Same from and to accounts")
        }

        if (amount < 0) {
            throw IllegalArgumentException("Amount is negative")
        }

        from.balance.getAndUpdate {
            if (amount > it) {
                throw InvalidAmountException(it, amount)
            }
            it - amount
        }
        to.balance.getAndUpdate { it + amount }
    }
}
