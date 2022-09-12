package hr.tvz.android.zavrsniprojekt.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import hr.tvz.android.zavrsniprojekt.R
import hr.tvz.android.zavrsniprojekt.databinding.RecyclerViewRowBinding


class RecyclerViewHolder(binding: RecyclerViewRowBinding, private var rec: RecyclerViewInterface) : RecyclerView.ViewHolder(binding.root) {

    var imgLogoView: ImageView = binding.logoRow
    var imgPicView: ImageView = binding.pictureRow
    var txtName: TextView = binding.nameRow
    var txtSubs: TextView = binding.gameRow

    init {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                rec.onItemClick(adapterPosition)
            }

            YoYo.with(Techniques.FadeIn).duration(2000).repeat(0).playOn(it)
        }
    }
}