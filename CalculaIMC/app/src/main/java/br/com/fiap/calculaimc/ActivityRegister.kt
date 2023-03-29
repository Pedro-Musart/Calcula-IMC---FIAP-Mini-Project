package br.com.fiap.calculaimc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
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
        txtEmail.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        val logoBtn = findViewById<Button>(R.id.logo_btn)
        val txtPass = findViewById<EditText>(R.id.txtRegisterPassword)

        loginNowBtn.setOnClickListener{
            val i = Intent(this,  MainActivity::class.java)
            startActivity(i)
            finish()
        }

        logoBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fiap.com.br"))
            startActivity(i)

        }

        btnSubmit.setOnClickListener(View.OnClickListener {

            if (txtNome.text.toString().isEmpty() || txtEmail.text.toString().isEmpty() || txtPass.text.toString().isEmpty()) {

                Toast.makeText(this, "Por favor, insira todos os Dados", Toast.LENGTH_SHORT).show()

            } else if (!isEmailValid(txtEmail.text.toString())){
                Toast.makeText(this, "Insira um e-mail válido, por favor!", Toast.LENGTH_SHORT).show()

            } else {
                val db = DatabaseManager(this, "tbl_dadosBasicos")
                db.cadastro(
                    txtNome.text.toString(),
                    txtEmail.text.toString(),
                    txtPass.text.toString()
                )

                var nome = txtNome.text.toString()
                Toast.makeText(this, "Bem vindo $nome, entre para continuar!", Toast.LENGTH_SHORT).show()

                val i = Intent(this,  MainActivity::class.java)
                startActivity(i)
                finish()
            }
        })

    }
    fun isEmailValid(email: String): Boolean {
        if (email.contains(" ")) {
            return false
        }
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

}
