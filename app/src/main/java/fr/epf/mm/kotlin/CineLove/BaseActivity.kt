package fr.epf.mm.kotlin.CineLove

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.epf.mm.kotlin.CineLove.R
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BaseActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_films)

        this.recyclerView = findViewById<RecyclerView>(R.id.list_films_recyclerview)

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_films_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_client -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }

            R.id.action_synchro -> {
                synchro()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun synchro() {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()



    }
    private fun getPopularMovies(context: Context, retrofitBuilder: ApiInterface,page: Int){
        val popularMovies = retrofitBuilder.getPopularMovies("en-US",1,"8d10df48aa66174ddcec6bed18d3aa18")
        popularMovies.enqueue(object : Callback<ApiResponse?> {
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    Log.e("Response",responseBody.toString())
                    val movieContainer = findViewById<RecyclerView>(R.id.list_films_recyclerview)
                    Glide.with(context)
                        .load("https://image.tmdb.org/t/p/w500/"+responseBody.poster_path)
                }
            }

            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        }
    }



}



