package com.example.prakmobel1compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerApp()
        }
    }
}

@Composable
fun DiceRollerApp() {

    var dice1 by remember { mutableIntStateOf(0) }
    var dice2 by remember { mutableIntStateOf(0) }

    val context = LocalContext.current

    fun getDiceImage(roll: Int): Int {
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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Image(
                painter = painterResource(id = getDiceImage(dice1)),
                contentDescription = "Dice 1 is $dice1",
                modifier = Modifier
                    .size(150.dp)
                    .padding(end = 16.dp)
            )

            Image(
                painter = painterResource(id = getDiceImage(dice2)),
                contentDescription = "Dice 2 is $dice2",
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = 16.dp)
            )
        }

        Button(onClick = {
            dice1 = (1..6).random()
            dice2 = (1..6).random()

            if (dice1 == dice2) {
                Toast.makeText(context, "Selamat, anda dapat dadu double!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Roll")
        }
    }
}