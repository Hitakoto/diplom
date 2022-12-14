package com.example.markpgt

import android.content.Intent
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.markpgt.dbHelper.DBHelper
import java.io.IOException

class EditUserInfoActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var database: SQLiteDatabase

    lateinit var btnCon : Button

    lateinit var editEm : EditText
    lateinit var editDes : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_info)

        btnCon = findViewById(R.id.btn_confirm_edit_user_info)

        editEm = findViewById(R.id.emailEdit)
        editDes = findViewById(R.id.descriptionEdit)

        val id : String? = intent.getStringExtra("Id")

        println("auishdiuaefuiaelfewuafuiyeuaiyfiuaeyfiuyueifueyfiuaeiufyiueayfiueayufiaeusfuyegsafuiguyeagfuieagfyaegsfukgaeyugfiueagfyuagefukyuasgfuaesgfyusgaefuigauyefguaesgfuyesgafugsaeyufgiuleasgfykegafluiagfuaegsifeue "+id)

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

        btnCon.setOnClickListener(View.OnClickListener { _ ->
            val queryEdit = "UPDATE Users SET email = '" + editEm.text.toString() + "', description = '" + editDes.text.toString() + "' WHERE idUser = '" + id + "'"
            database.execSQL(queryEdit)
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_LONG).show()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        })
    }
}