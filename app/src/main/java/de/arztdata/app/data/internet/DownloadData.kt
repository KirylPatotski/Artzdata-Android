package de.arztdata.app.data.internet

import android.content.Context
import de.arztdata.app.data.constants.JSON_FILE_NAME
import de.arztdata.app.data.constants.JSON_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

class DownloadData {

    companion object {
        private suspend fun getFileFromInternet(): Deferred<String> {
            return CoroutineScope(Dispatchers.IO).async {
                try {
                    URL(JSON_URL).readText()
                } catch (e: IOException) {
                    e.printStackTrace()
                    ""
                }
            }
        }

        suspend fun handleNewDataVersion(context: Context): String {
            val content = getFileFromInternet().await()

            if (content.isNotEmpty()) {
                try {
                    var file = File(context.filesDir, JSON_FILE_NAME)
                    if (file.exists()) file.delete()

                    file = File(context.filesDir, JSON_FILE_NAME)
                    val fos = FileOutputStream(file)
                    fos.write(content.toByteArray())
                    fos.close()
                    println(content)
                    println("File saved successfully.")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                println("Downloaded content is empty.")
            }

            return content
        }
    }
}
