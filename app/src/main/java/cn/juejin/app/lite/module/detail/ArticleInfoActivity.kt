package cn.juejin.app.lite.module.detail

import androidx.appcompat.widget.AppCompatTextView
import cn.juejin.app.lite.R
import cn.juejin.app.lite.common.AbstractActivity
import io.noties.markwon.Markwon
import io.noties.markwon.image.coil.CoilImagesPlugin
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin
import io.noties.markwon.linkify.LinkifyPlugin

class ArticleInfoActivity : AbstractActivity() {

    private lateinit var viewModel: ArticleInfoViewModel
    private lateinit var textView: AppCompatTextView
    private lateinit var markdown: Markwon

    override fun initView() {
        setContentView(R.layout.activity_article_info)
        textView = findViewById(R.id.tv_article)
    }

    override fun initData() {
        markdown = Markwon.builder(this)
            .usePlugin(CoilImagesPlugin.create(this))
            .usePlugin(MarkwonInlineParserPlugin.create())
            .usePlugin(LinkifyPlugin.create())
            .build()
        viewModel = viewModel(ArticleInfoViewModel::class.java)
        viewModel.article.observe(this, {
            markdown.setMarkdown(textView, it.content)
        })
        viewModel.getArticleDetail()
    }
}