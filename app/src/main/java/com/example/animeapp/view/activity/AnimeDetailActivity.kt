package com.example.animeapp.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.example.animeapp.databinding.ActivityAnimeDetailBinding
import com.example.animeapp.model.AnimeDetails
import com.example.animeapp.presenter.AnimeDetailPresenter
import com.example.animeapp.utils.Constants
import com.example.animeapp.view.contract.AnimeDetailView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class AnimeDetailActivity : ComponentActivity(), AnimeDetailView {

    private lateinit var binding: ActivityAnimeDetailBinding
    private lateinit var presenter: AnimeDetailPresenter
    private lateinit var youtubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        youtubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youtubePlayerView)

        val animeId = intent.getIntExtra(Constants.ANIME_ID, -1)
        if (animeId == -1) {
            Toast.makeText(this, "Invalid anime ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        presenter = AnimeDetailPresenter(this)
        presenter.loadAnimeDetails(animeId)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showAnimeDetails(animeDetails: AnimeDetails) {
        with(binding) {
            tvTitle.text = animeDetails.title
            tvEpisodes.text = "Episodes: ${animeDetails.episodes ?: "N/A"}"
            tvScore.text = "Rating: ${animeDetails.score}"
            tvSynopsis.text = animeDetails.synopsis

            val genres = animeDetails.genres.joinToString(", ") { it.name }
            tvGenre.text = genres

            val studios = animeDetails.studios.joinToString(", ") { it.name }
            tvStudios.text = studios
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun playTrailer(youtubeId: String) {
        binding.youtubePlayerView.visibility = View.VISIBLE
        binding.ivPoster.visibility = View.GONE

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(youtubeId, 0f)
            }
        })
    }

    override fun showPoster(imageUrl: String) {
        binding.youtubePlayerView.visibility = View.GONE
        binding.ivPoster.visibility = View.VISIBLE

        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(binding.ivPoster)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}