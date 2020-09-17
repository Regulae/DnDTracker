package com.example.dndtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.character_cell.view.*

class CharacterSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_settings)
        val intent = intent
        val character : Character = intent.getSerializableExtra("CHARACTER") as Character
        val title: TextView = findViewById<TextView>(R.id.title)
        title.setText(character.characterName)
    }
}