import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.riven_chris.webviewtest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenResumed {
//            val result = withContext(Dispatchers.IO) {
//                (URL("https://cms.51xuetang.com/m/article/getArticle?articleId=1272166871333892097").readText())
//            }
//            val jsonObject = JSONObject(result)
//            val data = jsonObject.getJSONObject("data")
//            val htmlData = data.getString("content")
//            webview.loadData(htmlData, "text/html", "UTF-8")
        }
    }
}
