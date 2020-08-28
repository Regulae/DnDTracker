package com.example.dndtracker

import android.content.Context
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

    override fun getView(index: Int, oldView: View?, viewGroup: ViewGroup?): View {
        var view: View
        if (oldView == null) { //check if we get a view to recycle
            view = layoutInflater.inflate(R.layout.character_cell, null)
        } else { //if yes, use the oldview
            view = oldView
        }
        val character =
            getItem(index) //get the data for this index
        view.characterName.text = character.characterName
        view.playerName.text = character.playerName

        return view
    }

}