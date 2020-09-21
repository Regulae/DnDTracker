package com.example.dndtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.character_cell.view.*
import org.json.JSONException
import org.json.JSONObject

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
            val outputJson = JSONObject()
            try {
                outputJson.put("id", character.id)
                outputJson.put("characterName", character.characterName)
                outputJson.put("playerName", character.playerName)
                outputJson.put("maxHealth", maxHP.text.toString().toInt())
                outputJson.put("currentHealth", currentHP.text.toString().toInt())
                outputJson.put("initiative", initiative.text.toString().toInt())
                outputJson.put("armourClass", armourClass.text.toString().toInt())
                outputJson.put("speed", speed.text.toString().toInt())
                outputJson.put("level", level.text.toString().toInt())
                outputJson.put("npc", false)
            } catch (e: JSONException) {
                Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_LONG).show()
            }

            val requestQueue = Volley.newRequestQueue(this)
            val ENDPOINT = "https://regula-zhaw.heisch.ch/initiative-tracker/character"
            val putRequest = JsonObjectRequest(
                Request.Method.PUT,
                ENDPOINT,
                outputJson,
                { response ->
                    Toast.makeText(
                        v.getContext(),
                        "PUT",
                        Toast.LENGTH_LONG
                    ).show()
                },
                { error -> Log.e("ERROR", error.message.toString()) }
            )
            requestQueue.add(putRequest)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}