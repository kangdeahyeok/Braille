package com.dongkun.braille

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val CHO = arrayOf(
            "ㄱ",
            "ㄲ",
            "ㄴ",
            "ㄷ",
            "ㄸ",
            "ㄹ",
            "ㅁ",
            "ㅂ",
            "ㅃ",
            "ㅅ",
            "ㅆ",
            "ㅇ",
            "ㅈ",
            "ㅉ",
            "ㅊ",
            "ㅋ",
            "ㅌ",
            "ㅍ",
            "ㅎ"
        )
        val JOONG = arrayOf(
            "ㅏ",
            "ㅐ",
            "ㅑ",
            "ㅒ",
            "ㅓ",
            "ㅔ",
            "ㅕ",
            "ㅖ",
            "ㅗ",
            "ㅘ",
            "ㅙ",
            "ㅚ",
            "ㅛ",
            "ㅜ",
            "ㅝ",
            "ㅞ",
            "ㅟ",
            "ㅠ",
            "ㅡ",
            "ㅢ",
            "ㅣ"
        )
        val JONG = arrayOf(
            "",
            "ㄱ",
            "ㄲ",
            "ㄳ",
            "ㄴ",
            "ㄵ",
            "ㄶ",
            "ㄷ",
            "ㄹ",
            "ㄺ",
            "ㄻ",
            "ㄼ",
            "ㄽ",
            "ㄾ",
            "ㄿ",
            "ㅀ",
            "ㅁ",
            "ㅂ",
            "ㅄ",
            "ㅅ",
            "ㅆ",
            "ㅇ",
            "ㅈ",
            "ㅊ",
            "ㅋ",
            "ㅌ",
            "ㅍ",
            "ㅎ"
        )

        var textViewCHO = findViewById<TextView>(R.id.textViewCHO)
        var textViewJOONG = findViewById<TextView>(R.id.textViewJOONG)
        var textViewJONG = findViewById<TextView>(R.id.textViewJONG)
        val button = findViewById<Button>(R.id.button)
        var editText = findViewById<EditText>(R.id.editTextTextPersonName)
        button.setOnClickListener {
            var cho = ((editText.text.toString()[0] - '\uAC00') / 28) / 21
            findViewById<TextView>(R.id.textViewCHO).text = CHO[cho]
            when {
                textViewCHO.text.toString() == "ㄱ" -> {
                    toArduino(0, 4, 1)

                }
                textViewCHO.text.toString() == "ㄲ" -> {
                    toArduino(0, 1, 1)
                    Handler().postDelayed({
                        toArduino(0, 4, 1)
                    }, 1000L)

                }
                textViewCHO.text.toString() == "ㄴ" -> {
                    toArduino(4, 4, 1)

                }
                textViewCHO.text.toString() == "ㄷ" -> {
                    toArduino(2, 4, 1)

                }
                textViewCHO.text.toString() == "ㄸ" -> {
                    toArduino(0, 1, 1)
                    Handler().postDelayed({
                        toArduino(2, 4, 1)
                    }, 1000L)

                }
                textViewCHO.text.toString() == "ㄹ" -> {
                    toArduino(0, 2, 1)

                }
                textViewCHO.text.toString() == "ㅁ" -> {
                    toArduino(4, 2, 1)

                }
                textViewCHO.text.toString() == "ㅂ" -> {
                    toArduino(0, 6, 1)

                }
                textViewCHO.text.toString() == "ㅃ" -> {
                    toArduino(0, 1, 1)
                    Handler().postDelayed({
                        toArduino(0, 6, 1)
                    }, 1000L)

                }
                textViewCHO.text.toString() == "ㅅ" -> {
                    toArduino(0, 1, 1)

                }
                textViewCHO.text.toString() == "ㅆ" -> {
                    toArduino(0, 1, 1)
                    Handler().postDelayed({
                        toArduino(0, 1, 1)
                    }, 1000L)

                }
                textViewCHO.text.toString() == "ㅇ" -> {
                    toArduino(0, 0, 1)

                }
                textViewCHO.text.toString() == "ㅈ" -> {
                    toArduino(0, 5, 1)

                }
                textViewCHO.text.toString() == "ㅉ" -> {
                    toArduino(0, 1, 1)
                    Handler().postDelayed({
                        toArduino(0, 5, 1)
                    }, 1000L)

                }
                textViewCHO.text.toString() == "ㅊ" -> {
                    toArduino(0, 3, 1)

                }
                textViewCHO.text.toString() == "ㅋ" -> {
                    toArduino(6, 4, 1)

                }
                textViewCHO.text.toString() == "ㅌ" -> {
                    toArduino(6, 2, 1)

                }
                textViewCHO.text.toString() == "ㅍ" -> {
                    toArduino(4, 6, 1)

                }
                textViewCHO.text.toString() == "ㅎ" -> {
                    toArduino(2, 6, 1)

                }
                else -> {
//                    toArduino(-1, -1)
                }
            }
            var joong = (editText.text.toString()[0] - '\uAC00') / 28 % 21
            findViewById<TextView>(R.id.textViewJOONG).text = JOONG[joong]
            when {
                textViewJOONG.text.toString() == "ㅏ" -> {
                    toArduino(6, 1, 2)

                }
                textViewJOONG.text.toString() == "ㅐ" -> {
                    toArduino(7, 2, 2)

                }
                textViewJOONG.text.toString() == "ㅑ" -> {
                    toArduino(1, 6, 2)

                }
                textViewJOONG.text.toString() == "ㅒ" -> {
                    toArduino(1, 6, 2)
                    Handler().postDelayed({
                        toArduino(7, 2, 2)
                    }, 1000L)

                }
                textViewJOONG.text.toString() == "ㅓ" -> {
                    toArduino(3, 4, 2)

                }
                textViewJOONG.text.toString() == "ㅔ" -> {
                    toArduino(5, 6, 2)

                }
                textViewJOONG.text.toString() == "ㅕ" -> {
                    toArduino(4, 3, 2)

                }
                textViewJOONG.text.toString() == "ㅖ" -> {
                    toArduino(1, 4, 2)

                }
                textViewJOONG.text.toString() == "ㅗ" -> {
                    toArduino(5, 1, 2)

                }
                textViewJOONG.text.toString() == "ㅘ" -> {
                    toArduino(7, 1, 2)

                }
                textViewJOONG.text.toString() == "ㅙ" -> {
                    toArduino(7, 1, 2)
                    Handler().postDelayed({
                        toArduino(7, 2, 2)
                    }, 1000L)

                }
                textViewJOONG.text.toString() == "ㅚ" -> {
                    toArduino(5, 7, 2)

                }
                textViewJOONG.text.toString() == "ㅛ" -> {
                    toArduino(1, 5, 2)

                }
                textViewJOONG.text.toString() == "ㅜ" -> {
                    toArduino(5, 4, 2)

                }
                textViewJOONG.text.toString() == "ㅝ" -> {
                    toArduino(7, 4, 2)

                }
                textViewJOONG.text.toString() == "ㅞ" -> {
                    toArduino(7, 4, 2)
                    Handler().postDelayed({
                        toArduino(7, 2, 2)
                    }, 1000L)

                }
                textViewJOONG.text.toString() == "ㅟ" -> {
                    toArduino(5, 4, 2)
                    Handler().postDelayed({
                        toArduino(7, 2, 2)
                    }, 1000L)

                }
                textViewJOONG.text.toString() == "ㅠ" -> {
                    toArduino(4, 5, 2)

                }
                textViewJOONG.text.toString() == "ㅡ" -> {
                    toArduino(2, 5, 2)

                }
                textViewJOONG.text.toString() == "ㅢ" -> {
                    toArduino(2, 7, 2)

                }
                textViewJOONG.text.toString() == "ㅣ" -> {
                    toArduino(5, 2, 2)

                }
                else -> {
//                    toArduino(-1, -1)
                }
            }
            var jong = (editText.text.toString()[0] - '\uAC00') % 28
            findViewById<TextView>(R.id.textViewJONG).text = JONG[jong]
            when {
                textViewJONG.text.toString() == "" -> {
                    toArduino(0, 0, 3)

                }
                textViewJONG.text.toString() == "ㄱ" -> {
                    toArduino(4, 0, 3)

                }
                textViewJONG.text.toString() == "ㄲ" -> {
                    toArduino(4, 0, 3)
                    Handler().postDelayed({
                        toArduino(4, 0, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄳ" -> {
                    toArduino(4, 0, 3)
                    Handler().postDelayed({
                        toArduino(1, 0, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄴ" -> {
                    toArduino(2, 2, 3)

                }
                textViewJONG.text.toString() == "ㄵ" -> {
                    toArduino(2, 2, 3)
                    Handler().postDelayed({
                        toArduino(5, 0, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄶ" -> {
                    toArduino(2, 2, 3)
                    Handler().postDelayed({
                        toArduino(1, 3, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄷ" -> {
                    toArduino(1, 2, 3)

                }
                textViewJONG.text.toString() == "ㄹ" -> {
                    toArduino(2, 0, 3)

                }
                textViewJONG.text.toString() == "ㄺ" -> {
                    toArduino(2, 0, 3)
                    Handler().postDelayed({
                        toArduino(4, 0, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄻ" -> {
                    toArduino(2, 0, 3)
                    Handler().postDelayed({
                        toArduino(2, 1, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄼ" -> {
                    toArduino(2, 0, 3)
                    Handler().postDelayed({
                        toArduino(6, 0, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄽ" -> {
                    toArduino(2, 0, 3)
                    Handler().postDelayed({
                        toArduino(1, 0, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄾ" -> {
                    toArduino(2, 0, 3)
                    Handler().postDelayed({
                        toArduino(3, 1, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㄿ" -> {
                    toArduino(2, 0, 3)
                    Handler().postDelayed({
                        toArduino(2, 3, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㅀ" -> {
                    toArduino(2, 0, 3)
                    Handler().postDelayed({
                        toArduino(1, 3, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㅁ" -> {
                    toArduino(2, 1, 3)

                }
                textViewJONG.text.toString() == "ㅂ" -> {
                    toArduino(6, 0, 3)

                }
                textViewJONG.text.toString() == "ㅄ" -> {
                    toArduino(6, 0, 3)
                    Handler().postDelayed({
                        toArduino(1, 0, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㅅ" -> {
                    toArduino(1, 0, 3)

                }
                textViewJONG.text.toString() == "ㅆ" -> {
                    toArduino(1, 0, 3)
                    Handler().postDelayed({
                        toArduino(1, 0, 3)
                    }, 1000L)

                }
                textViewJONG.text.toString() == "ㅇ" -> {
                    toArduino(3, 3, 3)

                }
                textViewJONG.text.toString() == "ㅈ" -> {
                    toArduino(5, 0, 3)

                }
                textViewJONG.text.toString() == "ㅊ" -> {
                    toArduino(3, 0, 3)

                }
                textViewJONG.text.toString() == "ㅋ" -> {
                    toArduino(3, 2, 3)

                }
                textViewJONG.text.toString() == "ㅌ" -> {
                    toArduino(3, 1, 3)

                }
                textViewJONG.text.toString() == "ㅍ" -> {
                    toArduino(2, 3, 3)

                }
                textViewJONG.text.toString() == "ㅎ" -> {
                    toArduino(1, 3, 3)

                }
                else -> {
//                    toArduino(-1, -1)
                }
            }

        }
    }

    private fun toArduino(first: Int, second: Int, pos: Int) {
        if (pos == 1) {
            findViewById<TextView>(R.id.textViewCHO).text = first.toString() + second.toString()

        } else if (pos == 2) {
            findViewById<TextView>(R.id.textViewJOONG).text = first.toString() + second.toString()

        } else if (pos == 3) {
            findViewById<TextView>(R.id.textViewJONG).text = first.toString() + second.toString()
        }

    }

}