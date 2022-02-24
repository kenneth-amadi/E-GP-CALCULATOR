package com.kixfobby.study.gpcalculator

open class GpModel {
    var grade = 0.0
    var credit = 0.0
    fun fetchCredit(cr: String?) {
        credit = when (cr) {
            "1 Unit" -> 1.0
            "2 Units" -> 2.0
            "3 Units" -> 3.0
            "4 Units" -> 4.0
            else -> 0.0
        }
    }

    fun fetchGrade(gr: String?) {
        grade = when (gr) {
            "A Grade" -> 5.00
            "B Grade" -> 4.00
            "C Grade" -> 3.00
            "D Grade" -> 2.00
            "E Grade" -> 1.00
            "F Grade" -> 0.00
            else -> 0.0
        }
    }

    @JvmName("setCredit1")
    fun setCredit(c: Double) {
        credit = c
    }

    @JvmName("setGrade1")
    fun setGrade(g: Double) {
        grade = g
    }

    @JvmName("getCredit1")
    fun getCredit(): Double {
        return credit
    }

    @JvmName("getGrade1")
    fun getGrade(): Double {
        return grade
    }
}