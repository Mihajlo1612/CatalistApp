package rs.raf.edu.ui.breedsdetails

sealed class BreedsDetailsEvent {
    data class LoadBreed(val breedId: String) : BreedsDetailsEvent()
}
