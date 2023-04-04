package com.ntt.androidday18

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ntt.androidday18.databinding.ActivityMainBinding
import com.ntt.androidday18.jsoup.JsoupParser
import kotlinx.coroutines.*
import java.lang.Runnable
import java.net.URL
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), Runnable {

    private lateinit var bitmap: Bitmap

    private lateinit var binding: ActivityMainBinding
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                binding.imgAvatar.setImageBitmap(bitmap)
            }
            if (msg.what == 2) {
                val number1 = msg.arg1
                val number2 = msg.arg2
                val obj = msg.obj
            }
        }
    }

    suspend fun getUserInfo() {
        Log.d("thanhnt", "Start get user info ${System.currentTimeMillis()}")
        val userFriends = mainScope.async {
            getUserFriend()
        }
//        val userMessages = mainScope.async {
//            getUserMessages()
//        }
//
        val userMessage = withContext(Dispatchers.IO) {
            getUserMessages()
        }
        val userInfo = userFriends.await() + userMessage
        Log.d("thanhnt", "user Info is $userInfo")
        Log.d("thanhnt", "Stop get user info ${System.currentTimeMillis()}")
    }


    suspend fun getUserFriend(): String {
        //FAKE
        Log.d("thanhnt", "start get user friends")
        Thread.sleep(5000)
        Log.d("thanhnt", "stop get user friends")
        return "Friends"
    }

    suspend fun getUserMessages(): String {
        //FAKE
        Log.d("thanhnt", "start get user message")
        Thread.sleep(3000)
        Log.d("thanhnt", "stop get user message")
        return "Messages"
    }

    private val job: Job = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val mainScope = CoroutineScope(coroutineContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        lifecycleScope.launch(Dispatchers.IO) {
//            JsoupParser.getAllQuestion("Jetpack")
//        }

        binding.btnLoad.setOnClickListener {
            //Application scope
//            GlobalScope.launch(Dispatchers.IO) {
//                downloadImageCoroutines()
//            }
            mainScope.launch(Dispatchers.IO) {
//                downloadImageCoroutines()
                getUserInfo()
//                downloadImage()
            }

//            DownloadFile(this).execute(
//                "https://photo-cms-baonghean.zadn.vn/w607/Uploaded/2021/ftgbtgazsnzm/2020_07_14/ngoctrinhmuonsinhcon1_swej7996614_1472020.jpg",
//            )
//            runBlocking {
//                downloadImageCoroutines()
//            }
//            val thread = Thread(this)
//            thread.start()
        }
    }

    suspend fun downloadImageCoroutines() {
        val link =
            "https://photo-cms-baonghean.zadn.vn/w607/Uploaded/2021/ftgbtgazsnzm/2020_07_14/ngoctrinhmuonsinhcon1_swej7996614_1472020.jpg"
        val url = URL(link)
        val connection = url.openConnection()
        val inputStream = connection.getInputStream()
        bitmap = BitmapFactory.decodeStream(inputStream)
        withContext(Dispatchers.Main) {
            binding.imgAvatar.setImageBitmap(bitmap)
        }
    }

    override fun run() {
        downloadImage()
    }

    inner class DownloadFile(private val context: Context) : AsyncTask<String, Void, Bitmap>() {
        private lateinit var dialog: AlertDialog
        override fun onPreExecute() {
            super.onPreExecute()
            dialog = AlertDialog.Builder(context)
                .setTitle("Downloading")
                .setMessage("Download a image")
                .show()
        }

        override fun doInBackground(vararg params: String?): Bitmap {
            Thread.sleep(4000)
            val link = params[0]
            val url = URL(link)
            val connection = url.openConnection()
            val inputStream = connection.getInputStream()
            return BitmapFactory.decodeStream(inputStream)
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            dialog.dismiss()
            binding.imgAvatar.setImageBitmap(result)
        }

    }

    fun downloadImage() {
        val link =
            "https://photo-cms-baonghean.zadn.vn/w607/Uploaded/2021/ftgbtgazsnzm/2020_07_14/ngoctrinhmuonsinhcon1_swej7996614_1472020.jpg"
        val url = URL(link)
        val connection = url.openConnection()
        val inputStream = connection.getInputStream()
        bitmap = BitmapFactory.decodeStream(inputStream)
        handler.sendEmptyMessage(1)
        val msg = Message()
        msg.what = 2
        msg.arg1 = 40
        msg.arg2 = 60
        msg.obj = bitmap
//        val bundle = Bundle()
//        //put something into bundle
//        msg.data = bundle
        handler.sendMessage(msg)
//        runOnUiThread {
//            binding.imgAvatar.setImageBitmap(bitmap)
//        }
//        binding.imgAvatar.post {
//            binding.imgAvatar.setImageBitmap(bitmap)
//        }
//        binding.imgAvatar.postDelayed({
//            binding.imgAvatar.setImageBitmap(bitmap)
//        }, 5000)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}