package de.arztdata.app.data.internet

import android.content.Context
import android.widget.Toast
import de.arztdata.app.R
import de.arztdata.app.data.app.AppData
import de.arztdata.app.data.constants.VERSION_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

class DownloadVersion {

    suspend fun doInBackground(): String {
        return withContext(Dispatchers.IO) {
            try {
                URL(VERSION_URL).readText()
            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }
        }
    }

    companion object {
        private suspend fun getNumberFromInternet(): Int? {
            var content = ""
            try {
                val downloadTask = DownloadVersion()
                val deferredContent = CoroutineScope(Dispatchers.Main).async {
                    downloadTask.doInBackground()
                }
                content = deferredContent.await().trim()
                return content.toIntOrNull()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return content.toIntOrNull()
        }

        fun handleNewDataVersion(context: Context) {
            CoroutineScope(Dispatchers.Main).launch {
                val index = getNumberFromInternet()
                val appData = AppData(context)
                val savedIndex = appData.getInt(AppData.DATA_VERSION,-1)

                println("Saved $savedIndex, Current $index")
                if (savedIndex != index && index != null) {
                    Toast.makeText(context, R.string.updating_data, Toast.LENGTH_LONG).show()
                    appData.setInt(AppData.DATA_VERSION, index)

                    DownloadData.handleNewDataVersion(context)
                }else{
                    println("There is still no new data available")
                }
            }
        }
    }
}