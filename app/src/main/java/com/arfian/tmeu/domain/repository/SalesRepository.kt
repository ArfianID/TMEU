package com.arfian.tmeu.domain.repository

import com.arfian.tmeu.domain.model.Sales

interface SalesRepository {
    suspend fun insertSales(sales: Sales)
    suspend fun getSales(): List<Sales>
    suspend fun updateSales(sales: Sales)
    suspend fun deleteSales(sales: Sales)
}