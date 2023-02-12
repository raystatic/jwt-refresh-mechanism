package com.example.jwtrefreshdemo

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
): ViewModel() {

    private val _newData = MutableLiveData<ResponseModel>()
    val newData:LiveData<ResponseModel> get() = _newData

    fun getSomeData() = viewModelScope.launch {
        repository.getSomeData()
            .onEach {
                _newData.value = it
            }
            .catch {
                _newData.value = ResponseModel(
                    message = "Something went wrong..."
                )
            }
            .launchIn(viewModelScope)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val repository: Repository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }

}