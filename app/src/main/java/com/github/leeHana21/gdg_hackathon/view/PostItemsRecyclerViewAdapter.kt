package com.github.leeHana21.gdg_hackathon.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.CardFullPostsBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.PostDetail

class PostItemsRecyclerViewAdapter(
    private val category : Category,
    private var values: List<PostDetail>
) : RecyclerView.Adapter<PostItemsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CardFullPostsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvTitle.text = item.title
        holder.tvCategory.text = when(category){
            Category.POPULAR -> "인기"
            Category.LIFE -> "생활"
            Category.INTERVIEW -> "면접"
        }
        Glide.with(holder.ivImage)
            .load(item.imageUrl)
            .into(holder.ivImage)
        holder.ivImage.setColorFilter(Color.parseColor("#555555"), PorterDuff.Mode.MULTIPLY)
        holder.tvUserCount.text = "+ ${(1..100).random()}명"
        holder.layCard.setOnClickListener {
            val intent = Intent(holder.layCard.context, DetailActivity::class.java)
            intent.putExtra("postId",item.postId)
            holder.layCard.context.startActivity(intent)
        }
        holder.ivBookmark.setOnClickListener {
            if((holder.ivBookmark.drawable.constantState == it.context.resources.getDrawable(R.drawable.ic_bookmark).constantState))
            {
                holder.ivBookmark.setImageResource(R.drawable.ic_bookmark_selected)
            } else {
                holder.ivBookmark.setImageResource(R.drawable.ic_bookmark)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: CardFullPostsBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle: TextView = binding.tvTitle
        val tvCategory: TextView = binding.tvCategory
        val ivImage: ImageView = binding.ivPostImage
        val tvUserCount : TextView = binding.tvUserCount
        val layCard : ConstraintLayout = binding.layCard
        val ivBookmark : ImageView = binding.ivBookmark
    }
}