package de.gabstr.smack.services

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import de.gabstr.smack.controller.App
import de.gabstr.smack.model.Channel
import de.gabstr.smack.utilities.URL_GET_CHANNELS
import org.json.JSONException

object MessageService {

    val channels = ArrayList<Channel>()

    fun getChannels(complete: (Boolean) -> Unit) {
        val url = URL_GET_CHANNELS

        val channelsRequest = object : JsonArrayRequest(Method.GET, url, null, Response.Listener {response ->
            try {
                for (x in 0 until response.length()) {
                    val channel = response.getJSONObject(x)

                    val channelName = channel.getString("name")
                    val channelDesc = channel.getString("description")
                    val channelId = channel.getString("_id")

                    val newChannel = Channel(channelName, channelDesc, channelId)
                    this.channels.add(newChannel)
                }
                complete(true)
            } catch (e: JSONException) {
                Log.d("JSON ERROR", "EXC: " + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener {error ->
            Log.d("ERROR", "Could not retrieve channels")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${ App.prefs.authToken}")
                return headers
            }
        }

        App.prefs.requestQueue.add(channelsRequest)
    }
}