package com.example.proj6_cyoapi_recycleredition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var coffeeImgList: MutableList<String>
    private lateinit var rvCoffee: RecyclerView

    // added features different from lab
    private lateinit var coffeeTitleList: MutableList<String>
    private lateinit var coffeeDescriptionList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCoffee = findViewById(R.id.coffee_list)
        coffeeImgList = mutableListOf()
        coffeeTitleList = mutableListOf()
        coffeeDescriptionList = mutableListOf()

        getCoffeeImageURL()
    }

    private fun getCoffeeImageURL() {
        val client = AsyncHttpClient()

        client["https://api.sampleapis.com/coffee/hot/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("Coffee", "response successful")
                val coffeeArray = json.jsonArray
                Log.d("coffee image link", coffeeArray.getJSONObject(1).getString("image"))

                for (i in 1 until coffeeArray.length()) {
                    coffeeImgList.add(coffeeArray.getJSONObject(i).getString("image"))
                    coffeeTitleList.add(coffeeArray.getJSONObject(i).getString("title"))
                    coffeeDescriptionList.add(coffeeArray.getJSONObject(i).getString("description"))

                    val adapter = CoffeeAdapter(coffeeImgList, coffeeTitleList, coffeeDescriptionList)
                    rvCoffee.adapter = adapter
                    rvCoffee.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Coffee Error", errorResponse)
            }
        }]
    }
}