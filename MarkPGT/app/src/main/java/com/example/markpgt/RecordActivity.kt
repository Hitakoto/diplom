package com.example.markpgt

import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.markpgt.dbHelper.DBHelper
import java.io.IOException

class RecordActivity : AppCompatActivity() {

    lateinit var linRec: LinearLayout
    lateinit var tableRec: TableLayout

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        linRec = findViewById(R.id.linearRec)
        tableRec = findViewById(R.id.tableRec)

        dbHelper = DBHelper(this)

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

        val cursor = database.rawQuery("SELECT RecordBook.semester, Students.surname, Discipline.nameDis, RecordBook.numberHours, Grades.nameGrade, RecordBook.examDate, RecordBook.lateHours, Teachers.surname FROM RecordBook, Discipline, Grades, Students, Teachers WHERE RecordBook.student = Students.idStudent AND RecordBook.discipline = Discipline.idDiscipline AND RecordBook.gradeSemester = Grades.idGrade AND RecordBook.teacher = Teachers.idTeacher", null)
        if (cursor.moveToFirst()) {
            do {
                val row = TableRow(this)
                val textSem = TextView(this)
                textSem.text = cursor.getInt(0).toString() + " "
                textSem.setTextColor(Color.WHITE)
                textSem.gravity = Gravity.CENTER

                val textStud = TextView(this)
                textStud.text = cursor.getInt(1).toString() + " "
                textStud.setTextColor(Color.WHITE)
                textStud.gravity = Gravity.CENTER

                val textDis = TextView(this)
                textDis.text = cursor.getInt(2).toString() + " "
                textDis.setTextColor(Color.WHITE)
                textDis.gravity = Gravity.CENTER

                val textHour = TextView(this)
                textHour.text = cursor.getInt(3).toString() + " "
                textHour.setTextColor(Color.WHITE)
                textHour.gravity = Gravity.CENTER

                val textGrade = TextView(this)
                textGrade.text = cursor.getInt(4).toString() + " "
                textGrade.setTextColor(Color.WHITE)
                textGrade.gravity = Gravity.CENTER

                val textExam = TextView(this)
                textExam.text = cursor.getInt(5).toString() + " "
                textExam.setTextColor(Color.WHITE)
                textExam.gravity = Gravity.CENTER

                val textHourL = TextView(this)
                textHourL.text = cursor.getInt(6).toString() + " "
                textHourL.setTextColor(Color.WHITE)
                textHourL.gravity = Gravity.CENTER

                val textTea = TextView(this)
                textTea.text = cursor.getInt(7).toString() + " "
                textTea.setTextColor(Color.WHITE)
                textTea.gravity = Gravity.CENTER

                row.addView(textSem)
                row.addView(textStud)
                row.addView(textDis)
                row.addView(textHour)
                row.addView(textGrade)
                row.addView(textExam)
                row.addView(textHourL)
                row.addView(textTea)
                tableRec.addView(row)
            } while (cursor.moveToNext())
        } else Log.d("mLog", "0 rows")
        cursor.close()
    }
}