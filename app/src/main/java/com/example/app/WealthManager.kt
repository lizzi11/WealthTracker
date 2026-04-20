package com.example.app

class WealthManager {

    //K = (Elisabed (8) + Dvalishvili (10 ქართულად)) / 11 = 1.636
    private val kCoefficient: Double = (8.0 + 10.0) / 11.0

    fun calculateFinalSavings(income: Double, expenses: Double): Double {
        return (income - expenses) * kCoefficient
    }
}
