@file:Suppress("UNUSED_EXPRESSION")

package com.ksivanova.popytochka

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ksivanova.popytochka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = CharactersAdapter()

    private val imageIdList = listOf(
        R.drawable.c1,
        R.drawable.c2,
        R.drawable.c3,
        R.drawable.c4,
        R.drawable.c5,
        R.drawable.c6,
        R.drawable.c7,
        R.drawable.c8,
        R.drawable.c9,
        R.drawable.c10,
        R.drawable.c11,
        R.drawable.c12,
        R.drawable.c13,
        R.drawable.c14,
        R.drawable.c15,
        R.drawable.c16,
        R.drawable.c17,
        R.drawable.c18,
        R.drawable.c19,
        R.drawable.c20,
    )

    private val namesList = listOf(
        "Rick Sanchez",
        "Morty Smith",
        "Summer Smith",
        "Beth Smith",
        "Jerry Smith",
        "Abadango Cluster Princess",
        "Abradolf Lincler",
        "Adjudicator Rick",
        "Agency Director",
        "Alan Rails",
        "Albert Einstein",
        "Alexander",
        "Alien Googah",
        "Alien Morty",
        "Alien Rick",
        "Amish Cyborg",
        "Annie",
        "Antenna Morty",
        "Antenna Rick",
        "Ants in my Eyes Johnson",
    )

    private val lowerCaseNames = namesList.map { it.lowercase() }

    private val speciesList = listOf(
        "Human",
        "Human",
        "Human",
        "Human",
        "Human",
        "Alien",
        "Human",
        "Human",
        "Human",
        "Human",
        "Human",
        "Human",
        "Alien",
        "Alien",
        "Alien",
        "Alien",
        "Human",
        "Human",
        "Human",
        "Human",
    )

    private val statusList = listOf(
        "Alive",
        "Alive",
        "Alive",
        "Alive",
        "Alive",
        "Alive",
        "unknown",
        "Dead",
        "Dead",
        "Dead",
        "Dead",
        "Dead",
        "unknown",
        "unknown",
        "unknown",
        "Dead",
        "Alive",
        "Alive",
        "unknown",
        "unknown",
    )

    private val gengerList = listOf(
        "Male",
        "Male",
        "Female",
        "Female",
        "Male",
        "Female",
        "Male",
        "Male",
        "Male",
        "Male",
        "Male",
        "Male",
        "unknown",
        "Male",
        "Male",
        "Male",
        "Female",
        "Male",
        "Male",
        "Male",
        )

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initSV()
    }

    fun initSV(){
        binding.apply {
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(text: String?): Boolean {
                    val newText = text?.lowercase()
                    buttonShow.isVisible = false
                    binding.rcView.smoothScrollToPosition(0);
                    var prov = false
                    for(i in 0..(lowerCaseNames.size - 1)){
                        if (newText?.let { lowerCaseNames[i].contains(it) } == true) prov = true
                    }
                    if (prov == true){
                        if (newText != null) {
                            adapter.upCharacters(newText)
                        }
                    }
                    else {
                            Toast.makeText(this@MainActivity, "No Match found", Toast.LENGTH_LONG).show()
                        }
                        Log.d("MyLog", "New Text : $newText ")
                    return true
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    buttonShow.isVisible = false
                    binding.rcView.smoothScrollToPosition(0);
                    val newText = text?.lowercase()
                    var prov = false
                    for(i in 0..(lowerCaseNames.size - 1)){
                        if (newText?.let { lowerCaseNames[i].contains(it) } == true) prov = true
                    }
                    if (prov == true){
                        if (newText != null) {
                            adapter.upCharacters(newText)
                        }
                    }
                    return true
                }
            })
        }
    }

    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
            searchView.isVisible = false
            buttonShow.setOnClickListener {
                while (index < 20) {
                    val characters = Characters(
                        imageIdList[index],
                        namesList[index],
                        "Species: " + speciesList[index],
                        "Gender: " + gengerList[index],
                        "Status: " + statusList[index]
                    )
                    adapter.addCharacters(characters)
                    index++
                }
                if(index == 20){
                    buttonShow.isVisible = false
                    searchView.isVisible = true
                }
            }
        }
    }
}