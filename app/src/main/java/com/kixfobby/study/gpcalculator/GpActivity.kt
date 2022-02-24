package com.kixfobby.study.gpcalculator

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class GpActivity() : AppCompatActivity(), View.OnClickListener {
    private val credits = arrayOf("1 Unit", "2 Units", "3 Units", "4 Units", "\u2014")
    private val grades =
        arrayOf("A Grade", "B Grade", "C Grade", "D Grade", "E Grade", "F Grade", "\u2014")
    private val credit = arrayOfNulls<Button>(32)
    private val grade = arrayOfNulls<Button>(32)
    private val gp = arrayOfNulls<GpModel>(credit.size)

    @get:VisibleForTesting
    var adView: AdView? = null
        private set
    private var item_view: View? = null
    private var calculate: Button? = null
    private var i = 0
    private var j = 0
    private var result: TextView? = null
    private var mBehavior: BottomSheetBehavior<*>? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gp)
        MobileAds.initialize(this) {
            Toasty.success(baseContext, "Load Successful!")
        }
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView!!.loadAd(adRequest)
        val bottom_sheet = findViewById<View>(R.id.bottom_sheet)
        mBehavior = BottomSheetBehavior.from(bottom_sheet)
        item_view = findViewById(R.id.no_item_view)
        credit[0] = findViewById(R.id.credit1)
        credit[1] = findViewById(R.id.credit2)
        credit[2] = findViewById(R.id.credit3)
        credit[3] = findViewById(R.id.credit4)
        credit[4] = findViewById(R.id.credit5)
        credit[5] = findViewById(R.id.credit6)
        credit[6] = findViewById(R.id.credit7)
        credit[7] = findViewById(R.id.credit8)
        credit[8] = findViewById(R.id.credit9)
        credit[9] = findViewById(R.id.credit10)
        credit[10] = findViewById(R.id.credit11)
        credit[11] = findViewById(R.id.credit12)
        credit[12] = findViewById(R.id.credit13)
        credit[13] = findViewById(R.id.credit14)
        credit[14] = findViewById(R.id.credit15)
        credit[15] = findViewById(R.id.credit16)
        credit[16] = findViewById(R.id.credit17)
        credit[17] = findViewById(R.id.credit18)
        credit[18] = findViewById(R.id.credit19)
        credit[19] = findViewById(R.id.credit20)
        credit[20] = findViewById(R.id.credit21)
        credit[21] = findViewById(R.id.credit22)
        credit[22] = findViewById(R.id.credit23)
        credit[23] = findViewById(R.id.credit24)
        credit[24] = findViewById(R.id.credit25)
        credit[25] = findViewById(R.id.credit26)
        credit[26] = findViewById(R.id.credit27)
        credit[27] = findViewById(R.id.credit28)
        credit[28] = findViewById(R.id.credit29)
        credit[29] = findViewById(R.id.credit30)
        credit[30] = findViewById(R.id.credit31)
        credit[31] = findViewById(R.id.credit32)
        grade[0] = findViewById(R.id.grade1)
        grade[1] = findViewById(R.id.grade2)
        grade[2] = findViewById(R.id.grade3)
        grade[3] = findViewById(R.id.grade4)
        grade[4] = findViewById(R.id.grade5)
        grade[5] = findViewById(R.id.grade6)
        grade[6] = findViewById(R.id.grade7)
        grade[7] = findViewById(R.id.grade8)
        grade[8] = findViewById(R.id.grade9)
        grade[9] = findViewById(R.id.grade10)
        grade[10] = findViewById(R.id.grade11)
        grade[11] = findViewById(R.id.grade12)
        grade[12] = findViewById(R.id.grade13)
        grade[13] = findViewById(R.id.grade14)
        grade[14] = findViewById(R.id.grade15)
        grade[15] = findViewById(R.id.grade16)
        grade[16] = findViewById(R.id.grade17)
        grade[17] = findViewById(R.id.grade18)
        grade[18] = findViewById(R.id.grade19)
        grade[19] = findViewById(R.id.grade20)
        grade[20] = findViewById(R.id.grade21)
        grade[21] = findViewById(R.id.grade22)
        grade[22] = findViewById(R.id.grade23)
        grade[23] = findViewById(R.id.grade24)
        grade[24] = findViewById(R.id.grade25)
        grade[25] = findViewById(R.id.grade26)
        grade[26] = findViewById(R.id.grade27)
        grade[27] = findViewById(R.id.grade28)
        grade[28] = findViewById(R.id.grade29)
        grade[29] = findViewById(R.id.grade30)
        grade[30] = findViewById(R.id.grade31)
        grade[31] = findViewById(R.id.grade32)
        for (i in credit.indices) {
            credit[i]?.visibility = View.GONE
            grade[i]?.visibility = View.GONE
        }
        calculate = findViewById(R.id.calculate)
        calculate!!.setOnClickListener(this)
        result = findViewById(R.id.result)
        var i = 0
        while (i < credit.size) {
            gp[i] = GpModel()
            credit[i]?.setOnClickListener(this)
            grade[i]?.setOnClickListener(this)
            i++
        }
    }

    override fun onClick(v: View) {
        for (s in credit.indices) {
            if ((v == credit[s])) putCredit(v) else if ((v == grade[s])) putGrade(v)
        }
        if ((v == calculate)) solve()
    }

    private fun putCredit(v: View) {
        val creditList = AlertDialog.Builder(this@GpActivity)
        creditList.setTitle("Set Your Course Unit")
        creditList.setItems(credits, object : DialogInterface.OnClickListener {
            @SuppressLint("SetTextI18n")
            override fun onClick(dialog: DialogInterface, item: Int) {
                for (j in credit.indices) {
                    if ((v == credit[j])) {
                        if ((credits[item] == "\u2014")) {
                            gp[j]!!.credit = 0.0
                            credit.get(j)!!.text = "Course " + (j + 1) + " Credit"
                        } else {
                            credit.get(j)!!.text = credits.get(item)
                            gp[j]!!.fetchCredit(credits[item])
                        }
                    }
                }
            }
        })
        creditList.show()
    }

    private fun putGrade(v: View) {
        val gradeList = AlertDialog.Builder(this@GpActivity)
        gradeList.setTitle("Set Your Grade")
        gradeList.setItems(grades, object : DialogInterface.OnClickListener {
            @SuppressLint("SetTextI18n")
            override fun onClick(dialog: DialogInterface, item: Int) {
                for (j in grade.indices) {
                    if ((v == grade[j])) {
                        if ((grades[item] == "\u2014")) {
                            gp[j]!!.grade = 0.0
                            grade.get(j)!!.text = "Course " + (j + 1) + " Grade"
                        } else {
                            grade.get(j)!!.text = grades.get(item)
                            gp[j]!!.fetchGrade(grades[item])
                        }
                    }
                }
            }
        })
        gradeList.show()
    }

    private fun solve() {
        val calc = GpCalc()
        if (grade[0]!!.visibility == View.VISIBLE) run {
            var i = 0
            while (i < gp.size) {
                if (credit.get(i)!!.getVisibility() == View.VISIBLE) {
                    if ((credit.get(i)!!.getText() == "Course " + (i + 1) + " Credit")) {
                        Toasty.warning(
                            this,
                            "Please, set course " + (i + 1) + " Credit !",
                            Toast.LENGTH_SHORT
                        ).show()
                        i = gp.size + 1
                        result!!.setText("...")
                    } else {
                        if (grade.get(i)!!.getVisibility() == View.VISIBLE) {
                            if ((grade.get(i)!!.getText() == "Course " + (i + 1) + " Grade")) {
                                Toasty.warning(
                                    this,
                                    "Please, set course " + (i + 1) + " Grade !",
                                    Toast.LENGTH_SHORT
                                ).show()
                                i = gp.size + 1
                                result!!.setText("...")
                            } else {
                                calc.credit = gp.get(i)!!.credit
                                calc.grade = gp.get(i)!!.grade
                                calc.calcTotalCredit()
                                calc.calcTotalGrade()
                                calc.calcGpa()
                                result!!.setText(calc.gpa.toString())
                            }
                        }
                    }
                }
                i++
            }
        } else {
            Toasty.warning(this, "Nothing to calculate, Add items !", Toast.LENGTH_SHORT).show()
            result!!.text = "..."
        }
    }

    private fun addItem() {
        try {
            item_view!!.visibility = View.INVISIBLE
            credit.get(i)!!.visibility = View.VISIBLE
            grade.get(i)!!.visibility = View.VISIBLE
            j = i
            i++
        } catch (e: Exception) {
            Toasty.normal(this, "Maximum item reached !", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun removeItem() {
        try {
            if (j == 0) if (item_view!!.visibility == View.VISIBLE) Toasty.normal(
                this,
                "No item to remove !",
                Toast.LENGTH_SHORT
            ).show() else item_view!!.visibility = View.VISIBLE
            credit.get(j)!!.visibility = View.GONE
            gp[j]!!.credit = 0.0
            credit.get(j)!!.text = "Course " + (j + 1) + " Credit"
            grade.get(j)!!.visibility = View.GONE
            gp[j]!!.grade = 0.0
            grade.get(j)!!.text = "Course " + (j + 1) + " Grade"
            i = j
            j--
        } catch (e: Exception) {
            item_view!!.visibility = View.VISIBLE
            Toasty.normal(this, "No item to remove !", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun removeAll() {
        try {
            if (j == 0) if (item_view!!.visibility == View.VISIBLE) Toasty.normal(
                this,
                "No item to remove !",
                Toast.LENGTH_SHORT
            ).show() else item_view!!.visibility = View.VISIBLE
            while (j >= 0) {
                credit.get(j)!!.visibility = View.GONE
                gp[j]!!.credit = 0.0
                credit.get(j)!!.text = "Course " + (j + 1) + " Credit"
                grade.get(j)!!.visibility = View.GONE
                gp[j]!!.grade = 0.0
                grade.get(j)!!.text = "Course " + (j + 1) + " Grade"
                j--
                item_view!!.visibility = View.VISIBLE
            }
            j++
            i = j
        } catch (e: Exception) {
            item_view!!.visibility = View.VISIBLE
            Toasty.normal(this, "No item to remove !", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun clearInputs() {
        if (i <= 0) Toasty.normal(this, "Nothing to clear !", Toast.LENGTH_SHORT).show()
        while (i >= 0) {
            gp[i]!!.credit = 0.0
            gp[i]!!.grade = 0.0
            credit.get(i)!!.text = "Course " + (i + 1) + " Credit"
            grade.get(i)!!.text = "Course " + (i + 1) + " Grade"
            i--
        }
        i = j + 1
    }

    private fun showMenu_A() {
        if (mBehavior!!.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        @SuppressLint("InflateParams") val view =
            layoutInflater.inflate(R.layout.menu_sheet_1, null)
        view.findViewById<View>(R.id.add_item).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                addItem()
            }
        })
        view.findViewById<View>(R.id.remove_item).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                removeItem()
            }
        })
        view.findViewById<View>(R.id.clear_inputs)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    clearInputs()
                }
            })
        view.findViewById<View>(R.id.remove_all).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                removeAll()
            }
        })
        view.findViewById<View>(R.id.more).setOnClickListener { /*showMenu_B();*/ }
        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(mBottomSheetDialog!!.window)?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        mBottomSheetDialog!!.show()
        mBottomSheetDialog!!.setOnDismissListener { mBottomSheetDialog = null }
    }

    private fun showMenu_B() {
        if (mBehavior!!.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        @SuppressLint("InflateParams") val view =
            layoutInflater.inflate(R.layout.menu_sheet_2, null)
        view.findViewById<View>(R.id.specify_items)
            .setOnClickListener { addItem() }
        view.findViewById<View>(R.id.semesters).setOnClickListener { removeItem() }
        view.findViewById<View>(R.id.print).setOnClickListener {
            //
        }
        view.findViewById<View>(R.id.about).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                //
            }
        })
        view.findViewById<View>(R.id.share).setOnClickListener {
            //
        }
        view.findViewById<View>(R.id.rate_app).setOnClickListener {
            //
        }
        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog!!.window!!.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        mBottomSheetDialog!!.show()
        mBottomSheetDialog!!.setOnDismissListener { mBottomSheetDialog = null }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add("Options")
            .setIcon(R.drawable.ic_menu)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        showMenu_A()
        return true
    }

    public override fun onPause() {
        if (adView != null) {
            adView!!.pause()
        }
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
        if (adView != null) {
            adView!!.resume()
        }
        /*if (!mInterstitialAd.isLoaded()) {
            requestNewInterstitial();
        }*/
    }

    public override fun onDestroy() {
        if (adView != null) {
            adView!!.destroy()
        }
        super.onDestroy()
    }

    private fun checkIds() {
        if ((APP_ID == getString(R.string.admob_app_id))) {
            Log.w(TAG, "Your admob_app_id is not configured correctly, please see the README")
        }
    }

    companion object {
        private val TAG = "GpActivity"
        private val APP_ID = "ca-app-pub-2571017324761827~2303822763"
    }
}