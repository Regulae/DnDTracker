package com.example.dndtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
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
) : Serializable {var characterList : MutableList<Character>? = null}

class Fight(val sequence : Array<String>, val characters: MutableList<Character>, val lastUpdate: Int, val id: String){};

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CharacterAdapter
    lateinit var characters: MutableList<Character>

//    var characters = mutableListOf<Character>(
//        Character("0", "Fjord", "Travis", 112, 112, 0, 17, 30, 10, false),
//        Character("1", "Jester", "Laura", 92, 92, 4, 18, 30, 10, false),
//        Character("2", "Yasha Nydoorin", "Ashley", 112, 112, 2, 17, 40, 10, false),
//        Character("3","Beauregard", "Marisha", 85, 85, 5, 20, 50, 10, false),
//        Character("456", "Caleb", "Liam", 65, 65, 1, 15, 30, 10, false),
//        Character("7","Caduceus", "Taliesin", 87, 87, 1, 18, 30, 10, false),
//        Character("8", "Nott the Brave", "Sam", 81, 81, 5, 18, 35, 10, false)
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestQueue = Volley.newRequestQueue(this)
        val ENDPOINT = "https://regula-zhaw.heisch.ch/initiative-tracker/"
        val request = StringRequest(
            Request.Method.GET, ENDPOINT,
            { response ->
                val characters_ = Klaxon().parse<Fight>(response)
//                characters = characters_?.characterList!!
//                characters.sortByDescending { it.initiative }
                adapter = CharacterAdapter(characters_?.characters!!, baseContext)
                character_list.adapter = adapter
            },
            { Log.e("VOLLEYERROR", it.message.toString()) }
        )
        requestQueue.add(request)

        character_list.setOnItemClickListener{parent, view, position, id ->
            val character = adapter.getItem(position)
            val intent = Intent(this, CharacterSettingsActivity::class.java)
            intent.putExtra("CHARACTER", character)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.next_player -> {
                // change order of players
                val finishedPlayer: Character = characters[0]
                characters.removeAt(0)
                characters.add(finishedPlayer)
                adapter.notifyDataSetChanged()
                true
            }
            R.id.end_fight -> {
                // clear initiative and remove npcs
                true
            }
            R.id.long_rest -> {
                // clear initiative, set currentHp to maxHP

                true
            }
            R.id.add_player -> {
                // add new player
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}