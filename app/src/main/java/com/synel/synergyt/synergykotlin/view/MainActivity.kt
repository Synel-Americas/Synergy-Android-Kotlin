package com.synel.synergyt.synergykotlin.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.synel.synergyt.synergykotlin.R
import com.synel.synergyt.synergykotlin.databinding.ActivityMainBinding
import com.synel.synergyt.synergykotlin.di.MainViewModelFactory
import com.synel.synergyt.synergykotlin.model.database.Employee
import com.synel.synergyt.synergykotlin.viewmodel.MainViewModel
import com.synel.synergyt.synergykotlin.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var sharedViewModel: SharedViewModel

    private val TAG = "MainActivity"

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setFullscreen()


        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

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

        binding.badgeEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d(TAG, "beforeTextChanged: DATA CHANGED................ .......")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(TAG, "onTextChanged: DATA CHANGED................ .......")
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG, "afterTextChanged: DATA CHANGED................ .......")
                viewModel.getEmployee(s.toString())
            }
        })


        val frameLayout = findViewById<FrameLayout>(R.id.out)
        frameLayout.setOnClickListener {
            // Do something when the frame layout is clicked
            Log.d(TAG, "on click--------------- ")
//            viewModel.addEmployee(employee)

        }


        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Handle navigation to menu item 1
                    Log.d(TAG, "bottomNavigation: 1")
                    binding.inText.text = "IN"
                    binding.outText.text = "Out"
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_lunch -> {
                    // Handle navigation to menu item 2
                    Log.d(TAG, "bottomNavigation: 2")
                    binding.inText.text = "Start Lunch"
                    binding.outText.text = "End Lunch"
                    return@setOnNavigationItemSelectedListener true
                }
                R.id._break -> {
                    // Handle navigation to menu item 2
                    Log.d(TAG, "bottomNavigation: 3")
                    binding.inText.text = "Start Break"
                    binding.outText.text = "End Break"
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_transfer -> {
                    // Handle navigation to menu item 2
                    Log.d(TAG, "bottomNavigation: 4")
                    binding.inText.text = "IN"
                    binding.outText.text = "Out"
                    return@setOnNavigationItemSelectedListener true
                }

                // Add additional cases for other menu items here
            }
            false
        }

    }
    private fun setFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
    }
    override fun onUserInteraction() {
        super.onUserInteraction()
        setFullscreen()
    }
}
