package com.example.dndtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

data class Character(
    var id: String,
    var characterName: String,
    val playerName: String,
    var maxHealth: Int,
    var currentHealth: Int,
    var initiative: Int,
    var armourClass: Int,
    var speed: Int,
    var level: Int,
    val npc: Boolean
) : Serializable

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    var characters = mutableListOf<Character>(
        Character("0", "Fjord", "Travis", 112, 112, 0, 17, 30, 10, false),
        Character("1", "Jester", "Laura", 92, 92, 4, 18, 30, 10, false),
        Character("2", "Yasha Nydoorin", "Ashley", 112, 112, 2, 17, 40, 10, false),
        Character("3","Beauregard", "Marisha", 85, 85, 5, 20, 50, 10, false),
        Character("456", "Caleb", "Liam", 65, 65, 1, 15, 30, 10, false),
        Character("7","Caduceus", "Taliesin", 87, 87, 1, 18, 30, 10, false),
        Character("8", "Nott the Brave", "Sam", 81, 81, 5, 18, 35, 10, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: CharacterAdapter = CharacterAdapter(characters, baseContext)

        character_list.adapter = adapter
        character_list.setOnItemClickListener{parent, view, position, id ->
            val character = adapter.getItem(position)
            val intent = Intent(this, CharacterSettingsActivity::class.java)
            intent.putExtra("CHARACTER", character)
            startActivity(intent)
        }
    }
}