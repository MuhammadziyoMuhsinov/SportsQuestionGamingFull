package uz.muhammadziyo.sportsquestiongamingfull

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.muhammadziyo.sportsquestiongamingfull.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}