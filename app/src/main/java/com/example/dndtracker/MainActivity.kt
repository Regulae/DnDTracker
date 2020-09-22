package com.example.dndtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.util.*

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

    lateinit var adapter: CharacterAdapter

    var characters = mutableListOf<Character>(
        Character(UUID.randomUUID().toString(), "Fjord", "Travis", 112, 112, 0, 17, 30, 10, false),
        Character(UUID.randomUUID().toString(), "Jester", "Laura", 92, 92, 0, 18, 30, 10, false),
        Character(
            UUID.randomUUID().toString(),
            "Yasha Nydoorin",
            "Ashley",
            112,
            112,
            0,
            17,
            40,
            10,
            false
        ),
        Character(
            UUID.randomUUID().toString(),
            "Beauregard",
            "Marisha",
            85,
            85,
            0,
            20,
            50,
            10,
            false
        ),
        Character(UUID.randomUUID().toString(), "Caleb", "Liam", 65, 65, 0, 15, 30, 10, false),
        Character(
            UUID.randomUUID().toString(),
            "Caduceus",
            "Taliesin",
            87,
            87,
            0,
            18,
            30,
            10,
            false
        ),
        Character(
            UUID.randomUUID().toString(),
            "Nott the Brave",
            "Sam",
            81,
            81,
            0,
            18,
            35,
            10,
            false
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characters.sortByDescending { it.initiative }

        adapter = CharacterAdapter(characters, baseContext)

        character_list.adapter = adapter
        character_list.setOnItemClickListener { _, _, position, _ ->
            val character = adapter.getItem(position)
            val editCharactersIntent = Intent(this, CharacterSettingsActivity::class.java)
            editCharactersIntent.putExtra("CHARACTER", character)
            startActivityForResult(editCharactersIntent, 1);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val updatedCharacter = data!!.getSerializableExtra("UPDATED CHARACTER") as Character
                val iterate = characters.listIterator()
                while (iterate.hasNext()) {
                    val oldCharacter = iterate.next()
                    if (oldCharacter.id == updatedCharacter.id) iterate.set(updatedCharacter)
                }
                characters.sortByDescending { it.initiative }
                adapter.notifyDataSetChanged()
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                val createdCharacter = data!!.getSerializableExtra("CREATED CHARACTER") as Character
                characters.add(createdCharacter)
                characters.sortByDescending { it.initiative }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.next_player -> {
                // change order of players
                val finishedPlayer: Character = characters[0]
                characters.removeAt(0)
                characters.add(finishedPlayer)
                adapter.notifyDataSetChanged()
                true
            }
            R.id.end_fight -> {
                // set initiative to 0 and remove npcs
                val iterate = characters.listIterator()
                while (iterate.hasNext()) {
                    val character = iterate.next()
                    character.initiative = 0
                    if (character.npc) {
                        iterate.remove()
                    }
                }
                characters.sortByDescending { it.initiative }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.long_rest -> {
                // set initiative to 0 and set currentHp to maxHP
                val iterate = characters.listIterator()
                while (iterate.hasNext()) {
                    val character = iterate.next()
                    character.initiative = 0
                    character.currentHealth = character.maxHealth
                }
                characters.sortByDescending { it.initiative }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.add_player -> {
                val createCharacterIntent = Intent(this, CreateCharacterActivity::class.java)
                startActivityForResult(createCharacterIntent, 2);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}