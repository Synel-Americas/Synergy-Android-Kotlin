package com.synel.synergyt.synergykotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.synel.synergyt.synergykotlin.R
import com.synel.synergyt.synergykotlin.di.MainViewModelFactory
import com.synel.synergyt.synergykotlin.model.database.Employee
import com.synel.synergyt.synergykotlin.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val TAG = "MainActivity"

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val employee = Employee(
            badgeNumber = "1256",
            badgeAltNumber = "789012",
            clock_action = "clock_in",
            devicePassword = "password",
            deviceRoleId = "1",
            deviceVerifyMode = "fingerprint",
            employeeNumber = "E123456",
            enableMealAttestation = "true",
            enableMealLockout = "false",
            enforceSchedule = true,
            faceAttestationAccepted = true,
            faceAttestationDate = "2022-01-01",
            faceAttestationDeviceSn = "device_123",
            firstName = "John",
            formattedName = "John Smith",
            fpAttestationAccepted = true,
            fpAttestationDate = "2022-01-01",
            fpAttestationDeviceSn = "device_123",
            managerEmployeeNum = "E789012",
            mealBreakLockoutPeriod = "30",
            submitted_date = "2022-01-01",
            sync_sent_time = "2022-01-01",
            sync_status = 1,
            transactionId = 123,
            updated_date = "2022-01-01"
        )

        viewModel.text.observe(this, Observer {
            // data has changed
        })

        viewModel.employees.observe(this, Observer {
            // data has changed
            Log.d(TAG, "onCreate: Employee has changed 00000000000000000000000000000000")
            findViewById<TextView>(R.id.temp).text = viewModel.employees.value?.firstName ?: "wew"
        })

        val frameLayout = findViewById<FrameLayout>(R.id.out)
        frameLayout.setOnClickListener {
            // Do something when the frame layout is clicked
            Log.d(TAG, "on click--------------- " + employee.badgeNumber)
//            viewModel.addEmployee(employee)
            viewModel.getEmployee("1256")
        }



    }
}