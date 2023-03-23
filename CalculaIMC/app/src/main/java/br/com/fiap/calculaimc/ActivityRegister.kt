package br.com.fiap.calculaimc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.StringTokenizer

class ActivityRegister : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val txtNome = findViewById<EditText>(R.id.txtRegisterName)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitRegister)
        val loginNowBtn = findViewById<Button>(R.id.btnLogin)
        val txtEmail = findViewById<EditText>(R.id.txtRegisterEmail)
        val logoBtn = findViewById<Button>(R.id.logo_btn)
        val txtPass = findViewById<EditText>(R.id.txtRegisterPassword)


        loginNowBtn.setOnClickListener{
            val i = Intent(this,  MainActivity::class.java)
            startActivity(i)
        }

        logoBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fiap.com.br"))
            startActivity(i)
        }

        btnSubmit.setOnClickListener(View.OnClickListener {

        val db = DatabaseManager (this, "tbl_dadosBasicos")
            db.cadastro(txtNome.text.toString(),txtEmail.text.toString(),txtPass.text.toString())


            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
    })



}
}
