package com.example.animeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.databinding.ActivityMainBinding
import com.example.animeapp.model.Anime
import com.example.animeapp.presenter.AnimeListPresenter
import com.example.animeapp.utils.Constants
import com.example.animeapp.view.activity.AnimeDetailActivity
import com.example.animeapp.view.adapter.AnimeAdapter
import com.example.animeapp.view.contract.AnimeListView

class MainActivity : ComponentActivity(), AnimeListView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: AnimeListPresenter
    private lateinit var adapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("!!!!!!!!!!!!!!!")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        presenter = AnimeListPresenter(this)
        presenter.loadAnimeList()
    }

    private fun setupRecyclerView() {
        adapter = AnimeAdapter { animeId ->
            presenter.onAnimeClicked(animeId)
        }

        binding.rvAnimeList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showAnimeList(animeList: List<Anime>) {
        adapter.updateData(animeList)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToDetail(animeId: Int) {
        val intent = Intent(this, AnimeDetailActivity::class.java).apply {
            putExtra(Constants.ANIME_ID, animeId)
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}