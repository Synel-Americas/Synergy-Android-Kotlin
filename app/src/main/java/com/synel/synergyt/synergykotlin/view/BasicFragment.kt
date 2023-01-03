package com.synel.synergyt.synergykotlin.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.synel.synergyt.synergykotlin.R
import com.synel.synergyt.synergykotlin.di.MainViewModelFactory
import com.synel.synergyt.synergykotlin.viewmodel.BasicViewModel
import com.synel.synergyt.synergykotlin.viewmodel.MainViewModel
import com.synel.synergyt.synergykotlin.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BasicFragment : Fragment() {

    private val TAG : String = "BasicFragment"

    companion object {
        fun newInstance() = BasicFragment()
    }


    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basic, container, false)
        // Get a reference to the TextView
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.userName.observe(viewLifecycleOwner, Observer { userName ->
            // Update the UI of the Fragment
            Log.d(TAG, "onCreateView: Basic Fragmetn recieved user name = $userName")
            textView = view.findViewById(R.id.user_name)
            textView.text = userName
        })


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}