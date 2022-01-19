package com.awkitsune.owmtest

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL

class Get {
    companion object{
        private const val requestType = "GET";

        suspend fun GetRandomFox(): FoxResponse? = withContext(Dispatchers.IO) {
            val url = URL("https://randomfox.ca/floof/");

            var response = ""
            var dataToReturn: FoxResponse? = null

            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = requestType
                    inputStream.bufferedReader().use {
                        it.lines().forEach { line ->
                            response += line
                        }
                    }
                }
            } catch (exception: Exception) {
                Log.d("requestGetError", exception.message.toString())
            }

            try {
                dataToReturn =  Gson().fromJson(response, FoxResponse::class.java)
            } catch (exception: Exception) {
                Log.d("requestParseError", exception.message.toString())
            }

            return@withContext dataToReturn
        }


    }
}