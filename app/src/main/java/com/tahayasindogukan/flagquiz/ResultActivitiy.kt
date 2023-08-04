package com.tahayasindogukan.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahayasindogukan.flagquiz.databinding.ActivityResultActivitiyBinding

class ResultActivitiy : AppCompatActivity() {

    private lateinit var binding: ActivityResultActivitiyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultActivitiyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogruSayac = intent.getIntExtra("dogruSayac",0)

        binding.textViewSonuc.text="$dogruSayac DOĞRU ${5-dogruSayac} YANLIŞ"

        binding.textViewYuzdeSonuc.text="% ${dogruSayac*100/5} Başarı"

        binding.buttonTekrar.setOnClickListener {
            startActivity(Intent(this@ResultActivitiy,MainActivity::class.java))
            finish()
        }

    }
}