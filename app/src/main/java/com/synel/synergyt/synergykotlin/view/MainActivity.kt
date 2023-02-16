package com.synel.synergyt.synergykotlin.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.synel.synergyt.synergykotlin.R
import com.synel.synergyt.synergykotlin.databinding.ActivityMainBinding
import com.synel.synergyt.synergykotlin.di.MainViewModelFactory
import com.synel.synergyt.synergykotlin.model.database.model.EmployeeEntity
import com.synel.synergyt.synergykotlin.viewmodel.MainViewModel
import com.synel.synergyt.synergykotlin.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
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

//        binding.fragmentContainer.getFragment<InOutFragment>().setButton1ClickListener()
        setFullscreen()


        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        val employee = EmployeeEntity(
            badgeNumber = "1256",
            badgeAltNumber = "789012",
            devicePassword = "password",
            deviceRoleId = 1,
            enableMealAttestation = true,
            enableMealLockout = false,
            enforceSchedule = true,
            faceAttestationAccepted = true,
            faceAttestationDate = "2022-01-01",
            faceAttestationDeviceSn = "device_123",
            fpAttestationAccepted = true,
            fpAttestationDate = "2022-01-01",
            fpAttestationDeviceSn = "device_123",
            managerEmployeeNum = "E789012",
            mealBreakLockoutPeriod = "30",
        )

        viewModel.text.observe(this) {
            // data has changed
        }



        binding.badgeNumber.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink))

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.home -> {
                    // Handle navigation to menu item 1
                    fragment = InOutFragment.newInstance("Clock Out", "Clock In", "Fragment 1")
                    Timber.d("bottomNavigation: 1")
                }
                R.id.nav_lunch -> {
                    // Handle navigation to menu item 2
                    fragment = InOutFragment.newInstance("Start Lunch", "End Lunch", "Fragment 2")
                    Timber.d("bottomNavigation: 2")
//                    viewModel.sendPunch()
//                    viewModel.getProvision()
//                    viewModel.getEmployees(resources)
                    viewModel.getHeartbeat()
                }
                R.id._break -> {
                    // Handle navigation to menu item 2
                    fragment = InOutFragment.newInstance("Start Break", "End Break", "Fragment 3")
                    Timber.d("bottomNavigation: 3")
                }
                R.id.nav_transfer -> {
                    // Handle navigation to menu item 2
                    fragment = InOutFragment.newInstance("Label 1", "Label 2", "Fragment 4")
                    Timber.d("bottomNavigation: 4")
                }

                // Add additional cases for other menu items here
            }
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                    .replace(R.id.fragment_container, it)
                    .commit()
            }
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.home

//        val dialog = KeypadFragment.newInstance("parm1", "parm2")
//        dialog.show(supportFragmentManager, "KeypadDialog")

    }

    private fun showKeypadDialog(): View.OnClickListener? {
        Timber.d("About to show keypadDialog1")
        return View.OnClickListener {
            Timber.d("About to show keypadDialog2")

            val dialog = KeypadFragment.newInstance("parm1", "parm2")
            dialog.show(supportFragmentManager, "KeypadDialog")
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
