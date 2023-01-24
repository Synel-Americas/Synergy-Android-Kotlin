package com.synel.synergyt.synergykotlin.view

import android.widget.ImageView
import androidx.cardview.widget.CardView
import android.widget.TextView

class SynergyButton private constructor(builder: ButtonBuilder) {
    val btnCardView: CardView?
    val btnImageView: ImageView?
    val btnOutput: String
    val btnType: ButtonType?
    val textView: TextView?

    enum class ButtonType {
        KEYPAD, CLOCKIN, CLOCKOUT
    }

    init {
        btnCardView = builder.btnCardView
        btnImageView = builder.btnImageView
        btnOutput = builder.btnOutput
        btnType = builder.btnType
        textView = builder.textView
    }

    class ButtonBuilder(val btnOutput: String) {
        var btnCardView: CardView? = null
        var btnImageView: ImageView? = null
        var btnType: ButtonType? = null
        var textView: TextView? = null
        fun btnImageView(btnImageView: ImageView?): ButtonBuilder {
            this.btnImageView = btnImageView
            return this
        }

        fun btnCardView(btnCardView: CardView?): ButtonBuilder {
            this.btnCardView = btnCardView
            return this
        }

        fun btnType(btnType: ButtonType?): ButtonBuilder {
            this.btnType = btnType
            return this
        }

        fun textView(textView: TextView?): ButtonBuilder {
            this.textView = textView
            return this
        }

        fun build(): SynergyButton {
            return SynergyButton(this)
        }
    }
}