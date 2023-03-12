package br.com.fiap.calculaimc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActivityRegister : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val txtNome = findViewById<EditText>(R.id.txtRegisterName)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitRegister)
        val loginNowBtn = findViewById<Button>(R.id.btnLogin)
        val txtEmail = findViewById<EditText>(R.id.txtRegisterEmail)
        val logoBtn = findViewById<Button>(R.id.logo_btn2)
        val txtPass = findViewById<EditText>(R.id.txtRegisterPassword)

        btnSubmit.setOnClickListener {
            val nomePersistencia = this.getSharedPreferences("dadosBasicos", Context.MODE_PRIVATE)

            val editor = nomePersistencia.edit()

            editor.putString("nome", txtNome.editableText.toString())
            editor.putString("email", txtEmail.editableText.toString())
            editor.apply()

            val nome = nomePersistencia.getString("nome", "")

            if (txtNome.editableText.toString().isEmpty() || txtEmail.editableText.toString().isEmpty() || txtPass.editableText.toString().isEmpty()){
                Toast.makeText(this, "Insira todos os Dados", Toast.LENGTH_SHORT).show()
            } else {
            Toast.makeText(this, "Bem vindo(a) $nome, entre para continuar!", Toast.LENGTH_SHORT).show()
            val i = Intent(this,  MainActivity::class.java)
            startActivity(i) }



        }
        loginNowBtn.setOnClickListener{
            val i = Intent(this,  MainActivity::class.java)
            startActivity(i)
        }

        logoBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fiap.com.br"))
            startActivity(i)
        }
    }
}
