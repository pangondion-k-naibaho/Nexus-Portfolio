package com.nexusportfolio.client.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexusportfolio.client.data.model.Portfolio
import com.nexusportfolio.client.data.repository.PortfolioRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


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

