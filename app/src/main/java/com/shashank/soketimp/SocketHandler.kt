package com.shashank.soketimp

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    lateinit var mSocket: Socket
    //const val  BASE_URL ="http://192.168.1.4:4000"
    const val BASE_URL = "https://webrtc-signaling-server-vmwe4oziqq-el.a.run.app"


    @Synchronized
    fun setSocket(identityName:String ) = try {
// "http://10.0.2.2:3000" is the network your Android emulator must use to join the localhost network on your computer
// "http://localhost:3000/" will not work
// If you want to use your physical phone you could use your ip address plus :3000
// This will allow your Android Emulator and physical device at your home to connect to the server
        val options = IO.Options()
        val identity = "$identityName"
        val type = "guard"
        options.query = "identity=$identity&type=$type"
        mSocket = IO.socket(BASE_URL,options)



    } catch (e: URISyntaxException) {

    }



    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}