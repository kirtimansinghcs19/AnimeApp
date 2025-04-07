Anime Browser App
An Android application that leverages the Jikan API (an unofficial MyAnimeList API) to browse and explore anime series. This app displays a list of top anime and allows users to view detailed information and trailers.
Features

Anime List Page (Home Screen):

Displays a scrollable list of top/popular anime series
Shows title, episode count, rating, and poster image for each anime
Implements pull-to-refresh functionality
Uses pagination to load more results as the user scrolls

Anime Detail Page:

Displays comprehensive information about the selected anime
Includes a video player for watching trailers (when available)
Shows poster image as fallback when trailer is unavailable
Provides title, synopsis, genres, main cast, episode count, and rating
Offers a back navigation button to return to the list

Technical Specifications
Architecture

MVP (Model-View-Presenter) architecture
Repository pattern for data handling

Libraries Used

Retrofit: For API calls to Jikan API
Gson: For JSON parsing
Glide: For image loading and caching
Material Design Components: For UI elements
RecyclerView: For efficient list display
CardView: For material design cards
ConstraintLayout: For responsive layouts
ViewBinding: For view access

API

Jikan API v4

Top Anime: https://api.jikan.moe/v4/top/anime
Anime Details: https://api.jikan.moe/v4/anime/{anime_id}


