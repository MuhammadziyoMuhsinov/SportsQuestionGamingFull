package uz.muhammadziyo.sportsquestiongamingfull.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.muhammadziyo.sportsquestiongamingfull.databinding.ItemRvBinding
import uz.muhammadziyo.sportsquestiongamingfull.models.Game

class RvAdapter(var list: List<Game>) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(user: Game) {
            itemRv.correct.text = "correct answer: ${user.correct}"
            itemRv.wrong.text = "wrong answer: ${user.wrong}"
            itemRv.task.text = "${user.step} - step"
            itemRv.date.text = user.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}