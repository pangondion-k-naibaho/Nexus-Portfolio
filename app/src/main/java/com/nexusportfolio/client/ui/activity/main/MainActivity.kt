package com.nexusportfolio.client.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nexusportfolio.client.ui.theme.NexusPortfolioTheme
import com.nexusportfolio.client.ui.viewmodels.MainViewModel
import androidx.lifecycle.ViewModelProvider
import com.nexusportfolio.client.data.repository.PortfolioRepositoryImpl
import com.nexusportfolio.client.ui.components.MainScreen
import com.nexusportfolio.client.ui.viewmodels.MainViewModelFactory

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = PortfolioRepositoryImpl()

        val factory = MainViewModelFactory(repository)

        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setContent {
            NexusPortfolioTheme {
                MainScreen(viewModel = mainViewModel, context = this@MainActivity)
            }
        }
    }
}