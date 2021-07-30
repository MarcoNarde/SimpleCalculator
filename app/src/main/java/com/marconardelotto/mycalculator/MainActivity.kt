package com.marconardelotto.mycalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true;
    }

    fun onClickClear(view: View) {
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onClickDot(view: View) {
        if (lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onClickEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix = "";
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    var splitValue = tvValue.split("-").toMutableList()

                    if(!prefix.equals("")){
                        splitValue[0] = prefix + splitValue[0]
                    }

                    var result = splitValue[0].toDouble() - splitValue[1].toDouble()

                    tvInput.text = removeZeroAfterDot(result.toString())
                }else if(tvValue.contains("+")){
                    var splitValue = tvValue.split("+").toMutableList()

                    if(!prefix.equals("")){
                        splitValue[0] = prefix + splitValue[0]
                    }

                    var result = splitValue[0].toDouble() + splitValue[1].toDouble()

                    tvInput.text = removeZeroAfterDot(result.toString())
                }else if(tvValue.contains("*")){
                    var splitValue = tvValue.split("*").toMutableList()

                    if(!prefix.equals("")){
                        splitValue[0] = prefix + splitValue[0]
                    }

                    var result = splitValue[0].toDouble() * splitValue[1].toDouble()

                    tvInput.text = removeZeroAfterDot(result.toString())
                }else if(tvValue.contains("/")){
                    var splitValue = tvValue.split("/").toMutableList()

                    if(!prefix.equals("")){
                        splitValue[0] = prefix + splitValue[0]
                    }

                    var result = splitValue[0].toDouble() / splitValue[1].toDouble()

                    tvInput.text = removeZeroAfterDot(result.toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String): String{
        var value = result

        if(result.contains(".0")){
            value = result.substring(0,result.length-2)
        }

        return value
    }

    fun onClickOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false;
            lastDot = false;
        }
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")
        }
    }
}