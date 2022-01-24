package uz.uniconsoft.messanger.presentation.util

val Any.TAG: String
    get() = "TTT@${this.javaClass.canonicalName?.split('.')?.last()}"