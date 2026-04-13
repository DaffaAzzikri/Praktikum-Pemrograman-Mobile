package com.example.tipcalculatorxml

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.widget.doOnTextChanged
import com.example.tipcalculatorxml.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tipOptions = listOf("15%", "18%", "20%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tipOptions)
        binding.tipPercentageAutoComplete.setAdapter(adapter)
        binding.tipPercentageAutoComplete.setText(tipOptions[0], false)
        binding.tipPercentageAutoComplete.setOnItemClickListener { _, _, _, _ ->
            calculateTip()
        }

        binding.amountEditText.doOnTextChanged { _, _, _, _ ->
            calculateTip()
        }

        binding.roundUpSwitch.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }
        calculateTip()
    }

    private fun calculateTip() {
        val amount = binding.amountEditText.text.toString().toDoubleOrNull() ?: 0.0
        val tipString = binding.tipPercentageAutoComplete.text.toString().replace("%", "")
        val tipPercent = tipString.toDoubleOrNull() ?: 15.0

        val roundUp = binding.roundUpSwitch.isChecked

        var tip = (tipPercent / 100) * amount
        if (roundUp) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResultTextView.text = getString(R.string.tip_amount, formattedTip)
    }
}