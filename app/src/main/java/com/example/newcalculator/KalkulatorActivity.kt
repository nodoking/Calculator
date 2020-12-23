package com.example.newcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kalkulator.*

class KalkulatorActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)
        init()
    }

    private fun init(){
        gilaki0.setOnClickListener(this)
        gilaki1.setOnClickListener(this)
        gilaki2.setOnClickListener(this)
        gilaki3.setOnClickListener(this)
        gilaki4.setOnClickListener(this)
        gilaki5.setOnClickListener(this)
        gilaki6.setOnClickListener(this)
        gilaki7.setOnClickListener(this)
        gilaki8.setOnClickListener(this)
        gilaki9.setOnClickListener(this)
        buttonDot.setOnClickListener(this)

        buttonBackspace.setOnLongClickListener {
            inputText.text = ""
            firstText.text = ""
            operationText.text = ""
            true
        }

    }

    override fun onClick(v: View?) {
        val button: Button = v as Button
        val text = button.text.toString()
        if(text == "."){
            buttonDot.isClickable = false
        }
        inputText.text = inputText.text.toString() + text
    }

    fun backspace(view: View){
        val text = inputText.text.toString()
        val operation = operationText.text.toString()
        if(text.isNotEmpty()){
            if(text.substring(text.length-1) == "."){
                buttonDot.isClickable = true
            }
            inputText.text = text.substring(0, text.length-1)
        }else if(operation.isNotEmpty()){
            operationText.text = ""
            inputText.text = firstText.text.toString()
            firstText.text = ""
        }
    }

    fun operation(view: View){
        val button: Button = view as Button
        val text = inputText.text.toString()
        if(text.isNotEmpty()){
            operationText.text = button.text.toString()
            firstText.text = text
            inputText.text = ""
            buttonDot.isClickable = true
        }
    }

    fun equal(view: View){
        val number1 = firstText.text.toString()
        val number2 = inputText.text.toString()
        val operation = operationText.text.toString()
        if(number1.isNotEmpty() && number2.isNotEmpty() && operation.isNotEmpty()) {
            firstText.text = ""
            operationText.text = ""
            var result:Double = 1.0
            var zeroDivision = false
            if (operation == "÷") {
                if (number2.toDouble() == 0.0) {
                    Toast.makeText(this, "0 ze gayofa ar sheidzleba", Toast.LENGTH_LONG).show()
                    zeroDivision = true
                    inputText.text = ""
                    buttonDot.isClickable = true
                } else {
                    result = number1.toDouble() / number2.toDouble()
                }
            } else if (operation == "x") {
                result = number1.toDouble() * number2.toDouble()
            }else if(operation == "+"){
                result = number1.toDouble() + number2.toDouble()
            }else if(operation == "-"){
                result = number1.toDouble() - number2.toDouble()
            }
            if(!zeroDivision) {
                if (result % 1.0 == 0.0) {
                    inputText.text = result.toInt().toString()
                    buttonDot.isClickable = true
                } else {
                    buttonDot.isClickable = false
                    inputText.text = result.toString()
                }
            }
        }
    }
}