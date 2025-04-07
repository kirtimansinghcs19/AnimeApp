package com.example.animeapp.presenter

// presenter/AnimeListPresenter.kt
import com.example.animeapp.model.AnimeRepository.AnimeRepository
import com.example.animeapp.view.contract.AnimeListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AnimeListPresenter(
    private val view: AnimeListView,
    private val repository: AnimeRepository = AnimeRepository()
) : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun loadAnimeList() {
        view.showLoading()
        launch {
            try {
                val animeList = repository.getTopAnime()
                if (animeList.isNotEmpty()) {
                    view.showAnimeList(animeList)
                } else {
                    view.showError("No anime found")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError("Error loading anime: ${e.message}")
                }
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                }
            }
        }
    }

    fun onAnimeClicked(animeId: Int) {
        view.navigateToDetail(animeId)
    }

    fun onDestroy() {
        job.cancel()
    }
}