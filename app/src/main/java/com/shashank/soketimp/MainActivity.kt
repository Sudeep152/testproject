package com.shashank.soketimp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.gson.Gson
import com.shashank.soketimp.databinding.ActivityMainBinding
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
lateinit var  binding: ActivityMainBinding
var OperatorSocketId = ""


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var name = intent.getStringExtra("myName")
        Toast.makeText(this, "$name", Toast.LENGTH_SHORT).show()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        SocketHandler.setSocket(name.toString())
        val mSocket = SocketHandler.getSocket()
        mSocket.connect()
        mSocket.emit("get-operator-list")

        val filePath = "file:android_asset/callq.html?identity=$name"
        binding.webView.settings.domStorageEnabled=true
        binding.webView.settings.javaScriptEnabled=true
        binding.webView.loadUrl(filePath)
       binding. webView.webChromeClient = object: WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest?) {
                request?.grant(request.resources)
            }
        }

      binding. webView.settings.mediaPlaybackRequiresUserGesture = false
      binding. webView.addJavascriptInterface(JavascriptInterface(this), "Android")


       Log.d("url",binding.webView.originalUrl.toString())



        binding.callBtn.setOnClickListener {
            mSocket.on("get-operator-list"){

                val jsonArrayString = it[0].toString()
                val gson = Gson()
                val dataList = gson.fromJson(jsonArrayString, Array<OperatorX>::class.java).toList()
                val socketIds = dataList.map { it.socketID }
                val OperatorId = socketIds[0]
                OperatorSocketId = OperatorId
                Log.d("data",socketIds[0].toString())
                runOnUiThread {
                    Toast.makeText(this, "$OperatorSocketId", Toast.LENGTH_SHORT).show()
                }



            }
        }



    }
    private fun loadVideoCall() {


       binding.webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {

            }
        }
    }

    fun  onPeerConnected(){

    }

}