package rs.raf.edu.ui.breedsdetails

import rs.raf.edu.model.Breed

sealed class BreedsDetailsState {
    object Loading: BreedsDetailsState()
    data class Success(val breed: Breed) : BreedsDetailsState()
    data class Error(val message: String) : BreedsDetailsState()
}