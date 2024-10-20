package com.example.pr19

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if(currentFragment == null)
            {
               val fragment = CrimeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit()
            }
        }
    }
    


