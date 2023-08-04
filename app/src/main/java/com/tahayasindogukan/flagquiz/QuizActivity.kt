package com.tahayasindogukan.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tahayasindogukan.flagquiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogruSoru:Bayraklar
    private lateinit var tumSecenekler:HashSet<Bayraklar>
    private lateinit var vt:VeritabaniYardimcisi


    private var soruSayac = 0 // bunu bir fazla yapıp görüntülicez
    // çünkü soru sayaçtan gelen indekse göre
    // arraylistten alıcaz indeksde 0 dan başlıyor
    private var dogruSayac = 0
    private var yanlisSayac = 0

//val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vt = VeritabaniYardimcisi(this)

        sorular = Bayraklardao().rasgele5BayrakGetir(vt)

        soruYukle()

        binding.buttonA.setOnClickListener {
            dogruKontrol(binding.buttonA)
            soruSayacKontrol()
        }
        binding.buttonB.setOnClickListener {
            dogruKontrol(binding.buttonB)
            soruSayacKontrol()
        }
        binding.buttonC.setOnClickListener {
            dogruKontrol(binding.buttonC)
            soruSayacKontrol()
        }
        binding.buttonD.setOnClickListener {
            dogruKontrol(binding.buttonD)
            soruSayacKontrol()
        }



        binding.buttonA.setOnClickListener {
            startActivity(Intent(this@QuizActivity,ResultActivitiy::class.java))
            finish()
        }

    }


    private fun soruYukle(){


        dogruSoru= sorular.get(soruSayac)
        binding.imageViewBayrak.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))

        yanlisSecenekler=Bayraklardao().rasgele3YanlisSecenekGetir(vt,dogruSoru.bayrak_id)

        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler[0])
        tumSecenekler.add(yanlisSecenekler[1])
        tumSecenekler.add(yanlisSecenekler[2])


        binding.buttonA.text = tumSecenekler.elementAt(0).bayrak_ad
        binding.buttonB.text = tumSecenekler.elementAt(1).bayrak_ad
        binding.buttonC.text = tumSecenekler.elementAt(2).bayrak_ad
        binding.buttonD.text = tumSecenekler.elementAt(3).bayrak_ad

        binding.textViewSoruSayisi.text="${soruSayac+1}. Soru"
    }
        private fun soruSayacKontrol(){
            soruSayac++

            if (soruSayac!=5){
                soruYukle()
            }else{
                val intent =Intent(this,ResultActivitiy::class.java)
                intent.putExtra("dogruSayac",dogruSayac)
                startActivity(intent)
                finish()
            }
        }


        private fun dogruKontrol(button:Button){
            val buttonYazi=button.text.toString()
            val dogruCevap = dogruSoru.bayrak_ad

            if (buttonYazi==dogruCevap){
                dogruSayac++
            }else {
                yanlisSayac++
            }
            binding.textViewDogru.text="DOĞRU : $dogruSayac"
            binding.textViewYanlis.text="YANLIŞ : $yanlisSayac"
        }

}