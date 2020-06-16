package com.riven_chris.webviewtest

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zzhoujay.richtext.RichText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoad.setOnClickListener {
            loadHtml()
        }
    }

    fun loadHtml() {
        web_view.settings.useWideViewPort = false
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
//                val id = "1272464926989930497"
//                val id = "1272524908989042690"
                val id = "1272524908989042690"
                (URL("https://cms.51xuetang.com/m/article/getArticle?articleId=$id").readText())
            }
            val jsonObject = JSONObject(result)
            val data = jsonObject.getJSONObject("data")
            var htmlData = data.getString("content")
//            tvHtml.setHtml(htmlData, HtmlHttpImageGetter(tvHtml))
            web_view.loadDataWithBaseURL(
                null,
                htmlData,
                "text/html;charset=utf-8",
                "UTF-8",
                null
            )
        }
    }

    private fun getHtmlData(bodyHTML: String): String {
        val head =
            "<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>"
    }

}
