package com.kixfobby.study.gpcalculator

internal class GpCalc : GpModel() {
    var gpa = 0.0
        private set
    private var totalGrade = 0.0
    private var totalCredit = 0.0
    fun calcTotalGrade() {
        val sumGrade = credit * grade
        totalGrade += sumGrade
    }

    fun calcTotalCredit() {
        totalCredit += credit
    }

    fun calcGpa() {
        gpa = totalGrade / totalCredit
    }
}