package com.example.markpgt.mainFragments.home

import android.content.Intent
import android.content.Intent.getIntent
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.markpgt.MainActivity
import com.example.markpgt.R
import com.example.markpgt.RecordActivity
import com.example.markpgt.dbHelper.DBHelper
import java.io.IOException

class HomeFragment : Fragment() {

    lateinit var ivbRecord : ImageView
    lateinit var v : View

    lateinit var linRet: LinearLayout
    lateinit var tableRet: TableLayout

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        v = inflater.inflate(R.layout.fragment_home, container, false)

        ivbRecord = v.findViewById(R.id.ivb_rec)

        ivbRecord.setOnClickListener(View.OnClickListener { _ ->
            startActivity(Intent(activity, RecordActivity::class.java))
        })

        linRet = v.findViewById(R.id.linearRatings)
        tableRet = v.findViewById(R.id.ratings)

        dbHelper = DBHelper(activity!!)

        try {
            dbHelper.updateDataBase()
        } catch (mIOException: IOException) {
            throw Error("UnableToUpdateDatabase")
        }

        try {
            database = dbHelper.writableDatabase
        } catch (mIOException: SQLException) {
            throw mIOException
        }

        val cursor = database.rawQuery("SELECT Discipline.nameDis, Grades.nameGrade FROM RecordBook, Discipline, Grades WHERE RecordBook.discipline = Discipline.idDiscipline AND RecordBook.gradeSemester = Grades.idGrade", null)
        if (cursor.moveToFirst()) {
            do {
                val row = TableRow(activity!!)
                val textDis = TextView(activity!!)
                textDis.text = cursor.getInt(0).toString() + " "
                textDis.setTextColor(Color.rgb(24, 79, 154))
                textDis.gravity = Gravity.CENTER

                val textGrade = TextView(activity!!)
                textGrade.text = cursor.getInt(1).toString() + " "
                textGrade.setTextColor(Color.rgb(24, 79, 154))
                textGrade.gravity = Gravity.CENTER

                row.addView(textDis)
                row.addView(textGrade)
                tableRet.addView(row)
            } while (cursor.moveToNext())
        } else Log.d("mLog", "0 rows")
        cursor.close()

        return v
    }
}