package com.nexusportfolio.client.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexusportfolio.client.data.model.Portfolio
import com.nexusportfolio.client.data.repository.PortfolioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class MainViewModel @Inject constructor(private val repository: PortfolioRepository) : ViewModel() {
//
//    private val _portfolioData = MutableLiveData<List<Portfolio>>()
//    val portfolioData: LiveData<List<Portfolio>> = _portfolioData
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    fun loadPortfolioData() {
//        _isLoading.value = true
//
//        // Simulasi loading data selama 5 detik
//        viewModelScope.launch {
//            delay(5000)
//            _portfolioData.value = repository.getPortfolioData()
//            _isLoading.value = false
//        }
//    }
//}

//@HiltViewModel
//class MainViewModel @Inject constructor(
//    private val portfolioRepository: PortfolioRepository
//) : ViewModel() {
//    private val _portfolioData = mutableStateOf<List<Portfolio>>(emptyList())
//    val portfolioData = _portfolioData
//
//    private val _isLoading = mutableStateOf(true)
//    val isLoading = _isLoading
//
//    fun loadPortfolioData() {
//        viewModelScope.launch {
//            delay(5000) // Simulasi loading selama 5 detik
//            _portfolioData.value = portfolioRepository.getPortfolioData()
//            _isLoading.value = false
//        }
//    }
//}

//@HiltViewModel
class MainViewModel @Inject constructor(
    private val portfolioRepository: PortfolioRepository
): ViewModel() {

    private val _portfolioData = MutableStateFlow<List<Portfolio>>(emptyList())
    val portfolioData: StateFlow<List<Portfolio>> = _portfolioData

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadPortfolioData()
    }

    private fun loadPortfolioData() {
        viewModelScope.launch {
            delay(5000) // Simulate loading delay
            _portfolioData.value = portfolioRepository.getPortfolioData()
            _isLoading.value = false
        }
    }
}

