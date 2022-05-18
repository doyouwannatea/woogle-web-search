package com.example.woogle.services

import android.content.Context
import android.util.Log
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class FileSerivce(private val context: Context) {
    fun saveText(filename: String, content: String) {
        var fos: FileOutputStream? = null
        try {
            fos = context.openFileOutput(filename, Context.MODE_APPEND)
            fos.write(content.toByteArray())
        } catch (ex: IOException) {
            Log.e("saveText", "saveText: ${ex.message}")
        } finally {
            fos?.close()
        }
    }

    fun deleteText(filename: String) {
        var fos: FileOutputStream? = null
        try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE)
            fos.write("".toByteArray())
        } catch (ex: IOException) {
            Log.e("deleteText", "deleteText: ${ex.message}")
        } finally {
            fos?.close()
        }
    }

    // открытие файла
    fun getText(filename: String): String {
        var fin: FileInputStream? = null
        try {
            fin = context.openFileInput(filename)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            return String(bytes)
        } catch (ex: IOException) {
            Log.e("getText", "getText: ${ex.message}")
        } finally {
            fin?.close()
        }
        return ""
    }
}