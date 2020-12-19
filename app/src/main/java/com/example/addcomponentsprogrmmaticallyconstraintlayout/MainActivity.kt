package com.example.addcomponentsprogrmmaticallyconstraintlayout

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import com.example.addcomponentsprogrmmaticallyconstraintlayout.databinding.ActivityMainBinding
import com.example.addcomponentsprogrmmaticallyconstraintlayout.databinding.ButtonBinding

class MainActivity :
    AppCompatActivity(),
    View.OnClickListener {

    // Binding
    private lateinit var binding: ActivityMainBinding

    // Components
    private var increment = 1
        get() = field++
    private var lastID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.constraintLayout)
        initListeners()
    }

    // ==============================
    //    Init Listeners
    // ==============================
    private fun initListeners() {
        binding.btn.setOnClickListener(this)
    }

    // ==============================
    //    add Button To ConstraintLayout
    // ==============================
    private fun addButtonToConstraintLayout() {
        val layout = binding.constraintLayoutExternal.constraintLayout
        val button = ButtonBinding.inflate(layoutInflater).btn
        val textView = binding.tv

        button.id = increment
        button.text = "${button.id}"

        layout.addView(button)

        val set = ConstraintSet()
        set.clone(layout)
        button.id.also { btn ->
            set.connect(btn, START, PARENT_ID, START)
            set.connect(btn, END, PARENT_ID, END)
            if (lastID == 0) {
                set.connect(btn, TOP, PARENT_ID, TOP)
            } else {
                set.connect(btn, TOP, lastID, BOTTOM)
            }
            lastID = btn
        }
        set.applyTo(layout)

        // Listeners
        button.setOnClickListener {
            textView.text = button.text
        }
    }

    // ==============================
    //    Add Button
    // ==============================
    private fun addButton() {
        addButtonToConstraintLayout()
    }

    // ==============================
    //    onClick
    // ==============================
    override fun onClick(v: View) {
        when (v.id) {
            binding.btn.id -> addButton()
        }
    }
}




