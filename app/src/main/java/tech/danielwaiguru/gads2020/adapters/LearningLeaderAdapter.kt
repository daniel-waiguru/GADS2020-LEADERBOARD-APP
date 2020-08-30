package tech.danielwaiguru.gads2020.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.learner_item.view.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.models.LearningLeader

class LearningLeaderAdapter : RecyclerView.Adapter<MainViewHolder>() {
    private var leadersList: List<LearningLeader> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.learner_item,
            parent,
            false
        ))
    }
    override fun getItemCount(): Int = leadersList.size
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val learningLeader = leadersList[position]
        holder.itemView.apply {
            Glide.with(this).load(learningLeader.badgeUrl).into(topLearnerBadge)
            topLearnerName.text = learningLeader.name
            val hours = "${learningLeader.hours} learning hours, ${learningLeader.country}"
            hoursAndCountry.text = hours
        }
    }
    internal fun initData(leaders: List<LearningLeader>) {
        this.leadersList = leaders
    }
}