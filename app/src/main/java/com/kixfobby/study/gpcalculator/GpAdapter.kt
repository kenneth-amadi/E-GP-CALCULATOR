package com.kixfobby.study.gpcalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*

class GpAdapter(private val c: Context, private val l: List<GpModel>) : ArrayAdapter<GpModel>(
    c, 0, l
) {
    private val credits = arrayOf("1 Unit", "2 Units", "3 Units", "4 Units", "\u2014")
    private val grades =
        arrayOf("A Grade", "B Grade", "C Grade", "D Grade", "E Grade", "F Grade", "\u2014")
    private var credit: Spinner? = null
    private var grade: Spinner? = null
    override fun getCount(): Int {
        return l.size
    }

    override fun getItem(p1: Int): GpModel {
        return l[p1]
    }

    override fun getItemId(p1: Int): Long {
        return p1.toLong()
    }

    override fun getView(position: Int, p2: View?, p3: ViewGroup): View {
        val v = LayoutInflater.from(c).inflate(R.layout.item_gp, p3, false)
        credit = v.findViewById(R.id.credit)
        grade = v.findViewById(R.id.grade)
        val cAdapter: ArrayAdapter<*> = ArrayAdapter(c, R.layout.spinner_item, credits)
        val gAdapter: ArrayAdapter<*> = ArrayAdapter(c, R.layout.spinner_item, grades)
        credit!!.adapter = cAdapter
        grade!!.adapter = gAdapter
        credit!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View, p2: Int, p3: Long) {
                val cr = credit!!.selectedItem.toString()
                var cred = 0.0
                when (cr) {
                    "1 Unit" -> cred = 1.0
                    "2 Units" -> cred = 2.0
                    "3 Units" -> cred = 3.0
                    "4 Units" -> cred = 4.0
                }
                getItem(position).setCredit(cred)
            }

            override fun onNothingSelected(p1: AdapterView<*>?) {}
        }
        grade!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View, p2: Int, p3: Long) {
                val gr = grade!!.selectedItem.toString()
                var grad = 0.0
                when (gr) {
                    "A Grade" -> grad = 5.00
                    "B Grade" -> grad = 4.00
                    "C Grade" -> grad = 3.00
                    "D Grade" -> grad = 2.00
                    "E Grade" -> grad = 1.00
                    "F Grade" -> grad = 0.00
                }
                Objects.requireNonNull(getItem(position)).setGrade(grad)
            }

            override fun onNothingSelected(p1: AdapterView<*>?) {}
        }
        return v
    }
}