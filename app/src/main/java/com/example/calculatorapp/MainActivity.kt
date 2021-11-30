package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var result: TextView? = null
    var lastNumeric:Boolean = false
    var lastDot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
    }

    fun onDigit(view: View) {
        result?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        result?.text = ""
    }

    fun onDecimalPoint(view: View){
        if ( lastNumeric == true && !lastDot) {
            result?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        result?.text?.let {
            if ( lastNumeric && !isOperatorAdded(it.toString())) {
                result?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }

        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = result?.text.toString()
            var prefix = ""
            try {
                if ( tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if ( tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if ( prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if ( tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if ( prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if ( tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if ( prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if ( tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if ( prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
            }catch (e:ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String {
        var value = result
        if ( result.contains(".0"))
            value = result. substring(0, result.length - 2)

            return value
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("+")
                    || value.contains("-")
                    || value.contains("/")
                    || value.contains("*")
        }
    }


}