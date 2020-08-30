package tech.danielwaiguru.gads2020.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.learner_item.view.*
import tech.danielwaiguru.gads2020.R
import tech.danielwaiguru.gads2020.models.LearningLeader
import timber.log.Timber

class LearningLeaderAdapter : RecyclerView.Adapter<MainViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<LearningLeader>() {
        override fun areItemsTheSame(oldItem: LearningLeader, newItem: LearningLeader): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: LearningLeader, newItem: LearningLeader): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    internal fun submitList(learningLeaders: List<LearningLeader>){
        Timber.d(learningLeaders.size.toString())
        Timber.d(learningLeaders[2].name)
        differ.submitList(learningLeaders)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.learner_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val learningLeader = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(learningLeader.badgeUrl).into(topLearnerBadge)
            topLearnerName.text = learningLeader.name
            val hours = "${learningLeader.hours} learning hours, ${learningLeader.name}"
            hoursAndCountry.text = hours
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}