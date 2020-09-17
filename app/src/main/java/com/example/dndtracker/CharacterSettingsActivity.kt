package com.example.dndtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.character_cell.view.*

class CharacterSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_settings)
        val intent = intent
        val character: Character = intent.getSerializableExtra("CHARACTER") as Character

        //set initial values
        val title: TextView = findViewById(R.id.title)
        title.setText(character.characterName)
        val playerName: TextView = findViewById(R.id.playerName)
        playerName.setText(character.playerName)
        val initiative: EditText = findViewById(R.id.editInitiative)
        initiative.setText(character.initiative.toString())
        val armourClass: EditText = findViewById(R.id.editArmourClass)
        armourClass.setText(character.armourClass.toString())
        val currentHP: EditText = findViewById(R.id.editCurrentHP)
        currentHP.setText(character.currentHealth.toString())
        val maxHP: EditText = findViewById(R.id.editMaxHP)
        maxHP.setText(character.maxHealth.toString())
        val speed: EditText = findViewById(R.id.editSpeed)
        speed.setText(character.speed.toString())
        val level: EditText = findViewById(R.id.editLevel)
        level.setText(character.level.toString())

        val button: Button = findViewById(R.id.saveButton)
        button.setOnClickListener { v ->
            Toast.makeText(v.getContext(), "Save clicked!", Toast.LENGTH_LONG).show()
        }
    }
}