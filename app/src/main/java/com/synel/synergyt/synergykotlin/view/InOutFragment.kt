package com.synel.synergyt.synergykotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.synel.synergyt.synergykotlin.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [inOutFirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InOutFragment : Fragment() {

    lateinit var button1: RelativeLayout
    lateinit var button2: RelativeLayout


    companion object {
        fun newInstance(label1: String, label2: String, fragmentName: String): InOutFragment {
            val fragment = InOutFragment()
            val args = Bundle()
            args.putString("label1", label1)
            args.putString("label2", label2)
            args.putString("fragmentName", fragmentName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_in_out, container, false)
        val label1 = view.findViewById<TextView>(R.id.label1)
        val label2 = view.findViewById<TextView>(R.id.label2)

        //get the values from arguments
        val label1Value = arguments?.getString("label1")
        val label2Value = arguments?.getString("label2")
        val fragmentName = arguments?.getString("fragmentName")

        //set the values on textview
        label1.text = label1Value
        label2.text = label2Value

        button1 = view.findViewById<RelativeLayout>(R.id.button1)
        button2 = view.findViewById<RelativeLayout>(R.id.button2)
        // Add onClickListener to button1
        view.findViewById<RelativeLayout>(R.id.button1).setOnClickListener {
            // Perform action when button1 is clicked
        }

        // Add onClickListener to button2
        view.findViewById<RelativeLayout>(R.id.button2).setOnClickListener {
            // Perform action when button2 is clicked
        }

        return view
    }

//    fun setButton1ClickListener(){
//        button1.setOnClickListener{
//
//        }
//    }
//    fun setButton2ClickListener(){}
}