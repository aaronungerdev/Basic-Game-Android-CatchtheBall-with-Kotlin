package com.unger.catchtheball

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.unger.catchtheball.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var score=0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable{}
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ArrayList
        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView13)
        imageArray.add(binding.imageView14)
        imageArray.add(binding.imageView15)
        imageArray.add(binding.imageView16)
        imageArray.add(binding.imageView17)
        imageArray.add(binding.imageView18)
        imageArray.add(binding.imageView19)
        imageArray.add(binding.imageView20)
        imageArray.add(binding.imageView21)
        imageArray.add(binding.imageView22)
        imageArray.add(binding.imageView23)
        imageArray.add(binding.imageView24)

        hideImages()

        // CountdownTimer
        object : CountDownTimer(30500,1000){
            override fun onTick(p0: Long) {
                binding.txtTime.text=   " Time: ${p0/1000} "
            }

            override fun onFinish() {
                binding.txtTime.text= " Time: 0 "

                handler.removeCallbacks(runnable)

                for(image in imageArray){
                    image.visibility= View.INVISIBLE}

                //AlertDialog
                val alertDialog= AlertDialog.Builder(this@MainActivity)
                alertDialog.setTitle("Time Over!")
                alertDialog.setMessage("Restart to Game?")
                alertDialog.setPositiveButton("Yes",DialogInterface.OnClickListener{dialogInterface,i ->
                    //restart
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)

                })
                alertDialog.setNegativeButton("No",DialogInterface.OnClickListener() {dialogInterface,i ->
                    Toast.makeText(this@MainActivity,"Game Over!",Toast.LENGTH_LONG).show()
                })
                alertDialog.show()
            }

        }.start()

    }

    fun hideImages(){

        runnable = object : Runnable{
            override fun run() {

                for(image in imageArray){
                    image.visibility= View.INVISIBLE}

                val random = Random()
                val randomIndex=random.nextInt(24)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View){

        score+=1
        binding.txtScore.text= "Score: ${score}"

    }


}