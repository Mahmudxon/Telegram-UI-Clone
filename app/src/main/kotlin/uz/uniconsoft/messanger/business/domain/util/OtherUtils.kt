package uz.uniconsoft.messanger.business.domain.util

val Any.TAG: String
    get() = "TTT@${this.javaClass.canonicalName?.split('.')?.last()}"