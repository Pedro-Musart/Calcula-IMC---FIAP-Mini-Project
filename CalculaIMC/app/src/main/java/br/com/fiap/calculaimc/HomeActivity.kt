package br.com.fiap.calculaimc

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    val txvName = findViewById<TextView>(R.id.txvUserName)
    val txvEmail = findViewById<TextView>(R.id.txvUserEmail)
    val idSharedPreferences = this.getSharedPreferences("id_persistencia", Context.MODE_PRIVATE)
    val id = idSharedPreferences.getLong("id", 0)
    val db = DatabaseManager (this, "tbl_dadosBasicos")
    val cursor = db.listaDadosBasicos(id.toLong())
    var nome = ""
    var email = ""

        if (cursor.moveToFirst()) {
            nome = cursor.getString(cursor.getColumnIndex("nome"))
            email = cursor.getString(cursor.getColumnIndex("email"))
        }

        txvName.text = nome
        txvEmail.text = email
}
}