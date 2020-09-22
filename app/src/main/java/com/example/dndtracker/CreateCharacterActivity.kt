package com.example.dndtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.util.*

class CreateCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        val createCharacterIntent = intent

        val characterName: EditText = findViewById(R.id.editCharacterName)
        val playerName: EditText = findViewById(R.id.editPlayerName)
        val initiative: EditText = findViewById(R.id.editInitiative)
        val armourClass: EditText = findViewById(R.id.editArmourClass)
        val currentHP: EditText = findViewById(R.id.editCurrentHP)
        val maxHP: EditText = findViewById(R.id.editMaxHP)
        val speed: EditText = findViewById(R.id.editSpeed)
        val level: EditText = findViewById(R.id.editLevel)
        val npc: CheckBox = findViewById(R.id.npcCheckBox)


        val button: Button = findViewById(R.id.createButton)
        button.setOnClickListener { v ->

            //if EditText left empty, set value to 0
            val newInitiative : Int
            if(initiative.text.toString() == ""){
                newInitiative = 0
            } else {
                newInitiative = initiative.text.toString().toInt()
            }
            val newArmourClass : Int
            if(armourClass.text.toString() == ""){
                newArmourClass = 0
            } else {
                newArmourClass = initiative.text.toString().toInt()
            }
            val newCurrentHP : Int
            if(currentHP.text.toString() == ""){
                newCurrentHP = 0
            } else {
                newCurrentHP = currentHP.text.toString().toInt()
            }
            val newMaxHP : Int
            if(maxHP.text.toString() == ""){
                newMaxHP = 0
            } else {
                newMaxHP = maxHP.text.toString().toInt()
            }
            val newSpeed : Int
            if(speed.text.toString() == ""){
                newSpeed = 0
            } else {
                newSpeed = speed.text.toString().toInt()
            }
            val newLevel : Int
            if(level.text.toString() == ""){
                newLevel = 0
            } else {
                newLevel = level.text.toString().toInt()
            }

            val createdCharacter = Character(
                id = UUID.randomUUID().toString(),
                characterName = characterName.text.toString(),
                playerName = playerName.text.toString(),
                initiative = newInitiative,
                armourClass = newArmourClass,
                currentHealth = newCurrentHP,
                maxHealth = newMaxHP,
                speed = newSpeed,
                level = newLevel,
                npc = npc.isChecked
            )

            Toast.makeText(v.getContext(), createdCharacter.toString(), Toast.LENGTH_LONG).show()
//            val updateCharactersIntent = Intent(this, MainActivity::class.java)
            createCharacterIntent.putExtra("CREATED CHARACTER", createdCharacter)
            setResult(RESULT_OK, createCharacterIntent)
            finish()
        }
    }
}