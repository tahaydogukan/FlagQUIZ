package com.tahayasindogukan.flagquiz
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import com.tahayasindogukan.flagquiz.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        veritabiKopyala()

        binding.buttonBasla.setOnClickListener {
            val intent = Intent(this,QuizActivity::class.java)
            startActivity(intent)

        }


    }

    fun veritabiKopyala(){
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()

        }catch(e:Exception){
            e.printStackTrace()
        }
    }

}