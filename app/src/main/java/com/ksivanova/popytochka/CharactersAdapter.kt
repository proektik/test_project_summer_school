package com.ksivanova.popytochka

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksivanova.popytochka.databinding.CharactersItemBinding

class CharactersAdapter: RecyclerView.Adapter<CharactersAdapter.CharactersHolder>() {
    var charactersList = ArrayList<Characters>()
    class CharactersHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = CharactersItemBinding.bind(item)
        fun bind(characters: Characters) = with(binding){
            im.setImageResource(characters.imageId)
            tvName.text = characters.characterName
            tvSpecies.text = characters.species
            tvGender.text = characters.gender
            tvStatus.text = characters.characterStatus
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.characters_item, parent, false)
        return CharactersHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersHolder, position: Int) {
        holder.bind(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    fun addCharacters(characters: Characters){
        charactersList.add(characters)
        notifyDataSetChanged()
    }

    fun removeCharacters(index: Int){
        var c = charactersList[index]
        charactersList.remove(c)
        charactersList.add(c)
        notifyDataSetChanged()
    }

    fun upCharacters(text: String){
        val newText = text.lowercase()
        for (i in 0..(charactersList.size - 1)){
            for (j in i..(charactersList.size - 1)) {
                val name1 = charactersList[i].characterName.lowercase()
                val name2 = charactersList[j].characterName.lowercase()
                if(name1.contains(newText) < name2.contains(newText)){
                    val temp = charactersList[i]
                    charactersList[i] = charactersList[j]
                    charactersList[j] = temp
                }
            }
        }
        notifyDataSetChanged()
    }
}