package com.example.primayudantra.myapplication

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val adapter = MoviesListAdapter()
        this.main_listview.adapter = adapter
        this.main_listview.layoutManager = LinearLayoutManager(this)
        this.main_listview.addItemDecoration(DividerItemDecoration(this.main_listview.context, GridLayoutManager.HORIZONTAL))



//        val retrofit = Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org/")
//                .build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/")
                .build()

        val service = retrofit.create<api>(api::class.java!!)

        service.movieResponse("37dc2d42ed324228aa382f0554ad1b28").enqueue(object:Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.d("status",t?.message)
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                Log.d("status",response?.body().toString())
                adapter.setItems(response?.body()?.listMovie)
            }
        })
    }
}
