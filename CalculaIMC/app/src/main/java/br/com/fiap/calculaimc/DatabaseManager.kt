package br.com.fiap.calculaimc

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context:Context,name: String): SQLiteOpenHelper(context, name, null, 3) {

    override fun onCreate(db: SQLiteDatabase?) {
        val criarTabela:String = "CREATE TABLE tbl_dadosBasicos (" +
                "id_dadosBasicos INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome VARCHAR(30)," +
                "email VARCHAR(50)," +
                "senha VARCHAR(50));"

        db!!.execSQL(criarTabela)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS tbl_dadosBasicos")
        onCreate(db)
    }

    fun cadastro(nome: String, email: String, senha: String){
        var db:SQLiteDatabase = this.writableDatabase
        var cv = ContentValues()
        cv.put("nome", nome)
        cv.put("email",email)
        cv.put("senha",senha)

        db.insert("tbl_dadosBasicos","id_dadosBasicos", cv)
    }

    fun listaDadosBasicos(id: Long): Cursor {
        val db = this.readableDatabase
        return db.rawQuery(
            "SELECT nome, email FROM tbl_dadosBasicos WHERE id_dadosBasicos = ?",
            arrayOf(id.toString())
        )
    }

    fun login(email: String, password: String): Pair<Boolean, Long?> {
        val db = this.readableDatabase
        val query = "SELECT id_dadosBasicos FROM tbl_dadosBasicos WHERE email = ? AND senha = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        val idIndex = cursor.getColumnIndex("id_dadosBasicos")
        val id = if (idIndex >= 0 && cursor.moveToFirst()) {
            cursor.getLong(idIndex)
        } else null
        cursor.close()
        db.close()
        return Pair(id != null, id)
    }


}