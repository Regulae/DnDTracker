package com.example.dndtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CharacterSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_settings)
        val editCharactersIntent = intent
        val character: Character =
            editCharactersIntent.getSerializableExtra("CHARACTER") as Character

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

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener { v ->

            //if EditText left empty, set value to 0
            val newInitiative: Int
            if (initiative.text.toString() == "") {
                newInitiative = 0
            } else {
                newInitiative = initiative.text.toString().toInt()
            }
            val newArmourClass: Int
            if (armourClass.text.toString() == "") {
                newArmourClass = 0
            } else {
                newArmourClass = armourClass.text.toString().toInt()
            }
            val newCurrentHP: Int
            if (currentHP.text.toString() == "") {
                newCurrentHP = 0
            } else {
                newCurrentHP = currentHP.text.toString().toInt()
            }
            val newMaxHP: Int
            if (maxHP.text.toString() == "") {
                newMaxHP = 0
            } else {
                newMaxHP = maxHP.text.toString().toInt()
            }
            val newSpeed: Int
            if (speed.text.toString() == "") {
                newSpeed = 0
            } else {
                newSpeed = speed.text.toString().toInt()
            }
            val newLevel: Int
            if (level.text.toString() == "") {
                newLevel = 0
            } else {
                newLevel = level.text.toString().toInt()
            }

            val updatedCharacter = Character(
                id = character.id,
                characterName = character.characterName,
                playerName = character.playerName,
                initiative = newInitiative,
                armourClass = newArmourClass,
                currentHealth = newCurrentHP,
                maxHealth = newMaxHP,
                speed = newSpeed,
                level = newLevel,
                npc = character.npc
            )

            editCharactersIntent.putExtra("UPDATED CHARACTER", updatedCharacter)
            setResult(RESULT_OK, editCharactersIntent)
            finish()
        }

        val deleteButton: Button = findViewById(R.id.deleteButton)
        deleteButton.setOnClickListener { v ->
            val updatedCharacter = Character(
                id = character.id,
                characterName = "",
                playerName = "",
                initiative = 0,
                armourClass = 0,
                currentHealth = 0,
                maxHealth = 0,
                speed = 0,
                level = 0,
                npc = false
            )
            editCharactersIntent.putExtra("UPDATED CHARACTER", updatedCharacter)
            setResult(RESULT_OK, editCharactersIntent)
            finish()
        }
    }
}