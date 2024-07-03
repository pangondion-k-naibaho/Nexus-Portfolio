package com.nexusportfolio.client.data.repository

import com.nexusportfolio.client.data.model.Constants
import com.nexusportfolio.client.data.model.Portfolio
import javax.inject.Inject


class PortfolioRepositoryImpl @Inject constructor(): PortfolioRepository {
    override fun getPortfolioData() = Constants.DUMMY_DATA.data
}