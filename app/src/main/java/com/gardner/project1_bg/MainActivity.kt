package com.gardner.project1_bg

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class MainActivity : AppCompatActivity() {
    lateinit var quantityInput: EditText
    lateinit var sidesInput: EditText
    lateinit var rollBtn: AppCompatButton
    lateinit var additionView: TextView
    lateinit var totalView: TextView
    var qFlag = false
    var sFlag = false
    var rollValue = 0
    var total = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quantityInput = findViewById(R.id.quantityInput)
        sidesInput = findViewById(R.id.sidesInput)
        rollBtn = findViewById(R.id.rollBtn)
        additionView = findViewById(R.id.additionView)
        totalView = findViewById(R.id.totalView)
        rollBtn.setOnClickListener( { doRollBtn() } )
    }

    fun doRollBtn() {
        Log.i("DiceRoller","Roll button tapped.")
        additionView.text = ""
        totalView.text = ""

        if (quantityInput.text.isEmpty()) {
            Log.i("DiceRoller","Roll button was tapped while input1 is empty.")
            quantityInput.background = resources.getDrawable(R.drawable.rounded_edittext_lightred)
            qFlag = false
        } else {
            Log.i("DiceRoller","Roll button was tapped with valid input1.")
            quantityInput.background = resources.getDrawable(R.drawable.rounded_edittext_lightgreen)
            qFlag = true
        }

        if (sidesInput.text.isEmpty() || sidesInput.text.toString().toInt() < 3 || sidesInput.text.toString().toInt() > 20) {
            Log.i("DiceRoller","Roll button was tapped while input2 is empty/out of range.")
            sidesInput.background = resources.getDrawable(R.drawable.rounded_edittext_lightred)
            sFlag = false
        } else {
            Log.i("DiceRoller","Roll button was tapped with valid input2.")
            sidesInput.background = resources.getDrawable(R.drawable.rounded_edittext_lightgreen)
            sFlag = true
        }

        if (qFlag && sFlag) {
            total = 0
            for (i in 1..quantityInput.text.toString().toInt()) {
                rollValue = (1..sidesInput.text.toString().toInt()).random()
                total += rollValue
                if (i == 1) {
                    additionView.text = rollValue.toString()
                } else {
                    additionView.text = additionView.text.toString() + ", " + rollValue.toString()
                }
            }
            totalView.text = total.toString()
        }
    }
}