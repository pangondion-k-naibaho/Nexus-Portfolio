package com.nexusportfolio.client.data.repository

import com.nexusportfolio.client.data.model.Portfolio

interface PortfolioRepository {
    fun getPortfolioData(): List<Portfolio>
}