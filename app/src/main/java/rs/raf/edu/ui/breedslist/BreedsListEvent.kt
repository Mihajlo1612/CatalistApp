package rs.raf.edu.ui.breedslist

sealed class BreedsListEvent {
    object FetchBreeds : BreedsListEvent()
    data class Search(val query: String) : BreedsListEvent()
}