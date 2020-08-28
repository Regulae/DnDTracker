package com.example.dndtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class Character(val characterName: String, val playerName: String, val maxHealth: Short, var currentHealth: Short, var initative: Short) {}

class MainActivity : AppCompatActivity() {

    var characters = mutableListOf<Character>(
            Character("Fjord", "Travis", 100, 100, 20),
            Character("Jester", "Laura", 80, 80, 15)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: CharacterAdapter = CharacterAdapter(characters, baseContext)

        character_list.adapter = adapter
    }
}