package br.com.fiap.calculaimc

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtNome = findViewById<EditText>(R.id.TextPersonName)
        val txtEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val btnSubmit = findViewById<Button>(R.id.button)
        val newUserBtn = findViewById<Button>(R.id.new_user_btn)
        val logoBtn = findViewById<Button>(R.id.logo_btn)

        btnSubmit.setOnClickListener {
            val dadosPersistencia = this.getSharedPreferences("dadosBasicos", Context.MODE_PRIVATE)

            val editor = dadosPersistencia.edit()

            editor.putString("nome", txtNome.editableText.toString())
            editor.putString("email", txtEmail.editableText.toString())
            editor.apply()

            val nome = dadosPersistencia.getString("nome", "")

            Toast.makeText(this, "Bem vindo(a) $nome", Toast.LENGTH_SHORT).show()
        }




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