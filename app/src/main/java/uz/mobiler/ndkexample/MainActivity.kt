package uz.mobiler.ndkexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import uz.mobiler.ndkexample.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var result: Int? = null
    private var myResult: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRandomNumbers()

        binding.button.setOnClickListener {
            if (binding.edit.text.toString().isNotEmpty()) {
                myResult = binding.edit.text.toString().toInt()
                if (result == myResult) {
                    Toast.makeText(this, "To'g'ri", Toast.LENGTH_SHORT).show()
                    setRandomNumbers()
                } else {
                    Toast.makeText(this, "Noto'g'ri", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Javob kiritilmagan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setRandomNumbers() {
        val random = Random()
        val a = random.nextInt(100)
        val b = random.nextInt(100)
        val c = random.nextInt(4)
        binding.number1.text = "$a"
        binding.number3.text = "$b"
        when (c) {
            0 -> {
                binding.number2.text = "+"
                result = add(a, b)
            }
            1 -> {
                binding.number2.text = "-"
                result = sub(a, b)
            }
            2 -> {
                binding.number2.text = "*"
                result = mult(a, b)
            }
            else -> {
                binding.number2.text = "/"
                result = div(a, b)
            }
        }
    }

    /**
     * A native method that is implemented by the 'ndkexample' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    external fun pdpOnlineJNI(): String
    external fun add(a: Int, b: Int): Int
    external fun sub(a: Int, b: Int): Int
    external fun mult(a: Int, b: Int): Int
    external fun div(a: Int, b: Int): Int

    companion object {
        // Used to load the 'ndkexample' library on application startup.
        init {
            System.loadLibrary("ndkexample")
        }
    }
}