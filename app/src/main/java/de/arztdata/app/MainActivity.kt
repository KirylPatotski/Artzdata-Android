package de.arztdata.app

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.arztdata.app.data.app.AppData
import de.arztdata.app.data.internet.DownloadVersion
import de.arztdata.app.ui.authentification.LoginFragment
import de.arztdata.app.ui.fragment.NotesFragment
import de.arztdata.app.ui.fragment.StartFragment
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        DownloadVersion.handleNewDataVersion(this)

        handleUIInit()
        handleLogin()
    }

    fun handleLogin(noAnimations: Boolean = false) {
        if (!AppData(this).getAnyBoolean(AppData.LOGGED_IN,false)) {
            if (noAnimations) openFragmentWithOutTransitions(LoginFragment(),true)
            else openFragment(LoginFragment(),true)
        } else {
            if (noAnimations) openFragmentWithOutTransitions(StartFragment(),true)
            else openFragment(StartFragment(),true)
        }
    }

    private fun handleUIInit(){
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.navigationBarColor = getColor(R.color.white)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar)

        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.red))
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { openFragment(NotesFragment()) }

        val spinner = findViewById<Spinner>(R.id.overflow)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item,  resources.getStringArray(R.array.options))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                try {
                    when(position){

                    }
                }catch (e:Exception){

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }
    fun openFragment(fragment: Fragment, doClearBackStack: Boolean = false, tag: String? = null) {
        if (doClearBackStack) clearBackStack()

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
            .replace(R.id.main_fragment_container, fragment, tag)
            .addToBackStack(null)
            .commit()
    }



    fun openFragmentWithOutTransitions(fragment: Fragment,doClearBackStack: Boolean = false,tag: String? = null): Boolean {
        if (doClearBackStack) clearBackStack()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment, tag)
            .addToBackStack(null)
            .commit()

        return true
    }


    fun openFragmentWithScrollTransition(fragment: Fragment,doClearBackStack: Boolean = false,tag: String? = null) {
        if (doClearBackStack) clearBackStack()

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.scroll_in, R.anim.scroll_out)
            .replace(R.id.main_fragment_container, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    private fun clearBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        try {
            val fragmentCount = supportFragmentManager.backStackEntryCount
            if (fragmentCount > 1) super.onBackPressed()
            else{
                handleLogin(true)
            }
        }catch (e: java.lang.IllegalStateException){
            e.printStackTrace()
        }
    }

}
