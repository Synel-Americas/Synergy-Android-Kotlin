package com.synel.synergyt.synergykotlin.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.synel.synergyt.synergykotlin.R
import com.synel.synergyt.synergykotlin.viewmodel.BasicViewModel

class BasicFragment : Fragment() {

    companion object {
        fun newInstance() = BasicFragment()
    }

    private lateinit var viewModel: BasicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_basic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BasicViewModel::class.java)
        // TODO: Use the ViewModel
    }

}