package com.example.markpgt

import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.markpgt.dbHelper.DBHelper
import java.io.IOException
import kotlin.properties.Delegates


class SignInActivity : AppCompatActivity() {

    lateinit var ctvForgotPassword : TextView
    lateinit var btnSignIn : Button

    var loginText : EditText
    var passwordText : EditText

    private var dbHelper: DBHelper by Delegates.notNull()
    var database: SQLiteDatabase by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        dbHelper = DBHelper(this)
        database = dbHelper.writableDatabase

        ctvForgotPassword = findViewById(R.id.ctv_forgot_password)
        btnSignIn = findViewById(R.id.btn_sign_in)

        ctvForgotPassword.setOnClickListener(View.OnClickListener { _ ->
            Toast.makeText(this, "Пока в разработке...", Toast.LENGTH_SHORT).show()
        })

        btnSignIn.setOnClickListener(View.OnClickListener { _ ->
            if (loginText.text.toString() == "" || passwordText.text.toString() == "") {
                Toast.makeText(null, "Не введены логин или пароль", Toast.LENGTH_LONG).show()
            } else {
                val sql = "SELECT login, password FROM user WHERE Login = '" + loginText.text.toString().toString() + "' and Password = '" + passwordText.text.toString()
                val cursor: Cursor = database.rawQuery(sql, null)
                if (cursor.count !== 0) {
                    Toast.makeText(null, "Авторизация", Toast.LENGTH_LONG).show()
                    val intent1 = Intent(this, MainActivity::class.java) // Передача данных
                    startActivity(intent1)
                } else {
                    Toast.makeText(null, "Неверный логин или пароль", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}