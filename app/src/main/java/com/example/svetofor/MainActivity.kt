package com.example.svetofor



import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.graphics.drawable.GradientDrawable


class MainActivity : AppCompatActivity() {

    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private lateinit var nextButton: Button

    private var currentStateIndex = 0
    private val states = listOf("RED", "YELLOW1", "GREEN", "YELLOW2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redLight = findViewById(R.id.red_light)
        yellowLight = findViewById(R.id.yellow_light)
        greenLight = findViewById(R.id.green_light)
        nextButton = findViewById(R.id.next_button)

        if (savedInstanceState != null) {
            currentStateIndex = savedInstanceState.getInt("STATE_INDEX", 0)
        }

        updateLights()

        nextButton.setOnClickListener {
            currentStateIndex = (currentStateIndex + 1) % states.size
            updateLights()
        }
    }

    private fun updateLights() {
        val redDrawable = redLight.background as GradientDrawable
        val yellowDrawable = yellowLight.background as GradientDrawable
        val greenDrawable = greenLight.background as GradientDrawable

        redDrawable.setColor(Color.GRAY)
        yellowDrawable.setColor(Color.GRAY)
        greenDrawable.setColor(Color.GRAY)

        when (states[currentStateIndex]) {
            "RED" -> redDrawable.setColor(Color.RED)
            "YELLOW1", "YELLOW2" -> yellowDrawable.setColor(Color.YELLOW)
            "GREEN" -> greenDrawable.setColor(Color.GREEN)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("STATE_INDEX", currentStateIndex)
    }
}

