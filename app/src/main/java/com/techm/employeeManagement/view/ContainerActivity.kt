package com.techm.employeeManagement.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import com.techm.employeeManagement.R

import kotlinx.android.synthetic.main.activity_main.*
/**
 * This class is for handling multiple fragment
 * */
class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}
