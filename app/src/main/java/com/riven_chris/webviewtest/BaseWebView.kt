package com.riven_chris.webviewtest

import android.content.Context
import android.util.AttributeSet
import android.webkit.*

class BaseWebView : WebView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    init {
        val webSettings = this.settings
        webSettings.javaScriptEnabled = true
        webSettings.defaultTextEncodingName = "UTF-8"
        webSettings.setSupportZoom(true)
        webSettings.javaScriptCanOpenWindowsAutomatically = true

        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT

        //        webSettings.setBuiltInZoomControls(false);
        //        webSettings.setLoadsImagesAutomatically(true);
        //        webSettings.setAllowFileAccess(true);
        //        webSettings.setLoadWithOverviewMode(true);
        //        webSettings.setPluginState(WebSettings.PluginState.ON);

        webViewClient = BaseWebViewClient()
        webChromeClient = BaseWebChromeClient()
    }

    open class BaseWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            view.loadUrl("about:blank")
        }
    }

    open class BaseWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }
    }

    fun onDestroy() {
        try {
            CookieManager.getInstance().removeAllCookies{}
            CookieManager.getInstance().flush()

//            this.loadUrl("about:blank")
            this.stopLoading()
            this.webChromeClient = null
            this.webViewClient = null
            this.clearCache(true)
            this.destroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}