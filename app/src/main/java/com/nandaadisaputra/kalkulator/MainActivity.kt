package com.nandaadisaputra.kalkulator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.nandaadisaputra.kalkulator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.switchDarkMode.setOnClickListener {
            if (binding.switchDarkMode.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        fun appendOnExpressions(string: String, canClear: Boolean) {
            if (binding.tvResult.text.isNotEmpty()) {
                binding.tvExpressions.text = ""
            }

            if (canClear) {
                binding.tvResult.text = ""
                binding.tvExpressions.append(string)
            } else {
                binding.tvExpressions.append(binding.tvResult.text)
                binding.tvExpressions.append(string)
                binding.tvResult.text = ""
            }
        }

        //nomor
        binding.text1.setOnClickListener { appendOnExpressions("1", true) }
        binding.text2.setOnClickListener { appendOnExpressions("2", true) }
        binding.text3.setOnClickListener { appendOnExpressions("3", true) }
        binding.text4.setOnClickListener { appendOnExpressions("4", true) }
        binding.text5.setOnClickListener { appendOnExpressions("5", true) }
        binding.text6.setOnClickListener { appendOnExpressions("6", true) }
        binding.text7.setOnClickListener { appendOnExpressions("7", true) }
        binding.text8.setOnClickListener { appendOnExpressions("8", true) }
        binding.text9.setOnClickListener { appendOnExpressions("9", true) }
        binding.text0.setOnClickListener { appendOnExpressions("0", true) }
        binding.textKoma.setOnClickListener { appendOnExpressions(".", true) }

        //operator
        binding.textPlus.setOnClickListener { appendOnExpressions(" + ", false) }
        binding.textMin.setOnClickListener { appendOnExpressions(" - ", false) }
        binding.textKali.setOnClickListener { appendOnExpressions(" * ", false) }
        binding.textBagi.setOnClickListener { appendOnExpressions(" / ", false) }
        binding.textOpen.setOnClickListener { appendOnExpressions("(", false) }
        binding.textClose.setOnClickListener { appendOnExpressions(")", false) }

        binding.textClear.setOnClickListener {
            binding.tvExpressions.text = ""
            binding.tvResult.text = ""
        }
        binding.backSpaceButton.setOnClickListener {
            val string = binding.tvExpressions.text.toString()
            if (string.isNotEmpty()) {
                binding.tvExpressions.text = string.substring(0, string.length - 1)
            }
            binding.tvResult.text = ""
        }
        binding.textEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.tvExpressions.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    binding.tvResult.text = longResult.toString()
                else
                    binding.tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", " message : " + e.message)
            }
        }

    }
}
