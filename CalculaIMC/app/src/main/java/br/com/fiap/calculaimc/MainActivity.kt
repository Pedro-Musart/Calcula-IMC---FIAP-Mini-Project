package br.com.fiap.calculaimc

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtEmail = findViewById<EditText>(R.id.editTextEmail)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitLogin)
        val newUserBtn = findViewById<Button>(R.id.new_user_btn)
        val logoBtn = findViewById<Button>(R.id.logo_btn)

        logoBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fiap.com.br"))
            startActivity(i)
        }

        newUserBtn.setOnClickListener{
            val i = Intent(this,  ActivityRegister::class.java)
            startActivity(i)
        }
    }
}