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


class SignInActivity : AppCompatActivity() {

    lateinit var ctvForgotPassword : TextView
    lateinit var btnSignIn : Button

    lateinit var loginText : EditText
    lateinit var passwordText : EditText

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        loginText = findViewById(R.id.login)
        passwordText = findViewById(R.id.password)

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

        ctvForgotPassword = findViewById(R.id.ctv_forgot_password)
        btnSignIn = findViewById(R.id.btn_sign_in)

        ctvForgotPassword.setOnClickListener(View.OnClickListener { _ ->
            Toast.makeText(this, "Пока в разработке...", Toast.LENGTH_SHORT).show()
        })

        btnSignIn.setOnClickListener(View.OnClickListener { _ ->
            if (loginText.text.toString() == "" || passwordText.text.toString() == "") {
                Toast.makeText(this, "Не введены логин или пароль", Toast.LENGTH_LONG).show()
            } else {
                val sql = "SELECT idUser, email, description, login, password FROM users WHERE login = '" + loginText.text.toString() + "' AND password = '" + passwordText.text.toString() + "'"
                val cursor: Cursor = database.rawQuery(sql, null)
                if (cursor.count !== 0) {
                    Toast.makeText(this, "Авторизация", Toast.LENGTH_LONG).show()
                    val intent1 = Intent(this, MainActivity::class.java)
                    val user = HashMap<String, Any>()
                    cursor.moveToFirst()
                    while (!cursor.isAfterLast) {
                        user["idUser"] = cursor.getString(0)
                        user["email"] = cursor.getString(1)
                        user["description"] = cursor.getString(2)
                        cursor.moveToNext()
                    }
                    intent1.putExtra("Email", user["email"].toString())
                    intent1.putExtra("Description", user["description"].toString())
                    intent1.putExtra("iduser", user["idUser"].toString())
                    startActivity(intent1)
                } else {
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}