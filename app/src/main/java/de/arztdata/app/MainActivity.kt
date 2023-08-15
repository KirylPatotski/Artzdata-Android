package de.arztdata.app

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.arztdata.app.data.internet.DownloadVersion
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.navigationBarColor = getColor(R.color.white)
        supportActionBar?.hide()

        DownloadVersion.handleNewDataVersion(this)
        openFragment(StartFragment())

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { openFragment(NotesFragment()) }

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
            else openFragmentWithOutTransitions(StartFragment())
        }catch (e: java.lang.IllegalStateException){
            e.printStackTrace()
        }
    }

}
