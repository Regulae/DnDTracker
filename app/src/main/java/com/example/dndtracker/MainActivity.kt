package com.example.dndtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class Character(
    val characterName: String,
    val playerName: String,
    var maxHealth: Short,
    var currentHealth: Short,
    var initative: Short,
    var armourClass: Short,
    var speed: Short,
    var level: Short,
    val npc: Boolean
) {}

class MainActivity : AppCompatActivity() {

    var characters = mutableListOf<Character>(
        Character("Fjord", "Travis", 112, 112, 0, 17, 30, 10, false),
        Character("Jester", "Laura", 92, 92, 4, 18, 30, 10, false),
        Character("Yasha Nydoorin", "Ashley", 112, 112, 2, 17, 40, 10, false),
        Character("Beauregard", "Marisha", 85, 85, 5, 20, 50, 10, false),
        Character("Caleb", "Liam", 65, 65, 1, 15, 30, 10, false),
        Character("Caduceus", "Taliesin", 87, 87, 1, 18, 30, 10, false),
        Character("Nott the Brave", "Sam", 81, 81, 5, 18, 35, 10, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: CharacterAdapter = CharacterAdapter(characters, baseContext)

        character_list.adapter = adapter
    }
}