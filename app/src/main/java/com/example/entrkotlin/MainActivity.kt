package com.example.entrkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val numberOfAttackers: EditText = findViewById(R.id.number_of_attackers)
        val numberOfDefenders: EditText = findViewById(R.id.number_of_defenders)
        val resultPercentage: TextView = findViewById(R.id.result_percentage)
        val resultAverageAttackers: TextView = findViewById(R.id.result_average_attackers)
        val resultAverageDefenders: TextView = findViewById(R.id.result_average_defenders)

        calculateButton.setOnClickListener {
            try {
                val attackers = numberOfAttackers.text.toString().toIntOrNull() ?: 0
                val defenders = numberOfDefenders.text.toString().toIntOrNull() ?: 0

                if (attackers <= 0 || defenders <= 0) {
                    resultPercentage.text = "Invalid input"
                    resultAverageAttackers.text = ""
                    resultAverageDefenders.text = ""
                    return@setOnClickListener
                }

                var totalAttackersLeft = 0
                var totalDefendersLeft = 0
                var totalWins = 0

                for (i in 1..1000) {
                    var attack = attackers
                    var defend = defenders

                    while (attack > 1 && defend > 0) {
                        val calculus = Calculus()
                        val (nbAttackDice, nbDefendDice) = calculus.number_of_dice(attack, defend)
                        val (attackDice, defendDice) = calculus.rollDice(nbAttackDice, nbDefendDice)
                        val (attackLoss, defendLoss) = calculus.calculateResult(attackDice, defendDice)
                        attack -= attackLoss
                        defend -= defendLoss

                        Log.d("MainActivity", "Iteration $i: Attackers left: $attack, Defenders left: $defend")
                    }

                    if (defend == 0) {
                        totalWins++
                    }
                    totalAttackersLeft += attack
                    totalDefendersLeft += defend
                }

                val avgAttackers = totalAttackersLeft / 1000
                val avgDefenders = totalDefendersLeft / 1000
                val percentage = totalWins / 10

                resultPercentage.text = "Percentage: $percentage%"
                resultAverageAttackers.text = "Avg. Attackers: $avgAttackers"
                resultAverageDefenders.text = "Avg. Defenders: $avgDefenders"

            } catch (e: Exception) {
                Log.e("MainActivity", "Error during calculation", e)
                resultPercentage.text = "${e.message}"
                resultAverageAttackers.text = ""
                resultAverageDefenders.text = ""
            }
        }
    }
}
