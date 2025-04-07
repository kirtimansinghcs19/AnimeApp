package com.example.animeapp.presenter


// presenter/AnimeDetailPresenter.kt
import com.example.animeapp.model.AnimeRepository.AnimeRepository
import com.example.animeapp.view.contract.AnimeDetailView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AnimeDetailPresenter(
    private val view: AnimeDetailView,
    private val repository: AnimeRepository = AnimeRepository()
) : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun loadAnimeDetails(animeId: Int) {
        view.showLoading()
        launch {
            try {
                val animeDetails = repository.getAnimeDetails(animeId)
                if (animeDetails != null) {
                    withContext(Dispatchers.Main) {
                        view.showAnimeDetails(animeDetails)

                        // Check if trailer exists
                        val youtubeId = animeDetails.trailer?.youtubeId
                        if (!youtubeId.isNullOrEmpty()) {
                            view.playTrailer(youtubeId)
                        } else {
                            // Show poster if no trailer
                            animeDetails.images?.jpg?.largeImageUrl?.let {
                                view.showPoster(it)
                            }
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        view.showError("Anime details not found")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError("Error loading anime details: ${e.message}")
                }
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                }
            }
        }
    }

    fun onDestroy() {
        job.cancel()
    }
}
