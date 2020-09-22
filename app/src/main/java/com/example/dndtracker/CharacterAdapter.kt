package com.example.dndtracker

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.character_cell.view.*

class CharacterAdapter(var characters: MutableList<Character>, val context: Context) : BaseAdapter() {
    var layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int { //number of elements to display
        return characters.size
    }

    override fun getItem(index: Int): Character { //item at index
        return characters.get(index)
    }

    override fun getItemId(index: Int): Long { //itemId for index
        return index.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(index: Int, oldView: View?, viewGroup: ViewGroup?): View {
        val view: View
        if (oldView == null) { //check if we get a view to recycle
            view = layoutInflater.inflate(R.layout.character_cell, null)
        } else { //if yes, use the oldview
            view = oldView
        }
        val character =
            getItem(index) //get the data for this index
        view.characterName.text = character.characterName
        val initiative : String = "Initiative\n" + character.initiative
        val ssInitiative: SpannableString = SpannableString(initiative)
        ssInitiative.setSpan(RelativeSizeSpan(1.5f), 11, ssInitiative.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        view.initiative.text = ssInitiative
        val armourClass : String = "AC\n" + character.armourClass
        val ssArmourClass: SpannableString = SpannableString(armourClass)
        ssArmourClass.setSpan(RelativeSizeSpan(1.5f), 3, ssArmourClass.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        view.armourClass.text = ssArmourClass
        val health : String = "HP\n" + character.currentHealth + "/" + character.maxHealth
        val ssHealth: SpannableString = SpannableString(health)
        ssHealth.setSpan(RelativeSizeSpan(1.5f), 3, ssHealth.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        view.health.text = ssHealth

        return view
    }

}