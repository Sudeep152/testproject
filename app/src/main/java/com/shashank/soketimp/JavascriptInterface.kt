package com.shashank.soketimp

import android.webkit.JavascriptInterface

class JavascriptInterface(val callActivity: MainActivity) {

    @JavascriptInterface
    public fun onPeerConnected() {
        callActivity.onPeerConnected()
    }

}