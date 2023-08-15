package de.arztdata.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import de.arztdata.app.data.CategoryItem
import de.arztdata.app.data.Data
import de.arztdata.app.data.constants.JSON_FILE_NAME
import de.arztdata.app.data.internet.DownloadData
import de.arztdata.app.ui.CategoryAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("SetTextI18n")
class StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_start, container, false)
    }

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var data: Data
    private lateinit var categories: List<CategoryItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            loadCategoriesFromJson()

            categoryRecyclerView = view.findViewById<RecyclerView>(R.id.categoryRecyclerView)
            categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            val adapter = CategoryAdapter(data.category,activity as MainActivity)
            categoryRecyclerView.adapter = adapter
        }catch (e:Exception){
            CoroutineScope(Dispatchers.Main).launch {
                val downloadedContent = DownloadData.handleNewDataVersion(requireContext())
                if (downloadedContent.isNotEmpty()) {
                    loadCategoriesFromJson()

                    categoryRecyclerView = view.findViewById<RecyclerView>(R.id.categoryRecyclerView)
                    categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())

                    val adapter = CategoryAdapter(data.category,activity as MainActivity)
                    categoryRecyclerView.adapter = adapter
                } else {
                    Toast.makeText(requireContext(),R.string.you_need_to_be_online,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun loadCategoriesFromJson() {
        val json = loadJSONFromAsset()
        data = Gson().fromJson(json, Data::class.java)
    }

    private fun loadJSONFromAsset(): String {
        return try {
            val inputStream = requireContext().openFileInput(JSON_FILE_NAME)
            val buffer = ByteArray(size = inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}
