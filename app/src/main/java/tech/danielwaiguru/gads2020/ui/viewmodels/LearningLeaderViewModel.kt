package tech.danielwaiguru.gads2020.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.danielwaiguru.gads2020.models.LearningLeader
import tech.danielwaiguru.gads2020.models.Resource
import tech.danielwaiguru.gads2020.repositories.MainRepository

class LearningLeaderViewModel
@ViewModelInject constructor(private val mainRepository: MainRepository): ViewModel() {
    private val _toast: MutableLiveData<String?> = MutableLiveData()
    val toast : LiveData<String?>
    get() = _toast
    private val _learningLeaders: MutableLiveData<List<LearningLeader>> = MutableLiveData()
    val learningLeaders : LiveData<List<LearningLeader>>
    get() = _learningLeaders
    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState : LiveData<Boolean>
    get() = _loadingState
    /**
     * get Learning Leaders and update leadingLeaders LiveData
     */
    fun fetchLearningLeaders(){
        _loadingState.postValue(true)
        viewModelScope.launch {
            when (val result = mainRepository.fetchLearningLeaders()){
                is Resource.Success -> {
                    _loadingState.postValue(false)
                    val leaders = ArrayList<LearningLeader>()
                    result.data?.forEach {
                        leaders.add(it)
                    }

                    //Timber.d(leaders.size.toString())
                    _learningLeaders.value = leaders
                }
                is Resource.Loading -> {
                    _loadingState.value = false
                }
                is Resource.Error -> {
                    _toast.value = result.message.toString()
                }
            }
        }
    }
}