package rs.raf.edu.ui.breedslist
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rs.raf.edu.data.repository.BreedRepository
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(
   private val repository: BreedRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<BreedsListState>(BreedsListState.Loading)
    val state: StateFlow<BreedsListState> = _state

    init {
        fetchBreeds()
    }

    fun onEvent(event: BreedsListEvent) {
        when (event) {
            is BreedsListEvent.FetchBreeds -> {
                fetchBreeds()
            } else -> {

            }
        }
    }

    private fun fetchBreeds() {
        _state.value = BreedsListState.Loading
        viewModelScope.launch {
            repository.getAllBreeds().collect { breeds ->
                if(breeds.isEmpty()) {
                    _state.value = BreedsListState.Error("No breeds found")
                } else {
                    _state.value = BreedsListState.Success(breeds)
                }
            }
        }
    }
}