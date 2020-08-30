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
import timber.log.Timber

class LearningLeaderViewModel
@ViewModelInject constructor(private val mainRepository: MainRepository): ViewModel() {
    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast : LiveData<String>
    get() = _toast
    private val _learningLeaders: MutableLiveData<List<LearningLeader>> = MutableLiveData()
    val learningLeaders : LiveData<List<LearningLeader>>
    get() = _learningLeaders
    /**
     * get Learning Leaders and update leadingLeaders LiveData
     */
    fun fetchLearningLeaders(){
        viewModelScope.launch {
            when (val result = mainRepository.fetchLearningLeaders()){
                is Resource.Success -> {
                    val leaders = ArrayList<LearningLeader>()
                    result.data?.forEach {
                        leaders.add(it)
                    }
                    Timber.d(leaders.size.toString())
                    _learningLeaders.value = leaders
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    _toast.value = result.message.toString()
                }
            }
        }
    }

}