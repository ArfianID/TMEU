package com.arfian.tmeu.data

import com.arfian.tmeu.domain.model.Sales
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MongoRepository {
    fun configureTheRealm()
    fun getMongoData(): String
    fun filterMongoData(name: String): Flow<List<Sales>>

    suspend fun insertSales(sales: Sales)
    suspend fun deleteSales(id: ObjectId)
    suspend fun updateSales(sales: Sales)
}