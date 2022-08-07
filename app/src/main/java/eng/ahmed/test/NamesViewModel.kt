package eng.ahmed.test

import androidx.lifecycle.*
import eng.ahmed.test.model.Name
import kotlinx.coroutines.launch

class NamesViewModel(private val repository: NamesRepository) : ViewModel() {

//    val allNames: LiveData<List<Name>> = repository.allNames.asLiveData()

    fun insert(name: Name) {
        viewModelScope.launch {
            repository.insert(name)
        }
    }

    suspend fun getNamesFromDB() = repository.getNames()
}

class NamesViewModelFactory(private val repository: NamesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NamesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
