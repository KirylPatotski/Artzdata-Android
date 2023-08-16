package de.arztdata.app.ui.authentification

import CodeGenerator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.contentValuesOf
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import de.arztdata.app.MainActivity
import de.arztdata.app.R
import de.arztdata.app.data.app.AppData
import de.arztdata.app.ui.fragment.StartFragment


class LoginFragment : Fragment() {

    private lateinit var textInputLayout: TextInputLayout
    private lateinit var editText: TextInputEditText

    private val handler = Handler(Looper.getMainLooper())
    private var typingTimer: Runnable? = null
    private val typingDelay: Long = 100

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleEdittext(view)
        handleSpinner(view)
    }

    private fun handleEdittext(view: View){
        textInputLayout = view.findViewById(R.id.textInputLayout)
        editText = view.findViewById(R.id.editText)

        editText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                typingTimer?.let { handler.removeCallbacks(it) }
            }

            override fun afterTextChanged(s: android.text.Editable?) {
                handler.postDelayed({
                    verifyText(s.toString())
                }, typingDelay)
            }
        })
    }

    private fun verifyText(inputText: String) {

        val correct =  CodeGenerator().verifyCodeIntegrity(inputText)
        AppData(requireContext()).setAnyBoolean(AppData.LOGGED_IN,correct)
        textInputLayout.error = if (correct) null else getString(R.string.invalid_text)

        if (correct) (activity as MainActivity).openFragment(StartFragment(),true)
    }

    private fun handleSpinner(view: View){
        val spinner = view.findViewById<Spinner>(R.id.help_spinner)
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,  resources.getStringArray(R.array.help_options))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                try {
                    when(position){
                        0 -> println("Default spinner position")
                        1 -> openWebsite()
                        2 -> openEmailSupport()
                        3 -> openPhone()
                    }
                }catch (e:Exception){

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }

    private fun openPhone() {
//        TODO
    }

    private fun openEmailSupport() {
//        TODO
    }

    private fun openWebsite() {
//        TODO
    }


}