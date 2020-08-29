package tech.danielwaiguru.gads2020.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.danielwaiguru.gads2020.models.Resource
import tech.danielwaiguru.gads2020.models.SkillIQLeader
import tech.danielwaiguru.gads2020.repositories.MainRepository

class IQLeaderViewModel
@ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel(){
    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast : LiveData<String>
        get() = _toast
    private val _learningLeaders: MutableLiveData<List<SkillIQLeader>> = MutableLiveData()
    val learningLeaders : LiveData<List<SkillIQLeader>>
        get() = _learningLeaders
    /**
     * get Learning Leaders and update leadingLeaders LiveData
     */
    fun fetchIQLeaders(){
        viewModelScope.launch {
            when (val result = mainRepository.fetchIQLeaders()){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    _learningLeaders.value = result.data
                }
                is Resource.Error -> {
                    _toast.value = result.message
                }
            }
        }
    }
}