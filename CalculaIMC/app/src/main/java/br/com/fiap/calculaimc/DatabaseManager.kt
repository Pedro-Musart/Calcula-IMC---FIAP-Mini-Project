package br.com.fiap.calculaimc

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context:Context, name: String): SQLiteOpenHelper(context, "", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val criarTabela:String = "CREATE TABLE tbl_dadosBasicos (" +
                "id_dadosBasicos INT NOT NULL," +
                "nome VARCHAR(30)," +
                "email VARCHAR(50)," +
                "senha VARCHAR(50)," +
                "PRIMARY KEY (id_dadosBasicos);"

        db!!.execSQL(criarTabela)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS tbl_dadosBasicos")
        onCreate(db)
    }

    fun cadastro(id: Int, nome: String, email: String, senha: String){
        var db:SQLiteDatabase = this.writableDatabase

        var cv = ContentValues()
        cv.put("id_dadosBasicos", id)
        cv.put("nome", nome)
        cv.put("email",email)
        cv.put("senha", senha)

        db.insert("tbl_dadosBasicos","id_dadosBasicos", cv)
    }

    fun listaDadosBasicos() : Cursor {
        var db:SQLiteDatabase = this.readableDatabase
        var cur = db.rawQuery("SELECT nome, email from tbl_dadoBasicos", null)
        return cur
    }
}