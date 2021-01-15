package cn.juejin.app.lite.module.home.adapter

import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import cn.juejin.app.lite.R
import cn.juejin.app.lite.model.Article
import cn.juejin.app.lite.module.webview.WebViewActivity
import coil.load

class FeedListAdapter : RecyclerView.Adapter<FeedListAdapter.FeedListViewHolder>() {

    private val models = arrayListOf<Article>()
    var canLoadMore = true
    var onLoadMore: () -> Unit = {}

    class FeedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarView: ImageFilterView =
            itemView.findViewById(R.id.home_recycler_iv_avatar)
        private val usernameView: AppCompatTextView =
            itemView.findViewById(R.id.home_recycler_tv_username)
        private val titleView: AppCompatTextView =
            itemView.findViewById(R.id.home_recycler_tv_title)
        private val contentView: AppCompatTextView =
            itemView.findViewById(R.id.home_recycler_tv_content)
        private val coverView: ImageFilterView =
            itemView.findViewById(R.id.home_recycler_iv_cover)
        private val thumbView: AppCompatTextView =
            itemView.findViewById(R.id.home_recycler_tv_thumb)
        private val commentView: AppCompatTextView =
            itemView.findViewById(R.id.home_recycler_tv_comment)

        fun bind(model: Article) {
            avatarView.load(model.user.avatar) { crossfade(true) }
            usernameView.text = model.user.username
            titleView.text = model.title
            if (TextUtils.isEmpty(model.content)) {
                contentView.visibility = View.GONE
            } else {
                contentView.visibility = View.VISIBLE
                contentView.text = model.content
            }
            if (TextUtils.isEmpty(model.cover)) {
                coverView.visibility = View.GONE
            } else {
                coverView.visibility = View.VISIBLE
                coverView.load(model.cover) { crossfade(true) }
            }
            thumbView.text = "${model.praiseCount}"
            commentView.text = "${model.commentCount}"
        }

    }

    fun submitList(elements: List<Article>) {
        if (elements.isEmpty()) {
            return
        }
        val size = models.size
        models.addAll(elements)
        if (size == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(size, elements.size)
        }
    }

    fun getItem(position: Int): Article? {
        if (position >= 0 && position < models.size) {
            return models[position]
        }
        return null
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.recycler_home_article
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)
        return FeedListViewHolder(view).apply {
            itemView.setOnClickListener {
                val item = getItem(adapterPosition)
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        WebViewActivity::class.java
                    ).apply {
                        putExtra("articleId", item?.id)
                    }
                )
            }
        }
    }

    override fun onBindViewHolder(holder: FeedListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onViewAttachedToWindow(holder: FeedListViewHolder) {
        val position = holder.adapterPosition
        if (canLoadMore && position == models.size - 2) {
            onLoadMore()
        }
    }

}