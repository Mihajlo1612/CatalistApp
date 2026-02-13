package rs.raf.edu.ui.breedslist

import rs.raf.edu.model.Breed

sealed class BreedsListState {
    object Loading: BreedsListState()
    data class Success(val breeds: List<Breed>) : BreedsListState()
    data class Error(val message: String) : BreedsListState()
}