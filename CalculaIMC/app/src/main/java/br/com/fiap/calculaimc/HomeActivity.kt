package br.com.fiap.calculaimc

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


    val txvName = findViewById<TextView>(R.id.txvUserName)
    val txvEmail = findViewById<TextView>(R.id.txvUserEmail)
    val dadosBasicosPersistenciaSharedPreferences = this.getSharedPreferences("dadosBasicos", Context.MODE_PRIVATE)
    val nome = dadosBasicosPersistenciaSharedPreferences.getString("nome", "")
    val email = dadosBasicosPersistenciaSharedPreferences.getString("email", "")

    txvName.text = nome
    txvEmail.text = email
}
}