package com.synel.synergyt.synergykotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.synel.synergyt.synergykotlin.R
import timber.log.Timber
import java.lang.Exception
import java.util.*
import java.util.function.Consumer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KeypadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeypadFragment : DialogFragment() {



    private var kbTextView: TextView? = null
    private var keyboardStr: String? = ""
    private val buttonMap = hashMapOf<String, SynergyButton>()


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KeypadFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KeypadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//         Inflate the layout for this fragment
        val curView = inflater.inflate(R.layout.fragment_kaypad, container, false)
        kbTextView = curView.findViewById(R.id.textview_badge_num)
        val buttonOne = SynergyButton.ButtonBuilder("1")
            .btnCardView(curView.findViewById(R.id.button_1))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num1))
            .build()
        val buttonTwo = SynergyButton.ButtonBuilder("2")
            .btnCardView(curView.findViewById(R.id.button_2))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num2))
            .build()
        val buttonThree = SynergyButton.ButtonBuilder("3")
            .btnCardView(curView.findViewById(R.id.button_3))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num3))
            .build()
        val buttonFour = SynergyButton.ButtonBuilder("4")
            .btnCardView(curView.findViewById(R.id.button_4))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num4))
            .build()
        val buttonFive = SynergyButton.ButtonBuilder("5")
            .btnCardView(curView.findViewById(R.id.button_5))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num5))
            .build()
        val buttonSix = SynergyButton.ButtonBuilder("6")
            .btnCardView(curView.findViewById(R.id.button_6))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num6))
            .build()
        val buttonSeven = SynergyButton.ButtonBuilder("7")
            .btnCardView(curView.findViewById(R.id.button_7))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num7))
            .build()
        val buttonEight = SynergyButton.ButtonBuilder("8")
            .btnCardView(curView.findViewById(R.id.button_8))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num8))
            .build()
        val buttonNine = SynergyButton.ButtonBuilder("9")
            .btnCardView(curView.findViewById(R.id.button_9))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num9))
            .build()
        val buttonZero = SynergyButton.ButtonBuilder("0")
            .btnCardView(curView.findViewById(R.id.button_0))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num0))
            .build()
        val buttonClr = SynergyButton.ButtonBuilder("clr")
            .btnCardView(curView.findViewById(R.id.button_10))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.num10))
            .build()
        val buttonOk = SynergyButton.ButtonBuilder("ok")
            .btnCardView(curView.findViewById(R.id.button_ok))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .textView(curView.findViewById(R.id.numok))
            .build()
        val buttonX = SynergyButton.ButtonBuilder("exit")
            .btnImageView(curView.findViewById(R.id.button_x))
            .btnType(SynergyButton.ButtonType.KEYPAD)
            .build()
        val buttonList = listOf(
            buttonOne,
            buttonTwo,
            buttonThree,
            buttonFour,
            buttonFive,
            buttonSix,
            buttonSeven,
            buttonEight,
            buttonNine,
            buttonZero,
            buttonClr,
            buttonOk,
            buttonX
        )
        buttonMap["1"] = buttonOne
        buttonMap["2"] = buttonTwo
        buttonMap["3"] = buttonThree
        buttonMap["4"] = buttonFour
        buttonMap["5"] = buttonFive
        buttonMap["6"] = buttonSix
        buttonMap["7"] = buttonSeven
        buttonMap["8"] = buttonEight
        buttonMap["9"] = buttonNine
        buttonMap["0"] = buttonZero
        buttonMap["ok"] = buttonOk
        buttonMap["clr"] = buttonClr
        buttonMap["exit"] = buttonX

        buttonList.forEach(Consumer { btn: SynergyButton ->
            var isNum = true
            if (btn.textView != null) {
                btn.textView.setTextColor(
                    ContextCompat.getColor(
                        curView.context,
                        R.color.empeon_primary
                    )
                )
            }
            try {
                btn.btnOutput.toInt()
            } catch (e: Exception) {
                isNum = false
            }

//            btn.getBtnCardView().callOnClick();
            if (isNum) {
                btn.btnCardView?.setOnClickListener {
                    Timber.d("%s button clicked", btn.btnOutput)
                    if (keyboardStr!!.length < 14) {
                        keyboardStr += btn.btnOutput
                        kbTextView?.text = keyboardStr
                    }
                }
            } else {
                when (btn.btnOutput.lowercase()) {
                    "clr" -> {
                        btn.btnCardView?.setOnClickListener {
                            Timber.d("clr button clicked")
                            if (keyboardStr != null && keyboardStr!!.isNotEmpty()) {
                                keyboardStr = keyboardStr!!.substring(0, keyboardStr!!.length - 1)
                            }
                            kbTextView?.text = keyboardStr
                        }
                        btn.btnCardView?.setOnLongClickListener {
                            Timber.d("clr button long pressed")
                            keyboardStr = ""
                            kbTextView?.text = ""
                            true
                        }
                    }
                    "ok" -> btn.btnCardView?.setOnClickListener {
                        Timber.d("ok button clicked")
//                        listener!!.onInput(keyboardStr)
                        keyboardStr = ""
                        kbTextView?.text = ""
                    }
                    "exit" -> btn.btnImageView?.setOnClickListener {
                        Timber.d("x button clicked")
//                        listener!!.onInput("")
                        keyboardStr = ""
                        kbTextView?.text = ""
                    }
                }
            }
        })
        return curView
    }


}