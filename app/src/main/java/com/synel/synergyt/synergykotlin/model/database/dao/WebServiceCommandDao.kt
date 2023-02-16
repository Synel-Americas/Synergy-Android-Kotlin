package com.synel.synergyt.synergykotlin.model.database.dao

import androidx.room.*
import com.synel.synergyt.synergykotlin.model.database.model.WebserviceCommandEntity
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD

@Dao
interface WebServiceCommandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCommand(webserviceCommandEntity: WebserviceCommandEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCommands(webserviceCommandEntities: List<WebserviceCommandEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCommand(webserviceCommandEntity: WebserviceCommandEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCommands(webserviceCommandEntities: List<WebserviceCommandEntity>)

    @Query("SELECT * FROM WebserviceCommand")
    fun getAllCommands():List<WebserviceCommandEntity>

    @Query("SELECT * FROM WebserviceCommand WHERE isProcessed = 0 ORDER BY receiveDate DESC")
    fun getAllPendingCommands():List<WebserviceCommandEntity>

    @Query("SELECT * FROM WebserviceCommand WHERE id = :id")
    fun getCommandById(id: Int):WebserviceCommandEntity

    @Query("SELECT * FROM WebserviceCommand WHERE commandType = :commandType")
    fun getCommandByType(commandType: WSCMD): List<WebserviceCommandEntity>

    @Query("SELECT * FROM WebserviceCommand WHERE commandType = :commandType AND isProcessed = 0")
    fun getPendingCommandByType(commandType: WSCMD): List<WebserviceCommandEntity>

}