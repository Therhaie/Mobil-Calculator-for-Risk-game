package com.example.entrkotlin

import kotlin.random.Random

class Calculus {
    fun rollDice(nbAttack: Int, nbDefenders: Int): Pair<List<Int>, List<Int>> {
        println("Rolling dice...")

        val attackDice = mutableListOf<Int>()
        val defendDice = mutableListOf<Int>()

        for (i in 1..nbAttack) {
            attackDice.add(Random.nextInt(1, 7))
        }
        for (i in 1..nbDefenders) {
            defendDice.add(Random.nextInt(1, 7))
        }

        println("Attack dice: $attackDice")
        println("Defend dice: $defendDice")

        return Pair(attackDice.sortedDescending(), defendDice.sortedDescending())
    }

    fun number_of_dice(nb_attackers: Int, nb_defenders: Int): Pair<Int, Int> {
        val nb_attack_dice = if (nb_attackers > 3) 3 else nb_attackers - 1
        val nb_defend_dice = if (nb_defenders > 2) 2 else nb_defenders

        return Pair(nb_attack_dice, nb_defend_dice)
    }

    fun calculateResult(attackDice: List<Int>, defendDice: List<Int>): Pair<Int, Int> {
        println("Calculating result...")

        var attackLosses = 0
        var defendLosses = 0

        val sortedAttackDice = attackDice.sortedDescending()
        val sortedDefendDice = defendDice.sortedDescending()

        for (i in 0 until minOf(sortedAttackDice.size, sortedDefendDice.size)) {
            if (sortedAttackDice[i] > sortedDefendDice[i]) {
                defendLosses++
            } else {
                attackLosses++
            }
        }

        return Pair(attackLosses, defendLosses)
    }
}
