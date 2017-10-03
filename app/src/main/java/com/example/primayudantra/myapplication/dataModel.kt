package com.example.primayudantra.myapplication

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by primayudantra on 10/3/17.
 */

data class MovieResponse(@SerializedName("page") @Expose val page: Int,
                         @SerializedName("total_results") @Expose val totalResults: Int,
                         @SerializedName("total_pages") @Expose val totalPages: Int,
                         @SerializedName("results")@Expose val listMovie: List<Movie>)

data class Movie(@SerializedName("vote_count")@Expose val voteCount: Int,
                 @SerializedName("id")@Expose val id: Int, @SerializedName("title")@Expose val title: String,
                 @SerializedName("vote_average")@Expose val voteAverage: String, @SerializedName("poster_path") @Expose val posterPath: String,
                 @Expose val overview: String, @SerializedName("backdrop_path") @Expose val backdrop: String
)