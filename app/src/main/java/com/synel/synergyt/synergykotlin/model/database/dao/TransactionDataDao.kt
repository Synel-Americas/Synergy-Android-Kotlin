package com.synel.synergyt.synergykotlin.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.synel.synergyt.synergykotlin.model.database.model.TransactionDataEntity

@Dao
interface TransactionDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransaction(transactionDataEntity: TransactionDataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransactions(transactionDataEntities: List<TransactionDataEntity>)

    @Query("SELECT * FROM TransactionData")
    fun getAllTransactions(): List<TransactionDataEntity>

    @Query("SELECT * FROM TransactionData WHERE status !='SUBMITTED'")
    fun getTransactionsNotUploaded(): List<TransactionDataEntity>

    @Query("SELECT * FROM TransactionData WHERE id =:id")
    fun getTransactionById(id: Int): TransactionDataEntity

    @Query("SELECT * FROM TransactionData WHERE attendanceKey = :key")
    fun getTransactionByAttendanceKey(key: String): TransactionDataEntity

    @Query("SELECT * FROM TransactionData WHERE employeeNumber = :empNumber ORDER BY punchDate ASC LIMIT 1")
    fun getLastTransactionByEmployeeNumber(empNumber: String): TransactionDataEntity
}