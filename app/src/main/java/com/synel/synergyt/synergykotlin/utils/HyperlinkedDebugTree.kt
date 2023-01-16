package com.synel.synergyt.synergykotlin.utils

import android.util.Log
import timber.log.Timber.DebugTree

class HyperlinkedDebugTree : DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        val lineNum = element.lineNumber
        var methodName = element.methodName
        val fileName = element.fileName
        if (methodName.contains("lambda") && methodName.split(Regex("\\$")).toTypedArray().size > 1) {
            methodName = methodName.split(Regex("\\$")).toTypedArray()[0]

        }
        return "($fileName:$lineNum) $methodName()" //Having the output specifically as (fileName:lineNumber)
    } //allows the logcat to create a hyperlink to the specific line
}