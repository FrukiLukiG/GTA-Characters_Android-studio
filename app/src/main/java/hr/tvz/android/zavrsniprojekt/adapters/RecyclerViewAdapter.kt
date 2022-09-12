package hr.tvz.android.zavrsniprojekt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.zavrsniprojekt.databinding.RecyclerViewRowBinding
import hr.tvz.android.zavrsniprojekt.models.Character

class RecyclerViewAdapter(private var characters: ArrayList<Character>,
                          private var recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding, recyclerViewInterface)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.txtName.text = characters[position].name
        holder.txtSubs.text = characters[position].game
        holder.imgPicView.setImageResource(characters[position].pic)
        holder.imgLogoView.setImageResource(characters[position].logo)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun insertData(character: Character){
        this.characters.add(character)
    }

}
