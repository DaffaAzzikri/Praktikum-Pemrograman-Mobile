package com.example.prakmobel1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun rollDice() {
        val dice = Dice(6)

        val diceRoll1 = dice.roll()
        val diceRoll2 = dice.roll()

        val diceImage1: ImageView = findViewById(R.id.imageView1)
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        fun getDrawableResource(roll: Int): Int {
            return when (roll) {
                0 -> R.drawable.dice_0
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        }

        diceImage1.setImageResource(getDrawableResource(diceRoll1))
        diceImage2.setImageResource(getDrawableResource(diceRoll2))

        if (diceRoll1 == diceRoll2) {
            android.widget.Toast.makeText(this, "Selamat, anda dapat dadu double!", android.widget.Toast.LENGTH_SHORT).show()
        } else {
            android.widget.Toast.makeText(this, "Anda belum beruntung!", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
    class Dice(val numSides: Int) {

        fun roll(): Int {
            return (1..numSides).random()
        }
    }
}