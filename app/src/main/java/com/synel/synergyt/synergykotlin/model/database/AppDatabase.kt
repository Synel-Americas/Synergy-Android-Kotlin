package com.synel.synergyt.synergykotlin.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.synel.synergyt.synergykotlin.model.database.converters.GenericsConverter
import com.synel.synergyt.synergykotlin.model.database.dao.EmployeeDao
import com.synel.synergyt.synergykotlin.model.database.dao.TransactionDataDao
import com.synel.synergyt.synergykotlin.model.database.dao.WebServiceCommandDao
import com.synel.synergyt.synergykotlin.model.database.model.EmployeeEntity
import com.synel.synergyt.synergykotlin.model.database.model.TransactionDataEntity
import com.synel.synergyt.synergykotlin.model.database.model.WebserviceCommandEntity

@Database(
    entities = [EmployeeEntity::class,
        TransactionDataEntity::class,
        WebserviceCommandEntity::class], version = 1
)
@TypeConverters(GenericsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun transactionDao(): TransactionDataDao
    abstract fun webServiceCommandDao(): WebServiceCommandDao
}