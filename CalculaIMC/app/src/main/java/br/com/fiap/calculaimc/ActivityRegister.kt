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
import java.nio.charset.Charset
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
        if (txtEmail.editableText.toString().isEmpty() || txtNome.editableText.toString().isEmpty() || txtPass.editableText.toString().isEmpty()  ){
            Toast.makeText(this, "Insira todos os Dados", Toast.LENGTH_SHORT).show()
       }else {
            val data = txtNome.editableText.toString() + ":" + txtEmail.editableText.toString()
            gravaDadoArchive("dadosBasicos", data)
            val dataReturn:String = recuperaDadoArchive("dadosBasicos")

            val tokenizer = StringTokenizer (dataReturn,":")
            val nome:String = if (tokenizer.hasMoreTokens()) tokenizer.nextToken() else "sem nome"
            val email:String = if (tokenizer.hasMoreTokens()) tokenizer.nextToken () else "sem email"


            Toast.makeText(this, "Boas vindas $nome, entre para continuar!", Toast.LENGTH_LONG).show()
            }
        })


    }

    fun gravaDadoArchive(filename: String, data: String){

        try {

            val fs:FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            fs.write(data.toByteArray())
            fs.close()

        } catch (e: FileNotFoundException){
            Log.i("gravaDadosArchive", "FileNotFoundException")
        } catch (e: IOException){
            Log.i("gravaDadoArchive", "IOException")
        }

    }

    fun recuperaDadoArchive(filename: String) : String {

        try {
            val fi = openFileInput(filename)
            val dataReturn:ByteArray = fi.readBytes()

            fi.close()

            return dataReturn.toString(Charset.defaultCharset())
        }
        catch (e: FileNotFoundException) {
            Log.i("recuperaDadoArchive", "FileNotFoundException")
            return ""
        }catch (e: IOException){
            Log.i("recuperaDadoArchive", "IOException")
            return ""
        }

    }

}
