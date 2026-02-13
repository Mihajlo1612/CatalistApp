package rs.raf.edu.ui.breedsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import rs.raf.edu.data.repository.BreedRepository
import rs.raf.edu.model.Breed
import rs.raf.edu.model.BreedImage
import javax.inject.Inject

@HiltViewModel
class BreedsDetailsViewModel @Inject constructor(
    private val breedsRepository: BreedRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BreedsDetailsState>(BreedsDetailsState.Loading)
    val state: StateFlow<BreedsDetailsState> = _state

    fun onEvent(event: BreedsDetailsEvent) {
        when (event) {
            is BreedsDetailsEvent.LoadBreed ->
                loadBreedById(event.breedId)
        }
    }

    private fun loadBreedById(breedId: String) {
        viewModelScope.launch {
            val breed = BreedsDetailsState.Loading
            try {
                val breed = breedsRepository.getBreedById(breedId)
                    ?: return@launch _state.emit(BreedsDetailsState.Error("Rasa nije pronadjena!"))

                val imageUrl = breedsRepository.getImageForBreed(breedId)
                val enriched = breed.copy(
                    image = imageUrl
                        ?.takeIf { it.isNotBlank() }
                        ?.let { BreedImage(it) }
                )
                _state.value = BreedsDetailsState.Success(enriched)
            } catch (e: Exception) {
                _state.value = BreedsDetailsState.Error(e.message ?: "Greska pri ucitavanju!")
            }
        }
    }
}