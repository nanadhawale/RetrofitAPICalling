package com.example.reteofitjsonparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ForkJoinTask.adapt

class MainActivity(private val listview: ListView) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rf=Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API =rf.create(RetrofitInterface::class.java)
        var call = API.posts
        call?.enqueue(object:Callback<List<Postmodel?>?>{

            override fun onFailure(call: Call<List<Postmodel?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }



            override fun onResponse(
                call: Call<List<Postmodel?>?>,
                response: Response<List<Postmodel?>?>
            ) {
                var postlist : List<Postmodel>?= response.body() as List<Postmodel>
                var post = arrayOfNulls<String>(postlist!!.size)

                for (i in postlist!!.indices)
                    post[i]=postlist!![i]!!.title
                var adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line,post)
                listview.adapter=adapter
            }
        })
    }
}