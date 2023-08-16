package de.arztdata.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import de.arztdata.app.R
import de.arztdata.app.data.app.AppData


class NotesFragment : Fragment() {

    private lateinit var notes: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notes = view.findViewById(R.id.notes_edittext)

        restoreTexts()



        view.setOnClickListener { saveProfileData() }

    }

    override fun onStop() {
        super.onStop()
        saveProfileData()
    }

    private fun saveProfileData() {
        saveDataIfNotEmpty(AppData.NOTE, notes, R.string.notes)
    }

    private fun saveDataIfNotEmpty(key: String, editText: EditText, hintResId: Int) {
        val appData = AppData(requireContext())
        appData.setAnyString(key, editText.editableText.toString())
    }

    private fun restoreTexts() {
        val appData = AppData(requireContext())

        if (appData.getAnyString(AppData.NOTE) != "")
            notes.setText(appData.getAnyString(AppData.NOTE))

    }


}