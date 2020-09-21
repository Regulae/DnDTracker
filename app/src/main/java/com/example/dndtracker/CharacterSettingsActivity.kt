package com.example.dndtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class CharacterSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_settings)
        val editCharactersIntent = intent
        val character: Character = editCharactersIntent.getSerializableExtra("CHARACTER") as Character

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
            val updatedCharacter = Character(
                id = character.id,
                characterName = character.characterName,
                playerName = character.playerName,
                initiative = initiative.text.toString().toInt(),
                armourClass = armourClass.text.toString().toInt(),
                currentHealth = currentHP.text.toString().toInt(),
                maxHealth = maxHP.text.toString().toInt(),
                speed = speed.text.toString().toInt(),
                level = level.text.toString().toInt(),
                npc = character.npc
            )

            Toast.makeText(v.getContext(), updatedCharacter.toString(), Toast.LENGTH_LONG).show()
            val updateCharactersIntent = Intent(this, MainActivity::class.java)
            editCharactersIntent.putExtra("UPDATED CHARACTER", updatedCharacter)
            setResult(RESULT_OK, editCharactersIntent)
            finish()
        }
    }
}