package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.databinding.CalculatorScreenBinding

class Calculator : AppCompatActivity() {

    private var firstNumber = 0.0
    private var operator = ""
    private var isNewInput = true


    private val binding: CalculatorScreenBinding by lazy {
        CalculatorScreenBinding.inflate(
            layoutInflater
        )
    }

    private val resultTextView: TextView by lazy { binding.displayText }
    private val inputTextView: TextView by lazy { binding.displayText }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        // Get the name from the intent
        val name = intent.getStringExtra("name").toString()

        // Display the name in the TextView using view binding
        binding.nameTextView.text = "Welcome,  $name!"

        //Set Action On Click Listener
        binding.button0.setOnClickListener { onDigitClick(it) }
        binding.button1.setOnClickListener { onDigitClick(it) }
        binding.button2.setOnClickListener { onDigitClick(it) }
        binding.button3.setOnClickListener { onDigitClick(it) }
        binding.button4.setOnClickListener { onDigitClick(it) }
        binding.button5.setOnClickListener { onDigitClick(it) }
        binding.button6.setOnClickListener { onDigitClick(it) }
        binding.button7.setOnClickListener { onDigitClick(it) }
        binding.button8.setOnClickListener { onDigitClick(it) }
        binding.button9.setOnClickListener { onDigitClick(it) }

        binding.buttonClear.setOnClickListener { onClearClick(it) }

        binding.buttonADD.setOnClickListener { onOperatorClick(it) }
        binding.buttonSUB.setOnClickListener { onOperatorClick(it) }
        binding.buttonMUL.setOnClickListener { onOperatorClick(it) }
        binding.buttonDIV.setOnClickListener { onOperatorClick(it) }
        binding.buttonREM.setOnClickListener { onOperatorClick(it) }

        binding.buttonEquals.setOnClickListener { onEqualsClick(it) }

        binding.buttonOPS.setOnClickListener { onPlusMinusClick(it) }

        binding.buttonDOT.setOnClickListener { onDotClick(it) }


    }

    fun onDigitClick(view: View) {
        if (isNewInput) {
            inputTextView.text = (view as Button).text
            isNewInput = false
        } else {
            inputTextView.append((view as Button).text)
        }
    }


    fun onOperatorClick(view: View) {
        if (!isNewInput) {
            firstNumber = inputTextView.text.toString().toDouble()
            operator = (view as Button).text.toString()
            isNewInput = true
        }
    }

    fun onEqualsClick(view: View) {
        if (!isNewInput) {
            try {
                val secondNumber = inputTextView.text.toString().toDouble()

                if (operator == "/" && secondNumber == 0.0) {
                    resultTextView.text = "Math Error"
                } else {
                    val result = when (operator) {
                        "+" -> firstNumber + secondNumber
                        "-" -> firstNumber - secondNumber
                        "x" -> firstNumber * secondNumber
                        "/" -> firstNumber / secondNumber
                        "%" -> firstNumber % secondNumber
                        else -> Double.NaN
                    }

                    if (result.isNaN()) {
                        resultTextView.text = "Math Error"
                    } else {
                        resultTextView.text = if (result % 1 == 0.0) {
                            result.toInt().toString()  // Show as integer if it's a whole number
                        } else {
                            result.toString()
                        }
                    }
                }
                isNewInput = true
            } catch (e: NumberFormatException) {
                resultTextView.text = "Input Error"
            }
        }
    }


    fun onClearClick(view: View) {
        inputTextView.text = "0"
        resultTextView.text = ""
        firstNumber = 0.0
        operator = ""
        isNewInput = true
    }

    fun onPlusMinusClick(view: View) {
        val  currentNumber = inputTextView.text.toString().toInt()
        val newNumber = -currentNumber
        inputTextView.text = newNumber.toString()
    }

    fun onDotClick(view: View) {
        val currentText = inputTextView.text.toString()
        if (!currentText.contains(".")) { // Check if there's no decimal point already
            inputTextView.text = "$currentText." // Append a decimal point
        }
    }


}
