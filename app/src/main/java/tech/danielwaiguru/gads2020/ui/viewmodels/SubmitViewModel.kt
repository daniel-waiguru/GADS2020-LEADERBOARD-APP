package tech.danielwaiguru.gads2020.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.danielwaiguru.gads2020.models.Resource
import tech.danielwaiguru.gads2020.repositories.MainRepository

class SubmitViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean>
    get() = _loadingState
    private val _submissionState: MutableLiveData<Boolean> = MutableLiveData()
    val submissionState: LiveData<Boolean>
    get() = _submissionState
    fun submitProject(
        firstName: String, lastName: String, emailAddress: String, projectLink: String
    ) = viewModelScope.launch {
        _loadingState.postValue(true)
        val result = mainRepository.submitProject(
            firstName, lastName, emailAddress, projectLink
        )
        when (result){
            is Resource.Success -> {
                _submissionState.postValue(true)
                _loadingState.postValue(false)
            }
            is Resource.Loading -> {
                _loadingState.postValue(true)
                _submissionState.postValue(false)
            }
            is Resource.Error -> {
                _submissionState.postValue(false)
                _loadingState.postValue(false)
            }
        }
    }
}